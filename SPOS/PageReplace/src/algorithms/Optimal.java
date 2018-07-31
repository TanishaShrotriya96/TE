package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;

public class Optimal {

	public void replacePage(ArrayList<String> pageRef,int frameSize) {
     ArrayList<String> pageFrame = new ArrayList<String>();
		
		int pageFault=0,pageHit=0;
		for(int i=0;i<pageRef.size();i++) {
		
			System.out.println("The number of pageHits are : "+pageHit +"\n The number of page faults are :"+pageFault);
			System.out.println(pageFrame.toString());

			if(i<frameSize) {
				 pageFrame.add(pageRef.get(i));
				 pageFault++;
		    }	

			else if(pageFrame.contains(pageRef.get(i))) {
				pageHit++;
			}
			else if(pageFrame.contains(pageRef.get(i))==false){
				
			    int max=0;
			    String toReplace="";
			    int flag=0;
				for(int k=0;k<pageFrame.size();k++) {
					for(int j=i+1;j<pageRef.size();j++) {
						System.out.println(max+" "+pageFrame.get(k)+" "+pageRef.get(j)+" "+j+toReplace);

						if(pageRef.get(j)==pageFrame.get(k)) {
							if(max<j) {
								max=j;
								toReplace=pageFrame.get(k);
							}
						}
						else if(max<pageRef.size()) {
							max=pageRef.size();
							toReplace=pageFrame.get(k);
						}
						else {
							flag=1;
							break;
						}
					}
					if(flag==1) {
						
						break;
					}
				}
				 pageFrame.add(pageRef.get(i));
                 pageFrame.remove(toReplace);
				 pageFault++;
			}
		
		}
		System.out.println("The number of pageHits are : "+pageHit +"\n The number of page faults are :"+pageFault);

	}
}
