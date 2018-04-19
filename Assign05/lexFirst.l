%{
int charcount=0,wordcount=0,linecount=0,space=0;
%}

word [a-zA-Z0-9]+

%%
{word} {wordcount++; charcount+=yyleng;}

[\n]   {linecount++;}
[ \t]+ {charcount++;space++;}
%%
main(){
	yylex();
	printf("characters %d,words: %d,lines:%d,spaces : %d\n", charcount,wordcount,linecount,space);
}
int yywrap()
{
return 1;
}
/*
Variable yytext is a pointer to the matched string (NULL-terminated) and yyleng is the length of the matched string.
Variable yyout is the output file and defaults to stdout.
Function yywrap is called by lex when input is exhausted. 
Return 1 if you are done or 0 if more processing is required. 

Every C program requires a main function. In this case we simply call yylex that is the main entry-point for lex . Some implementations of lex include copies of main and yywrap in a library thus eliminating the need to code them explicitly. This is why our first example, the shortest lex program, functioned properly.

*/
