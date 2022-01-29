package com.hossein.signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public SignupServlet() {
        super();
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		request.getRequestDispatcher("links.html").include(request, response);
		
		Cookie[] cookies = request.getCookies();
		boolean loggedIn = false;
		String cookieName = "";
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("username")) {
					loggedIn = true;
					cookieName = c.getValue();
				}
			}
		}
		
		if (loggedIn) {
			
			out.print("You Are Already Logged In " + cookieName);
			
		} else {
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			int rows = DB.insertData(name, email, username, password);
			
			if (rows > 0) {
				out.print("You Have Successfully Signed Up");
				out.print("<br>Welcome " + username);
				
				Cookie cookie = new Cookie("username", username);
				response.addCookie(cookie);
			} else {
				out.print("There Was A Problem While Signing Up");
				out.print("<br>Try Again Later");
			}
			
		}
		
		out.close();
	}

}
