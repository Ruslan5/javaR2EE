package com.mir.servlets.web.command;

import com.mir.servlets.dao.impl.JdbcUserDao;
import com.mir.servlets.web.SingletonDao;
import com.mir.servlets.Path;
import com.mir.servlets.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login command.
 *
 * This class accepts commands after authorization.
 * If the entered login and password are found
 * and match the entry in the database,
 * the user is determined by his role and
 * redirects to the appropriate command:
 * for {@Role} Admin forward to @ListUsersCommand
 * for {@Role} user forward to @userPage.jsp
 *
 * @see Command
 * @see ListUsersCommand
 * @author R.Mirzoiev
 * @since 31.01.2022
 * 
 */
public class LoginCommand extends Command {
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();
		JdbcUserDao userDao = SingletonDao.getUserInstance();

		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);

		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			LOG.error("Login/password cannot be empty");
			throw new RuntimeException("Login/password cannot be empty");
		}

		User user = userDao.findByLogin(login);
		LOG.trace("Found in DB: user --> " + user);

		if (user == null || !password.equals(user.getPassword())) {
			LOG.error("annot find user with such login/password");
			throw new RuntimeException("Cannot find user with such login/password");
		}
		String userRole = user.getRole().getName();
		LOG.trace("userRoleName --> " + userRole);

		String forward = Path.PAGE_ERROR_PAGE;

		if (userRole.equalsIgnoreCase("admin")) {
			LOG.trace("forward for user role--> " + userRole);
			forward = Path.COMMAND_LIST_USERS;
		}

		if (userRole.equalsIgnoreCase("user")) {
			LOG.trace("forward for user role--> " + userRole);
			forward = Path.PAGE_USER;
		}

		if (user == null || !password.equals(user.getPassword())) {
			LOG.error("Cannot find user with such login/password");
			throw new RuntimeException("Cannot find user with such login/password");
		}

		LOG.trace("userRole --> " + user.getRole().getName());

		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);

		session.setAttribute("login", user.getLogin());
		LOG.trace("Set the session attribute: login --> " + user.getLogin());

		session.setAttribute("password", user.getPassword());
		LOG.trace("Set the session attribute: password --> " + user.getPassword());

		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);

		LOG.debug("Command finished");
		return forward;
	}
}