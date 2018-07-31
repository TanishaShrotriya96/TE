import java.io.*;
import java.util.*;

public class Bankers {

    
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
		
		//Accept the allocated and max value for the resources 
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
		                	
		                	// avail=avail+allocation
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
		
	      int ch=0;
	      
	      
          do {
        	  System.out.println("Enter process number and request");
        	  int num=scan.nextInt();
        	  int req[][]=new int[pid][res];
        	  for(int i=0;i<pid;i++)
        	  {
        		  if(num==i) {
        	
           		  System.out.println("Enter allocated resources for process  "+num);
        		  for(int ji=0;ji<res;ji++)
        			{
        			    int numb=scan.nextInt();
        				req[num][ji]=numb;
        			}
        		  
        		  int flag=0;
        		  
        		  /// check for request less than need of process at num 
        		  for(int ji=0;j<res;j++)
      			  {
       			    if(req[num][ji]>=need[num][ji]) {
       			    	flag=1;
       			    	break;
      				 }
       			   
      			  }
        		  
        		  // if first condition of need is satisfied then check for req less than aval
        		  if(flag==0) {
        			  for(int ji=0;j<res;j++)
          			  {
           			    if(req[num][ji]>=aval[ji]) {
           			    	flag=2;
           			    	break;
          				 }
           			   
          			  }
        			  
        		  }
      			  
        		  // if either of the two aren't then resource must wait
        		  if(flag==2 || flag==1  ) {
        			  System.out.println("Requested resources can't be allocated");
        		  }
        		  
        		  else{
        			  //if all conditions are correct update the matrices
        			  for(int ji=0;ji<res;ji++)
          			  { 
          				alloc[num][ji]=alloc[num][ji]+req[num][ji];
          			 	aval[ji]=aval[ji]-req[num][ji];
          			 	need[num][ji]=need[num][ji]-req[num][ji];
    
          			  }
        			  
        			  //Printing Need Matrix
        			  System.out.print("New Need matrix is");
        				for(int k=0;k<pid;k++)
        				{
        					for(int ji=0;ji<res;ji++)
        					{
        						System.out.print(need[k][ji]+"\t");
        					}
        					System.out.println("\n");
        				}

          			  System.out.println("New Resource availability is");
    					for(int ji=0;ji<res;ji++)
    					{
    						System.out.print(aval[ji]+"\t");
    					}
    					System.out.println("\n");
    					
    				  System.out.println("New Allocation for process "+num);
      					for(int ji=0;ji<res;ji++)
      					{
      						System.out.print(alloc[num][ji]+"\t");
      					}
      					System.out.println("\n");
    				
        				
        		  }
        		   
        		  }
        			
        		}
        	  
        	  System.out.println("Request another?");
        	  ch=scan.nextInt();
        	  
          }while(ch!=1);

			
			
		}
	
	
	
	 private static boolean check(int i){
	       //checking if all resources for ith process can be allocated
	       for(int j=0;j<res;j++) 
	       if(aval[j]<need[i][j])
	          return false;
	   
	    return true;
	    }
		

}
