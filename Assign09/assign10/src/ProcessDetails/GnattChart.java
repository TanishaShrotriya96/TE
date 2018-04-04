package ProcessDetails;

public class GnattChart {

	int time;
	String process;
	
	public GnattChart(int t,String p) {
		time=t;
		process=p;
	}
	
	public GnattChart() {
		time=0;
		process=null;
	}
	
	public String toString() {
		return "|"+process +"|"+time+"|";
	}
	
	public int getTime() {
		return time;
	}
	public String getProcess() {
		return process;
	}
	
	public void setTime(int t) {
	    time=t;
	}
	public void getProcess(String p) {
		process=p;
	}
}
