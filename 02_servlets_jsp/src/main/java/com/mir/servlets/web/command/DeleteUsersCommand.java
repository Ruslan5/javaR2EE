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
 * DeleteUsersCommand command.
 *
 * @see Command
 * @author R.Mirzoiev
 * @since 31.01.2022
 *
 */
public class DeleteUsersCommand extends Command {
	private static final Logger LOG = Logger.getLogger(DeleteUsersCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("Command starts");

		JdbcUserDao userDao = SingletonDao.getUserInstance();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);

		User deleteUser = userDao.findByLogin(login);
		LOG.trace("Found in DB: findByLogin(login) user --> " + deleteUser);

		userDao.remove(deleteUser);
		LOG.trace("Found in DB: remove user --> " + deleteUser);

		request.setAttribute("deleteUser" + "=" + login, deleteUser);

		LOG.debug("Command finished");
		return Path.COMMAND_LIST_USERS;
	}

}