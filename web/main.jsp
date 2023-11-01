<%-- 
    Document   : main
    Created on : 26 Jul, 2023, 8:00:29 PM
    Author     : Shreya Dhoke
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .design{
                background-color: lightgrey;
                padding: 15px 20px;
                text-align: center;
                font-size: 18px;
                display: inline-block;
                margin: 10px 30px;
                cursor: pointer;
                width: 80px;
                height: 30px;
                border: 1px solid black;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>home</title>
    </head>
    <%! String uname = "user";%>
        <%  try{
                uname = session.getAttribute("uname").toString();
                log(uname);
            }catch(Exception e){log(e.getMessage());}
            %>
    <body>
           
            <% if(uname!="user"){ %>
            <form action="logout" method="get">
                <input type="submit" value="logout">
            </form>
            <% ;} %>
            
            

            
            <%  try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/proj","root", "abc456");
                Statement st= con.createStatement();
                ResultSet rs = st.executeQuery("select recipename,recipeid from recipeupload where cuisine='Italian'");
            // session = request.getSession();
              //  List<Integer> rid = new ArrayList<Integer>();
                while(rs.next())
                {     
            %><% out.println("<a class='design' href='rating.jsp?"+ rs.getInt("recipeid") +"'>");%>
<!--             <a class='design' href='reviewpage.jsp?rid1='>-->
              <%
                    out.println(rs.getString("recipename"));
                    //rid.add(rs.getInt("recipeid"));
                    //out.println(r);
                    session.setAttribute("recipeid",rs.getInt("recipeid"));
              %></a><br>
              <% }
               // for(int r: rid)
                //{   out.println(r);
                  // session.setAttribute("recipeid",rid);
                  //}   
              }
            catch(Exception e){out.println(e);}
            %>


           
    </body>
</html>
