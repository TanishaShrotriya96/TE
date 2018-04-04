
%{
#include "stdio.h"
%}

%token Noun Verb Pronoun
%%
statement:subject verb object {printf("\n Sentence is correct :)");}
     	;
subject: Noun|Pronoun
      ;
verb: Verb
      ;
object: Noun
       ;

%%
main(){
yyparse();
}
yyerror(){
printf("\n Invaild Statement ");
}

