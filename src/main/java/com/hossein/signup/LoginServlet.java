package com.hossein.signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		request.getRequestDispatcher("links.html").include(request, response);
		
		// Check if a cookie already exists
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
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			boolean user = DB.logIn(username, password);
			
			if (user) {
				out.print("You Have Successfully Logged In");
				out.print("<br>Welcome " + username);
				
				Cookie cookie = new Cookie("username", username);
				response.addCookie(cookie);
			} else {
				out.print("Sorry, username or password is incorrect");
				request.getRequestDispatcher("login.html").include(request, response);
			}
			
		}
		
		out.close();
		
		
		
	}

}
