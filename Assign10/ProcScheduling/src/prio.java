import java.util.Scanner;

public class prio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		Scanner scan=new Scanner(System.in);
			int p_id;
			System.out.println("HOW MANY PROCESSES ARE THERE?");
			p_id=scan.nextInt();
			
			int prio[]=new int[p_id];
			int proc[]= {1,2,3,4,5};
			
			int bur[]=new int[p_id];
			int starttime[]=new int[p_id];
	
		int temp;
		for(int i=0;i<p_id;i++)
		{
		System.out.println("Enter busrt time for " +i);

			bur[i]=scan.nextInt();
			
		}

		System.out.println("Enter the priority of ");
		for(int i=0;i<p_id;i++)
		{
			System.out.println(i+" \n");
		prio[i]=scan.nextInt();

		}
		/*for(int i=0;i<p_id;i++)
		{
			System.out.println(proc[i]+" - "+prio[i]+ " - "+ bur[i]);
		}*/
		//Bubble sort 
		int l;
			for(int i=0;i<p_id-1;i++)
			{
				for(int j=0;j<p_id-i-1;j++)
				{
					if(prio[j]>prio[j+1])
					{
						temp=prio[j];
						prio[j]=prio[j+1];
						prio[j+1]=temp;
					
						l=proc[j];
						proc[j]=proc[j+1];
						proc[j+1]=l;
				
						temp=bur[j];
						bur[j]=bur[j+1];
						bur[j+1]=temp;
					
					
					}
					
				}
				

			}
		
			
			//logic
			//System.out.print("0");
			for(int i=0;i<p_id;i++)
			{

				if(i==0)
				{
					starttime[0]=0;					
				}
				else 
				{
					starttime[i]=starttime[i-1]+bur[i-1];

				}
				
				System.out.print(" | "+starttime[i]+" | " +"P"+proc[i]);
				if(i==p_id-1)
				{
					starttime[i]=(starttime[i]+ bur[i]);
					System.out.println(starttime[i] + " | ");
				}
			}
		
			
			
			
	}

}
	

