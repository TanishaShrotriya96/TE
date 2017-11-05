package selectiveRepeat;

import java.net.*;
import java.util.*;
import java.io.*;

public class Sender {

	public static void main(String args[]) {
		
		ObjectOutputStream o;
		ObjectInputStream i;
		DataOutputStream dout;
		DataInputStream din;

		try {
			Socket S = new Socket("localhost",12345);
			S.setSoTimeout(2000);
            o=new ObjectOutputStream(S.getOutputStream());
		    i=new ObjectInputStream(S.getInputStream());
		    din=new DataInputStream(S.getInputStream());
		    dout=new DataOutputStream(S.getOutputStream());
			
			ArrayList<Integer> data = new ArrayList<Integer>();
			
			Scanner sc=new Scanner(System.in);
            int ack=-2,frame=0,window=0,fail=0;
            
            System.out.println("Enter the frame size");
            frame=sc.nextInt();
            System.out.println("Enter the window size");
            window=sc.nextInt();
            System.out.println("Enter the failure size");
 			fail=sc.nextInt();

			dout.writeInt(frame);
			dout.writeInt(window);
			dout.writeInt(fail);
			
			int pack=-1,count=0,resend=0;
			
			
			while(ack!=frame) {
				
				if(pack<frame) {
					System.out.println("Enter the next packet");
 	                pack=sc.nextInt();		
				    data.add(pack);
			   	}
				
				else {
					//Thread.sleep(2000);
					data.add(-2);
				}
				
				if(resend==1) {
					dout.writeInt(data.get(0));
				    resend =0;	
				}
				else {
					dout.writeInt(data.get(count));
					count++;
				}
				try{
					ack=din.readInt();
				
					if(ack==-1) {
						System.out.println("Acknowledgement failed and resending packet "+data.toString());
						System.out.println("Writing : " + data.get(0));
						resend=1;
					}
					
					if(ack==-3){
						System.out.println("Waiting for acknowledgement");
					}
					
					if(ack==data.get(0)) {	
						data.remove(0);
						count--;
						System.out.println("Acknowledgement received for "+ack);
					}
				}
				catch(SocketTimeoutException e) {
					System.out.println("Waiting for acknowledgemt");
				}
				
				
			}
			
			S.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
/*
 * Enter the frame size
10
Enter the window size
3
Enter the failure size
5
Enter the next packet
1
Waiting for acknowledgement
Enter the next packet
2
Waiting for acknowledgement
Enter the next packet
3
Acknowledgement received for 1
Enter the next packet
4
Acknowledgement received for 2
Enter the next packet
5
Acknowledgement received for 3
Enter the next packet
6
Acknowledgement received for 4
Enter the next packet
7
Acknowledgement failed and resending packet [5, 6, 7]
Writing : 5
Enter the next packet
8
Acknowledgement received for 5
Enter the next packet
9
Acknowledgement received for 6
Enter the next packet
10
Acknowledgement received for 7
Acknowledgement received for 8
Acknowledgement failed and resending packet [9, 10, -2, -2]
Writing : 9
Acknowledgement received for 9
Acknowledgement received for 10


RECEIVER SIDE :

 []
 [Packet:1 |failStatus:1 |ack:-1]
 [Packet:1 |failStatus:1 |ack:-1, Packet:2 |failStatus:2 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:-1, Packet:3 |failStatus:3 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:2, Packet:3 |failStatus:3 |ack:-1, Packet:4 |failStatus:4 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:2, Packet:3 |failStatus:3 |ack:3, Packet:4 |failStatus:4 |ack:-1, Packet:5 |failStatus:5 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:2, Packet:3 |failStatus:3 |ack:3, Packet:4 |failStatus:4 |ack:4, Packet:5 |failStatus:5 |ack:-1, Packet:6 |failStatus:1 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:2, Packet:3 |failStatus:3 |ack:3, Packet:4 |failStatus:4 |ack:4, Packet:-1 |failStatus:-1 |ack:-1, Packet:6 |failStatus:1 |ack:-1, Packet:7 |failStatus:2 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:2, Packet:3 |failStatus:3 |ack:3, Packet:4 |failStatus:4 |ack:4, Packet:5 |failStatus:3 |ack:5, Packet:6 |failStatus:1 |ack:-1, Packet:7 |failStatus:2 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:2, Packet:3 |failStatus:3 |ack:3, Packet:4 |failStatus:4 |ack:4, Packet:5 |failStatus:3 |ack:5, Packet:6 |failStatus:1 |ack:6, Packet:7 |failStatus:2 |ack:-1, Packet:8 |failStatus:4 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:2, Packet:3 |failStatus:3 |ack:3, Packet:4 |failStatus:4 |ack:4, Packet:5 |failStatus:3 |ack:5, Packet:6 |failStatus:1 |ack:6, Packet:7 |failStatus:2 |ack:7, Packet:8 |failStatus:4 |ack:-1, Packet:9 |failStatus:5 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:2, Packet:3 |failStatus:3 |ack:3, Packet:4 |failStatus:4 |ack:4, Packet:5 |failStatus:3 |ack:5, Packet:6 |failStatus:1 |ack:6, Packet:7 |failStatus:2 |ack:7, Packet:8 |failStatus:4 |ack:8, Packet:9 |failStatus:5 |ack:-1, Packet:10 |failStatus:1 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:2, Packet:3 |failStatus:3 |ack:3, Packet:4 |failStatus:4 |ack:4, Packet:5 |failStatus:3 |ack:5, Packet:6 |failStatus:1 |ack:6, Packet:7 |failStatus:2 |ack:7, Packet:8 |failStatus:4 |ack:8, Packet:-1 |failStatus:-1 |ack:-1, Packet:10 |failStatus:1 |ack:-1, Packet:-2 |failStatus:2 |ack:-1]
 [Packet:1 |failStatus:1 |ack:1, Packet:2 |failStatus:2 |ack:2, Packet:3 |failStatus:3 |ack:3, Packet:4 |failStatus:4 |ack:4, Packet:5 |failStatus:3 |ack:5, Packet:6 |failStatus:1 |ack:6, Packet:7 |failStatus:2 |ack:7, Packet:8 |failStatus:4 |ack:8, Packet:9 |failStatus:3 |ack:9, Packet:10 |failStatus:1 |ack:-1, Packet:-2 |failStatus:2 |ack:-1]

 */
}
