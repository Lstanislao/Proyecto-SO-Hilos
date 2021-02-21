/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LStanislao
 */
public class Ensamblador extends Thread{

    public Ensamblador(Semaphore mutexBotones, Semaphore mutexPantallasNormal, Semaphore mutexPantallasTactil, 
            Semaphore mutexTarjetaSD, Semaphore mutexJoystick, Semaphore semEnsBotones, 
            Semaphore semEnsPantallasNormales, Semaphore PantallasEnsTactiles, Semaphore semEnsJoystick, 
            Semaphore semEnsTarjetasSD, Semaphore semProBotones, Semaphore semProPantallas, 
            Semaphore semProJoystick, Semaphore semProTarjetaSD) {
        this.mutexBotones = mutexBotones;
        this.mutexPantallasNormal = mutexPantallasNormal;
        this.mutexPantallasTactil = mutexPantallasTactil;
        this.mutexTarjetaSD = mutexTarjetaSD;
        this.mutexJoystick = mutexJoystick;
        this.semEnsBotones = semEnsBotones;
        this.semEnsPantallasNormal = semEnsPantallasNormales;
        this.semEnsPantallasTactil = PantallasEnsTactiles;
        this.semEnsJoystick = semEnsJoystick;
        this.semEnsTarjetasSD = semEnsTarjetasSD;
        this.semProBotones = semProBotones;
        this.semProPantallas = semProPantallas;
        this.semProJoystick = semProJoystick;
        this.semProTarjetaSD = semProTarjetaSD;
    }
    
    Semaphore mutexBotones, mutexPantallasNormal,mutexPantallasTactil, 
            mutexTarjetaSD, mutexJoystick, 
            semEnsBotones, semEnsPantallasNormal, semEnsPantallasTactil,
            semEnsJoystick, semEnsTarjetasSD,
            semProBotones, semProPantallas, semProJoystick, semProTarjetaSD;



    public void run(){
        while(true){
            try {
                
                //BOTONES
                System.out.println("entro");
                this.semEnsBotones.acquire(5);
                System.out.println("entro2");
                this.mutexBotones.acquire();
                    System.out.println("CONSUMI BOTONES");
                    Central.numBotones = Central.numBotones - 5; 
                    System.out.println("El valor de botones es " + Central.numBotones );
                
                this.mutexBotones.release();
                this.semProBotones.release(5);
                
                //JOYSTICK
                this.semEnsJoystick.acquire(2);
                this.mutexJoystick.acquire();
                    System.out.println("CONSUMI JOYSTICK");
                    Central.numJoystick = Central.numJoystick -2;
                    System.out.println("El valor de joystick es " + Central.numJoystick );
                    
                this.mutexJoystick.release();
                this.semProJoystick.release(2);
                
                //TAREJTA SD
                this.semEnsTarjetasSD.acquire(1);
                this.mutexTarjetaSD.acquire();
                    System.out.println("CONSUMI SD");
                    Central.numTarjetasSD = Central.numTarjetasSD -1;
                    System.out.println("El valor de tarjeta es " + Central.numTarjetasSD );
                    
                this.mutexTarjetaSD.release();
                this.semProTarjetaSD.release(1);
                
                //Pantalla
                this.semEnsPantallasNormal.acquire(1);
                this.mutexPantallasNormal.acquire();
                   System.out.println("CONSUMI pantalla normal");
                    Central.numPantallasNormales = Central.numPantallasNormales - 1;
                    System.out.println("El valor de pantallas normales es " + Central.numPantallasNormales);
                    
                this.mutexPantallasNormal.release();
                
                //Pantalla tactil
                this.semEnsPantallasTactil.acquire(1);
                this.mutexPantallasTactil.acquire();
                     System.out.println("CONSUMI pantalla tactil");
                    Central.numPantallasTactiles = Central.numPantallasTactiles - 1;
                    System.out.println("El valor de pantallas normales es " + Central.numPantallasTactiles );
                                     
                this.mutexPantallasTactil.release();
               
                this.semProPantallas.release(2);
                
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA PRODUCI UNA CONSOLA");
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
    }
    
    




 
    
}
