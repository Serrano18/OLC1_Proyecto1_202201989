/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;
import java.util.List;

public class arreglos_publicos {
    public static ArrayList<Token> tokens = new ArrayList<Token>();
    public static int contador = 0;
    public static ArrayList<Error_> errores = new ArrayList<Error_> ();
    public static int contadorE = 0;
    public static ArrayList<Simbolo> simbolos = new ArrayList<Simbolo> ();
    public static int contadorS = 0;
     public static ArrayList<String> imagePaths = new ArrayList<>();
     
   

    public static List<String> getImagePaths() {
        return imagePaths;
    }
}
