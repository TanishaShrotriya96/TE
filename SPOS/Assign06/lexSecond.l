%{
#include<stdio.h>
%}

%%

"public static void main(String args[])" {printf("\n%s := This is a System function ",yytext);}
"public" {printf("\n:%s := This is a reserved keyword",yytext);}
"class" {printf("\n:%s := This is a reserved keyword ",yytext);}


int|float|double {printf("\n:The data type := %s",yytext);}
"try"[ \n]"{"|"catch(Exception e)"[\n]"{"[\n]"e.printStackTrace()"[\n]"}" {printf("\n:This is a try catch block%s",yytext);}
"+"|"="|"/"|"*" {printf("\nOperator is a := %s",yytext);}
int|float|double[A-Za-z]*[0-9]* {printf("\n Variable is a :=%s",yytext);}
"{"|"}"|"("|")" {printf("\nSymbol or bracket is a := %s",yytext);}
"System.out.println(\"".*"\")" {printf("\nThis is a system function := %s",yytext);}
%% 
main() {
 yylex();
 return 0;
}
int yywrap()
{
return 1;
}

/* output : 

This is a reserved keyword := public 
This is a reserved keyword := class 
Variable is a := Hello

Symbol or bracket is a := {
	
This is a System function := public static void main(String args[])
	
Symbol or bracket is a := {
		
Variable is a := try
		
Symbol or bracket is a := {
		   
:The data type := int 
Variable is a := a,
Variable is a := b;
		   
Variable is a := a
Operator is a := =
Variable is a := a
Operator is a := +
Variable is a := b;
		   
This is a system function := System.out.println("Good Morning");
		
Symbol or bracket is a := }
		
Variable is a := catch
Symbol or bracket is a := (
Variable is a := Exception 
Variable is a := e
Symbol or bracket is a := )
		
Symbol or bracket is a := {
		   
Variable is a := e.
Variable is a := printStackTrace
Symbol or bracket is a := (
Symbol or bracket is a := );	
		
Symbol or bracket is a := }
	
Symbol or bracket is a := }

Symbol or bracket is a := }
*/
