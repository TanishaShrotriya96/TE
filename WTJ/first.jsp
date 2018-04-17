    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ page import="java.io.*,java.util.*,java.sql.*"%>
    <html>
    <head>
    <title>Database to JSP</title>
    </head>
    <body>
    <h2>Data from Table info of table "info" of DB</h2>
    <%
    try{  
	Class.forName("com.mysql.jdbc.Driver");  
	Connection con=DriverManager.getConnection(  
	"jdbc:mysql://localhost:3306/practicals","root","12345");  
 
	Statement stmt=con.createStatement();  
     %>	  
     <br>Established<br>
     <%	
     //    HttpServletRequest req=new HttpServletRequest();
        String  n=request.getParameter("Name");
        String  d=request.getParameter("Div");
        String  e=request.getParameter("email");
        String p=request.getParameter("Phone");
        String  y=request.getParameter("Type");

        stmt.executeUpdate("Insert into Student values('"+n+"','"+d+"','"+e+"',"+p+",'"+y+"')");
     %>
        Data successfully entered<br>
       <%
        ResultSet rs=stmt.executeQuery("select * from Student");
        %>
        <table border =1>			
        <tr><td>Name</td>
        <td>Division</td>
        <td>Email</td>
        <td>Phone Number</td>
        <td>Year</td></tr>
        <%
	while(rs.next()) {
		out.println("<tr><td>"  + rs.getString(1)+"</td>");
                out.println("<td>"  + rs.getString(2)+"</td>");
		out.println("<td>"  + rs.getString(3)+"</td>");
		out.println("<td>"  + rs.getString(4)+"</td>");
		out.println("<td>"  + rs.getString(5)+"</td></tr>");
        }%>
	</table>
    <form method = "get">	
    <table font color ="black" border = "2">
    <% while(rs.next()) {%>
    <tr>
       <th><%out.println(rs.getInt(1));%></th>
       <th><%out.println(rs.getString(2));%></th>
    </tr>
    <tr>
       <th>ME</th>
       <th>HERE</th>
    </tr>
    <%}%>
    <%rs.close();
      con.close();
      }
      catch(Exception e) {
      }
      %>
    </table>
    </form>
    </body>
    </html>
