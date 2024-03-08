// ------------  Paquete e importaciones ------------
package Analizadores; 
//aqui va los tokens y la lista de errore
import java_cup.runtime.*;
import java.util.ArrayList;
import clases.*
//-------> Directivas (No tocar)
%%	
%public 
%class analizadorLexico
%cup
%char
%column
%line
%ignorecase
%function next_token

%{
  

    StringBuffer buffer = new StringBuffer();

    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

%eofval{
    return new Symbol(ParserSym.EOF, -1, -1, null);
%eofval}
ARROBA = "@"
NUMERO = [0-9]
Letra  = [a-zA-Z]
EXCLAMACION = "!"
MENOR="<"
MAYOR=">"
// ------> Expresiones Regulares 
CADENA=(\"|"“")[^\"\n]*(\"|"”")
DECIMAL = [0-9]+(\.[0-9]+)?
ID = {Letra}({Letra}|{NUMERO}|"_"|"-")*
//COMILLA = "\"" 
IDARR = {ARROBA}{ID}
COMENTARIO = {EXCLAMACION}~\n
COMENTARIOM = {MENOR}{EXCLAMACION}~{EXCLAMACION}{MAYOR}
%%
// ------------  Reglas Lexicas -------------------
<YYINITIAL>{


    "("     { arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.PARENTESIS_A, yycolumn, yyline, yytext());}

    ")"     {arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.PARENTESIS_C, yycolumn, yyline, yytext());}

    ";"     {arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.PUNTOYCOMA, yycolumn, yyline, yytext());}

    ":"     {arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.DOSPUNTOS, yycolumn, yyline, yytext());}

    "<-"    {arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.ASIGNACION, yycolumn, yyline, yytext());}

    "::"    {arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.PUNTOSDOBLE, yycolumn, yyline, yytext());}

    "["     {arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.CORCHETEA, yycolumn, yyline, yytext());}

    "]"     {arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.CORCHETEC, yycolumn, yyline, yytext());}

    ","     {arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.COMA, yycolumn, yyline, yytext());}

    "="     {arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.IGUAL, yycolumn, yyline, yytext());}

    "->"    {arreglos_publicos.contador = arreglos_publicos.contador + 1;
            arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"SIMBOLO"));
            return new Symbol(ParserSym.ASIGNACION2, yycolumn, yyline, yytext());}
//Palabras reservadas
    "end"       {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_END, yycolumn, yyline, yytext());}

    "program"   {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_PROGRAM, yycolumn, yyline, yytext());}
    
    "var"       { arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_VAR, yycolumn, yyline, yytext());}

    "char[]"    {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_CHAR, yycolumn, yyline, yytext());}

    "double"    {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_DOUBLE, yycolumn, yyline, yytext());}

    "arr"       {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_ARR, yycolumn, yyline, yytext());}

    "sum"       {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_SUM, yycolumn, yyline, yytext());}

    "res"       {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_RES, yycolumn, yyline, yytext());}

    "mul"       {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_MUL, yycolumn, yyline, yytext());}

    "div"       {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_DIV, yycolumn, yyline, yytext());}

    "mod"       {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_MOD, yycolumn, yyline, yytext());}

    "media"     {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_MEDIA, yycolumn, yyline, yytext());}

    "mediana"   {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_MEDIANA, yycolumn, yyline, yytext());}

    "moda"      {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_MODA, yycolumn, yyline, yytext());}

    "varianza"  {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_VARIANZA, yycolumn, yyline, yytext());}

    "max"       {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_MAX, yycolumn, yyline, yytext());}

    "min"       {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_MIN, yycolumn, yyline, yytext());}

    "console"   {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_CONSOLE, yycolumn, yyline, yytext());}

    "print"     {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_PRINT, yycolumn, yyline, yytext());}

    "column"    {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_COLUMN,yycolumn, yyline, yytext());}

    "graphbar"  {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_BAR,yycolumn, yyline, yytext());}

    "graphpie"  {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_PIE,yycolumn, yyline, yytext());}

    "graphLine" {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_LINE,yycolumn, yyline, yytext());}

    "histogram" {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_HISTOGRAM,yycolumn, yyline, yytext());}

    "titulo"    {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_TITULO,yycolumn, yyline, yytext());}

    "ejeX"      {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_EJEX,yycolumn, yyline, yytext());}

    "ejey"      {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_EJEY,yycolumn, yyline, yytext());}
                
    "titulox"   {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_TITULOX,yycolumn, yyline, yytext());}

    "tituloy"   {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_TITULOY,yycolumn, yyline, yytext());}

    "exec"      {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_EXEC,yycolumn, yyline, yytext());}

    "values"    {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_VALUES,yycolumn, yyline, yytext());}

    "label"     {arreglos_publicos.contador = arreglos_publicos.contador + 1;
                arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"RESERVADA"));
                return new Symbol(ParserSym.R_LABEL,yycolumn, yyline, yytext());}


//expresiones regulares


    {ID}          { arreglos_publicos.contador = arreglos_publicos.contador + 1;
                    arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"ID"));
                    return new Symbol(ParserSym.ID, yycolumn, yyline, yytext()); }

    {DECIMAL}     { arreglos_publicos.contador = arreglos_publicos.contador + 1;
                    arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"DOUBLE"));
                    return new Symbol(ParserSym.DECIMAL, yycolumn, yyline, yytext()); }

    {CADENA}      { arreglos_publicos.contador = arreglos_publicos.contador + 1;
                    arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"STRING"));   
                    return new Symbol(ParserSym.CADENA, yycolumn, yyline, yytext()); }

    {IDARR}       { arreglos_publicos.contador = arreglos_publicos.contador + 1;
                    arreglos_publicos.tokens.add(new Token(arreglos_publicos.contador, yyline,yycolumn,yytext(),"ID"));
                    return new Symbol(ParserSym.IDARR, yycolumn, yyline, yytext()); }

    {COMENTARIO}  { }
    {COMENTARIOM} { }
   

   
} 


// comentario {}

//------> Ingorados 
<YYINITIAL> [ \t\r\n\f]     {/* Espacios en blanco se ignoran */}

//------> Errores Léxicos 
[^] { System.out.println("Error Lexico: " + yytext() + " | Fila:" + yyline + " | Columna: " + yycolumn); }
