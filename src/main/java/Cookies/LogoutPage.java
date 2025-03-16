package Cookies;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/LogoutPage")
public class LogoutPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		Cookie ck=new Cookie("cname","");
		ck.setMaxAge(0);
		response.addCookie(ck);
		
		request.getRequestDispatcher("homepage.jsp").include(request, response);
		out.println("Logout Successfully...");
	
		out.close();
	}

}