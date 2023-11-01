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
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shreya Dhoke
 */
public class recipe extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException
        {        
             res.setContentType("text/html");
            PrintWriter out = res.getWriter();
             String u = req.getParameter("uname");
                  try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/proj","root", "abc456");
//                PreparedStatement pst = con.prepareCall("select recipe from recipeupload where uname=Shreya ");
//                pst.setString(1,u);
//                st.execute("use proj"); 
                Statement st= con.createStatement();
               ResultSet rs = st.executeQuery("select recipe from recipeupload where uname='Shreya'");
                
                if (rs.next())
                {
                    out.println(rs.getString("recipe"));
                }
                 } catch (Exception ex) {
                         out.println(ex);
                  }
          
        }     
}
