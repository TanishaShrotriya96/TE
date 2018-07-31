package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ProcessDetails.ProcessDetails;
import java.util.Queue;
import java.util.Scanner;

public class SJF implements Schedule
{

	public void schedule(Queue<ProcessDetails> p) {
		// TODO Auto-generated method stub
		
		// we first need an arraylist of all processes
		ArrayList<ProcessDetails> process=new ArrayList<ProcessDetails>();
		for(ProcessDetails p1 : p) {
			process.add(p1);
		}
		
		// ready queue is prepared from the arraylist based on arrival time
		Queue<ProcessDetails> ready=new LinkedList<ProcessDetails>();
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the time quantum");
		int quant=sc.nextInt();
		int t=0;
		
		HashMap<Integer,Integer> compTime=new HashMap<Integer,Integer>();
		ProcessDetails temp = new ProcessDetails();
		process.get(0).setSelected(1);
		temp=process.get(0);		
		ready.add(temp);
		
		while(ready.isEmpty()==false) {
		
			int burst=ready.peek().getCpuBurst();
			if(burst>=quant) {
				burst=burst-quant;
				t=t+quant;
			}
			else {
				t=t+burst;
				burst=0;
			}
			
			ready.peek().setCpuBurst(burst);
			if(burst==0) {
				compTime.put(ready.peek().getPid(),t);
				ready.poll();
			}
			
			for(ProcessDetails p1:p) {
				if(p1.getSelected()==0 && p1.getArrTime()<=t) {
					if(ready.peek().getCpuBurst()<=p1.getCpuBurst()) {
						ready.add(p1);
					}
					else {
						ProcessDetails p2=ready.poll();
						ready.add(p1);
						ready.add(p2);
					}
					p1.setSelected(1);
					break;
				}
			}
		}
		
		for(ProcessDetails p1 : p) {
				p1.setCtime(compTime.get(p1.getPid()));
		}
		System.out.println(p.toString());
	}
	
	Time.turnAround(p);
	Time.waitTime(p);
	Time.avgTurnAround(p);
	Time.avgWaitTime(p);
	

}
