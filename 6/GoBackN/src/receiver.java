import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

/*
 *  the receiver class is using ServerSocket class, this may seem contrary to how packets actually are transferred
 *  mainly from server to client, but here my client(sender) sends and server reads, as we use client 
 *  side for user interaction in most of our programs. 
 *  
 *  Since the problem statement says peer-to-peer and focuses more on the go-back-n implementation, 
 *  this shouldn't be a problem(ideally).
 *  However, the sides can be switched based on personal preference.
 */


public class receiver {

	public static void main(String args[]) {
		
		ServerSocket s;
		Socket S;
		ObjectOutputStream o;
		ObjectInputStream i;
		DataOutputStream dout;
		DataInputStream din;

    //    int totalIterations=0;
		
		try {
			s = new ServerSocket(1234);
			S=s.accept();
		    o=new ObjectOutputStream(S.getOutputStream());
		    i=new ObjectInputStream(S.getInputStream());
		    din=new DataInputStream(S.getInputStream());
		    dout=new DataOutputStream(S.getOutputStream());
			
		    LinkedList<packet> data = new LinkedList<packet>();
		    // this list maintains the incoming packets 
		    
	        int ack=-1,frame=0,window=0,fail=1,f=0,w=0;

	        //frame = frame size or total packets sent by user 
	        //ack is compared to the frame size, as the program stops when the ack=frame size
	        //implies last packet has been successfully sent
	        //w = window size, which should always be less than frame size, that test case hasn't been added
	        //window is initially 0  so till window == w no acknowledgments are sent
	        // fail is the count of the packet being sent
	        // f = number of the packet that fails 
	        
	        frame=din.readInt();
	        w=din.readInt();
	        f=din.readInt();
	        
	        while(ack!=frame) {
	        	
	        	packet pack=new packet();
	        	pack.packet=din.readInt();
	        	pack.failStatus=fail;
	        	data.addLast(pack);
	        	fail++;
	        	window++;
        		System.out.println(data.toString());
	        	
        		if(window >= w) {
	        		
	        		System.out.println(data.element().packet);
	        		
	        		if(data.element().failStatus!=f){
	        			
	        			// for all packets which aren't the failStatus packet send a +ve acknowledgement
	        			//that is the packet itself.
		        		System.out.println(data.element().packet);
	        			dout.writeInt(data.element().packet);
	        			
	        			// now discard the packet from queue as we don't need it and we can read the 
	        			//next from front of q
	        			
	        			data.remove();
	        			//totalIterations++;
	        		}
	        		else if(data.element().failStatus==f) {
	        			
	        			dout.writeInt(-1);
	        			//-1 for negative ack
	        			//resetting fail value 
	        			fail=0;
	        			
	        			while(!data.isEmpty()) {
	        				
	        				data.remove();
	        				fail=fail+1;
	        				//all the already received packets need ot be considered for 
	        				//the actual fail value too.
	        				//totalIterations++;
	        			}
	        			window=0;
	        			
	        		}
	        		
	        	}
	        	else {
	        		dout.writeInt(-3);
	        		// this basically means no acknowledgments are sent for these packets
	        	}
	        	
	        }

	    	
	    	S.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		
		}
		
	//	System.out.println("Total iterations required are : "+totalIterations);
				
	}
}
