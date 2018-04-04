import java.io.*;
import java.util.*;

public class bankers {

    
	static int aval[];
	static int need[][];
	static int res;
	static int pid;

	 
	public static void main(String args[])
	{
		Scanner scan=new Scanner(System.in);
		
	
		System.out.println("Enter Number of processes ");
		pid=scan.nextInt();
		
		System.out.println("Enter Number of resources ");
		res=scan.nextInt();
		
		int max_inst[]=new int[res];
		int safe_seq[]=new int[pid];
		aval=new int[res];
		int total_allo[]=new int[res];
		
		for(int j=0;j<res;j++)
		{
			
			total_allo[j]=0;
		}
		
		
		int alloc[][]=new int[pid][res];
		int max[][]=new int[pid][res];
		need=new int[pid][res];
	
		//Accept max instances of resources 
		for(int i=0;i<res;i++)
		{
			
			System.out.println("Enter maximum instances for " +(i+1)+"resource");
			max_inst[i]=scan.nextInt();
		}
		
		//ACCEPT ALLOCATED AND MAX VALUE OF RES FOR EACH PROCESS
		for(int i=0;i<pid;i++)
		{
			System.out.println("Enter allocated resources for process  "+(i+1));
			for(int j=0;j<res;j++)
			{
				
				
				alloc[i][j]=scan.nextInt();
				total_allo[j]=total_allo[j]+alloc[i][j];
			}
			System.out.println("Enter Max requirment");
			for(int j=0;j<res;j++)
			{
				
				max[i][j]=scan.nextInt();
				
				
			}
			
		}
		
		for(int j=0;j<res;j++)
		{
			
			System.out.println("Total allocated resources for "+(j+1)+" is "+total_allo[j]);
		}
		
		//Calculate available resources 
		for(int j=0;j<res;j++)
		{
			
			aval[j]=max_inst[j]-total_allo[j];
			
		}
		
		System.out.println("Available resources");
		for(int j=0;j<res;j++)
		{
			
			System.out.println((j+1)+" => "+aval[j]);
			
		}
		
		
		System.out.println("\nTHE NEED MATRIX ");
		//NEED MATRIX
		for(int i=0;i<pid;i++)
		{
			
			for(int j=0;j<res;j++)
			{
	
				need[i][j]=max[i][j]-alloc[i][j];
		
			}
			
	
	   }
		
		//Printing Need Matrix
		for(int i=0;i<pid;i++)
		{
			for(int j=0;j<res;j++)
			{
				System.out.print(need[i][j]+"\t");
			}
			System.out.println("\n");
		}
		
		
	
		
		 boolean done[]=new boolean[pid];
	       int j=0;

	       while(j<pid)
	       {  //until all process allocated
	    	   boolean allocated=false;
	           for(int i=0;i<pid;i++)
	        	   if(!done[i] && check(i))
	        	   {  //trying to allocate
		                for(int k=0;k<res;k++)
		                	aval[k]=aval[k]-need[i][k]+max[i][k];
			            
		                System.out.println("Allocated process : P"+i);
			            allocated=done[i]=true;
		                j++;
	                }
	           
	              if(!allocated) break;
	          
	       }
	       if(j==pid)  //if all processes are allocated
	        System.out.println("\nSafely allocated");
	       else
	        System.out.println("All proceess cant be allocated safely");
		


			
			
		}
	
	
	
	 private static boolean check(int i){
	       //checking if all resources for ith process can be allocated
	       for(int j=0;j<res;j++) 
	       if(aval[j]<need[i][j])
	          return false;
	   
	    return true;
	    }
		

	}
	
/*
Output=>

Enter Number of processes 
5
Enter Number of resources 
4
Enter maximum instances for 1resource
3
Enter maximum instances for 2resource
17
Enter maximum instances for 3resource
16
Enter maximum instances for 4resource
12
Enter allocated resources for process  1
0
1
1
0
Enter Max requirment
0
2
1
0
Enter allocated resources for process  2
1
2
3
1
Enter Max requirment
1
6
5
2
Enter allocated resources for process  3
1
3
6
5
Enter Max requirment
2
3
6
6
Enter allocated resources for process  4
0
6
3
2
Enter Max requirment
0
6
5
2
Enter allocated resources for process  5
0
0
1
4
Enter Max requirment
0
6
5
6
Total allocated resources for 1 is 2
Total allocated resources for 2 is 12
Total allocated resources for 3 is 14
Total allocated resources for 4 is 12
Available resources
1 => 1
2 => 5
3 => 2
4 => 0

THE NEED MATRIX 
0	1	0	0	

0	4	2	1	

1	0	0	1	

0	0	2	0	

0	6	4	2	

Allocated process : P0
Allocated process : P3
Allocated process : P4
Allocated process : P1
Allocated process : P2

Safely allocated

*/