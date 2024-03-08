/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author evams
 */
public class Token {
    private int id;
    private int fila;
    private int columna;
    private String token;
    private String tipo;

    public Token(int id, int fila, int columna, String token, String tipo=null) {
        this.id = id;
        this.fila = fila;
        this.columna = columna;
        this.token = token;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Token{" + "id=" + id + ", fila=" + fila + ", columna=" + columna + ", token=" + token + ", tipo=" + tipo + '}';
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

    public void setToken(String token) {
        this.token = token;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
          
}
