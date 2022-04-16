package com.mir.servlets.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Context listener.
 *
 * @author R.M.
 * @see ServletContextListener
 * @since 2022
 */
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    public void contextDestroyed(ServletContextEvent event) {
        LOG.debug("Servlet context destruction starts");
        LOG.debug("Servlet context destruction finished");
    }

    public void contextInitialized(ServletContextEvent event) {
        LOG.debug("Servlet context initialization starts");

        ServletContext servletContext = event.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();

        LOG.debug("Servlet context initialization finished");
    }

    /**
     * Initializes log4j framework.
     *
     * @param servletContext
     */
    private void initLog4J(ServletContext servletContext) {
        LOG.debug("Log4J initialization started");
        try {
            PropertyConfigurator.configure(
                    servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            LOG.error("Cannot configure Log4j: {}", ex);
            throw new RuntimeException("Cannot configure Log4j", ex);
        }
        LOG.debug("Log4J initialization finished");
    }

    /**
     * Initializes CommandContainer.
     *
     * @param servletContext
     */
    private void initCommandContainer() {

        try {
            Class.forName("com.mir.servlets.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            LOG.error("Cannot initialize Command Container: {}", ex);
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }
}