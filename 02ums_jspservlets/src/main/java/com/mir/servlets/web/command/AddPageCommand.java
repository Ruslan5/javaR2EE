package com.mir.servlets.web.command;

import com.mir.servlets.Path;
import com.mir.servlets.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login command.
 *
 * @see Command
 * @author R.Mirzoiev
 * @since 31.01.2022
 * 
 */
public class AddPageCommand extends Command {
	private static final Logger LOG = Logger.getLogger(AddPageCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.debug("Command starts");

		User user = new User();
		request.setAttribute("addPage", user);

		LOG.debug("Command finished");
		return Path.PAGE_ADD_USERS;
	}

}