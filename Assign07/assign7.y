%{
#include "y.tab.h"
#include "stdio.h"
%}
%token MAIN
%%
S:P {printf("Accept");};
P:MAIN'('')''{''}' {printf("In main");}
| MAIN'('')''{'     {printf("ERROR Bracket missing");};
%%
main() {
yyparse();
}
yyerror()
{
}
