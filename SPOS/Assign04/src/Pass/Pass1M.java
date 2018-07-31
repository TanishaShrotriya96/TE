package Pass;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Pass1M {

    String input="/home/ccoew/3476/SPOS/Assign4/src/macro";
	ArrayList<MacroName> MNT = new ArrayList<MacroName>();
	ArrayList<Param> PNTAB = new ArrayList<Param>();
	ArrayList<Param> SSNTAB = new ArrayList<Param>();
	ArrayList<Param> EVNTAB = new ArrayList<Param>();
	Map<String,String> KPDTAB = new HashMap<String,String>();
	ArrayList<Param> MDT = new ArrayList<Param>();	
	ArrayList<Param> MDT_FINAL = new ArrayList<Param>();	
	ArrayList<Param> SSTAB = new ArrayList<Param>();	
	
	public static void main(String[] args) throws Exception{

			Pass1M p = new Pass1M();	
			p.Pass1();
	}

    public void Pass1() throws Exception{
    	
		// reading instructions from code 
		String data = readFileAsString(input);
		String[] s = data.split("[\\s,]");
		//split based on space
		 
		ArrayList<String> str = new ArrayList<String>();
		for(String e : s){
		    if(!e.isEmpty()){    
			 str.add(e);
		    }
		    
	    }
        
		System.out.println(str.toString());				
		MacroName macro =new MacroName();
		// counters for MNT table
		int paramCount=1, sSymCount=1, eSymCount=1;
	    
		// flags to avoid repeats and increase efficiency
		int f1=0,f2=0;
		
		//===========================================================================================
		// Starting pass1 of Macro 
		
		// Creating tables PNTAB,EVNTAB,SSNTAB,KPDTAB
		for(int i=0;i<str.size();i++){
			
			// to search for macro name
			if(str.get(i).equals("MACRO")){
				i++;
				if(macro.name!=null) {
					MNT.add(macro);

					//System.out.println(macro.toString());
					macro=new MacroName();
					macro.name=str.get(i);
					
				}
				else {
					macro.name=str.get(i);
					//System.out.println(macro.toString());
					
					
				}
				//System.out.println(macro.toString());
			}
			
			// for & symbols
			if(str.get(i).matches("\\&.*")){
				f1=0;
				//Resetting flag
				
				//Setting flag to avoid repeats
				for(Param s1 : PNTAB){
					if(str.get(i).replaceAll("\\W","").contains(s1.name)){
						f1=1;
						break;
					}
					
				}
				
				// to avoid EVNTAB symbols 
				
				for(Param s1 : EVNTAB){
					if(str.get(i).replaceAll("\\W","").contains(s1.name)){
						f1=1;
						break;
					}
					
				}
				
				if(f1==0){
					Param p = new Param();
					
					if(str.get(i).contains("=")){
	
						String kpdtab[]=str.get(i).split("=");
						p.name=kpdtab[0].replaceAll("\\W","");
						p.num=paramCount;
						p.code="(P,"+paramCount+")";
						PNTAB.add(p);
						
						KPDTAB.put(kpdtab[0].replaceAll("\\W",""),kpdtab[1]);
						macro.KP++;
						macro.KPDTP++;
						paramCount++;
							
					}
					else {
						
						p.name=str.get(i).replaceAll("\\W","");
						p.num=paramCount;
						p.code="(P,"+paramCount+")";
						PNTAB.add(p);
						macro.PP++;
						//System.out.println(PNTAB.toString());
						paramCount++;
					}
					
				}
			}
			
			// for SSNTAB .More 
			if(str.get(i).matches("\\..*")){
				f2=0;
				//to avoid repeats
				for(Param s1 : SSNTAB){
					if(s1.name.equals(str.get(i).replaceAll("\\W",""))){
						f2=1;
						break;
					}
				}
				if(f2==0){
					Param p=new Param();
					p.name=str.get(i).replaceAll("\\W","");
					p.num=sSymCount;
					p.code="(S,"+sSymCount+")";
					SSNTAB.add(p);
				//	System.out.println(SSNTAB.toString());
					sSymCount++;
					
				}
			}
			if(str.get(i).matches("LCL")){
				
				Param p=new Param();
				p.name=str.get(i+1).replaceAll("\\W","");
				p.num=sSymCount;
				p.code="(E,"+eSymCount+")";
				EVNTAB.add(p);
				macro.EV++;
				eSymCount++;
				
			//	System.out.println(EVNTAB.toString());
				
			}
			
			// To catch the last macro
			if(str.get(i).equals("MEND")&&i==str.size()-1) {
				MNT.add(macro);
				System.out.println(macro.toString());
			}
		}		
	   //Creating MDT and SSTAB
		int SSTAB_count=1;
		int lc=1;
		BufferedReader br = new BufferedReader(new FileReader(input));
		
	    // Reading input file line by line into MDT
		String line = null;
		while ((line = br.readLine()) != null) {
		    
			Param p=new Param();
			p.num=lc;
			p.name=line;
			MDT.add(p); 
			lc++;
		}

		//System.out.println(MDT.toString());
	    br.close();	
	    str.clear();
	    //System.out.println(str.toString());
	    lc=0;
		for(int j=0;j<MDT.size();j++){

			// flag for skipping MEND between two macros 
			int skip=0;
			
		    str.clear();
			String values[]=MDT.get(j).name.split("\\s");
			for(String e : values){
			    if(!e.isEmpty()){    
				 str.add(e);
			    }
			    
		    }
			Param value=new Param();
		    String macroPos=null;
		    for(int i=0;i<str.size();i++){
		    	
		    	if(str.get(i).equals("MACRO")) {
		    		
		    		for(MacroName m : MNT) {
		    			
		    			if(MDT.get(j+1).name.contains(m.name)) {
		    				macro=new MacroName();
		    				macro=m;
		    			//	System.out.println(macro.toString());
		    				break;
		    			}
		    		}
		            macroPos=str.get(i);
		    		j=j+1;
		    		break;
		    	}
		    	else {
		    		int f=0;
		    		// f is used to avoid searching through all tables once a match is found
		    		for(Param p:PNTAB){
			    		
		    	        if(str.get(i).replaceAll("&", "").equals(p.name)) {
			    			value.code=p.code;
			    			f=1;
			    			break;
		    	        }
		    	        else if(str.get(i).contains("&"+p.name)){
		    	        	
		    	        	String x=str.get(i).replaceAll("&"+p.name,p.code);
		    	        	str.remove(i);
		    	        	str.add(i, x);
		    	        	value.code=str.get(i);
		    	        	// f=4 is set so that all TAB and PNTAB are checked for that one entry 
		    	        	// and it is not mistaken to be an instruction
		    	        	
		    	        	f=4;
		    	        	break;
		    	        }
			    		
			    	}
		    		// if match not found in PNTAB only then try SSNTAB
		    		if(f!=1) {
			    		for(Param p:SSNTAB){
			    			
			    			if(str.get(i).replaceAll("\\.", "").equals(p.name)) {
			    				
			    				// condition to avoid occurrence of .MORE as label instead of operand
			    				if(i!=0) {
					    			value.code=p.code;
					    			f=2;
					    			break;
			    				}
			    				else {
			    					Param e =new Param();
			    					e.num=SSTAB_count++;
			    					e.name=""+lc;
			    					SSTAB.add(e);
			    					macro.SSTP=lc;
			    					f=2;
			    					break;
			    				}
			    	        }
			    	        else if(str.get(i).contains("\\."+p.name)){
			    	        	
			    	        	String x=str.get(i).replaceAll("\\."+p.name,p.code);
			    	        	str.remove(i);
			    	        	str.add(i, x);
			    	        	value.code=str.get(i);
			    	        	f=4;
			    	        	break;
			    	        }
				    	}	
			    	}
		    		// if match not found in PNTAB and SSNTAB then try EVNTAB
		    		if(f!=2 && f!=1) {
			    		for(Param p:EVNTAB){
			    			if(str.get(i).replaceAll("&", "").equals(p.name)) {
				    			value.code=p.code;
				    			f=3;
				    			break;
			    	        }
			    	        else if(str.get(i).contains("&"+p.name)){
			    	        	
			    	        	String x=str.get(i).replaceAll("&"+p.name,p.code);
			    	        	str.remove(i);
			    	        	str.add(i, x);
			    	        	value.code=str.get(i);
			    	        	f=4;
			    	        	break;
			    	        }
				    	}	
			    	}
		    		// if neither is true then copy paste the instruction
		    		if(f==0) {
		    			if(i==0) {
		    				value.code="      "+str.get(i);
		    			}
		    			else if(i==1 && str.get(0).matches("\\..*")) {
		    				value.code="      "+str.get(i);
		    			} 
		    			else {
		    				value.code=str.get(i);
			    				
		    			}
		    		}              
			    	value.num=lc;
			    	if(value.name!=null) {
			    		value.name=value.name+" "+value.code;
			    		value.code="";
			    	}
			    	else {
			    		value.name=value.code;
			    		value.code="";
			    	}
			    	if(str.get(i).equals("MEND")) {
			    		skip=1;
			    	}
		    	}
		    }
		    if(skip==0) {
		    	lc++;
		    }
		    else {
		    	skip=0;
		    }
		    MDT_FINAL.add(value);
		    if(macroPos!=null && macroPos.equals("MACRO")) {
		    	macroPos=null;
		    	macro.MDTP=lc;
		    }
		    
		}
		/*========================================================================================*/
		//Printing PNTAB 
		System.out.println("PNTAB -----------");
		for(Param value : PNTAB)
		{
			System.out.println(value.toString());
		}
		System.out.println("-----------------");
		
		//Printing PNTAB 
		System.out.println("EVNTAB -----------");
		for(Param value : EVNTAB)
		{
			System.out.println(value.toString());
		}
		System.out.println("-----------------");
		
		//Printing PNTAB 
		System.out.println("SSNTAB -----------");
		for(Param value : SSNTAB)
		{
			System.out.println(value.toString());
		}
		System.out.println("-----------------");
		
        // Printing KPDTAB 
		System.out.println("KPDTAB -----------");
		Set<Entry<String, String>> s1=KPDTAB.entrySet();
		Object[] x = s1.toArray();
		// converting to object array in order to have good formatting 
		for(Object s2 : x){
			System.out.println(s2.toString().split("=")[0]+" "+ s2.toString().split("=")[1]);
		}
		System.out.println("-----------------");
		
		//Printing SSTAB
		System.out.println("SSTAB -----------");
		for(Param value : SSTAB)
		{
		    System.out.println(value.num+" "+value.toString());
			
		}
		System.out.println("-----------------");
	
		//Printing MDT
		System.out.println("MDT -----------");
		for(Param value : MDT_FINAL)
		{
			if(value.num!=0) {
				System.out.println(value.num+" "+value.toString());
			}
		}
		System.out.println("-----------------");
		
		//Printing MNT
		System.out.println("MNT -----------");
		for(MacroName value : MNT)
		{
			System.out.println(value.toString());
			
		}
		System.out.println("-----------------");
    }
	public static String readFileAsString(String fileName)throws Exception
	{
	 String data = "";
	 data = new String(Files.readAllBytes(Paths.get(fileName)));
	 return data;
	}
}

/*OUTPUT ------------------------------------
[MACRO, CLEARMEM, &X, &N, &REG=AREG, LCL, &M, &M, SET, 0, MOVER, &REG, ='0', .MORE, MOVEM, &REG, &X+&M, &M, SET, &M+1, AIF, (&M, NE, &N), .MORE, MEND, MACRO, CUBE, &P, &CUB, MOVER, AREG, &P, MULT, AREG, &P, MULT, AREG, &P, MOVEM, AREG, &CUB, MEND]
CLEARMEM 0 0 0 0 0 0
 
CLEARMEM 2 1 1 0 1 0
 
CUBE 2 0 0 0 0 0
 
CLEARMEM 2 1 1 0 1 0
 
CUBE 2 0 0 0 0 0
 
PNTAB -----------
X
N
REG
P
CUB
-----------------
EVNTAB -----------
M
-----------------
SSNTAB -----------
MORE
-----------------
KPDTAB -----------
REG AREG
-----------------
SSTAB -----------
1 4
-----------------
MDT -----------
1       LCL (E,1)
2 (E,1) SET 0
3       MOVER (P,3), ='0'
4       MOVEM (P,3), (P,1)+(E,1)
5 (E,1) SET (E,1)+1
6       AIF ((E,1) NE (P,2)) (S,1)
7       MEND
8       MOVER AREG,(P,4)
9       MULT AREG,(P,4)
10       MULT AREG,(P,4)
11       MOVEM AREG,(P,5)
12       MEND
-----------------
MNT -----------
CLEARMEM 2 1 1 1 1 4
 
CUBE 2 0 0 8 0 0
 
-----------------
*/
