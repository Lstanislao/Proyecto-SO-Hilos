/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class Central {
    
    public static volatile int tiempoDia = 2000;
    public static volatile int diasDespacho = 20;
    public static int maxAlmacenBotones;
    public static int maxAlmacenPantallas;
    public static int maxAlmacenTarjetas;
    public static int maxAlmacenJoystick;
    public static int inicialProdBotones;
    public static int inicialProdPantallas;
    public static int inicialProdTarjetas;
    public static int inicialProdJoystick;
    public static int inicialEnsambladores;
    public static int maxProdBotones;
    public static int maxProdPantallas;
    public static int maxProdTarjetas;
    public static int maxProdJoystick;
    public static int maxEnsambladores;
            
    public static volatile int numBotones = 0;
    public static int maxProBotones = 3;
    
    public static volatile int numPantallasNormales = 0;
    public static volatile int numPantallasTactiles = 0;
    public static int maxProPantalla = 5;
    
    public static volatile int numTarjetasSD = 0;
    public static int maxProTarjetasSD = 4;
    
    public static volatile int numJoystick = 0;
    public static int maxProJoystick = 4;
    
    public static void CargarInfomacionInicial() {
        File miArchivo;
        String linea, itemsLinea[];
        int numero;
        
        miArchivo = new File("informacionInicial.txt");
        
        try {
            FileReader fileReader
                    = new FileReader(miArchivo);
            BufferedReader bufferedReader
                    = new BufferedReader(fileReader);

            while ((linea = bufferedReader.readLine()) != null) {
                if (!linea.equals("")) {
//                    linea = bufferedReader.readLine();
                    
                    // Asignando valores
                    itemsLinea = linea.split(": ");
                    System.out.println("Mi linea es" + itemsLinea[0]);
                    numero = Integer.parseInt(itemsLinea[1]);
                    System.out.println("Mi numero es" + numero);
                    
                    switch(itemsLinea[0]) {
                        case "Dia en segundos":
                            Central.tiempoDia = numero;
                            break;
                        case "Dias entre despachos": 
                            Central.diasDespacho = numero;
                            break;
                        case "Capacidad maxima en almacen de botones":
                            Central.maxAlmacenBotones = numero;
                            break;
                        case "Capacidad maxima en almacen de pantallas": 
                            Central.maxAlmacenPantallas = numero;
                            break;
                        case "Capacidad maxima en almacen de joysticks": 
                            Central.maxAlmacenJoystick = numero;
                            break;
                        case "Capacidad maxima en almacen de tarjetas": 
                            Central.maxAlmacenTarjetas = numero;
                            break;
                        case "Capacidad inicial de productores de botones":
                            Central.inicialProdBotones = numero;
                            break;
                        case "Capacidad inicial de productores de pantallas": 
                            Central.inicialProdPantallas = numero;
                            break;
                        case "Capacidad inicial de productores de joysticks":
                            Central.inicialProdJoystick = numero;
                            break;
                        case "Capacidad inicial de productores de tarjetas": 
                            Central.inicialProdTarjetas = numero;
                            break;
                        case "Capacidad inicial de ensambladores": 
                            Central.inicialEnsambladores = numero;
                            break;
                        case "Capacidad maxima de productores de botones": 
                            Central.maxProdBotones = numero;
                            break;
                        case "Capacidad maxima de productores de pantallas":
                            Central.maxProdPantallas = numero;
                            break;
                        case "Capacidad maxima de productores de joysticks": 
                            Central.maxProdJoystick = numero;
                            break;
                        case "Capacidad maxima de productores de tarjetas":
                            Central.maxProdTarjetas = numero;
                            break;
                        case "Capacidad maxima de ensambladores": 
                            Central.maxEnsambladores = numero;
                            break;
                    }
                }    
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "No se puede abrir este archivo'"
                    + miArchivo + "'");
        } catch (IOException ex) {
            System.out.println(
                    "No se puede leer este archivo '"
                    + miArchivo + "'");
            
        } catch (IndexOutOfBoundsException ex) {
        }
    }

    

}
