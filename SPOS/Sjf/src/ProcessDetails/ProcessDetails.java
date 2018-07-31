package ProcessDetails;

import java.util.Comparator;

public class ProcessDetails {

	// Bean class
	
    int pid;
    int arrTime;
	int cpuBurst;
	int turnAround;
	int waitTime;
	int cTime;
	int priority;
	int selected;//used only with sjf
	
	public ProcessDetails() {
		pid=0;
		arrTime=0;
		cpuBurst=0;
		turnAround=0;
		waitTime=0;
		cTime=0;
		priority=0;
		selected=0;
	}
	public ProcessDetails(int p,int a,int c, int t,  int w,int com,int pr) {
		pid=p;
		arrTime=a;
		cpuBurst=c;
		turnAround=t;
		waitTime=w;
		cTime=com;
		priority=pr;
		selected=0;
	}
	
	public String toString() {
	    String x = pid + " " + arrTime+ " "+ cpuBurst + " "+cTime+" "+turnAround+" ";
	    x=x+waitTime+" "+priority;
	    return x;
	 
	}
	
	// getters
	
	public int getPid() {
		return pid;
	}
	public int getArrTime() {
		return arrTime;
	}
	public int getCpuBurst() {
		return cpuBurst;
	}
	public int getTurnAround() {
		return turnAround;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public int getCtime() {
		return cTime;
	}
	public int getPriority() {
		return priority;
	}
	public int getSelected() {
		return selected;
	}
	
	// setters 
	
	public void setPid(int p) {
		pid = p;
	}
	public void setArrTime(int a) {
		arrTime = a;
	}
	public void setCpuBurst(int c) {
		cpuBurst = c;
	}
	public void setTurnAround(int t) {
		turnAround = t;
	}
	public void setWaitTime(int w) {
		waitTime = w;
	}
	public void setCtime(int c) {
		cTime = c;
	}
	public void setPriority(int p) {
		priority=p;
	}
	public void setSelected(int s) {
		selected=s;
	}
	
	// using comparators for comparison
	
	public static Comparator<ProcessDetails> byArrival = new Comparator<ProcessDetails>() {
		
		public int compare(ProcessDetails p1,ProcessDetails p2) {
			
			String first=""+p1.getArrTime();
			String second=""+p2.getArrTime();
			return first.compareTo(second);
			
		}
	};
	
	public static Comparator<ProcessDetails> byPriority = new Comparator<ProcessDetails>() {
		
		public int compare(ProcessDetails p1, ProcessDetails p2) {
			String first=""+p1.getPriority();
			String second=""+p2.getPriority();
			return first.compareTo(second);
		}
	};
	
    public static Comparator<ProcessDetails> byCpuBurst = new Comparator<ProcessDetails>() {
		
		public int compare(ProcessDetails p1, ProcessDetails p2) {
			String first=""+p1.getCpuBurst();
			String second=""+p2.getCpuBurst();
			return first.compareTo(second);
		}
	};
}
