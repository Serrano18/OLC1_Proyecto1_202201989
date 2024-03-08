// ------------  Paquete e importaciones ------------
package Analizadores; 
//aqui va los tokens y la lista de errore
import java_cup.runtime.*;
import java.util.ArrayList;

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
   // public ArrayList<Token> token = new ArrayList<Token>();

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
punto = "."
COMILLA = "\"" 
EXCLAMACION = "!"
MENOR="<"
MAYOR=">"
// ------> Expresiones Regulares 
CADENA=(\"|"“")[^\"\n]*(\"|"”")
//CADENA  =  ({COMILLA}|{COMILLAA}) ~({COMILLA}|{COMILLAC})
DECIMAL = [0-9]+(\.[0-9]+)?
ID = {Letra}({Letra}|{NUMERO}|"_"|"-")*
COMILLA = "\"" 
IDARR = {ARROBA}{ID}
COMENTARIO = {EXCLAMACION}~\n
COMENTARIOM = {MENOR}{EXCLAMACION}~{EXCLAMACION}{MAYOR}

%%
// ------------  Reglas Lexicas -------------------
<YYINITIAL>{




    "("     {return new Symbol(ParserSym.PARENTESIS_A, yycolumn, yyline, yytext());}
    ")"     {return new Symbol(ParserSym.PARENTESIS_C, yycolumn, yyline, yytext());}
    ";"     {return new Symbol(ParserSym.PUNTOYCOMA, yycolumn, yyline, yytext());}
    ":"     {return new Symbol(ParserSym.DOSPUNTOS, yycolumn, yyline, yytext());}
    "<-"    {return new Symbol(ParserSym.ASIGNACION, yycolumn, yyline, yytext());}
    "::"    {return new Symbol(ParserSym.PUNTOSDOBLE, yycolumn, yyline, yytext());}
    "["     {return new Symbol(ParserSym.CORCHETEA, yycolumn, yyline, yytext());}
    "]"     {return new Symbol(ParserSym.CORCHETEC, yycolumn, yyline, yytext());}
    ","     {return new Symbol(ParserSym.COMA, yycolumn, yyline, yytext());}
    "="     {return new Symbol(ParserSym.IGUAL, yycolumn, yyline, yytext());}
    "->"    {return new Symbol(ParserSym.ASIGNACION2, yycolumn, yyline, yytext());}
//Palabras reservadas
    "end"       {return new Symbol(ParserSym.R_END, yycolumn, yyline, yytext());}
    "program"   {return new Symbol(ParserSym.R_PROGRAM, yycolumn, yyline, yytext());}
    
    "var"       { return new Symbol(ParserSym.R_VAR, yycolumn, yyline, yytext());}
    "char[]"    {return new Symbol(ParserSym.R_CHAR, yycolumn, yyline, yytext());}
    "double"    {return new Symbol(ParserSym.R_DOUBLE, yycolumn, yyline, yytext());}
    "arr"       {return new Symbol(ParserSym.R_ARR, yycolumn, yyline, yytext());}

    "sum"       {return new Symbol(ParserSym.R_SUM, yycolumn, yyline, yytext());}
    "res"       {return new Symbol(ParserSym.R_RES, yycolumn, yyline, yytext());}
    "mul"       {return new Symbol(ParserSym.R_MUL, yycolumn, yyline, yytext());}
    "div"       {return new Symbol(ParserSym.R_DIV, yycolumn, yyline, yytext());}
    "mod"       {return new Symbol(ParserSym.R_MOD, yycolumn, yyline, yytext());}

    "media"     {return new Symbol(ParserSym.R_MEDIA, yycolumn, yyline, yytext());}
    "mediana"   {return new Symbol(ParserSym.R_MEDIANA, yycolumn, yyline, yytext());}
    "moda"      {return new Symbol(ParserSym.R_MODA, yycolumn, yyline, yytext());}
    "varianza"  {return new Symbol(ParserSym.R_VARIANZA, yycolumn, yyline, yytext());}
    "max"       {return new Symbol(ParserSym.R_MAX, yycolumn, yyline, yytext());}
    "min"       {return new Symbol(ParserSym.R_MIN, yycolumn, yyline, yytext());}

    "console"   {return new Symbol(ParserSym.R_CONSOLE, yycolumn, yyline, yytext());}
    "print"     {return new Symbol(ParserSym.R_PRINT, yycolumn, yyline, yytext());}
    "column"    {return new Symbol(ParserSym.R_COLUMN,yycolumn, yyline, yytext());}

    "graphbar"  {return new Symbol(ParserSym.R_BAR,yycolumn, yyline, yytext());}
    "graphpie"  {return new Symbol(ParserSym.R_PIE,yycolumn, yyline, yytext());}
    "graphLine" {return new Symbol(ParserSym.R_LINE,yycolumn, yyline, yytext());}
    "histogram" {return new Symbol(ParserSym.R_HISTOGRAM,yycolumn, yyline, yytext());}

    "titulo"    {return new Symbol(ParserSym.R_TITULO,yycolumn, yyline, yytext());}
    "ejeX"      {return new Symbol(ParserSym.R_EJEX,yycolumn, yyline, yytext());}
    "ejey"      {return new Symbol(ParserSym.R_EJEY,yycolumn, yyline, yytext());}
    "titulox"   {return new Symbol(ParserSym.R_TITULOX,yycolumn, yyline, yytext());}
    "tituloy"   {return new Symbol(ParserSym.R_TITULOY,yycolumn, yyline, yytext());}
    "exec"      {return new Symbol(ParserSym.R_EXEC,yycolumn, yyline, yytext());}
    "values"    {return new Symbol(ParserSym.R_VALUES,yycolumn, yyline, yytext());}
    "label"     {return new Symbol(ParserSym.R_LABEL,yycolumn, yyline, yytext());}


//expresiones regulares


    {ID}          { return new Symbol(ParserSym.ID, yycolumn, yyline, yytext()); }
    {DECIMAL}     { return new Symbol(ParserSym.DECIMAL, yycolumn, yyline, yytext()); }
    {CADENA}      { return new Symbol(ParserSym.CADENA, yycolumn, yyline, yytext()); }
    {IDARR}       { return new Symbol(ParserSym.IDARR, yycolumn, yyline, yytext()); }
    {COMENTARIO}  { }
    {COMENTARIOM} { }
   
} 


// comentario {}

//------> Ingorados 
<YYINITIAL> [ \t\r\n\f]     {/* Espacios en blanco se ignoran */}

//------> Errores Léxicos 
[^] { System.out.println("Error Lexico: " + yytext() + " | Fila:" + yyline + " | Columna: " + yycolumn); }
