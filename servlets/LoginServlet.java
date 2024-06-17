package my_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		final String dbUserName = "root"; // username for database
		final String dbPassword = "root"; // password for database
		
		// get values from request object
		String userEmail = req.getParameter("email2");
		String userPassword = req.getParameter("pass2");
		
		if(userEmail.isEmpty() || userPassword.isEmpty()) {
			resp.setContentType("text/html");
			out.print("<h3 style='color:red'>Please fill all fields!</h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.include(req, resp);
			return ;
		}
		
		// database connection
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register",dbUserName,dbPassword);
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
			ps.setString(1, userEmail);
			ps.setString(2, userPassword);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) 
			{
				// login successful.
				RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
				rd.forward(req, resp);
				
			}
			else
			{
				// login failed.
				out.print("<h3 style='color:red'>Error : invalid username or password!</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.include(req, resp);
				
			}
			
		}catch(Exception e) {
			
			out.print("<h3 style='color:red'>Error : { " + e.getMessage() + " }</h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.include(req, resp);
		}
		
	}

}
