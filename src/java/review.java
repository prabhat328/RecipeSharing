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
import java.sql.Date;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shreya Dhoke
 */
public class review extends HttpServlet {
   
    private static java.sql.Date getCurrentDate() {
    java.util.Date today = new java.util.Date();
    return new java.sql.Date(today.getTime());
 
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
        {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
        //    Date d = new Date();
            // java.util.Date date = new java.util.Date();
            String uname = req.getParameter("uname");
            String review = req.getParameter("review");
            int rating =Integer.parseInt(req.getParameter("rating"));
            //out.println(rating);
            HttpSession session = req.getSession();
            int rid =  (int) session.getAttribute("recipeid");
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/proj","root", "abc456");
                PreparedStatement pst = con.prepareCall("insert into rating_and_review(recipeid,uname,review,rating,date) values(?,?,?,?,?)");
                pst.setInt(1,rid);
                pst.setString(2,uname);
                pst.setString(3,review);
                pst.setInt(4,rating);
                pst.setDate(5,getCurrentDate());
                int r = pst.executeUpdate();    
                if (r!=0)
                { 
                    out.println("Review sent");
                }
                else
                {out.println("Review did not sent");
                }
            }catch(Exception e){
                out.println(e);
            }
        }
}
