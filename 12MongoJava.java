//ASSIGNMENT 12
//3476

import com.mongodb.*;
import java.util.*;


public class MongoJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			//insert
			MongoClient m= new MongoClient();
			DB db=m.getDB("b76");
			System.out.println("connected");
			DBCollection c=db.getCollection("connectivity");
			System.out.println("Inserting");
			BasicDBObject doc= new BasicDBObject("name", "pooja").append("add","pune").append("dept", "comp").append("salary", "60000");
			
		    c.insert(doc);
			
			System.out.println("OPENING CURSOR");
			DBCursor cur=c.find();
			while(cur.hasNext())
			{
				
				System.out.println(cur.next());
				
			}
			
			//display the computer dept people
			
			BasicDBObject search= new BasicDBObject("dept", "comp");
			cur=c.find(search);
			while(cur.hasNext())
			{
				
				System.out.println(cur.next());
				
			}
		
			//display people with salary>50000
			BasicDBObject search1= new BasicDBObject("salary", new BasicDBObject("$gt",50000));
			cur=c.find(search1);
			while(cur.hasNext())
			{
				
				System.out.println(cur.next());
				
			}
		
			//delete
			
			BasicDBObject del= new BasicDBObject();
			del.append("name", "pooja");
			c.remove(del);
			while(cur.hasNext())
			{
				
				System.out.println(cur.next());
				
			}
			
			Set<String> colllist=db.getCollectionNames();
			System.out.println("LIST OF COLLECTIONS");
			for(String s:colllist)
			{
				System.out.println(s);
			}
			
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
   }

}

/*output :

Sep 13, 2017 11:09:50 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Cluster created with settings {hosts=[127.0.0.1:27017], mode=SINGLE, requiredClusterType=UNKNOWN, serverSelectionTimeout='30000 ms', maxWaitQueueSize=500}
connected
Inserting
Sep 13, 2017 11:09:50 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: No server chosen by PrimaryServerSelector from cluster description ClusterDescription{type=UNKNOWN, connectionMode=SINGLE, all=[ServerDescription{address=127.0.0.1:27017, type=UNKNOWN, state=CONNECTING}]}. Waiting for 30000 ms before timing out
Sep 13, 2017 11:09:50 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Opened connection [connectionId{localValue:1, serverValue:6}] to 127.0.0.1:27017
Sep 13, 2017 11:09:50 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Monitor thread successfully connected to server with description ServerDescription{address=127.0.0.1:27017, type=STANDALONE, state=CONNECTED, ok=true, version=ServerVersion{versionList=[3, 0, 2]}, minWireVersion=0, maxWireVersion=3, electionId=null, maxDocumentSize=16777216, roundTripTimeNanos=531053}
Sep 13, 2017 11:09:50 AM com.mongodb.diagnostics.logging.JULLogger log
INFO: Opened connection [connectionId{localValue:2, serverValue:7}] to 127.0.0.1:27017
OPENING CURSOR
{ "_id" : { "$oid" : "59b8c4a6b35b7b0dc7800a9f"} , "name" : "pooja" , "add" : "pune" , "dept" : "comp" , "salary" : "60000"}
{ "_id" : { "$oid" : "59b8c4a6b35b7b0dc7800a9f"} , "name" : "pooja" , "add" : "pune" , "dept" : "comp" , "salary" : "60000"}
LIST OF COLLECTIONS
Student
Teacher
connectivity
system.indexes
*/

