<%-- 
    Document   : reviewpage
    Created on : 27 Jul, 2023, 6:40:58 PM
    Author     : Shreya Dhoke
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Review Page</title>
    </head>
    <body>
        
        <%  int rid2 = Integer.parseInt((request.getQueryString())); 
        %>
        Recipe:<br>
        <% try{
             Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/proj","root", "abc456");
            PreparedStatement pst1 = con.prepareCall("select recipe,recipeid from recipeupload where recipeid = ?");
            session = request.getSession();
            session.setAttribute("recipeid",rid2);
                pst1.setInt(1,rid2);
                ResultSet rs = pst1.executeQuery();
              if (rs.next())
                {
                    out.println(rs.getString("recipe"));
                }
            }
            catch(Exception e){out.println(e);}
        %>
        <br><br>
        Rating: 
        <%
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3307/proj","root", "abc456");
                PreparedStatement pst1 = con1.prepareCall("select round(avg(rating),1) avgrate,recipeid from rating_and_review where recipeid = ?");
                pst1.setInt(1,rid2);
                ResultSet rs1 = pst1.executeQuery();
                while(rs1.next())
                {
                        out.println(rs1.getString("avgrate"));
                }
            }
            catch(Exception e){out.println(e);}
        %><br><br>
        Review:<br><br>
        <%
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3307/proj","root", "abc456");
                PreparedStatement pst2 = con2.prepareCall("select recipeid,review,uname,date from rating_and_review where recipeid = ?");
                pst2.setInt(1,rid2);
                ResultSet rs2 = pst2.executeQuery();
                while(rs2.next())
                {
                    out.println("Username: "+rs2.getString("uname"));
        %><br>
                    <%out.println("Review: " +rs2.getString("review"));%><br>
                    <%out.println("Date: "+rs2.getString("date"));%><br><br>
        <%
                 }
          }
          catch(Exception e){out.println(e);}
        %>
        <br><br>
        <form action="review" method="get">
        Username:  <input type="text" name="uname"><br><br>
        Review: <input type="textarea" name="review" rows="20" cols="30"><br>
        Rate: 
             <input type="radio" name="rating" value="1">
             <input type="radio" name="rating" value="2">
            <input type="radio" name="rating" value="3">
            <input type="radio" name="rating" value="4">
            <input type="radio" name="rating" value="5"><br><br>
        <input type="submit" value="Send Review">
        </label>
        </form>
     
    </body>
</html>