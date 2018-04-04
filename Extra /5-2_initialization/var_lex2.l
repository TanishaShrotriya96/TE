%{
#include "y.tab.h"
%}
%%
int	{return(INT);}
char	{return(CHAR);}
float	{return(FLOAT);}
string	{return(STRING);}
[a-z]* {return(VARIABLE);}
= {return(ASSIGNMENT);}
;	{return(SEMICOLON);}
[0-9]* {return(VALUE_INT);}
"\'"[a-zA-Z]"\'"	{return(VALUE_CHAR);}
-?[0-9]*["."[0-9]*]??	{return(VALUE_FLOAT);}
"\""[a-zA-Z]+[A-Za-z0-9]*"\""	{return(VALUE_STRING);}
%%
yywrap()
{
	return 1;
}
