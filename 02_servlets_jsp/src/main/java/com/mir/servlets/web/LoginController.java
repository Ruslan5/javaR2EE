package com.mir.servlets.web;

import com.mir.servlets.Path;
import com.mir.servlets.web.command.Command;
import com.mir.servlets.web.command.LoginCommand;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * LoginController coming login command.
 *
 * @author R.Mirzoiev
 * @see HttpServlet
 * @since 31.01.2022
 */
public class LoginController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(LoginController.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd2=request.getRequestDispatcher(Path.PAGE_LOGIN);
		rd2.forward(request,response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("Start method doPost");

		Command command = new LoginCommand();
		LOG.trace("Obtained command --> " + command);

		String forward = command.execute(request, response);
		LOG.debug("LoginController finished, now go to forward address --> " + forward);
		request.getRequestDispatcher(forward).forward(request, response);
	}

}