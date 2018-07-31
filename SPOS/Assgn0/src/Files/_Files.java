package Files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
//Java Program to illustrate reading from text file
//as string in Java
import java.nio.file.*;
import java.util.*;

public class _Files {

public static String readFileAsString(String fileName)throws Exception
{
 String data = "";
 data = new String(Files.readAllBytes(Paths.get(fileName)));
 return data;
}

public static void main(String[] args) throws Exception
{
	
 String data = readFileAsString("/home/ccoew/3476/Assgn0/src/one.txt");
 String symbols[] = data.split("[\\w,]");
 /*for(String e:symbols) {
  System.out.println(e);
 }*/

 ArrayList<String> sym = new ArrayList<String>();
 
 String comma="=";
 for(String e : symbols){
   // if(!e.matches("[\\s]")&&!e.isEmpty()&&!e.equals(",")){    
	 if(e.matches("[+=-^!*|]")){ 
	 sym.add(e);
	 write("/home/ccoew/3476/Assgn1/src/Files/symbol.text",e);
	 }
   // }
    
 }
 System.out.println(sym.toString());
 

 String[] s = data.split("\\W");
 
 ArrayList<String> str = new ArrayList<String>();
 for(String e : s){
    if(!e.isEmpty()){    
	 str.add(e);
	 
    }
    
 }
 System.out.print(str.toString());

 int counter = 3;
 	
 for(String w :str) {
	
	 if(w.matches("\\d*")||w.matches("\\D")||w.matches(".REG+")&&counter!=3)
	 { 
		 //separate operands
		 
		 System.out.print(w+" ");

		 if(counter==1){
			 System.out.print(counter+" ");
		     counter=3;	
		     write("/home/ccoew/3476/Assgn1/src/Files/op2.text",w+"\n");
		     	   
		 }

		 if(counter==2){

		     write("/home/ccoew/3476/Assgn1/src/Files/op1.text",w+"\n");
		     	   
			 System.out.print(counter+" ");
		     counter--;				 
		 }

		 
	 }
	 else {
 		 
       //else it is instruction
		 System.out.print(w+"\n");
	     

	     write("/home/ccoew/3476/Assgn1/src/Files/instr.text",w+"\n");
	     	   
		 if(counter==1){
			 
			 
			 System.out.print(counter+" ");
		     counter=2;				 
		 }

		 else if(counter==3){
			 System.out.println(counter+" ");
		     counter--;				 
		 }
	 }
	 
	 }

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