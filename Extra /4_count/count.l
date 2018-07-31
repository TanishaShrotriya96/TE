%{
#include<stdio.h>
int ch=0, bl=0, ln=0, wr=0;
%}
%%
[\n] {ln++;wr++;}
[\t] {bl++;wr++;}
[" "] {bl++;wr++;}
[^\n\t] {ch++;}
%%

main()
{
yylex();
printf("\n blankcount      :  %d\n",bl);
printf("\n linescount      :  %d\n",ln);
printf("\n charactercount  :  %d\n",ch);
printf("\n wordscount      :  %d\n",wr); 
}

int yywrap()
{
return 1;
}


 
