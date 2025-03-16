package Cookies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginDatabase
 */
@WebServlet("/LoginDatabase")
public class LoginDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Database credentials
	String URL = "jdbc:mysql://localhost:3306/userinfo";
	String dbUser = "root";
	String dbPassword = "Preetha*9";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String user = request.getParameter("uname");
		String password = request.getParameter("upass");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// Load the MySQL driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Establish connection
			conn = DriverManager.getConnection(URL, dbUser, dbPassword);
			
			// Prepare and execute SQL query
			stmt = conn.prepareStatement("SELECT * FROM uses WHERE username = ? AND password = ?");
			stmt.setString(1, user);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			
			// Check login
			if (rs.next()) {
				// Create a cookie
				Cookie cookie = new Cookie("cname", user);
				//cookie.setMaxAge(3600); // Optional: Set cookie lifespan (1 hour)
				response.addCookie(cookie);
				
				request.getRequestDispatcher("homepage.jsp").include(request, response);
				out.println("Logged in Successfully.....");
			} else {
				request.getRequestDispatcher("login.jsp").include(request, response);
				out.println("Username or Password is incorrect");
			}
		} /*catch (SQLException e) {
			out.println("SQL Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			out.println("Driver Error: " + e.getMessage());
		}*/catch (Exception e) {
				out.println("Error closing resources: " + e.getMessage());
			}
		
		out.close();
	}
}
