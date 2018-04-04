package Bankers;
import java.util.*;

public class Process {
   
	int process;
    ArrayList<Resource> r = new ArrayList<Resource>();
    
    Process() {
    	process=0;
    }
    
    public String toString() {
    	return process + r.toString();
    }
}
