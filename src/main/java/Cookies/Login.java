package Cookies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("uname");
		String pass=request.getParameter("upass");
		if(name.equals("uniq")&& pass.equals("123")) {
			Cookie ck=new Cookie("cname",name);
			response.addCookie(ck);
			request.getRequestDispatcher("homepage.jsp").include(request,response);
			out.println("Login successful");
		}
		else {
			request.getRequestDispatcher("login.jsp").include(request,response);
			out.println("Username or password is incorrect");
			
		}
		out.close();
	}

}
