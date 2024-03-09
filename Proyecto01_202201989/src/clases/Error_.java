/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author evams
 */
public class Error_ {
    private int id;
    private int fila;
    private int columna;
    private String Descripcion;
    private String Tipo;

    public Error_(int id, int fila, int columna, String Descripcion, String Tipo) {
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.Descripcion = Descripcion;
        this.Tipo = Tipo;
    }

 

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getTipo() {
        return Tipo;
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

    public String getDescripcion() {
        return Descripcion;
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

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
    
}
