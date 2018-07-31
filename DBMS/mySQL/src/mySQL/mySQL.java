package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class mySQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj","root","12345");
		
			System.out.println("Connected");
			Statement stmt=(Statement)con.createStatement();
			stmt.execute("Insert into x values(1,'haha');");
			
			ResultSet rs= stmt.executeQuery("Select * from x");
			while(rs.next()) {
				System.out.println(rs.getInt("roll")+rs.getString("name"));
			}
			
			stmt.execute("Delete from x;");
			
			PreparedStatement p = null;
			p=(PreparedStatement)con.prepareStatement("Insert into x values(?,?);");
			p.setInt(1,2);
			p.setString(2, "Tanisha");
			
			p.execute();
			System.out.println(p.executeUpdate());
			
			
	 }
	 catch (Exception e) {
		 System.out.println(e.getMessage());
	 }
	}

}
