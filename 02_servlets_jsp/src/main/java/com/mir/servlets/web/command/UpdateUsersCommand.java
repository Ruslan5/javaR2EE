package com.mir.servlets.web.command;

import com.mir.servlets.dao.impl.JdbcRoleDao;
import com.mir.servlets.dao.impl.JdbcUserDao;
import com.mir.servlets.entity.Role;
import com.mir.servlets.entity.User;
import com.mir.servlets.Path;
import com.mir.servlets.dao.ConfigManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.regex.Pattern;

/**
 * UpdateUsersCommand command.
 * the command updates the information
 * in the database and returns a list of all users.
 *
 * @author R.Mirzoiev
 * @see Command
 * @see ListUsersCommand
 * @since 31.01.2022
 */
public class UpdateUsersCommand extends Command {
    private static final Logger LOG = Logger.getLogger(UpdateUsersCommand.class);
    ConfigManager configManager = new ConfigManager("/db.properties");

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Command starts");
        return updateUser(request);
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
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    /**
     * additional private method
     * for validation input valid birthday date

     * @param date entered birthday
     * @return tru or false
     */
    private boolean isDateValid(String date) {
        Date d = Date.valueOf(date);
        Date date1 = new Date(System.currentTimeMillis());
        return d.before(date1);
    }

    /**
     * additional private method
     * to get parameters from requests, log info,
     * and validate them
     *
     * @param request - request parameter
     * @return COMMAND_LIST_USERS
     */

    private String updateUser(HttpServletRequest request){
        LOG.debug("Command starts");
        JdbcUserDao userDao = new JdbcUserDao(configManager);
        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);

        User updateUser = userDao.findByLogin(login);
        LOG.trace("Found in DB: findByLogin(login) user --> " + updateUser);

        String password = request.getParameter("password");
        LOG.trace("Request parameter: password --> " + password);

        String email = request.getParameter("email");
        LOG.trace("Request parameter: email --> " + email);

        String firstName = request.getParameter("firstName");
        LOG.trace("Request parameter: firstName --> " + firstName);

        String lastName = request.getParameter("lastName");
        LOG.trace("Request parameter: lastName --> " + lastName);

        String birthday = request.getParameter("birthday");
        LOG.trace("Request parameter: birthday --> " + birthday);

        Long roleId = Long.valueOf(request.getParameter("role"));

        int count = 0;
        updateUser.setLogin(login);

        if (password != null && password.length() > 7) {
            updateUser.setPassword(password);
        } else {
            count++;
            request.setAttribute("password_error", "The password entered is incorrect. Please try again");
        }

        boolean result = isValid(email);
        if (result == true) {
            updateUser.setEmail(email);
        } else {
            count++;
            request.setAttribute("email_error", "The email entered is incorrect. Please try again");
        }

        if (firstName != null && firstName.length() > 2) {
            updateUser.setFirstName(firstName);
        } else {
            count++;
            request.setAttribute("firstName_error", "The firstName entered is incorrect. Please try again");
        }

        if (lastName != null && lastName.length() > 2) {
            updateUser.setLastName(lastName);
        } else {
            count++;
            request.setAttribute("lastName_error", "The lastName entered is incorrect. Please try again");
        }

        if (isDateValid(birthday)) {
            updateUser.setBirthday(Date.valueOf(birthday));
        } else {
            count++;
            request.setAttribute("date_error", "The date entered is incorrect. Please try again");
        }

        if (count > 0) {
            return Path.PAGE_EDIT_USERS;
        }

        JdbcRoleDao dao = new JdbcRoleDao(configManager);
        Role role = dao.findById(roleId);
        updateUser.setRole(role);

        userDao.update(updateUser);
        LOG.trace("Found in DB: updateUser user --> " + updateUser);
        request.setAttribute("update", updateUser);
        LOG.trace("set attribute 'update' --> " + updateUser);
        LOG.debug("Command finished");

        return Path.COMMAND_LIST_USERS;
    }
}