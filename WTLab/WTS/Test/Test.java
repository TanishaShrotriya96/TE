import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Test extends HttpServlet {

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		
		PrintWriter pw = res.getWriter();

                String  n=req.getParameter("Name");
                String  d=req.getParameter("Div");
                String  e=req.getParameter("email");
                String p=req.getParameter("Phone");
                String  y=req.getParameter("Type");

		pw.println("<html><body>");
		pw.println("WELCOME");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","ccoew");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practicals","root","12345");	
			Statement stmt = con.createStatement();
                        pw.println("<br>Established<br>");
                        
			stmt.executeUpdate("Insert into Student values('"+n+"','"+d+"','"+e+"',"+p+",'"+y+"')");
			
                        pw.println("Data successfully entered<br>");
                        ResultSet rs = stmt.executeQuery("Select * from Student;");

			pw.println("<table border =1>"); 			
			pw.println("<tr><td>Name</td>");
                        pw.println("<td>Division</td>");
                        pw.println("<td>Email</td>");
                        pw.println("<td>Phone Number</td>");
                        pw.println("<td>Year</td></tr>");
                         
			while(rs.next()) {
			
				pw.println("<tr><td>"  + rs.getString(1)+"</td>");
			        pw.println("<td>"  + rs.getString(2)+"</td>");
				pw.println("<td>"  + rs.getString(3)+"</td>");
				pw.println("<td>"  + rs.getString(4)+"</td>");
				pw.println("<td>"  + rs.getString(5)+"</td></tr>");
			}
			pw.println("</table>");

		}
		catch(Exception excep) {
                        pw.println(excep);
		}

		pw.close();
	}
}
