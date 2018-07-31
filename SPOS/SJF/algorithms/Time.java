package algorithms;

import java.util.Queue;
import ProcessDetails.ProcessDetails;

// as there are no objects
abstract public class Time {
    
	// to allow direct access without creating objects
	public static void waitTime(Queue<ProcessDetails> p) {
		
		for(ProcessDetails pi : p) {
			pi.setWaitTime(pi.getTurnAround()-pi.getCpuBurst());
		    System.out.println(pi.toString());
		 }
	}
	
	public static void turnAround(Queue<ProcessDetails> p) {
		for(ProcessDetails pi : p) {
			pi.setTurnAround(pi.getCtime()-pi.getArrTime());
		 }
	}
	
    public static void avgWaitTime(Queue<ProcessDetails> p) {
		
    	float avgWt=0;
		for(ProcessDetails pi : p) {
			avgWt=avgWt+pi.getWaitTime();
		}
		avgWt=avgWt/p.size();
		System.out.println("Average Wait Time = "+ avgWt);
		
	}
    
    public static void avgTurnAround(Queue<ProcessDetails> p) {
		float avgTa=0;
		for(ProcessDetails pi : p) {
			avgTa=avgTa+pi.getTurnAround();
			
		 }
	
		avgTa=avgTa/p.size();
		System.out.println("Average Turn Around Time = "+ avgTa);
		
    }
}
