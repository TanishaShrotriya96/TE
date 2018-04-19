package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Fifo {

	public void replacePage(ArrayList<String> pageRef,int frameSize) {
		
		Queue<String> pageFrame = new LinkedList<String>();
		
		int pageFault=0,pageHit=0;
		for(int i=0;i<pageRef.size();i++) {
		
			if(i<frameSize) {
				 pageFrame.add(pageRef.get(i));
				 pageFault++;
		    }	

			else if(pageFrame.contains(pageRef.get(i))) {
				pageHit++;
			}
			else if(pageFrame.contains(pageRef.get(i))==false){
				 pageFrame.add(pageRef.get(i));
                 pageFrame.poll();
				 pageFault++;
			}
		
		}
		System.out.println("The number of pageHits are : "+pageHit +"\n The number of page faults are :"+pageFault);

	}
}
