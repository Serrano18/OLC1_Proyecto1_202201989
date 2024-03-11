/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class reportes {
    //codigo complementado con chatsito y mio 
   public static void generarReporteTokens() {
        try (FileWriter fw = new FileWriter("REPORTE_TOKENS.html")) {
            fw.write("<html><head><title>Reporte de Tokens</title>");
            fw.write("<style>");
            fw.write("table {");
            fw.write("    width: 50%;"); // Centrar la tabla
            fw.write("    margin: 0 auto;"); // Centrar la tabla
            fw.write("    border-collapse: collapse;");
            fw.write("}");
            fw.write("th, td {");
            fw.write("    border: 1px solid black;");
            fw.write("    padding: 8px;");
            fw.write("    text-align: left;");
            fw.write("}");
            fw.write("th {");
            fw.write("    background-color: #f2f2f2;"); // Color de fondo para las celdas de encabezado
            fw.write("}");
            fw.write("</style>");
            fw.write("</head><body>");
            fw.write("<h1 style=\"text-align:center;\">Tabla de Tokens</h1>");
            fw.write("<table>");
            fw.write("<tr><th>#</th><th>Lexema</th><th>Tipo</th><th>Línea</th><th>Columna</th></tr>");
            for (Token token : arreglos_publicos.tokens) {
             fw.write("<tr>");
                fw.write("<td>" + token.getId() + "</td>");
                fw.write("<td>" + token.getToken() + "</td>");
                fw.write("<td>" + token.getTipo() + "</td>");
                fw.write("<td>" + token.getFila() + "</td>");
                fw.write("<td>" + token.getColumna() + "</td>");
                fw.write("</tr>");
            }
            fw.write("</table>");
            fw.write("</body></html>");
            
            
            // Abrir el archivo generado
            File file = new File("REPORTE_TOKENS.html");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
   
   public static void generarReporteErrores() {
        try (FileWriter fw = new FileWriter("REPORTE_ERRORES.html")) {
            fw.write("<html><head><title>Reporte de Errores</title>");
            fw.write("<style>");
            fw.write("table {");
            fw.write("    width: 50%;"); // Centrar la tabla
            fw.write("    margin: 0 auto;"); // Centrar la tabla
            fw.write("    border-collapse: collapse;");
            fw.write("}");
            fw.write("th, td {");
            fw.write("    border: 1px solid black;");
            fw.write("    padding: 8px;");
            fw.write("    text-align: left;");
            fw.write("}");
            fw.write("th {");
            fw.write("    background-color: #f2f2f2;"); // Color de fondo para las celdas de encabezado
            fw.write("}");
            fw.write("</style>");
            fw.write("</head><body>");
            fw.write("<h1 style=\"text-align:center;\">Tabla de Errores</h1>");
            fw.write("<table>");
            fw.write("<tr><th>ID</th><th>Tipo</th><th>Descripcion</th><th>Línea</th><th>Columna</th></tr>");
            for (Error_ er : arreglos_publicos.errores) {
             fw.write("<tr>");
                fw.write("<td>" + er.getId() + "</td>");
                fw.write("<td>" + er.getTipo()+ "</td>");
                fw.write("<td>" + er.getDescripcion() + "</td>");
                fw.write("<td>" + er.getFila() + "</td>");
                fw.write("<td>" + er.getColumna() + "</td>");
                fw.write("</tr>");
            }
            fw.write("</table>");
            fw.write("</body></html>");
            
            File file = new File("REPORTE_ERRORES.html");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
   public static void generarReporteSimbolos() {
        try (FileWriter fw = new FileWriter("REPORTE DE SIMBOLOS.html")) {
            fw.write("<html><head><title>Tabla de Simbolos</title>");
            fw.write("<style>");
            fw.write("table {");
            fw.write("    width: 70%;"); // Ajustar el ancho de la tabla según tus necesidades
            fw.write("    margin: 0 auto;");
            fw.write("    border-collapse: collapse;");
            fw.write("}");
            fw.write("th, td {");
            fw.write("    border: 1px solid black;");
            fw.write("    padding: 8px;");
            fw.write("    text-align: left;");
            fw.write("}");
            fw.write("th {");
            fw.write("    background-color: #f2f2f2;");
            fw.write("}");
            fw.write("</style>");
            fw.write("</head><body>");
            fw.write("<h1 style=\"text-align:center;\">Tabla de Simbolos</h1>");
            fw.write("<table>");
            fw.write("<tr><th>ID</th><th>ID</th><th>Nombre</th><th>Tipo</th><th>Línea</th><th>Columna</th></tr>");
            int contador = 0;
            for (Map.Entry<String, Valores> entry : Interprete.hash.entrySet()) {
                Valores valor = entry.getValue();
                fw.write("<tr>");
                fw.write("<td>" + contador + "</td>");
                fw.write("<td>" + valor.getNombre() + "</td>");
                fw.write("<td>" + valor.getTipo() + "</td>");
                fw.write("<td>" + valor.getValorParaTabla() + "</td>");
              //  fw.write("<td>" + valor.getFila() + "</td>");
               // fw.write("<td>" + valor.getColumna() + "</td>");
                fw.write("</tr>");
                contador ++;
            }
            fw.write("</table>");
            fw.write("</body></html>");

            // Abrir el archivo generado
            File file = new File("REPORTE DE SIMBOLOS.html");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
