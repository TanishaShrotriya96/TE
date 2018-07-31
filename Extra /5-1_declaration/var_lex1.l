%{
	#include "y.tab.h"
	extern int yylval;
%}
%%
String|void|int|float {printf(" type %s: ",yytext) ; return Datatype;}
[A-Za-z]+ {printf("\n variable : %s",yytext) ;return variable;}
";" {printf("\n Delimeter : %s ",yytext) ;return delimeter;}
" " {printf ("\n SPace : %s ",yytext ) ;return space;}
[\t]  ; /*ignore whitespace */
[\n]  {return 0 ;} /*logical EOf */
 .     {return yytext[0];}
%%
int yywrap()
{
return 0;
}

