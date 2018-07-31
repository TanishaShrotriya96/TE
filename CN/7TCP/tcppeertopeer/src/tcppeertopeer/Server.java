package tcppeertopeer;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server
{

	public static void main(String[] args) 
	{
		Scanner sci = new Scanner(System.in);
		String userstr ,str;
		int ch=1;
		try
		{
			ServerSocket sc = new ServerSocket(8234);
			Socket s= sc.accept();
		do
		{
			
			// RECEIVING MESSAGE FROM CLIENT......

			DataInputStream dis = new DataInputStream (s.getInputStream());
			str = (String)dis.readUTF(); 
			System.out.println("Message from client is:: \n"+ str);

			// SENDING MESSAGE TO CLIENT.... 

			DataOutputStream dout = new DataOutputStream (s.getOutputStream());
			System.out.println("Enter message for client...");
			userstr = sci.nextLine();
			if(userstr.equals("exit"))
			{
				break;
			}
			
			dout.writeUTF(userstr);
		}while(ch==1);

			// CLOSING CONNECTIONS.....
			
			System.out.println("EXITED......");
			s.close();
			sc.close();
		}
		catch (Exception e)
		{
			System.out.println("EXITED......");
		}

	}

}
