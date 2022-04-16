package com.mir.servlets.web.filter;

import com.mir.servlets.dao.impl.JdbcUserDao;
import com.mir.servlets.Path;
import com.mir.servlets.dao.ConfigManager;
import com.mir.servlets.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LoginFilter filter.
 *
 * @author R.M.
 * @see Filter
 * @since 2022
 */
public class LoginFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(LoginFilter.class);
    ConfigManager configManager = new ConfigManager("/db.properties");
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        JdbcUserDao userDao = new JdbcUserDao(configManager);

        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession httpSession = req.getSession();
        HttpServletResponse resp = (HttpServletResponse)response;

        String login = request.getParameter("login");

        User user = userDao.findByLogin(login);
        LOG.trace("Found in DB: user --> " + user);



        if(allowParam(httpSession, "login")){
            chain.doFilter(req, resp);//sends request to next resource
        }
        else{
            RequestDispatcher rd = req.getRequestDispatcher(Path.PAGE_LOGIN);
            rd.include(req, resp);
        }
    }
    @Override
    public void destroy() {
    }

    private boolean allowParam(HttpSession httpSession, String param){
        Object result = httpSession.getAttribute(param);
        return result != null;
    }
}
