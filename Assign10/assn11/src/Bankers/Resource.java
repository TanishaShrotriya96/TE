package Bankers;

public class Resource {


	int allocated;
    int max;
    int need;
    int instance;
    int available;
    
    Resource() {
    	
  
    	allocated=0;
    	max=0;
    	need=0;
    	instance=0;
    	available=0;
    }
    
    public String toString() {
    	return allocated + " " + max;
    }
}
