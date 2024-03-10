/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import Analizadores.Arbol;
import java.util.HashMap;
import clases.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Interprete {
    
    private final HashMap<String,Valores> hash = new HashMap<>();
    private final ArrayList<Instruccion> instrucciones;
    private List<String> aritmeticas = Arrays.asList("SUM","RES","MUL","DIV","MOD");
    private List<String> estadisticas = Arrays.asList("MEDIA","MEDIANA","VARIANZA","MAX","MODA","MIN");
    private String consola;
    
    public Interprete(Arbol arbol){
        this.instrucciones = arbol.getInstruccion(arbol);
        this.consola = "";
    }

    public String getConsola() {
        return consola;
    }
    
    public void run()  {
        
            while (!this.instrucciones.isEmpty()) {
                String instru = this.instrucciones.remove(0).getLexema();
                switch (instru) {
                    case ("var"):
                        this.instrucciones.remove(0);//:
                        this.declaracionVariable();
                        break;
                    case ("arr") :
                         this.instrucciones.remove(0);//:
                         //this.declaracionarreglo();
                    case ("CONSOLE"):
                        this.instrucciones.remove(0);//::
                        //this.imprimir();
                        break;
                        
                    case ("gbar"):
                       
                         
                    case ("gline"):
                        
                    case ("gpie"):
                        
                    case ("ghisto"):
                        
                    default:
                        throw new AssertionError();
                }
            
            
        }
            
            
   
            
        
        
          
    }
    
    public void declaracionVariable() {
        String tipo = this.instrucciones.remove(0).getLexema(); //obtiene el tipo de dato double o char
        this.instrucciones.remove(0); // :: 
        Instruccion variable = this.instrucciones.remove(0); //obtiene el id
        this.instrucciones.remove(0);//<-
        switch (tipo) {
            case ("double"):
                     float numero = this.getvalorfloat();
                     this.hash.put(variable.getLexema(), new Valores(variable.getLinea(), variable.getColumna(), variable.getLexema(),null, numero, null, null,"variable double"));
                     break;
            case ("char[]"):
                String valor =this.instrucciones.remove(0).getLexema();
                //AQUI O ES STRIND O ID
                    if (this.hash.containsKey(valor)){
                        valor = this.hash.get(valor).getValorC();
                    }
                    this.hash.put(variable.getLexema(), new Valores(variable.getLinea(), variable.getColumna(), variable.getLexema(),valor, (float) 454333333433.333, null, null,"variable char"));
                     
                     
            default:
                throw new AssertionError();
        }
            
    }
    public void declaracionarreglo(){
        String tipo = this.instrucciones.remove(0).getLexema();//Double o char[]
        this.instrucciones.remove(0); // elimnamos :: 
        Instruccion variable = this.instrucciones.remove(0); //obtenemosidarr
        this.instrucciones.remove(0); // eliminamos <-
        this.instrucciones.remove(0); // eliminamos [
        switch(tipo){
            case("double"):
                ArrayList<Float> arreglofl = new ArrayList<>();
                while(true){
                    arreglofl.add(this.getvalorfloat());
                    if(this.instrucciones.remove(0).getLexema() == "]"){
                        break;
                    }
                }
                this.hash.put(variable.getLexema(), new Valores(variable.getLinea(), variable.getColumna(), variable.getLexema() ,null,(float) 454333333433.333,null, arreglofl,"Arreglo double"));
                break;
            case ("char[]"):
               ArrayList<String> arreglostr = new ArrayList<>();
                while(true){
                    String valor = this.instrucciones.remove(0).getLexema();
                    //Str,id
                    if(this.hash.containsKey(valor)){
                    }
                            
                            
                }
        }
    }
    
    private float getvalorfloat(){
        String valor = this.instrucciones.remove(0).getLexema();
        //numero o funcion o id 
        float numero = 0;
        if(aritmeticas.contains(valor)){
            numero = this.funcionesaritmeticas(valor);
        }else if (estadisticas.contains(valor)){
            numero = this.funcionesestadisticas(valor);
        }else if(this.convertidoafloat(valor)){
            numero = Float.parseFloat(valor);
        
        }else if (this.hash.containsKey(valor)){
            numero = this.hash.get(valor).getValord();
        }
        return numero;
    }
    
   
    public boolean convertidoafloat(String entrada){
        try{
            Float.parseFloat(entrada);
            return true;
        
        }catch(NumberFormatException e){
            return false;
        }
    
    }
    public float funcionesaritmeticas(String operacion){
        float num1 = 0, num2=0;
        this.instrucciones.remove(0);//elimina (
        num1=this.getvalorfloat(); //obtengo el primer numero
        this.instrucciones.remove(0);//elimina ,
        num2=this.getvalorfloat(); //obtengo segundo numero
        this.instrucciones.remove(0);// elimina )
        
        //dependiendo de la operacion vamos a retornar el valor de la operacion por si hay que imprimir algo
        return switch(operacion){
            case("SUM")-> num1+num2;
            case("RES")-> num1-num2;
            case("DIV")-> num1/num2;
            case("MUL")-> num1*num2;
            case("MOD")-> num1%num2;    
            
            default ->-1;
        };
    }
    
    //En esta parte van las funciones estadisticas 
    //calcular media
    public float media(ArrayList<Float> arreglo){
        //vamos a calcular de una la media
        
        float suma = 0;
        for (float numero : arreglo){
            suma += suma;
        }
        return suma/arreglo.size();
    }
    //calcular moda
    public float moda(ArrayList<Float> arreglo){
        float moda = 0;
        int maximo = 0;
        for(int i =0; i<arreglo.size();i++){
            int contador = 0;
            for(int j = 0;j<arreglo.size();j++){
                if(arreglo.get(j)== arreglo.get(i)){
                    contador ++;
                }
            }
            if (contador>maximo){
                moda = arreglo.get(i);
                maximo = contador;
            }
        }
        return moda;
    }
    public float mediana(ArrayList<Float> arreglo){
        //par (a/1)+((n/2)+1)/2
        //impar n/2
        int a = arreglo.size();
        if(a%2==0){
            return(arreglo.get(a/2)+arreglo.get(a/2-1))/2;
        }else{
            return arreglo.get(a/2);
        }
    }
    public float varianza(ArrayList<Float> arreglo){
        float media = this.media(arreglo);
        float suma = 0;
        for(float numero : arreglo){
            suma += Math.pow(numero - media,2);
        }
        return suma/arreglo.size();
    }
    public float min(ArrayList<Float> arreglo){
        float minimo = arreglo.get(0);
        for(float numero:arreglo){
            if(numero<minimo){
                minimo = numero;
            }
        }
        return minimo;
    }
    public float max(ArrayList<Float> arreglo){
        
        //lo mismo que minimo pero cambia a > 
        float maximo = arreglo.get(0);
        for(float numero:arreglo){
            if(numero>maximo){
                maximo = numero;
            }
        }
        return maximo;
    }

    public float funcionesestadisticas(String operacion){
        boolean seleccionado = false;
        ArrayList<Float> arreglodob = new ArrayList<>();
        float numero=0;
        this.instrucciones.remove(0);//(
        String valor = this.instrucciones.remove(0).getLexema();
        //id o numeros por lo tanto es necesario saber si se puede parsear
            if(valor!="["){
                seleccionado = true;
            }
            if (seleccionado){
                if(this.hash.containsKey((valor))){
                    arreglodob = this.hash.get(valor).getArrayD();
                }else{
                    arreglodob = null;
                }
            }else{
                while(true){
                    arreglodob.add(this.getvalorfloat());
                    if(this.instrucciones.remove(0).getLexema() == "]"){
                        break;
                    }
                }
            }
            if(arreglodob == null){
                return -1;
             }
            switch (operacion) {
            case ("MEDIANA"):
                numero = this.mediana(arreglodob);
                break;
            case ("MODA"):
                numero = this.moda(arreglodob);
                break;
            case ("MIN"):
                numero = this.min(arreglodob);
                break;
            case ("VARIANZA"):
                numero = this.varianza(arreglodob);
                break;
            case ("MAX"):
                numero = this.max(arreglodob);
                break;
            case ("MEDIA"):
                numero = this.media(arreglodob);
                break;
            }
            return numero;
        }
        
        
        
    
    }
    
    
    
    

