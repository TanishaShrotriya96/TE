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
			ArrayList<Integer> total = new ArrayList<Integer>();
			
			
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
					total.add(data.get(0));
				    resend =0;	
				}
				else {
					dout.writeInt(data.get(count));
					total.add(data.get(count));
					count++;
				}
				try{
					ack=din.readInt();
				
					if(ack==-1) {
						System.out.println("Acknowledgement failed and resending packet "+data.get(0));
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
			System.out.println(total.toString());
	        int TotalPacketsCnt=0;
			for(Integer e : total) {
				if(e!=-2) {
					TotalPacketsCnt=TotalPacketsCnt+1;
				}
			}
			System.out.println("Total packets sent after packet failures : "+TotalPacketsCnt);
			
			S.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
/*
Enter the frame size
10
Enter the window size
4
Enter the failure size
6
Enter the next packet
1
Waiting for acknowledgement
Enter the next packet
2
Waiting for acknowledgement
Enter the next packet
3
Waiting for acknowledgement
Enter the next packet
4
Acknowledgement received for 1
Enter the next packet
5
Acknowledgement received for 2
Enter the next packet
6
Acknowledgement received for 3
Enter the next packet
7
Acknowledgement received for 4
Enter the next packet
8
Acknowledgement received for 5
Enter the next packet
9
Acknowledgement failed and resending packet 6
Writing : 6
Enter the next packet
10
Acknowledgement received for 6
Acknowledgement received for 7
Acknowledgement received for 8
Acknowledgement received for 9
Acknowledgement received for 10
[1, 2, 3, 4, 5, 6, 7, 8, 9, 6, 10, -2, -2, -2]
Total packets sent after packet failures : 11

//-2 represents a time delay caused by one packet


RECEIVER SIDE :

Failed packet detected : Packet:6 |FailStatus:6 |ack:-1
Packet value set as -1 : Packet:-1 |FailStatus:-1 |ack:-1
Updated failed packet position : 
Packet:6 |FailStatus:4 |ack:-1

 */
}
