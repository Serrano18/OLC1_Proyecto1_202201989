/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.StringReader;
import java.util.HashMap;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        analizadores("src/Analizadores/", "lexema.jflex", "parser.cup");

        String entrada = """
                         PROGRAM
                         var : double :: valio4 <- 25.5 end ; <!iGNORAME
                         
                         SFKSLD
                         FSMGÑKLS}
                         SGKS}
                         !>
                         var : double :: valio5 <- 30 end ;
                         var : char[] :: nombre <- "Maria" end ;
                         arr:double :: @nfk<- [1,2,3,4] end;
                         arr:double::@darray<-@numero end;
                         arr:char[]:: @jfk <- ["mando","mio"] end;
                         arr:double::@darray <- [1, 2, 3, 4, 5] end; ! Arreglo de tipo double
                          arr:char[]::@carray <- ["12", "2", "3"] end; ! Arreglo de tipo string
                          arr:double::@carray <- [numero, copia, 7] end; ! Puede usar variables
                          ! Operaciones
                          var:double:: suma <- SUM(5, 2) end;
                          var:double:: resta <- RES(3, 2) end;
                          var:double:: multi <- MUL(4, numero) end; ! Funciona con variables
                          var:double:: division <- DIV(1, variable) end;
                          var:double:: modulo <- MOD(5, 4) end;
                          ! Operaciones anidadas
                          var:double:: suma <- MUL( SUM(7,3) , RES(7, DIV(25,5)) ) end;
                          arr:double::@darray <- [ SUM(7,3), DIV(25,5)] end; ! Arreglo con funciones
                          ! se pueden ingresar el arreglo directamente o por variable
                          var:double:: med1 <- Media([1, 2, SUM(3, b), 4, a]) end;
                          var:double:: med2 <- Mediana( @arreglo ) end;
                          arr:double::@arreglo <- [Media(@data), Mediana(@data)] end;
                          ! Tambien se pueden utilizar en operaciones aritmeticas
                          var:double:: mitad <- DIV( SUM(Max(@data), Min(@data) ), 2) end;
                         
                          var:double::numero<-15 end;
                          console::print="hola",numero,15,"adios" end;
                          console::print=1,2,SUM(3,5),Media(@arreglo)end;
                            arr:double::@darray<-[1,2,3,4,5]end;
                            var:char[]:: mensa<-"Enteros"end;
                            console::column=“Enteros”-> @darray end;
                            console ::column= mensa -> [1,2,3,4,5] end;                         
                          graphBar(
                                titulo ::char[] = “Estudiantes” end;
                                ejeX ::char[] = [“1 Parcial”, “2 parcial”, “Final”] end;
                                ejeY ::double = [50, 30, 70] end;
                                tituloX ::char[] = “Actividades” end;
                                tituloY :: char[] = “Notas” end;
                                EXEC graphBar end;
                            ) end;
                            ! Ejemplo
                            graphPie(
                                label::char[] = [“Uno”, “Dos”, “Tres”] end;
                                values::double = [50, 30, 20] end;
                                titulo::char[] = “Ejemplo Gráfica de Pie” end;
                                EXEC graphPie end;
                            ) end;
                            graphLine(
                                titulo::char[]=“GráficadeLínea”end;
                                ejeX::char[]=[“1Parcial”,“2parcial”,“Final”]end;
                                ejeY::double=[50,30,70]end;
                                tituloX::char[]=“Actividades”end;
                                tituloY::char[]=“Notas”end;
                                EXEC graphLine end;
                            )end;
                            Histogram(
                                 titulo::char[] = "Analisis de Arreglo" end;
                                 values::double = [2,2,2,5,5,7,8] end;
                                 EXEC Histogram end;
                                 ) end;
                         END PROGRAM
                         """;

        analizar(entrada);
    }

    public static void analizadores(String ruta, String jflexFile, String cupFile) {
        try {
            String opcionesJflex[] = {ruta + jflexFile, "-d", ruta};
            jflex.Main.generate(opcionesJflex);
            System.out.println("Inicio cup");
            String opcionesCup[] = {"-destdir", ruta, "-parser", "Parser", ruta + cupFile};
            java_cup.Main.main(opcionesCup);

        } catch (Exception e) {
            System.out.println("No se ha podido generar los analizadores");
            System.out.println(e);
        }
    }

    // Realizar Analisis
    public static void analizar(String entrada) {
        try {
            Analizadores.analizadorLexico lexer = new Analizadores.analizadorLexico(new StringReader(entrada)); //para llamar impoprt o llamamo asi
            Analizadores.Parser parser = new Analizadores.Parser(lexer);
            parser.parse();
        } catch (Exception e) {
            System.out.println("Error fatal en compilación de entrada.");
            System.out.println(e);
        }
    }
}
