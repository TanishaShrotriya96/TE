%{
#include <stdio.h>
%}
%token NOUN VERB PRONOUN CONJUNCTION
%%
sentence:subject VERB object CONJUNCTION subject VERB object  {printf("\nSentence is Compound and Valid Sentence");};
subject:NOUN | PRONOUN;
object:NOUN;
%%
main()
{
yyparse();
}
yyerror()
{
printf("Error");
}
