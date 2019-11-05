package javaServlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javaDAO.ItemsDAO;
import javaDAO.UsersDAO;
import javaModels.Items;
import javaModels.Users;

@WebServlet({"/HomeServlet", "/HomeServlet/*"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		String action = null;
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		
		if (pathInfo == null || pathInfo.equals("/")) {
			action = servletPath;
		} else {
			action = servletPath + pathInfo;
		}

		System.out.println(action);
		
		try
		{
			switch(action) {
				case "/HomeServlet":
					showHomePage(request, response);
					break;
				case "/HomeServlet/signIn":
					showSignIn(request, response);
					break;
				case "/HomeServlet/verifySignIn":
					verifySignIn(request,response);
					break;
				case "/HomeServlet/register":
					showRegisterPage(request,response);
					break;
				case "/HomeServlet/verifyRegister":
					verifyRegister(request,response);
					break;
				case "/HomeServlet/showInventory":
					showInventoryPage(request,response);
					break;
				case "/HomeServlet/showRepairsPage":
					showRepairsPage(request,response);
					break;
				case "/HomeServlet/showLessonsPage":
					showLessonsPage(request,response);
					break;
				case "/HomeServlet/showAboutPage":
					showAboutPage(request,response);
					break;
				default:
					showHomePage(request, response);
					break;
			}
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void showRegisterPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/registerPage.jsp");
		
		request.setAttribute("error", "");
		
		rd.forward(request, response);
		
	}
	
	private void verifyRegister(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String providedUsername = request.getParameter("usernameRegister");
		
		String providedPassword = request.getParameter("passwordRegister");
		
		String confirmPassword = request.getParameter("passwordConfirmRegister");
		
		String providedEmail = request.getParameter("emailRegister");
		
		
		RequestDispatcher rd = null;
		
		UsersDAO user_dao = new UsersDAO();
		
		
		if(!(confirmPassword.equals(providedPassword))){
			
			rd = request.getRequestDispatcher("/WEB-INF/views/registerPage.jsp");
			
			request.setAttribute("error", "Passwords do not match");
			
			rd.forward(request, response);
					
		}
		else {
			
			try {
				
			
				if(Objects.isNull(user_dao.getUserByName(providedUsername))) {
					
					Users newUser = new Users(1,providedUsername,providedPassword,providedEmail,0);
					
					user_dao.registerUserExcludeId(newUser);
					
					request.setAttribute("error", "");
					
					rd = request.getRequestDispatcher("/WEB-INF/views/signInPage.jsp");
					
					rd.forward(request, response);
					
					
				}
				else {
					
					rd = request.getRequestDispatcher("/WEB-INF/views/registerPage.jsp");
					
					request.setAttribute("error", "Username already registered!");
					
					rd.forward(request, response);
					
				}
				
				
				
			} catch (Exception e) {
				
				System.out.println("e");
				
			}
			
		}
		
		
	}
	
	private void verifySignIn(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//Get the username and password provided by the user through the form
		String givenUsername = request.getParameter("usernameSignIn");
		String givenPassword = request.getParameter("passwordSignIn");
		
		RequestDispatcher rd = null;
	
		//Create a userDAO object to access the database
		UsersDAO user_dao = new UsersDAO();
		
		try {
			
			//Get the user information with the provided username
			Users givenUser = user_dao.getUserByName(givenUsername);
			
			//Make sure the returned user is not null. Otherwise redirect and display error message
			if(Objects.nonNull(givenUser)) {
				
				//If all info is correct then proceed to the inventory page
				if(givenUsername.equals(givenUser.getUserName())&&givenPassword.contentEquals(givenUser.getUserPassword())) {
					
					rd = request.getRequestDispatcher("/HomeServlet/showInventory");
					
					//Store the user information in the session
					HttpSession currentSession = request.getSession();
					
					currentSession.setAttribute("currentUser", givenUsername);
					
					rd.forward(request, response);
				}
				else {
					
					//If password does not match the username provided then provide error message
					rd = request.getRequestDispatcher("/WEB-INF/views/signInPage.jsp");
					
					request.setAttribute("error", "Invalid Login Information");
					
					rd.forward(request, response);
					
				}
				
			}
			else {
				
				//If the username provided does not exist then display error message
				rd = request.getRequestDispatcher("/WEB-INF/views/signInPage.jsp");
				
				request.setAttribute("error", "Invalid Login Information");
				
				rd.forward(request, response);
				
			}
			
		}catch(Exception e) {
			
			System.out.println(e);
			System.out.println("Unable to sign in");
			
		}
		
	}
	
	private void showInventoryPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		ItemsDAO items_dao = new ItemsDAO();
		
		try {
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/inventoryPage.jsp");
			
			ArrayList<Items> allInv = items_dao.getAllItems();
			
			request.setAttribute("allInventory", allInv);
			
			rd.forward(request, response);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Unable to retrieve all objects");
			
		}
		
	}
	
	private void showHomePage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		rd.forward(request, response);
	}
	
	private void showSignIn(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/signInPage.jsp");
		
		request.setAttribute("error", "");
		
		rd.forward(request, response);
	}
	
	private void showRepairsPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/repairs.jsp");
		
		rd.forward(request, response);
		
	}
	
	private void showLessonsPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/lessons.jsp");
		
		rd.forward(request, response);
		
	}
	
	private void showAboutPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/aboutStore.jsp");
		
		rd.forward(request, response);
		
	}
	
}
