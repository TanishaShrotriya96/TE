%{
int charcount=0,wordcount=0,linecount=0;
%}

word [a-zA-Z0-9]+

%%
{word} {wordcount++; charcount+=yyleng;}
[\n]   {charcount++; linecount++;}
.    {charcount++;}
%%
main(){
yylex();
printf("characters %d,words: %d,lines:%d\n", charcount,wordcount,linecount);
}
int yywrap()
{
return 1;
}
