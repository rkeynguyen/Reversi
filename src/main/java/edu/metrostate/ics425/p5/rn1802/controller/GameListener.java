package edu.metrostate.ics425.p5.rn1802.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.metrostate.ics425.p5.rn1802.model.Game;


/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class GameListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public GameListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         sce.getServletContext().log("context destroyed");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        final ServletContext sc = sce.getServletContext();
		sc.log("context initialized");
		sc.setAttribute("game", new Game());
    }
	
}
