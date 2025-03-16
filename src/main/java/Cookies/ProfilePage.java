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
 * Servlet implementation class ProfilePage
 */
@WebServlet("/ProfilePage")
public class ProfilePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Cookie[] ck=request.getCookies();
		if(ck!=null) {
			String name=ck[0].getValue();
			if(!(name.equals(null))&& !
					(name.equals(" "))) {
				out.println("Welcome" + name);
			}
			else {
				request.getRequestDispatcher("login.jsp").include(request,response);
				out.println("please login first!!!");
			}
		}
		out.close();
	}

}
