package Scheduler;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Random;
import ProcessDetails.ProcessDetails;
import algorithms.SJF;

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
             SJF s=new SJF();
	         s.schedule(p);
	         System.out.println("Schedule again?y/n");
             ch=sc.next().toCharArray()[0];
         }
         while(ch=='y');
           
	}

}

/*output:

Enter details of incoming processes
Add process
Arrival Time :
0
CPU Burst : 
10
Add another process?y/n
y
Add process
Arrival Time :
2
CPU Burst : 
1
Add another process?y/n
y
Add process
Arrival Time :
4
CPU Burst : 
2
Add another process?y/n
y
Add process
Arrival Time :
8
CPU Burst : 
4
Add another process?y/n
y
Add process
Arrival Time :
12
CPU Burst : 
3
Add another process?y/n
n
1 0 10 0 0 0 0
2 2 1 0 0 0 0
3 4 2 0 0 0 0
4 8 4 0 0 0 0
5 12 3 0 0 0 0
Enter the time quantum
1
[1 0 10 20 0 0 0, 2 2 1 3 0 0 0, 3 4 2 6 0 0 0, 4 8 4 12 0 0 0, 5 12 3 15 0 0 0]
1 0 10 20 20 10 0
2 2 1 3 1 0 0
3 4 2 6 2 0 0
4 8 4 12 4 0 0
5 12 3 15 3 0 0
Average Turn Around Time = 6.0
Average Wait Time = 2.0
Schedule again?y/n
n
*/
