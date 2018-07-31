%{
#include "y.tab.h"
%}
%%
[\t]+ ;

is|am|are|were|was|can|has|have|being|be|had  {printf("\n Verb %s",yytext); return VERB;}
I|he|she|we|they|them|it  {printf("\n Pronoun %s",yytext); return PRONOUN;}
and|but|also|or|yet {printf("\n Conjunction %s",yytext); return CONJUNCTION;}
[a-zA-Z]+ {printf("\n Noun %s",yytext); return NOUN;}
.|\n ;
%%
int yywrap()
{
return 1;
}


