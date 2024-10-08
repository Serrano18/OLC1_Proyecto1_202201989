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
import java.util.Map;
import java.util.concurrent.CompletableFuture;
//Parte de todo este codigo fue guiado de clases de lfp y https://www.youtube.com/playlist?list=PLx5H_6IYW07qhMksk4Q-JQl75FcEI90_P
public class Interprete {
    
    public static HashMap<String,Valores> hash = new HashMap<>();
    private static ArrayList<Instruccion> instrucciones;
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
                System.out.println(instru);
                switch (instru) {
                    case ("var"):
                        this.instrucciones.remove(0);//:
                        this.declaracionVariable();
                        break;
                    case ("arr") :
                         this.instrucciones.remove(0);//:
                         this.declaracionarreglo();
                         break;
                    case ("CONSOLE"):
                        this.instrucciones.remove(0);//::
                        this.imprimir();
                        break;
                        
                    case ("BAR"): //bar y line tienen los mismo parametros
                        this.instrucciones.remove(0); //(
                        this.graficarbaryline("BAR");
                        break;
                    case ("LINE"):
                            this.instrucciones.remove(0); //(
                            this.graficarbaryline("LINE");
                            break;
                    case ("PIE"):
                        this.instrucciones.remove(0); //(
                        this.graficarbaryline("PIE");
                        break;
                        
                    case ("HISTOGRAM"):
                        this.instrucciones.remove(0); //(
                        this.graficarbaryline("HISTOGRAM");
                        break;
                }
            
            
        }  
    }
    
    public void graficarbaryline(String tipo){

        String titulo = "Grafica";
        String titulox = "Graficax";
        String tituloy = "Graficay";
        ArrayList<Float> ejey = new ArrayList<>();
        ArrayList<String> ejex = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        ArrayList<Float> values = new ArrayList<>();
        while(true){
            String datosg = this.instrucciones.remove(0).getLexema();//Titulo, titulox, tituloy, ejex y ejey, label, value, exec
            //Verificamos los que sean igual titulo titulox y tituloy reciben los mismos datos
            //eje y recibe array float
            //eje x recibe array string
            if(datosg.equals("TITULO") || datosg.equals("TITULOX") || datosg.equals("TITULOY")){

                String temporal = "";
                this.instrucciones.remove(0);//::
                this.instrucciones.remove(0);//char[]
                this.instrucciones.remove(0);//=
                if(this.hash.containsKey(this.instrucciones.get(0).getLexema())){
                    if(this.hash.get(this.instrucciones.get(0).getLexema()).getValorC()!= null){
                        temporal = this.hash.get(this.instrucciones.remove(0).getLexema()).getValorC();
                    }else{
                        this.instrucciones.remove(0);
                    }
                }else{
                    temporal=this.instrucciones.remove(0).getLexema();//String
                }
                this.instrucciones.remove(0); // END
               this.instrucciones.remove(0); // ;
                switch (datosg){
                    case("TITULO"):
                        titulo = temporal;
                        break;
                    case("TITULOX"):
                        titulox = temporal;
                        break;
                    case("TITULOY"):
                        tituloy = temporal;
                        break;
                }
                System.out.println(temporal);
            }else if(datosg.equals("EJEY")){
                
                ejey = new ArrayList<>();
                this.instrucciones.remove(0);//::
                this.instrucciones.remove(0);//double
                this.instrucciones.remove(0);//=
                //o viene [ o idarreglo
                datosg = this.instrucciones.remove(0).getLexema();
                if(datosg == "["){
                    while (true){
                         ejey.add(this.getvalorfloat());
                        if(this.instrucciones.remove(0).getLexema()== "]"){ //,
                            break;
                        }
                    }
                }else{
                    //idarr
                    if(this.hash.containsKey(datosg)){
                        if(this.hash.get(datosg).getArrayD() != null){
                            ejey = this.hash.get(datosg).getArrayD();
                        } else{
                            ejey = null;
                        }
                    } else if (this.convertidoafloat(datosg)){
                        ejey.add(Float.parseFloat(datosg));
                    }
                }
                this.instrucciones.remove(0); // END
                this.instrucciones.remove(0); // ;
            }else if(datosg.equals("EJEX")){
                 //string
                 
                 ejex = new ArrayList<>();
                 this.instrucciones.remove(0);//::
                 this.instrucciones.remove(0);//char[]
                 this.instrucciones.remove(0);//=
                 datosg = this.instrucciones.remove(0).getLexema();
                 if(datosg == "["){ //o vienen String o vienen id
                     while (true){
                        datosg = this.instrucciones.remove(0).getLexema();
                         if(this.hash.containsKey(datosg)){
                            if(this.hash.get(datosg).getValorC() != null){
                                ejex.add(this.hash.get(datosg).getValorC());
                            } else{
                                ejex.add(datosg+"No es String");
                            }
                         }else if (datosg.endsWith("”") || datosg.endsWith("\"")){
                            ejex.add(datosg);
                         }else{
                            ejex.add(datosg+" no es id, ni string");
                         }
                         if(this.instrucciones.remove(0).getLexema() == "]"){ //,
                             break;
                         }
                     }
                 }else{
                     //idarr
                     if(this.hash.containsKey(datosg)){
                         if(this.hash.get(datosg).getArrayS() != null){
                             ejex = this.hash.get(datosg).getArrayS();
                         } else{
                             ejex = null;
                         }
                     } else{
                        ejex.add(datosg);
                     }
                 }
                 this.instrucciones.remove(0); // END
                 this.instrucciones.remove(0); // ;
            }else if(datosg.equals("VALUES")){
                
                values = new ArrayList<>();
                this.instrucciones.remove(0);//::
                this.instrucciones.remove(0);//double
                this.instrucciones.remove(0);//=
                //o viene [ o idarreglo
                datosg = this.instrucciones.remove(0).getLexema();
                if(datosg == "["){
                    while (true){
                          values.add(this.getvalorfloat());
                        if(this.instrucciones.remove(0).getLexema()== "]"){ //,
                            break;
                        }
                    }
                }else{
                    //idarr
                    if(this.hash.containsKey(datosg)){
                        if(this.hash.get(datosg).getArrayD() != null){
                            values = this.hash.get(datosg).getArrayD();
                        } else{
                            values = null;
                        }
                    } else if (this.convertidoafloat(datosg)){
                        values.add(Float.parseFloat(datosg));
                    }
                }
                this.instrucciones.remove(0); // END
                this.instrucciones.remove(0); // ;
            }else if(datosg.equals("LABEL")){
                
                 //string
                 labels = new ArrayList<>();
                 this.instrucciones.remove(0);//::
                 this.instrucciones.remove(0);//char[]
                 this.instrucciones.remove(0);//=
                 datosg = this.instrucciones.remove(0).getLexema();
                 if(datosg == "["){ //o vienen String o vienen id
                     while (true){
                        datosg = this.instrucciones.remove(0).getLexema();
                         if(this.hash.containsKey(datosg)){
                            if(this.hash.get(datosg).getValorC() != null){
                                labels.add(this.hash.get(datosg).getValorC());
                            } else{
                                labels.add(datosg+"No es String");
                            }
                         }else if (datosg.endsWith("”") || datosg.endsWith("\"")){
                            labels.add(datosg);
                         }else{
                            labels.add(datosg+" no es id, ni string");
                         }
                         if(this.instrucciones.remove(0).getLexema() == "]"){ //,
                             break;
                         }
                     }
                 }else{
                     //idarr
                     if(this.hash.containsKey(datosg)){
                         if(this.hash.get(datosg).getArrayS() != null){
                             ejex = this.hash.get(datosg).getArrayS();
                         } else{
                             ejex = null;
                         }
                     } else{
                        ejex.add(datosg);
                     }
                 }
                 this.instrucciones.remove(0); // END
                 this.instrucciones.remove(0); // ;
            }else if(datosg.equals("EXEC")){
                //si viene lo grafico
                if (tipo.equals("LINE")) {
                    //GRAFIQUE LINE
                    Graficas.graficarLinea(titulo, titulox, tituloy, ejex, ejey);
                } else if (tipo.equals("BAR")) {
                   //grafique barra
                   Graficas.graficarBarra(titulo, ejex, ejey, titulox, tituloy);
                } else if (tipo.equals("PIE")) {
                    Graficas.graficarPie(titulo, values, labels);
                    //grafique PIE
                } else if (tipo.equals("HISTOGRAM")) {
                    //grafique HISTOGRAM
                    Graficas.graficarHistograma(titulo, values);
                    consola += createFrequencyTable(values);
                 }
                break;
            }
        }
            //this.instrucciones.remove(0); // END
            //this.instrucciones.remove(0); // ;
            this.instrucciones.remove(0); // )
            this.instrucciones.remove(0); // END
            this.instrucciones.remove(0); // ;
        
    }
    
    public String createFrequencyTable(ArrayList<Float> numbers) {
        // Calcular la frecuencia de cada número
        Map<Float, Integer> frequencyMap = new HashMap<>();
        for (float number : numbers) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }
        // Crear el StringBuilder para la tabla
        StringBuilder table = new StringBuilder();
        // Agregar el título de la tabla
        table.append("\nAnálisis de Arreglo\n");
        // Agregar los nombres de las columnas
        table.append(String.format("%-10s %-10s %-20s %-20s\n", "Valor", "F", "FA", "FR"));
        // Calcular los totales
        int totalFrequency = 0;
        int totalAbsoluteFrequency = 0;
        float totalRelativeFrequency = 0;
        // Recorrer el HashMap y agregar cada fila a la tabla
        for (Map.Entry<Float, Integer> entry : frequencyMap.entrySet()) {
            float value = entry.getKey();
            int frequency = entry.getValue();
            totalAbsoluteFrequency += frequency;
            float relativeFrequency = (float) frequency / numbers.size();
            table.append(String.format("%-10.2f %-10d %-20d %-20.2f\n", value, frequency, totalAbsoluteFrequency, relativeFrequency));
            totalFrequency += frequency;
            totalRelativeFrequency += relativeFrequency;
        }
        // Agregar los totales a la tabla
        table.append(String.format("%-10s %-10d %-20d %-20.2f\n", "Total", totalFrequency, totalAbsoluteFrequency, totalRelativeFrequency));
        return table.toString();
    }
   
    public void imprimir (){
        //this.printHash();
        String valor = this.instrucciones.remove(0).getLexema();//puede venir un COLUIMN O UN PRINT
        this.instrucciones.remove(0);//Viene un = 
        if(valor.equals("PRINT")){
             consola +=  "\n";
            while(true){
                String imprime = this.instrucciones.remove(0).getLexema();//id cadena o decimal o una funcion
                //si viene un id
                if (this.hash.containsKey(imprime)){
                    //si la variable es double
                    if(this.hash.get(imprime).getTipo().equals("variable double")){
                       consola += this.hash.get(imprime).getValord();
                    }else if (this.hash.get(imprime).getTipo().equals("variable char")){//Si la variable es char
                        consola +=  this.hash.get(imprime).getValorC();
                    }else{
                        consola += " Esta variable no existe";
                    }
                    //por si viene un numero normal
                }else if (this.convertidoafloat(imprime)){
                    consola += imprime;
                //por si viene una funcion aritmetica
                }else if(aritmeticas.contains(imprime)){
                    consola += this.funcionesaritmeticas(imprime);
            //por si viene una estadistica
                }else if(estadisticas.contains(imprime)){
                    consola += this.funcionesestadisticas(imprime);
                }else if(imprime.endsWith("\"")|imprime.endsWith("”") ){
                    consola += imprime;
                }else if (imprime.equals(",")) { //por si viene mas de una expresion 
                    consola += ", ";
                } else if (imprime.equals("END")) { //para finalizar la impresion
                    this.instrucciones.remove(0); // llegamos al ;
                    break;
                } else{
                    consola += "\nNo se reconoce el dato " + "\"" + imprime + "\"";
                }
            }
        }else if (valor.equals("COLUMN")){
            // por si viene imprimir un arreglo
            consola += "\n---------------";
            String col = this.instrucciones.remove(0).getLexema(); 
            this.instrucciones.remove(0); //->
            if(this.hash.containsKey(col)){
                if(this.hash.get(col).getValorC()!= null){
                    consola += "\n" + this.hash.get(col).getValorC();
                }else{
                    consola += "\n"+ "Irreconocido ";
                }
            }else if (col.endsWith("”") || col.endsWith("\"")) {
                consola += "\n" + col;
            } else{
                consola += "\n Ese titulo no existe " + col;
            }
            consola += "\n---------------";
            col = this.instrucciones.remove(0).getLexema(); //Ahora puede venir un id arreglo o  un [
            if(col.equals("[")){
                while(true){
                    col = this.instrucciones.remove(0).getLexema(); 
                    //ahora el arreglo puede ser de id o string
                    if(this.hash.containsKey(col)){
                        //por si viene un char
                        if(this.hash.get(col).getValorC() != null){
                            consola +=  "\n"+this.hash.get(col).getValorC();
                            //por si viene un numero
                        }else if (this.hash.get(col).getValord()!= (float) 454333333433.333){
                            consola += "\n"+this.hash.get(col).getValord();
                            //esta vacio
                        }else {
                            consola += "No hay datos1"+ col;
                        }
                        
                    }else if(convertidoafloat(col)){
                        consola += "\n" + Float.parseFloat(col);
                    }else if(col.equals(",")){
                        continue;
                    }else if(col.equals("]")){
                        break;
                    }else{
                        consola += "\nNo se reconocio "+col;
                    }
                    
                }
            }else{
                if(this.hash.containsKey(col)){
                    if(this.hash.get(col).getArrayD()!=null){
                        for (float numero : this.hash.get(col).getArrayD()){
                            consola +=  "\n" + numero;
                        }
                    }else if(this.hash.get(col).getArrayS()!= null){
                        for (String palabras : this.hash.get(col).getArrayS()){
                            consola += "\n" + palabras;
                        }

                    }else{
                        consola += "No hay datos" + col;
                    }
                }else{
                    consola += "\n  No se reconocen los datos " + col;
                }
            }     
        this.instrucciones.remove(0);//end
        this.instrucciones.remove(0);//;
        consola += "\n";
        }else{
            consola += "\n"+ "No identifico " + valor;
        }           
    }
    //hecho por chatsito
    public void printHash(){
        for (HashMap.Entry<String, Valores> entry : this.hash.entrySet()) {
            String key = entry.getKey();
            Valores value = entry.getValue();
            System.out.println("Clave: " + key + ", Valor: " + value.toString());
        }
    }

    public HashMap<String, Valores> getHash() {
        return hash;
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
                     break;
        }
        this.instrucciones.remove(0);//end
        this.instrucciones.remove(0);//;
            
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
                    if("]".equals(this.instrucciones.remove(0).getLexema())){
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
                        valor=this.hash.get(valor).getValorC();
                    }
                    arreglostr.add(valor);
                    if (this.instrucciones.remove(0).getLexema() == "]"){
                        break;
                    }
                    
                }
                this.hash.put(variable.getLexema(), new Valores(variable.getLinea(), variable.getColumna(), variable.getLexema() ,null,(float) 454333333433.333,arreglostr, null,"Arreglo char"));
                break;
          
        }
        this.instrucciones.remove(0);//end
        this.instrucciones.remove(0);//;
    }
    
    private float getvalorfloat(){
        String valor = this.instrucciones.remove(0).getLexema();
        //numero o funcion o id 
        float numero = 0;
        if(aritmeticas.contains(valor)){//por si viene una aritmetica
            numero = this.funcionesaritmeticas(valor);
        }else if (estadisticas.contains(valor)){//una estadistica
            numero = this.funcionesestadisticas(valor);
        }else if(this.convertidoafloat(valor)){//es un numero normal
            numero = Float.parseFloat(valor);
        
        }else if (this.hash.containsKey(valor)){
            numero = this.hash.get(valor).getValord();//es una variable
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
            suma += numero;
        }
        return suma/arreglo.size();
    }
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
    public float funcionesestadisticas(String operacion){
        boolean seleccionado = false;
        ArrayList<Float> arreglodob = new ArrayList<>();
        float numero=0;
        this.instrucciones.remove(0);//(
        String valor = this.instrucciones.remove(0).getLexema();
        //id o numeros por lo tanto es necesario saber si se puede parsear
            if(!"[".equals(valor)){
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
                    if("]".equals(this.instrucciones.remove(0).getLexema())){
                        break;
                    }
                }
            }
            //me faltaba eliminar el parentesis
            this.instrucciones.remove(0);
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
                    numero=arreglodob.get(0);
                    for(float m : arreglodob){
                        if(m<numero){
                            numero = m;
                        }
                    }
                break;
            case ("VARIANZA"):
                numero = this.varianza(arreglodob);
                break;
            case ("MAX"):
                numero=arreglodob.get(0);
                for(float m : arreglodob){
                    if(m>numero){
                        numero = m;
                    }
                }
                break;
            case ("MEDIA"):
                numero = this.media(arreglodob);
                break;
            }
            return numero;
        }
        
        
        
    
}
    
    
    
    

