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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{		
		PrintWriter out = resp.getWriter();
		
		// Database Credentials
		final String dbUserName = "root"; // username for database
		final String dbPassword = "root"; // password for database
		
		// getting values from request
		String userName = req.getParameter("name1");
		String userEmail = req.getParameter("email1");
		String userPassword = req.getParameter("pass1");
		String userCity = req.getParameter("city1");
		
		if(userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || userCity.isEmpty()) {
			resp.setContentType("text/html");
			out.print("<h3 style='color:red'>Please fill all fields!</h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.include(req, resp);
			return ;
		}
		
		// Inserting user into database
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register",dbUserName,dbPassword);
			
			PreparedStatement selectQuery = con.prepareStatement("SELECT * FROM users WHERE email = ?");
			PreparedStatement ps = con.prepareStatement("INSERT INTO users VALUES(?,?,?,?)");
			
			ps.setString(1, userName);
			ps.setString(2, userEmail);
			ps.setString(3, userPassword);
			ps.setString(4, userCity);
			
			selectQuery.setString(1, userEmail);
			ResultSet rs = selectQuery.executeQuery();
			
			if(!rs.next()) {
				int count = ps.executeUpdate();
				
				if(count > 0) 
				{
					// registration done successfully.
					HttpSession session = req.getSession();
					session.setAttribute("name_key", userName);
					session.setAttribute("email_key", userEmail);
					
					
					resp.setContentType("text/html");
					out.print("<h3 style='color:green'>Registration done successfully...</h3>");
					
					RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
					rd.include(req, resp);
					
				}
				else
				{
					// registration not done successfully!
					resp.setContentType("text/html");
					out.print("<h3 style='color:red'>Registration not done due to some error!</h3>");
					
					RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
					rd.include(req, resp);
					
				}
			}
			else
			{
				// user already exists
				resp.setContentType("text/html");
				out.print("<h3 style='color:red'>Registration Failed : User already exists!</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
				rd.include(req, resp);
			}
			
			
		}catch(Exception e) {
			resp.setContentType("text/html");
			out.print("<h3 style='color:red'>Error : { "+e.getMessage()+" } Occured!</h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.include(req, resp);
		}
		
	}

}
