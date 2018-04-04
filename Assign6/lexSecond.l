%{
#include<stdio.h>
%}
quotes  [""]

// make a few more changes
%%

"public static void main(String args[])" {printf("\nThis is a System function := %s",yytext);}
"public" {printf("\n:This is a reserved keyword := %s",yytext);}
"class" {printf("\n:This is a reserved keyword := %s",yytext);}


int|float|double {printf("\n:The data type := %s",yytext);}
"try"[\n]"{"|"catch(Exception e)"[\n]"{"[\n]"e.printStackTrace()"[\n]"}" {printf("\n:This is a try catch block%s",yytext);}
"+"|"="|"/"|"*" {printf("\nOperator is a := %s",yytext);}
[A-Za-z]*[0-9]* {printf("\nVariable is a := %s",yytext);}
"{"|"}"|"("|")" {printf("\nSymbol or bracket is a := %s",yytext);}
"System.out.println(\"".*"\")" {printf("\nThis is a system function := %s",yytext);}
%% 
main(argc,argv)
int argc;
char **argv;
{
	if(argc>1)
	{
		FILE *file;
		file=fopen(argv[1],"r");
		if(!file){
		fprintf(stderr,"could not open %s\n",argv[1]);
		exit(1);
		}
	yyin=file;
	}


yylex();

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
