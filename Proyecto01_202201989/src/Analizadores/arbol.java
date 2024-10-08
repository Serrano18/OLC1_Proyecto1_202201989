/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

/**
 *
 * @author evams
 */
import clases.Instruccion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//Este codigo viene de https://github.com/Adiel13/compi_ex4/blob/main/ex4/src/main/java/compi1/ex4/arbol.java
//y modificaciones hechas por mi
public class Arbol {
        private int fila,columna;
    	public String lex;
	public ArrayList<Arbol> hijos;
    private List<String> Ignorados = Arrays.asList("ini","List Instruction","Instruccion","IntruccionDV","IntruccionDA","IntruccionIE",
             "IntruccionIA","IntruccionG","arreglo","Idarreglo","Lista-expre","FARITMETICA","FESTADISTICA",
             "ARITMETICAS","ARITMETICAR","ARITMETICAD","ARITMETICAM","ARITMETICAMO","EstadisticaME","EstadisticaMED","EstadisticaV","EstadisticaMA",
             "EstadisticaMO","EstadisticaMI","tipoDato","exp","expre","ImprimirE","ImprimirA","Graficar","ParametrosG","PTITULO",
             "PVALUES","PLABEL","PEJEX","PEJEY","PTILULOX","PTITULOY","gbar","gline","gpie","ghisto","ParametroC","ParametroAC","ParametroAD");
        
    private ArrayList<Instruccion> instruccion=new ArrayList<>();
    
        public Arbol(String lex, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.lex = lex;
        this.hijos = new ArrayList<>();
    }


    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }


	public void addHijo(Arbol hijo) {
		this.hijos.add(hijo);
        }

	public void printArbol(Arbol raiz) {
		for(Arbol hijo : raiz.hijos) {
			printArbol(hijo);
		}
		System.out.println(raiz.lex);
	}

        public void guardarArbol (Arbol guardado){
            if(guardado==null){
                return;
            }else if(guardado.lex.equals("ERR")){
                return;
            }
            for (Arbol hijo : guardado.hijos){
                this.guardarArbol(hijo);
            }
            if (!Ignorados.contains(guardado.lex)){
                instruccion.add(new Instruccion(guardado.fila,guardado.columna,guardado.lex));
                System.out.println(guardado.lex);
            }

        }
        public ArrayList<Instruccion> getInstruccion(Arbol arbol) {
            this.instruccion = new ArrayList<Instruccion>();
            guardarArbol(arbol);
            return this.instruccion;
        }
}

