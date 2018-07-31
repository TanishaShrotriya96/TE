package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Lru {

	public void replacePage(ArrayList<String> pageRef,int frameSize) {
		
		Queue<String> pageFrame = new LinkedList<String>();
		Queue<String> leastQueue = new LinkedList<String>();
		HashMap<String,Integer> leastRecent= new HashMap<String,Integer>();
		
		int pageFault=0,pageHit=0;
		for(int i=0;i<pageRef.size();i++) {
			
			if(i<frameSize) {
				 pageFrame.add(pageRef.get(i));
				 leastRecent.put(pageRef.get(i),1);
				 leastQueue.add(pageRef.get(i));
				 pageFault++;
		    }	

			else if(pageFrame.contains(pageRef.get(i))) {
				
				pageHit++;
				// update the count of the current element
				int hits=leastRecent.get(pageRef.get(i));
				leastRecent.put(pageRef.get(i),hits+1);
				//update the recentQueue
				 leastQueue.add(pageRef.get(i));
				// reduce the count of the element at the top of the pageFrame
				int old=leastRecent.get(leastQueue.peek());
				leastRecent.put(leastQueue.peek(),old-1);
				leastQueue.poll();
				
			}
			else if(pageFrame.contains(pageRef.get(i))==false){
				
				 //pageFrame.add(pageRef.get(i));
				 pageFault++;
				 
                 int min=frameSize;
                 for(Integer v : leastRecent.values()) {
                	 if(v<min) {
                		 min=v;
                	 }
                 }
                 String mini=null;
                 for(String k: leastRecent.keySet()) {
                	 if(leastRecent.get(k)==min) {
                		 mini=k;
                		 break;
                	 }
                 }
                  
                 pageFrame.remove(mini);
                 pageFrame.add(pageRef.get(i));
			
			}
			
		}
		System.out.println("The number of pageHits are : "+pageHit +"\n The number of page faults are :"+pageFault);

	}
}
