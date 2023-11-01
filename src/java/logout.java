/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shreya Dhoke
 */
public class logout extends HttpServlet {

     public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
                res.setContentType("text/html");
                PrintWriter out = res.getWriter();
                HttpSession session = req.getSession();
               // String s1 = (String) session.getAttribute("uname");
                //session.invalidate(s1);
                req.getSession(false).invalidate();
                out.println("Thank you! You are successfully logged out.");
        
        }
}
