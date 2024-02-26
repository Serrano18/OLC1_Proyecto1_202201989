package Inicio
import java_cup.runtime.Symbol;

%%
%class lexEx1
%public
%line
%char 
%cup
%unicode
%ignorecase

%init{ 
    yyline = 1;
    yychar = 1; 
%init}

RETORNOS = [ \r\t\n]+
digito=[0-9]+
DECI=[0-9]+("."[0-9]+)?
letra = [a-zA-Z]+
Whitespace = [ \t\f] | {LineTerminator}
LineTerminator = \r|\n|\r\n

%eofval{
    return new Symbol(ParserSym.EOF);
%eofval}
%%

<YYINITIAL>"=" {return new Symbol (ParserSym.IG, yyline, yychar, yytext());}
<YYINITIAL>"int" {return new Symbol (ParserSym.INT, yyline, yychar, y
