%{
   #include "y.tab.h"
  
%}
%%
am|is|are|eat|singing|dancing|playing {printf("\n VERB : %s ",yytext); return Verb;}
I|you|he|she|it|they|we {printf(" \n Pronoun :%s",yytext); return Pronoun;}
[A-Za-z]+ {printf("\n Noun : %s",yytext);return Noun;}
%%
int yywrap()
{
return 1;
}

