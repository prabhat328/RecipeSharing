/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shreya Dhoke
 */
public class login extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException{
            res.setContentType("text/html");
              PrintWriter out = res.getWriter();
            String u = req.getParameter("uname");
            String p = req.getParameter("pass");
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/proj","root", "abc456");
                PreparedStatement pst = con.prepareCall("select uname from user where (uname =? or email = ?) and password =? ");
                pst.setString(1,u);
                pst.setString(2,u);
                pst.setString(3,p);
                
                //pst.execute("use proj");
                ResultSet rs = pst.executeQuery();
                
                if (rs.next())
                {
                    HttpSession session = req.getSession();
                    session.setAttribute("uname", rs.getString("uname"));
                    RequestDispatcher rd=req.getRequestDispatcher("main.jsp");  
                    rd.include(req,res);
                }
                else
                {   
                    out.println("login failed");
                    RequestDispatcher rd=req.getRequestDispatcher("login.html");  
                    rd.forward(req,res);
                }
            }catch(Exception e){
                out.println(e);
            }
    }
}
