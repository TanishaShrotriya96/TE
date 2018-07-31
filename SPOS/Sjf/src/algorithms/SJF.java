package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ProcessDetails.ProcessDetails;
import java.util.Queue;
import java.util.Scanner;

public class SJF {

	public void schedule(Queue<ProcessDetails> p) {
		
		// taking arrayList as we want to update the first element later on
		ArrayList<ProcessDetails> process=new ArrayList<ProcessDetails>();
		for(ProcessDetails p1 : p) {
			process.add(p1);
		}
		
		//System.out.println(process.toString());
		//sorting- comparator byArrival defined in ProcessDetails
		
		Collections.sort(process,ProcessDetails.byArrival);
		
		//System.out.println(process.toString());
		
		//rewriting the process ids as per sorted ordered.
		int pid=1;
		for(ProcessDetails p1:p) {
			p1.setPid(pid++);
		}
		
		// creating ready queue
		Queue<ProcessDetails> ready=new LinkedList<ProcessDetails>();
		
		// considering time quantum
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the time quantum");
		int quant=sc.nextInt();
		int t=0;
		
		// saving cpuBurst as we will be updating it during the scheduling
		ArrayList<Integer> Burst = new ArrayList<Integer>();
		for(ProcessDetails pi : p) {
			Burst.add(pi.getCpuBurst());
		}
		
		//to keep track of the completion times as we aren't updating the arrayList 
		//as it will require an extra for loop inside the already running loop
		//so to reduce complexity we will add the completion times after the scheduling is done
		HashMap<Integer,Integer> compTime=new HashMap<Integer,Integer>();
		ProcessDetails temp = new ProcessDetails();
		process.get(0).setSelected(1);
		temp=process.get(0);		
		ready.add(temp);
		
		
		while(ready.isEmpty()==false) {
		
			// peak will not remove the element from q, but return the front element's value
			int burst=ready.peek().getCpuBurst();
			
			if(burst>=quant) {
				burst=burst-quant;
				t=t+quant;
			}
			// for cases where the burst is less than quantum
			else {
				t=t+burst;
				burst=0;
			}
			
			ready.peek().setCpuBurst(burst);
			if(burst==0) {
				compTime.put(ready.peek().getPid(),t);
				ready.poll();
			}
			
			// after every time quantum check for new arrival in process queue
			for(ProcessDetails p1:p) {
				if(p1.getSelected()==0 && p1.getArrTime()<=t) {
					ready.add(p1);
					ArrayList<ProcessDetails> tempr=new ArrayList<ProcessDetails>(ready);
					Collections.sort(tempr,ProcessDetails.byCpuBurst);
					ready.clear();
					ready=new LinkedList<ProcessDetails>(tempr);
					p1.setSelected(1);
					break;
				}
			}
		}
		
		//now with just one for loop using HashMap we can easily set the completion times 
		for(ProcessDetails p1 : p) {
				p1.setCtime(compTime.get(p1.getPid()));
		}
		System.out.println(p.toString());
		
		//reset the burst values to original
		int index=0;
		for(ProcessDetails pi : p) {
			pi.setCpuBurst(Burst.get(index));
            index++;
		}
		
		System.out.println(p.toString());
		
		// calculating waiting and turnAround time 
		Time.turnAround(p);
		Time.waitTime(p);
		Time.avgTurnAround(p);
		Time.avgWaitTime(p);
	}

}
