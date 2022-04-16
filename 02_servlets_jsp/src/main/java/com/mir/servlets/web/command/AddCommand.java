package com.mir.servlets.web.command;

import com.mir.servlets.dao.impl.JdbcRoleDao;
import com.mir.servlets.dao.impl.JdbcUserDao;
import com.mir.servlets.web.SingletonDao;
import com.mir.servlets.Path;
import com.mir.servlets.entity.Role;
import com.mir.servlets.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.regex.Pattern;

/**
 * AddCommand command.
 *
 * The command to add a user to the database.
 * Returns a list of all users upon successful
 * completion of the fields
 *
 * @see Command
 * @see ListUsersCommand
 * @author R.Mirzoiev
 * @since 31.01.2022
 * 
 */
public class AddCommand extends Command {
	private static final Logger LOG = Logger.getLogger(AddCommand.class);
	JdbcUserDao userDao = SingletonDao.getUserInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOG.debug("Command starts");
		User user = new User();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);

		String password = request.getParameter("password");
		LOG.trace("Request parameter: password --> " + password);

		String email = request.getParameter("email");
		LOG.trace("Request parameter: email --> " + email);

		String firstName = request.getParameter("firstName");
		LOG.trace("Request parameter: firstName --> " + firstName);

		String lastName = request.getParameter("lastName");
		LOG.trace("Request parameter: lastName --> " + lastName);

		String birthday = request.getParameter("birthday");
		System.out.println(birthday);

		LOG.trace("Request parameter: birthday --> " + birthday);

		Long roleId = Long.valueOf(request.getParameter("role"));

		int count = 0;

		if (login != null && login.length() > 2){
			user.setLogin(login);
		}else {
			count++;
			request.setAttribute("login_error", "The login entered is incorrect. Please try again");
		}

		if (password != null && password.length() > 3){
			user.setPassword(password);
		}else {
			count++;
			request.setAttribute("password_error", "The password entered is incorrect. Please try again");
		}

		if (isValid(email)){
			user.setEmail(email);
		}else {
			count++;
			request.setAttribute("email_error", "The email entered is incorrect. Please try again");
		}

		if (firstName != null && firstName.length() > 2){
			user.setFirstName(firstName);
		}else {
			count++;
			request.setAttribute("firstName_error", "The firstName entered is incorrect. Please try again");
		}

		if (lastName != null && lastName.length() > 2){
			user.setLastName(lastName);
		}else {
			count++;
			request.setAttribute("lastName_error", "The lastName entered is incorrect. Please try again");
		}

		if (isDateValid(birthday) && birthday != ""){
			user.setBirthday(Date.valueOf(birthday));
		} else {
			count++;
			request.setAttribute("birthday_error", "The date entered is incorrect. Please try again");
		}

		if (count > 0){
			return Path.PAGE_ADD_USERS;
		}

		JdbcRoleDao dao = SingletonDao.getRoleInstance();
		Role role = dao.findById(roleId);
		user.setRole(role);
		userDao.create(user);
		LOG.trace("Found in DB: user --> " + userDao.findAll());
		request.setAttribute("add", user);
		LOG.debug("Command finished");
		return Path.COMMAND_LIST_USERS;
	}

	/**
	 * additional private method
	 * for validation input valid email
	 *
	 * @param email - entered email from jsp field
	 * @return - tru or false
	 */
	private boolean isValid(String email) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		if (email == null){
			return false;
		}
		return pattern.matcher(email).matches();
	}

	/**
	 * additional private method
	 * for validation input valid birthday date
	 *
	 * @param date entered birthday
	 * @return tru or false
	 */
	private boolean isDateValid(String date) {
		Date d = Date.valueOf(date);
		Date date1 = new Date(System.currentTimeMillis());
		return d.before(date1);
	}
}