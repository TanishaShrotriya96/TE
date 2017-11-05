package udppeertopeer;


import java.net.*;
import java.io.*;
import java.util.*;

public class Server extends Thread
{
    private static String str="true";
	public static void main(String[] args) 
	{
		Scanner sci = new Scanner(System.in);
		String userstr ;
		int ch=1;	
		try
		{
			DatagramSocket ds;
			
			do
			{

				
				// SENDING MESSAGE TO CLIENT.... 
				if(!str.equals("false"))
				{
					str="false";
					new Thread(new Server()).start();
					break;
				}
				ds = new DatagramSocket();
				System.out.println("Enter message for client...");
				userstr = sci.nextLine();
				InetAddress in = InetAddress.getByName("localhost");
				DatagramPacket dp2 = new DatagramPacket (userstr.getBytes(),userstr.length(),in,8082);
				ds.send(dp2);
			
				ds.close();
			}while(ch==1);

			// CLOSING CONNECTIONS.....
			
			System.out.println("EXITED......");
		
			
		}
		catch (Exception e)
		{
			System.out.println("EXITED......");
		}

	}

	public void run() {
		// RECEIVING MESSAGE FROM CLIENT......

			DatagramSocket ds=null;
			try {
				ds = new DatagramSocket(8080);
			
			byte[] buf = new byte[1024];
			DatagramPacket dp1 = new DatagramPacket (buf,1024);
			ds.receive(dp1);
			str = new String(dp1.getData(),0,dp1.getLength());
	
			ds.close();
			System.out.println("Message from client is:: \n"+ str);
			str="true";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}

