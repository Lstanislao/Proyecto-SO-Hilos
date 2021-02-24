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
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;
import ventanas.Dashboard;

public class Central {

    public static Dashboard dashboard = ProyectoSO.dashboard;
    public static boolean iniciado = false;

    //Tiempo que dura el dia en segundos
    public static volatile int tiempoDia = 2;

    //Dias entre despachos
    public static int diasDespacho = 20;

    //Dias para proximo despacho
    public static volatile int diasRestantes = 20;

    //Consolas producidas
    public static volatile int consolasProducidas = 0;

    //Disponibilidad maxima de almacenes
    public static int maxAlmacenBotones;
    public static int maxAlmacenPantallas;
    public static int maxAlmacenTarjetas;
    public static int maxAlmacenJoystick;

    //Cantidad inicial de productores
    public static int inicialProdBotones;
    public static int inicialProdPantallas;
    public static int inicialProdTarjetas;
    public static int inicialProdJoystick;

    //Cantidad inicial de ensambladores
    public static int inicialEnsambladores;

    //Cantidad maxima de productores
    public static int maxProdBotones;
    public static int maxProdPantallas;
    public static int maxProdTarjetas;
    public static int maxProdJoystick;

    //Cantidad maxima de ensambladores
    public static int maxEnsambladores;

    //Contadores de elementos
    public static volatile int numBotones = 0;
    public static volatile int numPantallasNormales = 0;
    public static volatile int numPantallasTactiles = 0;
    public static volatile int numTarjetasSD = 0;
    public static volatile int numJoystick = 0;

    //Accion actual de jefe y gerente
    public static volatile String accionJefe = "Iniciando";
    public static volatile String accionGerente = "Iniciando";

    //Duracion de produccion
    public static volatile double diasProdBotones = 0.5;
    public static volatile int diasProdPantallasNormales = 1;
    public static volatile int diasProdPantallasTactiles = 2;
    public static volatile int diasProdJoystick = 2;
    public static volatile int diasProdTarjetasSD = 3;

    //Duracion de ensamblaje
    public static volatile int diasEnsamblaje = 1;

    //Duracion acciones gerente y jefe
    public static volatile double diasCambioJefe = 0.25;
    public static volatile double diasDormirGerente = 0.0833;

    public static ManejadorDePersonal manejadorDePersonal;

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

                    // Asignando valores
                    itemsLinea = linea.split(": ");
                    System.out.println("Mi linea es" + itemsLinea[0]);
                    numero = Integer.parseInt(itemsLinea[1]);
                    System.out.println("Mi numero es" + numero);

                    switch (itemsLinea[0]) {
                        case "Dia en segundos":
                            tiempoDia = numero * 1000;
                            break;
                        case "Dias entre despachos":
                            diasDespacho = numero;
                            break;
                        case "Capacidad maxima en almacen de botones":
                            maxAlmacenBotones = numero;
                            break;
                        case "Capacidad maxima en almacen de pantallas":
                            maxAlmacenPantallas = numero;
                            break;
                        case "Capacidad maxima en almacen de joysticks":
                            maxAlmacenJoystick = numero;
                            break;
                        case "Capacidad maxima en almacen de tarjetas":
                            maxAlmacenTarjetas = numero;
                            break;
                        case "Capacidad inicial de productores de botones":
                            inicialProdBotones = numero;
                            break;
                        case "Capacidad inicial de productores de pantallas":
                            inicialProdPantallas = numero;
                            break;
                        case "Capacidad inicial de productores de joysticks":
                            inicialProdJoystick = numero;
                            break;
                        case "Capacidad inicial de productores de tarjetas":
                            inicialProdTarjetas = numero;
                            break;
                        case "Capacidad inicial de ensambladores":
                            inicialEnsambladores = numero;
                            break;
                        case "Capacidad maxima de productores de botones":
                            maxProdBotones = numero;
                            break;
                        case "Capacidad maxima de productores de pantallas":
                            maxProdPantallas = numero;
                            break;
                        case "Capacidad maxima de productores de joysticks":
                            maxProdJoystick = numero;
                            break;
                        case "Capacidad maxima de productores de tarjetas":
                            maxProdTarjetas = numero;
                            break;
                        case "Capacidad maxima de ensambladores":
                            maxEnsambladores = numero;
                            break;
                    }
                }
            }

            //Validaciones
            if (inicialProdBotones > maxProdBotones || inicialProdBotones < 0 || maxProdBotones < 0) {
                JOptionPane.showMessageDialog(dashboard, "Los datos ingresados de productores de botones son invalidos!");
            } else if (inicialProdPantallas > maxProdPantallas || inicialProdPantallas < 0 || maxProdPantallas < 0) {
                JOptionPane.showMessageDialog(dashboard, "Los datos ingresados de productores de pantallas son invalidos!");
            } else if (inicialProdJoystick > maxProdJoystick || inicialProdJoystick < 0 || maxProdJoystick < 0) {
                JOptionPane.showMessageDialog(dashboard, "Los datos ingresados de productores de joysticks son invalidos!");
            } else if (inicialProdTarjetas > maxProdTarjetas || inicialProdTarjetas < 0 || maxProdTarjetas < 0) {
                JOptionPane.showMessageDialog(dashboard, "Los datos ingresados de productores de tarjetas son invalidos!");
            } else if (inicialEnsambladores > maxEnsambladores || inicialEnsambladores < 0 || maxEnsambladores < 0) {
                JOptionPane.showMessageDialog(dashboard, "Los datos ingresados de ensambladores son invalidos!");
            } else {
                iniciado = true;
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

        } catch (Exception e) {
            System.out.println("Problema en lectura del archivo '"
                    + miArchivo + "'");
        }
    }

    public static void IniciarSimulacion() {
        CargarInfomacionInicial();

        if (iniciado) {
            Semaphore mutexTiempo = new Semaphore(1);
            Semaphore mutexConsolas = new Semaphore(1);

            Semaphore mutexBotones = new Semaphore(1);

            System.out.println("iniciando semProBotones" + maxAlmacenBotones);

            Semaphore semProBotones = new Semaphore(maxAlmacenBotones);
            Semaphore semEnsBotones = new Semaphore(0);

            Semaphore mutexPantallasNormal = new Semaphore(1);
            Semaphore mutexPantallasTactil = new Semaphore(1);
            Semaphore semProPantallas = new Semaphore(maxAlmacenPantallas);
            Semaphore semEnsPantallasNormal = new Semaphore(0);
            Semaphore semEnsPantallasTactil = new Semaphore(0);

            Semaphore mutexTarjetaSD = new Semaphore(1);
            Semaphore mutexJoystick = new Semaphore(1);

            Semaphore semEnsJoystick = new Semaphore(0);
            Semaphore semEnsTarjetaSD = new Semaphore(0);

            Semaphore semProJoystick = new Semaphore(maxAlmacenJoystick);
            Semaphore semProTarjetaSD = new Semaphore(maxAlmacenTarjetas);


            manejadorDePersonal = new ManejadorDePersonal(mutexBotones, mutexPantallasNormal, mutexPantallasTactil,
                    mutexTarjetaSD, mutexJoystick, mutexConsolas,
                    semEnsBotones, semEnsPantallasNormal, semEnsPantallasTactil,
                    semEnsJoystick, semEnsTarjetaSD,
                    semProBotones, semProPantallas, semProJoystick, semProTarjetaSD);

            Jefe jefe = new Jefe(mutexTiempo);
            jefe.start();

            Gerente gerente = new Gerente(mutexTiempo, mutexConsolas);
            gerente.start();

            boolean prueba = manejadorDePersonal.ContratarProBotones();
            manejadorDePersonal.ContratarProJoystick();
            manejadorDePersonal.ContratarProPantallas();
            manejadorDePersonal.ContratarProTarjetasSD();
            manejadorDePersonal.ContratarEnsamblador();

        } else {
            
        }

//        ensamblador.start();
    }

}
