package com.mir.servlets.web;

import com.mir.servlets.web.command.Command;
import com.mir.servlets.web.command.CommandContainer;
import com.mir.servlets.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller coming all commands.
 *
 * @author R.M.
 * @see HttpServlet
 * @since 2022
 */
public class Controller extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(Controller.class);
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		try {
		LOG.debug("Controller starts");

		// extract command name from the request
		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);

		// obtain command object by its name
		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);

		// execute command and get forward address
		String forward = Path.PAGE_ERROR_PAGE;
		try {
			LOG.debug("---1: " + forward);
			forward = command.execute(request, response);
			LOG.debug("---2: " + forward);
		} catch (Exception ex) {
			LOG.debug("process catch (Exception ex: " + ex);
			request.setAttribute("errorMessage", ex.getMessage());
		}
		LOG.trace("Forward address --> " + forward);

		LOG.debug("Controller finished, now go to forward address --> " + forward);
		
		// go to forward
		request.getRequestDispatcher(forward).forward(request, response);
		} catch (Exception ex) {
			LOG.debug(ex.getMessage(), ex);
		}
	}

}