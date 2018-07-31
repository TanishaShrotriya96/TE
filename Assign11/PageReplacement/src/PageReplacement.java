import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PageReplacement {

	public void fifo()
	{
		int noPages,noSlots,flag=0,flag1=0;
		Scanner scan=new Scanner(System.in);
		 Queue<String> q = new LinkedList<>();
		 
		 System.out.println("Enter the total number of pages:");
		 noPages=scan.nextInt();
		 String[] pages=new String[noPages];
		 System.out.println("Enter the page reference string\n");
		 for(int i=0;i<noPages;i++)
		 {
			 pages[i]=scan.next();
		 }
	
		 int count=0,index=0;int pagefault=0;
		 //Page Slots
		 System.out.println("Enter the total number of slots:");
		 noSlots=scan.nextInt();
		 
		 for(int i=0;i<noPages;i++)
		 {
			
			for(int j=0;j<i;j++)
			{
				if(pages[i].equals(pages[j]))
				{
					flag1=1;
					break;
				}
				
			}
			if(flag1==0)
			{
				if(count!=noSlots)
				{
					
				q.add(pages[i]);
				count++;
				
				pagefault++;
				}
				if(count==noSlots)
				{
					index=i;
					break;
					
				}
			}
		 }
		 int flag2=0;
		 for(int i=index;i<noPages;i++)
		 {
			 flag2=0;
			 Iterator itr=q.iterator();
			 while(itr.hasNext())
			 {
				 if(pages[i].equals(itr.next()))
				 {
					 flag2=1;
					 break;
				 }
			 }
			 if(flag2==0)
			 {
				 q.remove();
				 q.add(pages[i]);
				 pagefault++;
			 }
		 }
		 
		 System.out.println("PageFault is:"+pagefault);
		
	}
	public void optimal()
	{
		int noPages,noSlots;
		int index1=0;
		Scanner scan=new Scanner(System.in);
		 System.out.println("Enter the total number of pages:");
		 noPages=scan.nextInt();
		 String[] pages=new String[noPages];
		 System.out.println("Enter the page reference string\n");
		 for(int i=0;i<noPages;i++)
		 {
			 pages[i]=scan.next();
		 }
	
		 int pagefault=0,flag=0,count=0,index=0,flag1=0;
		 //Page Slots
		 System.out.println("Enter the total number of slots:");
		 noSlots=scan.nextInt();
		 
		 String[] slot=new String[noSlots];
		 int[] signal=new int[noPages];
		 for(int i=0;i<noPages;i++)
		 {
			 signal[i]=1;
			 flag=0;
			 for(int j=0;j<i;j++)
			 {
				 if(slot[j].equals(pages[i]))
				 {
					 flag=1;
					 break;
				 }
			 }
			 if(flag==0)
			 {
				 if(count!=noSlots)
				 {
					 
					 slot[i]=pages[i];
					 count++;
					 pagefault++;
				 }
				 if(count==noSlots)
				{
					 slot[i]=pages[i];
					 
						index=i+1;
						break;
						
				}
			 }
		 }
		 int count1=0,mincount=noPages;
		 for(int i=index;i<noPages;i++)
		 {
			 flag1=0;
			 count1=0;
			 mincount=noPages;
			 for(int j=0;j<noSlots;j++)
			 {
				 if(pages[i].equals(slot[j]))
				 {
					 //System.out.println(pages[i]+"page Found same in slot"+slot[j]);
					 flag1=1;
					 break;
				 }
			 }
			 if(flag1==0)
			 {
				for(int p=0;p<noSlots;p++)
				{
					count1=0;
					for(int q=i+1;q<noPages;q++)
					{
						if(slot[p].equals(pages[q]))
						{
							count1++;
						}
					}
					if(mincount>=count1)
					{
						mincount=count1;
						index1=p;
					}
				}
				slot[index1]=pages[i];
				pagefault++;
			 }
			 
		 }
		 System.out.println("pagefault:"+pagefault);
		 
	}
	public void lru() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int frames,pointer = 0, hit = 0, fault = 0,ref_len;
        Boolean isFull = false;
        int buffer[];
        ArrayList<Integer> stack = new ArrayList<Integer>();
        int reference[];
        int mem_layout[][];
        
        System.out.println("Please enter the number of Frames: ");
        frames = Integer.parseInt(br.readLine());
        
        System.out.println("Please enter the length of the Reference string: ");
        ref_len = Integer.parseInt(br.readLine());
        
        reference = new int[ref_len];
        mem_layout = new int[ref_len][frames];
        buffer = new int[frames];
        for(int j = 0; j < frames; j++)
                buffer[j] = -1;
        
        System.out.println("Please enter the reference string: ");
        for(int i = 0; i < ref_len; i++)
        {
            reference[i] = Integer.parseInt(br.readLine());
        }
        System.out.println();
        for(int i = 0; i < ref_len; i++)
        {
            if(stack.contains(reference[i]))
            {
             stack.remove(stack.indexOf(reference[i]));
            }
            stack.add(reference[i]);
            int search = -1;
            for(int j = 0; j < frames; j++)
            {
                if(buffer[j] == reference[i])
                {
                    search = j;
                    hit++;
                    break;
                }
            }
            if(search == -1)
            {
             if(isFull)
             {
              int min_loc = ref_len;
                    for(int j = 0; j < frames; j++)
                    {
                     if(stack.contains(buffer[j]))
                        {
                            int temp = stack.indexOf(buffer[j]);
                            if(temp < min_loc)
                            {
                                min_loc = temp;
                                pointer = j;
                            }
                        }
                    }
             }
                buffer[pointer] = reference[i];
                fault++;
                pointer++;
                if(pointer == frames)
                {
                 pointer = 0;
                 isFull = true;
                }
            }
            for(int j = 0; j < frames; j++)
                mem_layout[i][j] = buffer[j];
        }
        
        for(int i = 0; i < frames; i++)
        {
            for(int j = 0; j < ref_len; j++)
                System.out.printf("%3d ",mem_layout[j][i]);
            System.out.println();
        }
        
        System.out.println("The number of Hits: " + hit);
        System.out.println("Hit Ratio: " + (float)((float)hit/ref_len));
        System.out.println("The number of Faults: " + fault);
    }
    
	

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc=new Scanner(System.in);
		do{
		int choice;
		PageReplacement pg=new PageReplacement();
		System.out.println("Enter the choice\n1.FIFO\n2.LRU\n3.Optimal");
		choice=sc.nextInt();
		switch(choice)
		{
		case 1:pg.fifo();
		break;
		case 2:pg.lru();//only for integer
		break;
		case 3:pg.optimal();
		break;
		}
		System.out.println("Continue(1/0)?");
		}while(sc.nextInt()==1);
		
	}
	
}
/*Enter the choice
1.FIFO
2.LRU
3.Optimal
1
Enter the total number of pages:
14
Enter the page reference string

a
b
c
d
c
a
d
b
e
b
a
b
c
d
Enter the total number of slots:
4
PageFault is:9



Enter the choice
1.FIFO
2.LRU
3.Optimal
3
Enter the total number of pages:
20
Enter the page reference string

7
0
1
2
0
3
0
4
2
3
0
3
2
1
2
0
1
7
0
1
Enter the total number of slots:
3
pagefault:9


Enter the choice
1.FIFO
2.LRU
3.Optimal
2
Please enter the number of Frames: 
3
Please enter the length of the Reference string: 
20
Please enter the reference string: 
7
0
1
2
0
3
0
4
2
3
0
3
2
1
2
0
1
7
0
1

  7   7   7   2   2   2   2   4   4   4   0   0   0   1   1   1   1   1   1   1 
 -1   0   0   0   0   0   0   0   0   3   3   3   3   3   3   0   0   0   0   0 
 -1  -1   1   1   1   3   3   3   2   2   2   2   2   2   2   2   2   7   7   7 
The number of Hits: 8
Hit Ratio: 0.4
The number of Faults: 12


*/
 