%{
#include "y.tab.h"
#include "stdio.h"
%}
%token INT CHAR FLOAT STRING BOOL VARIABLE SEMICOLON ASSIGNMENT VALUE_INT VALUE_CHAR VALUE_FLOAT VALUE_STRING BOOL_VALUE
%%
S:i var sc	{printf("Valid Statement");}
| c var sc	{printf("Valid Statement");}
| f var sc	{printf("Valid Statement");}
| s var sc	{printf("Valid Statement");}
| i var assign value_int sc	{printf("Valid Statement with proper int declaration");}
| c var assign value_char sc	{printf("Valid Statement with proper Char declaration");}
| f var assign value_float sc	{printf("Valid Statement with proper float declaration");}
| s var assign value_string sc	{printf("Valid Statement with proper string declaration");}
;
i: INT	
;
c: CHAR	
;
f: FLOAT	
;
s: STRING	
;
var: VARIABLE	
;
assign: ASSIGNMENT	
;
sc: SEMICOLON	
;
value_int: VALUE_INT	
;
value_char: VALUE_CHAR	
;
value_float: VALUE_FLOAT	
;
value_string: VALUE_STRING	
;
%%
main()
{
yyparse();
}
yyerror()
{
 printf("Invalid statement");
}
