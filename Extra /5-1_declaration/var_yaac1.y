%{
#include "stdio.h"
%}
%token Datatype variable space delimeter
%%
statement:Datatype space variable delimeter {printf("valid");}
;
%%
main(){
yyparse();
}
yyerror()
{
printf("\n invalid input");
}
