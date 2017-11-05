package selectiveRepeat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Receiver {

	public static void main(String args[]) {
		
		ServerSocket s;
		Socket S;
		ObjectOutputStream o;
		ObjectInputStream i;
		DataOutputStream dout;
		DataInputStream din;

		try {
			s = new ServerSocket(12345);
			S=s.accept();
		    o=new ObjectOutputStream(S.getOutputStream());
		    i=new ObjectInputStream(S.getInputStream());
		    din=new DataInputStream(S.getInputStream());
		    dout=new DataOutputStream(S.getOutputStream());

		    ArrayList<Packet> info = new ArrayList<Packet>();
		    // this list maintains the incoming packets 
		    
	        int ack=-1,frame=0,window=0,fail=1,f=0,w=0;
	        frame=din.readInt();
	        w=din.readInt();
	        f=din.readInt();
	        int flag=0;
	        
	        while(ack!=frame) {
	        	System.out.println(" "+info.toString());
	        	Packet pack=new Packet();
	        	pack.packet=din.readInt();
	        	pack.failStatus=fail;
	        	
	         	if(fail==f) {
	         		fail=0;
	         	}

        		
	         	fail++;
	         	window++;
	        	flag=0;
	        	
        		for(Packet x :info) {
        		
        			if(x.failStatus==(-1)) {
        				
        				x.failStatus=pack.failStatus;
        				x.packet=pack.packet;
        				flag=1;
        				break;
        			}
        		}
        		if(flag!=1) {
        			info.add(pack);
        		}
        		
        		if(window >= w) {
	        		
	        		for(Packet x : info ) {
	        			
		        		if(x.failStatus!=f && x.ack==-1) {
		        			dout.writeInt(x.packet);
                            x.ack=x.packet;
                            ack=x.ack;
                            break;
		        		}
		        		else if(x.failStatus==f) {
		        			x.failStatus=-1;
		        		    x.packet=-1;
		        			dout.writeInt(-1);	
		        			break;
		        		}
	        		}
	        		
	        	}
	        	else {
	        		dout.writeInt(-3);
	        	}
	        	
	        }

	    	S.close();
		} catch (Exception e) {
		
		}
				
	}
}
