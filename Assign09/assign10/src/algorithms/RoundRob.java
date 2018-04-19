package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import Graphics.Graphics;
import ProcessDetails.GnattChart;
import ProcessDetails.ProcessDetails;

public class RoundRob implements Schedule {

	@Override
	public void schedule(Queue<ProcessDetails> p) {
		
		
		//sorting by arrival if you use random process allocation
		ArrayList<ProcessDetails> tempr=new ArrayList<ProcessDetails>(p);
		Collections.sort(tempr,ProcessDetails.byArrival);
		p.clear();
		p=new LinkedList<ProcessDetails>(tempr);
		int pid=1;
		for(ProcessDetails p1:p) {
			p1.setPid(pid++);
		}
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the time quantum");
		int quant=sc.nextInt();
		int t=0;
		Queue<GnattChart>  gnatChart = new LinkedList<GnattChart>();
		int zeroCount=0;
		
		// saving cpuBurst;
		ArrayList<Integer> Burst = new ArrayList<Integer>();
		for(ProcessDetails pi : p) {
			Burst.add(pi.getCpuBurst());
		}
		
		//algorithm 
		
		//till all processes are done with cpuBurst
		while(zeroCount!=p.size()) {
			
			for(ProcessDetails pi : p) {
				
				//-1 to avoid usage of the finished process again
				int currentBurst=pi.getCpuBurst();
				if(currentBurst>=quant) {
					String process="P"+pi.getPid();
					t=t+quant;
					
					GnattChart g=new GnattChart(t,process);
					gnatChart.add(g);
					
					pi.setCpuBurst(currentBurst-quant);

					System.out.print(g.toString());
				}
				//-1 to avoid going in infinite loop
				
				if(pi.getCpuBurst()<quant && pi.getCpuBurst()>=0) {
					zeroCount++;
					t=t+pi.getCpuBurst();
					pi.setCtime(t);
                    pi.setCpuBurst(-1);
				}
				
			}
		}

		int index=0;
		// putting back the original value for cpuBurst and setting arrival to zero
		for(ProcessDetails pi : p) {
			pi.setCpuBurst(Burst.get(index));
			pi.setArrTime(0);
            index++;
		}
		
		// calculating waiting and turnAround time 
		Time.turnAround(p);
		Time.waitTime(p);
		Time.avgTurnAround(p);
		Time.avgWaitTime(p);
		
		System.out.println("\n"+p.toString());
		//Graphics g = new Graphics();
		//g.buildChart(gnatChart);
		
	}

}
