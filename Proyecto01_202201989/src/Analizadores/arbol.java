/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

/**
 *
 * @author evams
 */
import java.util.ArrayList;
//Este codigo viene de https://github.com/Adiel13/compi_ex4/blob/main/ex4/src/main/java/compi1/ex4/arbol.java
//y modificaciones hechas por mi
public class arbol {
    	public String lex;
	public ArrayList<arbol> hijos;
	
	public arbol(String lex) {
		this.lex = lex;
		this.hijos = new ArrayList();
	}
	
	public void addHijo(arbol hijo) {
		this.hijos.add(hijo);
        }
        
	public void printArbol(arbol raiz) {
		for(arbol hijo : raiz.hijos) {
			printArbol(hijo);
		}
		System.out.println(raiz.lex);
	}
}
