import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class RR {

	static int CT,i;
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int totalp=0;
		int quantum;
				
		System.out.print("----------------Round Robin---------------"+
		"\n\nEnter no. of process to be executed: ");
		totalp = sc.nextInt();
		
		int burst[] = new int[totalp];
		
		for(int i=0;i<totalp;i++)
		{
			System.out.print("\nEnter CPU burst for process P"+(i+1)+ ": ");
			
			burst[i]=sc.nextInt();
			
			
		}
		System.out.println("\nEnter the quantum slice : ");
		quantum=sc.nextInt();
		
		//--------------------------------------------------------------------------------
		 CT=0;
		 i=0;
		 int pro=totalp;
		 System.out.print("0");
		while(pro!=0)
		{
			if(burst[i] > quantum)
			{
				burst[i]= burst[i]-quantum;
				CT= CT + quantum;
				System.out.print("| P"+ (i+1)+"(" + CT +") ");
			}
			else if(burst[i] <= quantum  &&  burst[i] >0)
			{
				CT= CT + burst[i];
				burst[i] = burst[i] - burst[i];
				System.out.print("| P"+ (i+1)+"(" + CT +") ");
				pro--;
			}
			
			i++;
			if(i==totalp)
			{
				i=0;
			}
		}
	
	}

}
