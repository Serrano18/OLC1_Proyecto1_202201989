/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author evams
 */
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
