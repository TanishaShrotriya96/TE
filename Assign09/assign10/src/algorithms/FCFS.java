package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import Graphics.Graphics;
import ProcessDetails.GnattChart;
import ProcessDetails.ProcessDetails;

public class FCFS implements Schedule{

	public void schedule(Queue<ProcessDetails> p){
		
		//sorting by arrival if you use random process allocation
		ArrayList<ProcessDetails> tempr=new ArrayList<ProcessDetails>(p);
		Collections.sort(tempr,ProcessDetails.byArrival);
		p.clear();
		p=new LinkedList<ProcessDetails>(tempr);
		int pid=1;
		for(ProcessDetails p1:p) {
			p1.setPid(pid++);
		}
		
		for(ProcessDetails p1:p) {
			p1.setArrTime(0);
		}
		
		int complete=0;
		for(ProcessDetails pi : p) {
			
			if(complete==0) {
				pi.setCtime(pi.getCpuBurst());
			}
			else {
				while(pi.getArrTime()-complete<0) {
					complete++;
				}
				pi.setCtime(pi.getCpuBurst()+complete);
				
			}
			complete=pi.getCtime();
		 }
		

		Time.turnAround(p);
		Time.waitTime(p);
		Time.avgTurnAround(p);
		Time.avgWaitTime(p);
		
		Queue<GnattChart>  gnatChart = new LinkedList<GnattChart>();
	/*	for(ProcessDetails pi : p) {
			GnattChart g = new GnattChart(pi.getCtime(),"P"+pi.getPid());
			gnatChart.add(g);
		}*/
		
		//Graphics g = new Graphics();
		//g.buildChart(gnatChart);
	}

}
