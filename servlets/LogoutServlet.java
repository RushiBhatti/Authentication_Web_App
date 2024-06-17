package my_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
	
		PrintWriter out = resp.getWriter();
		
		// Database Credentials
		final String dbUserName = "root"; // username for database
		final String dbPassword = "root"; // password for database
		
		// Invalidating session
		HttpSession session = req.getSession();
		String userEmail = (String) session.getAttribute("email_key");
		session.invalidate();
		
		// Deleting user from database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register",dbUserName,dbPassword);
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE email = ?");
			ps.setString(1, userEmail);
			
			int count = ps.executeUpdate();
			
			if(count > 0) {
				resp.setContentType("text/html");
				out.print("<h3 style='color:green'>Logout Done!</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
				rd.include(req, resp);
			}
			else {
				resp.setContentType("text/html");
				out.print("<h3 style='color:red'>User does not exists!</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
				rd.include(req, resp);
			}
			
			
		}catch(Exception e) {
			resp.setContentType("text/html");
			out.print("<h3 style='color:red'>Error : { "+e.getMessage()+" } Occured!</h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
			rd.include(req, resp);
		}
		
	}

}
