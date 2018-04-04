%{
#include "y.tab.h"
%}
%%
main {return(MAIN);} 
.*    {return (yytext[0]);}
%%

yywrap()
{
}
