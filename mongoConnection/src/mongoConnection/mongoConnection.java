package mongoConnection;
//add external jars to -
//Right click on project  > Properties > Java Build Path > Add External Jars

import java.util.Scanner;
import java.util.logging.Level;

import com.mongodb.*;

//27017- default port number for mongoDB

public class mongoConnection {

	public static void main(String[] args) {
		
		java.util.logging.Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
		// to stop logging from mongoDB
		
		MongoClient m =new MongoClient();
		DB db = m.getDB("tan");
		DBCollection c=db.getCollection("connect");
		
		int roll=0;
		String name=null;
		String addr=null;
		String ch=null;
		
		Scanner sc= new Scanner(System.in);
		do {	
			do {
			
				System.out.println("Enter the next entry in order of roll,name and addr ");
				roll=sc.nextInt();
				name=sc.next();
				addr=sc.next();
				
				BasicDBObject doc=new BasicDBObject("roll",roll).append("name",name).append("addr",addr);
				c.insert(doc);
				System.out.println("Entry successfully inserted");
				System.out.println("Insert another entry?");
				ch=sc.next();
				
			}while(ch.equals("y"));
			ch="n";
			//display
			DBCursor c1 = c.find();
			while(c1.hasNext()) {
				System.out.println(c1.next());
			}
			
			//search
			System.out.println("Enter the roll number to search for : ");
			roll=sc.nextInt();
		
			BasicDBObject doc1= new BasicDBObject("roll",roll);
			DBCursor c2=c.find(doc1);
			while(c2.hasNext()) {
				System.out.println(c2.next());
			}
			
			System.out.println("Enter the roll number : ");
			roll=sc.nextInt();
			System.out.println("Roll numbers greater than this will be printed : ");
			BasicDBObject doc2= new BasicDBObject("roll",new BasicDBObject("$gt",roll));
			DBCursor c3=c.find(doc2);
			while(c3.hasNext()) {
				System.out.println(c3.next());
			}
			System.out.println("Do you wish to continue?");
			ch=sc.next();
		}while(ch.equals("y"));
	}

}
