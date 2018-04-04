import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

class process
{
	int arrival,burst;
	int turn,comp=0;
	process(int a, int b)
	{
		arrival=a;
		burst=b;
	}
}
public class fcfs {
	static int avgTA=0,avgWT;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int totalp=0;
		int arrival,burst;
		int turn,comp=0,wait=0;
		int ec=0;
		
		
		System.out.print("----------------FCFS---------------"+
		"\n\nEnter no. of process to be executed: ");
		totalp = sc.nextInt();
		
		process[] processes=new process[totalp];
		ArrayList readyQ = new ArrayList(totalp);

		
		for(int i=0;i<totalp;i++)
		{
			System.out.println("\nEnter Arrival time and CPU burst for process "+(i+1));
			arrival=sc.nextInt();
			burst=sc.nextInt();
			processes[i] = new process(arrival,burst);
			
		}
		
		System.out.println("\nGiven Input Bu User\nProcess\tArrival-Time\tCPU Burst");
		for(int i=0;i<totalp;i++)
		{
			System.out.println("  "+(i+1)+"\t     "+processes[i].arrival + "\t\t    "+ processes[i].burst);
		}
		
		//apply fcfs
		for(int i=0;i<totalp;i++)
		{
			ec=ec + processes[i].burst;
			turn = ec - processes[i].arrival;			//turn around = exec tie - arrival
			wait= turn-processes[i].burst;				//waiting time
			readyQ.add("P"+(i+1) +"\t" + ec + "\t"+turn +"\t"+ wait);
			avgTA=avgTA+turn;
			avgWT=avgWT+wait;
		}
		
		System.out.println("\n\nExecution Queue: "
						   +"\n____________________________" );
		for(int i=0;i<totalp;i++)
		{
			int n= processes[i].burst;
			System.out.print("P"+(i+1));
			for(int j=0;j<n;j++)
			{
				System.out.print("|");
			}
			System.out.print(" ");
		}
		System.out.println("\n------------------------");
		System.out.println("\n\n\tET\tTA\tWT");
		for(int i=0;i<readyQ.size();i++)
		{
			System.out.println(readyQ.get(i) + "\n");
		}
		
		System.out.println("\n\nAverage Turn Around time: "+(avgTA/totalp)+
				"\nAverage Waiting Time: "+(avgWT/totalp));
	}
	
	

}
