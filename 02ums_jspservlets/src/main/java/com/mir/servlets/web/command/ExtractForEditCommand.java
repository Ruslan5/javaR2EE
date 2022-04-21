package com.mir.servlets.web.command;

import com.mir.servlets.dao.impl.JdbcUserDao;
import com.mir.servlets.web.SingletonDao;
import com.mir.servlets.Path;
import com.mir.servlets.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ExtractForEditCommand command.
 *
 * method retrieves custom values for editing
 * and forward them for updating to the {@UpdateUsersCommand}
 *
 * @see Command
 * @see UpdateUsersCommand
 * @author R.Mirzoiev
 * @since 31.01.2022
 *
 */
public class ExtractForEditCommand extends Command {
	private static final Logger LOG = Logger.getLogger(ExtractForEditCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("Command starts");

		JdbcUserDao userDao = SingletonDao.getUserInstance();

		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);

		User editUser = userDao.findByLogin(login);

		request.getParameter("password");
		LOG.trace("Request parameter: password --> " + editUser.getPassword());

		request.getParameter("email");
		LOG.trace("Request parameter: email --> " + editUser.getEmail());

		request.getParameter("firstName");
		LOG.trace("Request parameter: firstName --> " + editUser.getFirstName());

		request.getParameter("lastName");
		LOG.trace("Request parameter: lastName --> " + editUser.getLastName());

		request.getParameter(" ");
		LOG.trace("Request parameter: birthday --> " + editUser.getBirthday());

		request.getParameter("role");
		LOG.trace("Found in DB: user --> " + editUser.getRole());

		request.setAttribute("editUser", editUser);
		request.setAttribute("admin", editUser.getRole().getName());


		LOG.debug("Command finished");
		return Path.PAGE_EDIT_USERS;
	}
}