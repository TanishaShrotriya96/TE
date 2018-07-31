package pagereplace;
import algorithms.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PageReplace {

	public static void main(String args[]) {
		
		ArrayList<String> pageRef = new ArrayList<String>();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the page reference string");
		
		char ch='n';
		do {
			String str=sc.next();
			pageRef.add(str);
			System.out.println("Enter another?y/n");
			ch=sc.next().charAt(0);
		}while(ch=='y');
		
		System.out.println("Enter size of page frame");
		int frameSize=sc.nextInt();
		
		System.out.println("Enter the page replacement algorithm\n1.FIFO\n2.LRU\n.Optimal");
		int choice=sc.nextInt();
		switch(choice) {
		
			case 1:Fifo f =new Fifo();
			       f.replacePage(pageRef,frameSize);
			case 2:Lru l=new Lru();
			       l.replacePage(pageRef,frameSize);
			case 3:Optimal o= new Optimal();
			       o.replacePage(pageRef,frameSize);
			default:
		
		}
		
	}
}
