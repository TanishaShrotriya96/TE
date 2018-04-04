

import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
    
	public static void main(String[] args)
	{
		int ch;
		Scanner scan=new Scanner(System.in);
		do
		{
			System.out.println("1.FCFS\n2.RR\n3.Priority\n4.SJF");
			int choice=scan.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter number of processes");
				int no=scan.nextInt();
				ArrayList<ArrayList<Integer>> input=new ArrayList<ArrayList<Integer>>();
				for(int i=0;i<no;i++)									//input
				{
					ArrayList<Integer> al1=new ArrayList<Integer>();
					al1.add(i+1);
					System.out.println("Enter Arrival Time");
					al1.add(scan.nextInt());
					System.out.println("Enter CPU Burst");
					al1.add(scan.nextInt());
					
					input.add(al1);
				}
				System.out.println("-----------FCFC------------");
				System.out.println("Input is: ");
				System.out.println("Process \tArrival Time \tCPU Burst");		//display
				for(int i=0;i<input.size();i++)
				{
					for(int j=0;j<input.get(i).size();j++)
					{
						System.out.print(input.get(i).get(j)+"\t\t");
					}
					System.out.println();
				}	
				for(int i=0;i<input.size();i++)									//sorted array wrt Arrival Time
				{
					for(int j=0;j<input.size()-1;j++)
					{
						if(input.get(j).get(1).intValue()>input.get(j+1).get(1).intValue())
						{
							ArrayList<Integer> temp=input.get(j);
							input.set(j, input.get(j+1));
							input.set(j+1, temp);
						}
					}
				}
//				System.out.println("Sorted array is: ");						//display
//				for(int i=0;i<input.size();i++)
//				{
//					for(int j=0;j<input.get(i).size();j++)
//					{
//						System.out.print(input.get(i).get(j)+"\t\t");
//					}
//					System.out.println();
//				}		
				int[] servicetime=new int[no];
				int[] waitingtime=new int[no];
				int[] turnaroundtime=new int[no];
				int[] completiontime=new int[no];
				servicetime[0]=0;
				waitingtime[0]=0;
				turnaroundtime[0]=input.get(0).get(2);
				completiontime[0]=input.get(0).get(1)+turnaroundtime[0];
//				System.out.println(completiontime[0]+"\t"+servicetime[0]+"\t"+waitingtime[0]+"\t"+turnaroundtime[0]);
				for(int i=1;i<input.size();i++)
				{
					servicetime[i]=servicetime[i-1]+input.get(i-1).get(2).intValue();
					waitingtime[i]=servicetime[i]-input.get(i).get(1).intValue();
					if(waitingtime[i]<0)
						waitingtime[i]=0;
					turnaroundtime[i]=input.get(i).get(2)+waitingtime[i];
					completiontime[i]=input.get(i).get(1)+turnaroundtime[i];
//					System.out.println(completiontime[i]+"\t"+servicetime[i]+"\t"+waitingtime[i]+"\t"+turnaroundtime[i]);
				}
				for(int i=0;i<no;i++)
				{
					input.get(i).add(completiontime[i]);
					input.get(i).add(turnaroundtime[i]);
					input.get(i).add(waitingtime[i]);
				}
				System.out.println("Output  is: ");
				System.out.println("Process \tArrival Time \tCPU Burst \tCompletionTime\tTurnAroundTime\tWaitingTime");		//display
				for(int i=0;i<input.size();i++)
				{
					for(int j=0;j<input.get(i).size();j++)
					{
						System.out.print(input.get(i).get(j)+"\t\t");
					}
					System.out.println();
				}
				int[] array=new int[100];
				for(int i=0;i<100;i++)
				{
					array[i]=0;
				}
				System.out.println("Processes: ");
				for(int i=0;i<input.size();i++)
				{
					System.out.print(input.get(i).get(0)+"  ");
				}
				System.out.println();
				break;
			case 2:
				ArrayList<Integer> remtime=new ArrayList<Integer>();
				ArrayList<Integer> waittime=new ArrayList<Integer>();
				ArrayList<Integer> done=new ArrayList<Integer>();
				System.out.println("Enter number of processes");
				int no1=scan.nextInt();
				ArrayList<ArrayList<Integer>> input1=new ArrayList<ArrayList<Integer>>();
				for(int i=0;i<no1;i++)									//input
				{
					ArrayList<Integer> al1=new ArrayList<Integer>();
					al1.add(i+1);
					System.out.println("Enter Arrival Time");
					al1.add(scan.nextInt());
					System.out.println("Enter CPU Burst");
					int cb=scan.nextInt();
					al1.add(cb);
					input1.add(al1);
					waittime.add(0);
					done.add(0);
				}
				System.out.println("Enter time slice");
				int ts=scan.nextInt();
				System.out.println("-----------RR------------");
				System.out.println("Input is: ");
				System.out.println("Process \tArrival Time \tCPU Burst");		//display
				for(int i=0;i<input1.size();i++)
				{
					for(int j=0;j<input1.get(i).size();j++)
					{
						System.out.print(input1.get(i).get(j)+"\t\t");
					}
					System.out.println();
				}	
				for(int i=0;i<input1.size();i++)									//sorted array wrt Arrival Time
				{
					for(int j=0;j<input1.size()-1;j++)
					{
						if(input1.get(j).get(1).intValue()>input1.get(j+1).get(1).intValue())
						{
							ArrayList<Integer> temp=input1.get(j);
							input1.set(j, input1.get(j+1));
							input1.set(j+1, temp);
						}
					}
				}	
				for(int i=0;i<input1.size();i++)
				{
					remtime.add(input1.get(i).get(2));
				}
				int time=0;
				int flag=1;
				while(true)
				{
					for(int i=0;i<input1.size();i++)
					{
						if(done.get(i).equals(0))							//if not completed
						{
							System.out.println("Not done yet: "+i);
							System.out.println(remtime.get(i)+" "+ts);
							if(remtime.get(i)>ts)								
							{
								System.out.println("Greater: "+remtime.get(i));
								remtime.set(i, remtime.get(i)-ts);
								time=time+ts;
								System.out.println("P"+(i+1));
							}
							else												//will get completed
							{
								done.set(i, 1);
								System.out.println("Done: "+i);
								input1.get(i).add(time+remtime.get(i));						//add the CT
								System.out.println("Adding CT: "+(time+remtime.get(i)));
								input1.get(i).add(input1.get(i).get(3)-input1.get(i).get(1));		//add the TT=CT-AT
//								input1.get(i).add(input1.get(i).get(4)-input1.get(i).get(2));		//WT=TT-CPU Burst
								time=time+remtime.get(i);
								remtime.set(i,0);
								System.out.println("P"+(i+1));
							}
						}
						else
							continue;	
					}
					for(int j=0;j<done.size();j++)
					{
						if(done.get(j).equals(0))
						{
							flag=1;
							break;
						}
						else							//all completed
							flag=0;
					}
					if(flag==0)
						break;
				}
				for(int i=0;i<input1.size();i++)
				{
					input1.get(i).add(input1.get(i).get(4)-input1.get(i).get(2));
				}
				System.out.println("Process \tArrival Time \tCPU Burst \tCT \t\tTT \t\tWT");		//display
				for(int k=0;k<input1.size();k++)
				{
					for(int j=0;j<input1.get(k).size();j++)
					{
						System.out.print(input1.get(k).get(j)+"\t\t");
					}
					System.out.println();
				}
				break;
			case 3:
				System.out.println("Enter number of processes");
				int no11=scan.nextInt();
				ArrayList<ArrayList<Integer>> input11=new ArrayList<ArrayList<Integer>>();
				ArrayList<Integer> done1=new ArrayList<Integer>();
				ArrayList<Integer> complete=new ArrayList<Integer>();
				ArrayList<ArrayList<Integer>> ready=new ArrayList<ArrayList<Integer>>();
				for(int i=0;i<no11;i++)									//input
				{
					ArrayList<Integer> al1=new ArrayList<Integer>();
					al1.add(i+1);
					System.out.println("Enter Arrival Time");
					al1.add(scan.nextInt());
					System.out.println("Enter CPU Burst");
					al1.add(scan.nextInt());
					System.out.println("Enter Priority");
					al1.add(scan.nextInt());
					
					input11.add(al1);
					done1.add(0);
				}
				System.out.println("-----------Priority------------");
				System.out.println("Input is: ");
				System.out.println("Process \tArrival Time \tCPU Burst \tPriority");		//display
				for(int i=0;i<input11.size();i++)
				{
					for(int j=0;j<input11.get(i).size();j++)
					{
						System.out.print(input11.get(i).get(j)+"\t\t");
					}
					System.out.println();
				}	
				for(int i=0;i<input11.size();i++)									//sorted array wrt Arrival Time
				{
					for(int j=0;j<input11.size()-1;j++)
					{
						if(input11.get(j).get(1).intValue()>input11.get(j+1).get(1).intValue())
						{
							ArrayList<Integer> temp=input11.get(j);
							input11.set(j, input11.get(j+1));
							input11.set(j+1, temp);
						}
					}
				}
				int time1=0,flag1=0,flag2=1;
				for(int i=0;i<input11.size();i++)
				{
					flag2=1;
					System.out.println("\nIteration: "+i);
					System.out.println("Done: ");
					for(int k=0;k<done1.size();k++)
					{
						System.out.print(done1.get(k)+" ");
					}
					for(int i1=0;i1<input11.size();i1++)
					{
						if(done1.get(i1).equals(0))
						{
							flag1=1;
							break;
						}
						else
							flag1=0;
					}
					if(flag1==1)
					{
						System.out.println("Flag1: "+flag1);
						System.out.println("time1: "+time1);
						System.out.println("Ready is: ");
						for(int j=0;j<ready.size();j++)
						{
							System.out.println(ready.get(j));
						}
						System.out.println("input11 is: "+input11.get(i));
//						time1=input11.get(i).get(1);
						for(int j=0;j<ready.size();j++)
						{
							if(ready.get(j).equals(input11.get(i)))
								flag2=0;
							else
								flag2=1;
						}
						if(flag2==1)
						{
							if(done1.get(i).equals(0))
							{
								ready.add(input11.get(i));
								System.out.println("   Added in ready: "+input11.get(i));	
							}
						}
						flag2=0;
						for(int i1=i+1;i1<input11.size();i1++)
						{
							System.out.println("input11 is: "+input11.get(i1));
							if(done1.get(i1).equals(0))
							{
								if(input11.get(i1).get(1)<=time1)
								{
									for(int j=0;j<ready.size();j++)
									{
										if(ready.get(j).equals(input11.get(i1)))
										{
											flag2=0;
											break;
										}
										else
											flag2=1;
									}
									if(flag2==1)
									{
										ready.add(input11.get(i1));
										System.out.println("         Added in ready: "+input11.get(i1));
									}
								}
								else
									break;
							}
						}
						System.out.println("Ready: ");
						for(int k=0;k<ready.size();k++)
						{
							System.out.print(ready.get(k)+" ");
						}
						for(int j=0;j<ready.size();j++)
						{
							for(int k=0;k<ready.size()-1;k++)
							{
								if(ready.get(k).get(3)>ready.get(k+1).get(3))
								{
									ArrayList<Integer> temp=new ArrayList<Integer>();
									temp=ready.get(k);
									ready.set(k, ready.get(k+1));
									ready.set(k+1, temp);
								}
							}
						}
						done1.set(ready.get(0).get(0)-1, 1);
						time1=time1+ready.get(0).get(2);
						System.out.println("Time1: "+time1);
						input11.get(ready.get(0).get(0)-1).add(time1);				//add CT
						input11.get(ready.get(0).get(0)-1).add(time1-ready.get(0).get(1));
						System.out.println("Removed: "+ready.get(0));
						complete.add(ready.get(0).get(0));
						ready.remove(0);
						
						System.out.println("Done: ");
						for(int k=0;k<done1.size();k++)
						{
							System.out.print(done1.get(k)+" ");
						}
						System.out.println("Ready: ");
						for(int k=0;k<ready.size();k++)
						{
							System.out.println(ready.get(k)+" ");
						}
					}
				}
				for(int i=0;i<input11.size();i++)
				{
					input11.get(i).add(input11.get(i).get(5)-input11.get(i).get(2));
				}
				System.out.println("Process \tArrival Time \tCPU Burst \tPriority \tCT \t\tTT \t\tWT");		//display
				for(int i=0;i<input11.size();i++)
				{
					for(int j=0;j<input11.get(i).size();j++)
					{
						System.out.print(input11.get(i).get(j)+"\t\t");
					}
					System.out.println();
				}
				System.out.println("\nProcesses: ");
				for(int i=0;i<complete.size();i++)
				{
					System.out.print(complete.get(i)+" ");
				}
				break;
			case 4:
				System.out.println("Enter number of processes");
				int no111=scan.nextInt();
				ArrayList<ArrayList<Integer>> input111=new ArrayList<ArrayList<Integer>>();
				ArrayList<Integer> done11=new ArrayList<Integer>();
				ArrayList<Integer> rem=new ArrayList<Integer>();
				ArrayList<ArrayList<Integer>> ready1=new ArrayList<ArrayList<Integer>>();
				for(int i=0;i<no111;i++)									//input
				{
					ArrayList<Integer> al1=new ArrayList<Integer>();
					al1.add(i+1);
					System.out.println("Enter Arrival Time");
					al1.add(scan.nextInt());
					System.out.println("Enter CPU Burst");
					al1.add(scan.nextInt());
					
					input111.add(al1);
					done11.add(0);
				}
				for(int i=0;i<input111.size();i++)
				{
					rem.add(input111.get(i).get(2));
				}
				System.out.println("-----------SJF------------");
				System.out.println("Input is: ");
				System.out.println("Process \tArrival Time \tCPU Burst");		//display
				for(int i=0;i<input111.size();i++)
				{
					for(int j=0;j<input111.get(i).size();j++)
					{
						System.out.print(input111.get(i).get(j)+"\t\t");
					}
					System.out.println();
				}	
				for(int i=0;i<input111.size();i++)									//sorted array wrt Arrival Time
				{
					for(int j=0;j<input111.size()-1;j++)
					{
						if(input111.get(j).get(1).intValue()>input111.get(j+1).get(1).intValue())
						{
							ArrayList<Integer> temp=input111.get(j);
							input111.set(j, input111.get(j+1));
							input111.set(j+1, temp);
						}
					}
				}
				int time11=0,flag3=0;
				while(true)
				{
					System.out.println("\n\nDone is: ");
					for(int i=0;i<done11.size();i++)
					{
						System.out.print(done11.get(i)+" ");
						if(done11.get(i).equals(0))
						{
							flag3=1;
							break;
						}
						else
							flag3=0;
					}
					System.out.println("flag3: "+flag3);
					if(flag3==1)
					{
						System.out.println("Remaining: ");
						for(int i=0;i<rem.size();i++)
						{
							System.out.print(rem.get(i)+" ");
						}
						for(int i=0;i<input111.size();i++)
						{
							if(done11.get(i).equals(0))
							{
								if(input111.get(i).get(1)<=time11)				//add in ready queue
								{
									int flag4=0;
									for(int j=0;j<ready1.size();j++)
									{
										if(ready1.get(j).equals(input111.get(i)))
										{
											flag4=1;
											break;
										}
									}	
									if(flag4==0)
									{
										ready1.add(input111.get(i));
										System.out.println("Added in ready: "+input111.get(i));
									}
								}
							}
						}
						for(int j=0;j<ready1.size();j++)				//sort wrt CPU Burst
						{
							for(int k=0;k<ready1.size()-1;k++)
							{
								if(ready1.get(k).get(2)>ready1.get(k+1).get(2))
								{
									ArrayList<Integer> temp=ready1.get(k);
									ready1.set(k, ready1.get(k+1));
									ready1.set(k+1, temp);
								}
							}
						}
						int index=ready1.get(0).get(0);
						rem.set(index-1,rem.get(index-1)-1); 		//decrement remaining
						if(rem.get(index-1).equals(0))
						{
							done11.set(ready1.get(0).get(0)-1, 1);
							ready1.remove(0);
							input111.get(index-1).add(time11+1-input111.get(index-1).get(1));
							input111.get(index-1).add(input111.get(index-1).get(3)-input111.get(index-1).get(2));
						}
						System.out.println("time11: "+time11);
						 time11++;
						 System.out.println("Done is: ");
						for(int i=0;i<done11.size();i++)
						{
							System.out.print(done11.get(i)+" ");
						}
					}
					else
						break;
					System.out.println("Output: ");
					System.out.println("Process \tArrival Time \tCPU Burst \tTT \t\tWT");		//display
					for(int i=0;i<input111.size();i++)
					{
						for(int j=0;j<input111.get(i).size();j++)
						{
							System.out.print(input111.get(i).get(j)+"\t\t");
						}
						System.out.println();
					}
				}
				break;
			default:
				System.out.println("Invalid Choice");
				break;
			}
			System.out.println("Do you want to continue?(1/0)");
			ch=scan.nextInt();
		}
		while(ch==1);
		scan.close();
	}

}


/*1.FCFS
2.RR
3.Priority
4.SJF

======FCFS======
1
Enter number of processes
4
Enter Arrival Time
0
Enter CPU Burst
5
Enter Arrival Time
1
Enter CPU Burst
3
Enter Arrival Time
2
Enter CPU Burst
8
Enter Arrival Time
3
Enter CPU Burst
6
-----------FCFC------------
Input is: 
Process 	Arrival Time 	CPU Burst
1		0		5		
2		1		3		
3		2		8		
4		3		6		
Output  is: 
Process 	Arrival Time 	CPU Burst 	CompletionTime	TurnAroundTime	WaitingTime
1		0		5		5		5		0		
2		1		3		8		7		4		
3		2		8		16		14		6		
4		3		6		22		19		13		
Processes: 
1  2  3  4  
Do you want to continue?(1/0)



==============RR==============

1.FCFS
2.RR
3.Priority
4.SJF
2
Enter number of processes
6
Enter Arrival Time
5
Enter CPU Burst
5
Enter Arrival Time
4
Enter CPU Burst
6
Enter Arrival Time
3
Enter CPU Burst
7
Enter Arrival Time
1
Enter CPU Burst
9
Enter Arrival Time
2
Enter CPU Burst
2
Enter Arrival Time
6
Enter CPU Burst
3
Enter time slice
3
-----------RR------------
Input is: 
Process 	Arrival Time 	CPU Burst
1		5		5		
2		4		6		
3		3		7		
4		1		9		
5		2		2		
6		6		3		
Not done yet: 0
9 3
Greater: 9
P1
Not done yet: 1
2 3
Done: 1
Adding CT: 5
P2
Not done yet: 2
7 3
Greater: 7
P3
Not done yet: 3
6 3
Greater: 6
P4
Not done yet: 4
5 3
Greater: 5
P5
Not done yet: 5
3 3
Done: 5
Adding CT: 17
P6
Not done yet: 0
6 3
Greater: 6
P1
Not done yet: 2
4 3
Greater: 4
P3
Not done yet: 3
3 3
Done: 3
Adding CT: 26
P4
Not done yet: 4
2 3
Done: 4
Adding CT: 28
P5
Not done yet: 0
3 3
Done: 0
Adding CT: 31
P1
Not done yet: 2
1 3
Done: 2
Adding CT: 32
P3
Process 	Arrival Time 	CPU Burst 	CT 		TT 		WT
4		1		9		31		30		21		
5		2		2		5		3		1		
3		3		7		32		29		22		
2		4		6		26		22		16		
1		5		5		28		23		18		
6		6		3		17		11		8		
Do you want to continue?(1/0)


*/