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
public class Arbol {
        private int fila,columna;
    	public String lex;
	public ArrayList<Arbol> hijos;

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setLex(String lex) {
        this.lex = lex;
    }

    public void setHijos(ArrayList<Arbol> hijos) {
        this.hijos = hijos;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getLex() {
        return lex;
    }

    public ArrayList<Arbol> getHijos() {
        return hijos;
    }

    public Arbol(String lex, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.lex = lex;
        this.hijos = new ArrayList<>();
    }

	
	public void addHijo(Arbol hijo) {
		this.hijos.add(hijo);
        }
        
	public void printArbol(Arbol raiz) {
            if(raiz == null){
                return;
            }
		for(Arbol hijo : raiz.hijos) {
			printArbol(hijo);
		}
		System.out.println(raiz.lex);
	}
}
