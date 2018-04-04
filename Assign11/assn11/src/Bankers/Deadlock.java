package Bankers;
import java.util.*;

public class Deadlock {

	public static void main(String args[]) { 
		
		Deadlock d=new Deadlock();
		Scanner sc= new Scanner(System.in);
		int choice=0;
		char ch='n';
        
		int allocated;
	    int max;
	    int need;
	    int instance;
		int p=1;
	   
		// total number of resources
		int totRes=0;
		ArrayList<Process> processArray = new ArrayList<Process>();
		
		System.out.println("Enter number of resources");
        totRes=sc.nextInt();
        // matrix to store availability of each resource
        int available[]=new int[totRes]; 
        
        int k=0;
        
        // get the instance limits in available matrix for each resource
		while(k!=totRes){
			System.out.println("2. Instance limit");
			available[k]=sc.nextInt();
			System.out.print(available[k]);
			k++;
		}
		do {
			
			k=0;
			ArrayList<Resource> resource = new ArrayList<Resource>();
			
			// create row of resources for one process
			while(k!=totRes) {
				System.out.println("Enter process details for p"+ p +": 1. Allocation");
	            allocated=sc.nextInt();
				System.out.println("2. Max value for process");
				max=sc.nextInt();
	
				Resource r = new Resource();
				r.allocated=allocated;
				r.max=max;
				resource.add(r);
				k++;
			}
			
			// add process processes table (processArray)
			Process proc = new Process();
			proc.process=p;
			proc.r=resource;
			processArray.add(proc);
			p++;
			System.out.println("Add another process?");
			ch=sc.next().charAt(0);
			
			
		}while(ch=='y');
		
		System.out.print(processArray.toString());
		
		do {
			 
			System.out.println("Choose method:\n1.Simple Bankers\n2.Extension ");
            choice=sc.nextInt();
            switch(choice) {
            case 1:d.bankers(processArray,totRes,available);
            	   break; 
            case 2:d.extendedBankers(processArray,totRes,available);
            	   break;
            }
			System.out.println("Continue?y/n");
			ch=sc.next().charAt(0);
			
			
		}while(ch=='y');
		
		
	}
	
	public void bankers(ArrayList<Process> proc,int totRes,int[] available){
		
		int[] total=new int[totRes];
		int sum=0;
		
		// calculating total values allocated to all processes per resource
		for(Process p1 : proc) {
			
			int inc=0;
			for(Resource r1:p1.r){
				total[inc]=total[inc]+r1.allocated;
				inc++;
			}
			
			
		}

		// calculating total allocation
		for(int inc=0;inc<totRes;inc++) {
             available[inc]=available[inc]-total[inc];
             System.out.println(available[inc]);
		}
		
		for(Process p1 : proc) {
			
			int inc=0;
			for(Resource r1:p1.r){
				r1.need=r1.max-r1.allocated;
			
			}
			
			
		}
		
		System.out.println(proc.toString());
		
		
		
	}
	
	public void extendedBankers(ArrayList<Process> proc,int totRes,int[] available) {
		
	}
	
	
}
