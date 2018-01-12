package Pass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Pass1 {

	
	public static void main(String[] args) throws Exception {
		
		// reading instructions from code 
		String data = readFileAsString("/home/ccoew/3476/Assign1/src/input");
		String[] s = data.split("[\\s,]");
		//split based on space
		 
		ArrayList<String> str = new ArrayList<String>();
		for(String e : s){
		    if(!e.isEmpty()){    
			 str.add(e);
		    }
		    
	    }
	
		System.out.println(str.toString());
		
		//read the mnemonics file
		
	    String mnemo = readFileAsString("/home/ccoew/3476/Assign1/src/mnemonics");
	    String[] mnem = mnemo.split("\\s");
	    
	    ArrayList<String> mnemonics = new ArrayList<String>();
		
	    for(String e : mnem){
		    
	    	if(!e.isEmpty()){
		    	 mnemonics.add(e);
		    }
		    
	    }
		
		System.out.println(mnemonics.toString());
		
		// wrapping the created ref array around object of class mnemonics
		
		Mnemonics m;
		ArrayList<Mnemonics> ref = new ArrayList<Mnemonics>();	    
		String n=null,op=null,t=null;
	    int l=0;
	    for(int i=0;i<mnemonics.size();i++) {
			if(i%4==0) {
				n=mnemonics.get(i);
				
			}
			else if(i%4==1) {
				t=mnemonics.get(i);
			
			}
			else if(i%4==2) {
				op=mnemonics.get(i);
				
			}
			else if(i%4==3) {
				l=Integer.parseInt(mnemonics.get(i));
				m=new Mnemonics();
				m.name=n;
				m.len=l;
				m.opcode=op;
				m.type=t;
				ref.add(m);
			
			}
		}
	
	    System.out.println(ref.toString());
			
	    String opIn=null;
	    int lc=0;
        int flag=0; // used for the final condition for finding symbols
        
		//stage one of parse one - compare with regs,start and so on..
	    for(int i=0;i<str.size();i++) {
		    flag=0;
		    System.out.println(lc);
	        for(int j=0;j<ref.size();j++) {
	    		
	    		String st=null;
	    	    String one=str.get(i);
	    	    String two=ref.get(j).name;
	    	    
	    		//	System.out.println(str.get(i)+(ref.get(j).name));
	    		if(one.equals(two)){
	    			
	    			st=str.get(i)+" "+ ref.get(j).name+" ";
	    			
	    			if(lc!=0) {
	    				opIn=lc+" ("+ref.get(j).type+","+ref.get(j).opcode+")";
	    				if(ref.get(j).len!=256){
	    					lc=lc+ref.get(j).len;
	    				}
	    				System.out.println("For other");
	    			}
	    			
	    			else {
	    				System.out.println("For start");
		    			opIn="("+ref.get(j).type+","+ref.get(j).opcode+")";
		    		}
	    			
	    			System.out.println(st);
	    			write("/home/ccoew/3476/Assign1/src/IC",opIn);
	    			
	    			flag=1;
	    			System.out.println("Here "+flag);
	    			break;			
	    		}
	    	}

	    	if(str.get(i).matches("\\d*")){
		   			
	    		opIn=" "+str.get(i)+"\n";
	   			lc=Integer.parseInt(str.get(i));
	    		System.out.println(opIn);
	   			flag=1;
	   			write("/home/ccoew/3476/Assign1/src/IC",opIn);
	   		}
	    	
	    	if(str.get(i).matches(".REG")){
		   			
	    		flag=1;	
	    		opIn=" "+str.get(i)+",";
	   			System.out.println(opIn);
		   	    write("/home/ccoew/3476/Assign1/src/IC",opIn);
		    					
			}
            
	    	if(str.get(i).matches("=.*")){
		   			
	    		flag=1;
	    		opIn="(L,"+str.get(i).replaceAll("\\W","")+")\n";
	   			System.out.println(opIn);
		   	    write("/home/ccoew/3476/Assign1/src/IC",opIn);
	    					
		    }
	 
	    	
	    	else if(flag==0) {
	    		
	    		// Reading SYMTAB file to compare already existing symbols
	    		String three = readFileAsString("/home/ccoew/3476/Assign1/src/SYMTAB");
	    	    String[] trois= three.split("\\s");
	    	    int f=0;
	    	    // to avoid repeats
	    	    for(String s1 : trois ){
	    	    	
	    	    	// get the newest symbol, and strip of all digits and non-word characters
	    	    	System.out.print(s1);
	    	    	String s2=str.get(i).replaceAll("[\\W\\d*]","");
	    	    	if(s1.matches(s2)){
	    	    		f=1;
	    	    		break;
	    	    	}
	    	    }
                if(f!=1){
		    		System.out.println(flag);
		    		// for labels 
		    		if(str.get(i).matches(".*:")){
		    			opIn=str.get(i).replaceAll("\\W", "")+"\n";
			   			System.out.println(opIn);
			   		    write("/home/ccoew/3476/Assign1/src/SYMTAB",opIn);
				    	
		    		}
		    		// for variables
		    		else{
		    			opIn=str.get(i)+"\n";
		    			System.out.println("THIS"+opIn);
		    			write("/home/ccoew/3476/Assign1/src/SYMTAB",opIn);	
		    			write("/home/ccoew/3476/Assign1/src/IC",opIn);
		    		}
                }
	    	}
		}

	}


	
	public static String readFileAsString(String fileName)throws Exception
	{
	 String data = "";
	 data = new String(Files.readAllBytes(Paths.get(fileName)));
	 return data;
	}
	
	public static void write(String path,String update){
		try {
			FileWriter file = new FileWriter(path,true);
			file.write(update);
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
