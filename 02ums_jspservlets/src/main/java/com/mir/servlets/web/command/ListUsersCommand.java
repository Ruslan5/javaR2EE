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
import java.util.List;

/**
 * ListUsersCommand command.
 *
 * @see Command
 * @author R.Mirzoiev
 * @since 31.01.2022
 *
 */
public class ListUsersCommand extends Command {

	private static final Logger LOG = Logger.getLogger(ListUsersCommand.class);
//	ConfigManager configManager = new ConfigManager("/db.properties");

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("Command starts");

		JdbcUserDao userDao = SingletonDao.getUserInstance();

		List<User> userList = userDao.findAll();
		LOG.debug("Found in DB: userList --> " + userList);

		request.setAttribute("userList", userList);
		LOG.debug("set attribute userList--> " + userList);
		LOG.debug("Command finished");
		return  Path.PAGE_LIST_USERS;
	}
}