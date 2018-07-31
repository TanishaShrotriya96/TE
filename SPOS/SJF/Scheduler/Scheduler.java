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
		System.out.println("Enter details of incoming processes");
	    Scanner sc=new Scanner(System.in);
        int pid=0;
        
        do {
        	
    	 int arrTime=0;
    	 int cpuBurst=0;
    	 int choice=0;
    	 System.out.println("Add process");
    	 pid++;
 		 System.out.println("Arrival Time :");
 		 arrTime=sc.nextInt();
 		 System.out.println("CPU Burst : ");
 		 cpuBurst=sc.nextInt();

    	 ProcessDetails process = new ProcessDetails(pid,arrTime,cpuBurst,0,0,0,0);
     	 p.add(process);   
         System.out.println("Add another process?y/n");
         ch=sc.next().toCharArray()[0];

        }while(ch=='y');
        
        for(ProcessDetails proc : p){
            System.out.println(proc.toString());
        }
        
        // Choose algorithm
        do {
             s=new SJF();
	         s.schedule(p);
	         System.out.println("Schedule again?y/n");
             ch=sc.next().toCharArray()[0];
         }
         while(ch=='y');
           
	}

}
