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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shreya Dhoke
 */
public class register extends HttpServlet {
        public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
        {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
          //  String i = req.getParameter("id");
            String u = req.getParameter("uname");
            String n = req.getParameter("name");
            String e = req.getParameter("email");
            String p1= req.getParameter("pass1");
            String p2 = req.getParameter("pass2");
            if(p1.equals(p2)){
                  try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/proj","root", "abc456");
                PreparedStatement pst = con.prepareCall("insert into user(uname,name,email,password) values(?,?,?,?)");
                pst.setString(1,u);
                pst.setString(2,n);
                pst.setString(3,e);
                pst.setString(4,p1);
                //pst.execute("use proj");
                int r = pst.executeUpdate();    
                if (r!=0)
                {
                out.println("Registered successfully");
                RequestDispatcher rd=req.getRequestDispatcher("login.html");  
                rd.forward(req,res);
                }
                else{
                 out.println("Did not register!Please try again!");
                 RequestDispatcher rd=req.getRequestDispatcher("register.html");  
                rd.forward(req,res);
                }
                 } catch (Exception ex) {
                         out.println(ex);
                  }
            }else
            {
                out.println("password doesnt match");
                 RequestDispatcher rd=req.getRequestDispatcher("register");  
                rd.forward(req,res);
            }
          
            
        
        }
    
}
