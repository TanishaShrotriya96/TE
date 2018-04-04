package algorithms;

import java.util.LinkedList;
import java.util.Queue;

import Graphics.Graphics;
import ProcessDetails.GnattChart;
import ProcessDetails.ProcessDetails;

public class FCFS implements Schedule{

	public void schedule(Queue<ProcessDetails> p){
		
		int complete=0;
		for(ProcessDetails pi : p) {
			
			if(pi.getArrTime()==0) {
				pi.setCtime(pi.getCpuBurst());
			}
			else {
				pi.setCtime(pi.getCpuBurst()+complete);
			}

			complete=pi.getCtime();
		 }
		

		Time.turnAround(p);
		Time.waitTime(p);
		Time.avgTurnAround(p);
		Time.avgWaitTime(p);
		
		Queue<GnattChart>  gnatChart = new LinkedList<GnattChart>();
		for(ProcessDetails pi : p) {
			GnattChart g = new GnattChart(pi.getCtime(),"P"+pi.getPid());
			gnatChart.add(g);
		}
		
		Graphics g = new Graphics();
		g.buildChart(gnatChart);
	}

}
