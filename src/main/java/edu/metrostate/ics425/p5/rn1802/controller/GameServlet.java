package edu.metrostate.ics425.p5.rn1802.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import edu.metrostate.ics425.p5.rn1802.model.Game;

public class GameServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public GameServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request  the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException      if an error occurred
	 */
	/**
	 * @param request  object
	 * @param response object implements the functionality for both get and post
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if (!(action == null || action.isBlank())) {
			if(action.equals("restart")){
				restart();
			}else if(action.startsWith("loc")){
				addPiece(action);
			}else {
				throw new ServletException("Unknown command");
			}
		}
		
		//check for valid spaces
		boolean over = !getGame().setValid();
		
		//if no valid space and skipped once, check again
		if(over && getGame().getSkips() == 1)
			over= !getGame().setValid();
		
		//send if game is over, the grid, and valid moves grid
		request.setAttribute("over", over);
		request.setAttribute("valid", getGame().getValid());
		request.setAttribute("grids", Arrays.asList(getGame().getGrid()));

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * Restarts the game
	 */
	private void restart() {
		getGame().restart();
	}

	/**
	 * Places the piece on the board given a location
	 */
	private void addPiece(String location){
		String[] locString = location.split(" ");
		getGame().placeMark(Integer.parseInt(locString[1]));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private Game getGame() {
		return (Game) getServletContext().getAttribute("game");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {

	}

}
