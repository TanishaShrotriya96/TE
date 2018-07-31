package Pass;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Pass2M {
	
	String input="/home/ccoew/3476/SPOS/Assign4/src/Input";
	ArrayList<Param> TC = new ArrayList<Param>();
	ArrayList<Param> APTAB = new ArrayList<Param>();
	ArrayList<Param> EVNTAB = new ArrayList<Param>();
	Pass1M p1;
	
	Pass2M(Pass1M p) {
		p1=p;
	}
	public static void main(String[] args) throws Exception {
		Pass1M p = new Pass1M();
		p.Pass1();
		Pass2M p2 = new Pass2M(p);
		p2.Pass2();
		
	}
	
	public void Pass2() throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(input));
		int lc =0;
		int mntIndex=0;
	    // Reading input file line by line into IC
		String line = null;
		
		ArrayList<String> IC = new ArrayList<String>();
		ArrayList<String> STR = new ArrayList<String>();
		
		while ((line = br.readLine()) != null) {
		    
			IC.add(line); 
			lc++;
		}

		System.out.println(IC.toString());
	    br.close();	
	   
	    for(int i=0;i<IC.size();i++){
	    	
	    	//Splitting IC by space into STR
	    	STR.clear();
	    	String values[]=IC.get(i).split("\\s");
			for(String e : values){
			    if(!e.isEmpty()){    
			    	STR.add(e);
			    }
			    
		    }
		   // System.out.println(STR.toString());
			int macroFlag=0;
			
			//checking for preprocessor directive across MNT and STR values
			for(int j=0;j<STR.size();j++) {
				
				//preprocessor exists only at index zero of STR
				if(j==0){
					
					//check if name of macro is found at index k in MNT
					for(int k=0;k<p1.MNT.size();k++) {
						if(p1.MNT.get(k).name.equals(STR.get(j))){
							mntIndex=k;
						    macroFlag=1;
						    break;
						}
					}
					
					if(macroFlag==0) {
						break;
					}
				}
			
			}
			if(macroFlag==0){
				
				Param e = new Param();
				e.name=IC.get(i);
				TC.add(e);
			}
			else {
				processMacro(mntIndex);
			}
			
	    }
	    for(Param tc : TC) {
	    	//System.out.println(tc);
	
	    }
	}
	
	public void processMacro(int index) {
		
		for(int i=0;i<p1.MDT_FINAL.size();i++) {
		
			// as there are null entries to avoid error
			String s[]=null;
		    if(p1.MDT_FINAL.get(i).name!=null){
		    	s=p1.MDT_FINAL.get(i).name.split("\\s"); 
		    
			    // processing MDT line by line
			    ArrayList<String> str=new ArrayList<String>();
			    
				for(String s1 : s) {
					if(!s1.isEmpty()){
						str.add(s1);
					}
				}
				
				int count =0;
				
				for(int k=0;k<str.size();k++) {
					
					if(str.get(k).equals("SET")) {
					   
					   //travel through EVNTAB
					   for(int j=0;j<p1.EVNTAB.size();j++) {
						
						   System.out.println(p1.EVNTAB.get(j).code+str.get(k-1));
						   if(p1.EVNTAB.get(j).code.equals(str.get(k-1))) {
							   count=count +Integer.parseInt(str.get(k+1).replaceAll("[^\\d*]", ""));
							   System.out.println("count="+count);
								
						   }
					   }
					}
				}
	//			/System.out.println(p1.MDT_FINAL.get(i).num+p1.MDT_FINAL.get(i).name);
			
		    }
		}
		
	}
}
