import java.net.*;
import java.util.*;
import java.io.*;

public class sender {

	public static void main(String args[]) {
		
		ObjectOutputStream o;
		ObjectInputStream i;
		DataOutputStream dout;
		DataInputStream din;

		try {
			Socket S = new Socket("localhost",1234);
			S.setSoTimeout(2000);
		    o=new ObjectOutputStream(S.getOutputStream());
		    i=new ObjectInputStream(S.getInputStream());
		    din=new DataInputStream(S.getInputStream());
		    dout=new DataOutputStream(S.getOutputStream());
			
			ArrayList<Integer> data = new ArrayList<Integer>();
		    //Used to store all the packets coming in from the user.
			ArrayList<Integer> d = new ArrayList<Integer>();
			
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
			
			int pack=-1,count=0;
			
			
			while(ack!=frame) {
				
				// take input from user only till frame size isn't exceeded
				if(pack<frame) {
					System.out.println("Enter the next packet");
 	                pack=sc.nextInt();		
				    data.add(pack);
				    
			        //add pack to arraylist data  
				}
				
				//once frame size is exceeded keep writing -2, to keep the program running until all 
				//ack are received and to not hinder read and write cycles of
				//the socket portion of the program.
				
				else {
				    //only to slow down the program when user interaction with system stops
					Thread.sleep(1000);
					data.add(-2);
				}
				
				dout.writeInt(data.get(count));
				//write the packet to receiver.
				d.add(data.get(count));
				count++;
				
				try{
					ack=din.readInt();
				//	System.out.println("Array   "+data.toString());
				//	System.out.println("Acknowledgement  "+ ack);
					if(ack==data.get(0)) {
						
						data.remove(0);
						count--;
						System.out.println("Acknowledgement received for "+ack);
				//		totalIterations++;
					}
					if(ack==-1) {
						
						System.out.println("Acknowledgement failed and resending packets "+data.toString());
						count=0;
					}
					if(ack==-3){
						System.out.println("Waiting for acknowledgement");
					}
				}
				catch(SocketTimeoutException e) {
					System.out.println("Waiting for acknowledgemt");
				}
				
				
			}
			System.out.println(d.toString());
	        int TotalPacketsCnt=0;
			for(Integer e : d) {
				if(e!=-2) {
					TotalPacketsCnt=TotalPacketsCnt+1;
				}
			}
			System.out.println("Total packets sent after packet failures : "+TotalPacketsCnt);
			S.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
/*
 * Enter the frame size
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
Acknowledgement failed and resending packets [6, 7, 8, 9]
Enter the next packet
10
Waiting for acknowledgement
Waiting for acknowledgement
Waiting for acknowledgement
Acknowledgement received for 6
Acknowledgement received for 7
Acknowledgement failed and resending packets [8, 9, 10, -2, -2, -2, -2, -2]
Waiting for acknowledgement
Waiting for acknowledgement
Waiting for acknowledgement
Acknowledgement received for 8
Acknowledgement received for 9
Acknowledgement received for 10
[1, 2, 3, 4, 5, 6, 7, 8, 9, 6, 7, 8, 9, 10, -2, 8, 9, 10, -2, -2, -2]
Total packets sent after packet failures : 17

// -2 value represents a time gap.
*/
