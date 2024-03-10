/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author evams
 */
public class Valores {
    private int id;
    private int fila;
    private int columna;
    private String nombre;
    private String valorC;
    private float valord;
    private  ArrayList<String> arrayS;
    private ArrayList<Float> arrayD;
    private String tipo;

    public Valores(int fila, int columna, String nombre, String valorC, float valord, ArrayList<String> arrayS, ArrayList<Float> arrayD,String tipo) {
        this.fila = fila;
        this.columna = columna;
        this.nombre = nombre;
        this.valorC = valorC;
        this.valord = valord;
        this.arrayS = arrayS;
        this.arrayD = arrayD;
        this.tipo = tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValorC(String valorC) {
        this.valorC = valorC;
    }

    public void setValord(float valord) {
        this.valord = valord;
    }

    public void setArrayS(ArrayList<String> arrayS) {
        this.arrayS = arrayS;
    }

    public void setArrayD(ArrayList<Float> arrayD) {
        this.arrayD = arrayD;
    }

    public int getId() {
        return id;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getNombre() {
        return nombre;
    }

    public String getValorC() {
        return valorC;
    }

    public float getValord() {
        return valord;
    }

    public ArrayList<String> getArrayS() {
        return arrayS;
    }

    public ArrayList<Float> getArrayD() {
        return arrayD;
    }
    
    
    
    
}
