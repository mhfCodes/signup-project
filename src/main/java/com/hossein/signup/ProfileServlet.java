package com.hossein.signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public ProfileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			
			out.print("<b>Welcome To Your Profile " + cookieName + "</b>");
			
		} else {
			
			out.print("You Should Login First");
			request.getRequestDispatcher("login.html").include(request, response);
			
		}
		
	}



}
