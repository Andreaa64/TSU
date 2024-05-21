package main.java.it.unisa.control;
import java.io.IOException; 

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import main.java.it.unisa.model.User;
import main.java.it.unisa.model.UserModel;


@WebServlet("/user")
public class UserControl extends HttpServlet{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String action = request.getParameter("action");
		UserModel um = new UserModel();

		
		try {
		
			if(action.equalsIgnoreCase("login")) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String redirectedPage;
				
				try {
					String controlL = um.checkLogin(username, password);
					
					if(controlL.equalsIgnoreCase("noError")) {
					request.getSession().setAttribute("adminFilterRoles", true);
					redirectedPage = "/index.jsp";
					request.getSession().setAttribute("username", username);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirectedPage);
					dispatcher.forward(request, response);
					}else {
						System.out.println("Errore");
						request.setAttribute("control", controlL);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-form.jsp");
						dispatcher.forward(request, response);
					}
				} catch (Exception e) {
					System.out.println("Excoso + " + e);
					request.getSession().removeAttribute("adminFilterRoles");
					redirectedPage = "/login-form.jsp";
					response.sendRedirect(request.getContextPath() + redirectedPage);
				}
		
		}else if(action.equalsIgnoreCase("register")){
				String Email = request.getParameter("email");
				String Nome = request.getParameter("nome");
				String Cognome = request.getParameter("cognome");
				String Username = request.getParameter("username");
				String DataNascita= request.getParameter("nascita");
				String psw = request.getParameter("password");
				
								
				if(um.doArleadyRegister(Email)) {
					System.out.println("Account esiste già");
					request.setAttribute("control", "Falso");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-form.jsp");
					dispatcher.forward(request, response);
				}
				
				User u = new User();
				u.setEmail(Email);
				u.setNome(Nome);
				u.setCognome(Cognome);
				u.setUsername(Username);
				u.setDataNascita(DataNascita);
				u.setPsw(psw);
				um.doRegister(u);
				System.out.println("Registrazione confermata.");
				request.setAttribute("control", "Vero");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login-form.jsp");
				dispatcher.forward(request, response);

				
			
				}
			
		}catch (SQLException e){
			System.out.println("Error:" + e.getMessage());
		}
	}
	
}
