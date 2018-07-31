package Scheduler;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Random;
import ProcessDetails.ProcessDetails;
import algorithms.FCFS;
import algorithms.Priority;
import algorithms.RoundRob;
import algorithms.SJF;
import algorithms.Schedule;

public class Scheduler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char ch='n';
		//LinkedList<ProcessDetails> p=new LinkedList<ProcessDetails>();
		Queue<ProcessDetails> p=new LinkedList<ProcessDetails>();
		
		// Create Process Queue
		
		System.out.println("Enter details of incoming processes");
	    Scanner sc=new Scanner(System.in);
        int pid=0;
        
        do {
        	
    	 int arrTime=0;
    	 int cpuBurst=0;
    	 int choice=0;
    	 System.out.println("1.Add process manually\n2.Generate Random Process");
    	 choice=sc.nextInt();
    		
    	 switch(choice){
			 
    	     case 1:pid++;
			 		System.out.println("Arrival Time :");
			 		arrTime=sc.nextInt();
			 		System.out.println("CPU Burst : ");
			 		cpuBurst=sc.nextInt();
			        break;
			        
			 case 2:pid++;
			        if(pid==1) {
				        Random rand = new Random();
			            arrTime  = 0;
			            cpuBurst = rand.nextInt(25);
			        }
			        else {
				        Random rand = new Random();
			            arrTime  = rand.nextInt(15);
			            cpuBurst = rand.nextInt(10)+1;
			        }
			        break;
		            
		 
    	 }
    	 ProcessDetails process = new ProcessDetails(pid,arrTime,cpuBurst,0,0,0,0);
     	 p.add(process);   
         System.out.println("Add another process?y/n");
         ch=sc.next().toCharArray()[0];

        }
        while(ch=='y');
        
        for(ProcessDetails proc : p){
            System.out.println(proc.toString());
        }
        
        // Choose algorithm
        
        do {
      

          System.out.println("Choose scheduling algorithm : \n1.FCFS\n2.RoundRobin(Preemptive)"
          		+ "\n3.Priority(Non-Preemptive)\n4.SJF(Preemptive)");
          int choice =0;
          choice=sc.nextInt();
          Schedule s;
          
          switch(choice){
          
	          case 1: s=new FCFS(); 
	          		  s.schedule(p);
	                  break;
	          case 2: s=new RoundRob();
	                  s.schedule(p);
	                  break;
	          case 3: s=new Priority(); 
	                  s.schedule(p);
	                  break;
	          case 4: s=new SJF();
	                  s.schedule(p);
	                  break;
	    
          }
          
          System.out.println("Schedule again?y/n");
          ch=sc.next().toCharArray()[0];

         }
         while(ch=='y');
           
        
	}
	
	

}
