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
    
    Semaphore mutexBotones, mutexPantallas, mutexTarjetaSD, mutexJoystick, 
            semEnsBotones, semEnsPantallas, semEnsJoystick, semEnsTarjetasSD,
            semProBotones, semProPantallas, semProJoystick, semProTarjetaSD;

    public Ensamblador(Semaphore mutexBotones, Semaphore mutexPantallas, Semaphore mutexTarjetaSD, Semaphore mutexJoystick,
            Semaphore semEnsBotones, Semaphore semEnsPantallas, Semaphore semEnsJoystick, Semaphore semEnsTarjetasSD, 
            Semaphore semProBotones, Semaphore semProPantallas, Semaphore semProJoystick, Semaphore semProTarjetaSD) {
        
        this.mutexBotones = mutexBotones;
        this.mutexPantallas = mutexPantallas;
        this.mutexTarjetaSD = mutexTarjetaSD;
        this.mutexJoystick = mutexJoystick;
        this.semEnsBotones = semEnsBotones;
        this.semEnsPantallas = semEnsPantallas;
        this.semEnsJoystick = semEnsJoystick;
        this.semEnsTarjetasSD = semEnsTarjetasSD;
        this.semProBotones = semProBotones;
        this.semProPantallas = semProPantallas;
        this.semProJoystick = semProJoystick;
        this.semProTarjetaSD = semProTarjetaSD;
    }


    public void run(){
        while(true){
            try {
                
                //BOTONES
                this.semEnsBotones.acquire(5);
                this.mutexBotones.acquire();
                
                    Central.numBotones = Central.numBotones - 5; 
                    System.out.println("El valor de botones es " + Central.numBotones );
                
                this.mutexBotones.release();
                this.semProBotones.release(5);
                
                //JOYSTICK
                this.semEnsJoystick.acquire(2);
                this.mutexJoystick.acquire();
                    
                    Central.numJoystick = Central.numJoystick -2;
                    System.out.println("El valor de joystick es " + Central.numJoystick );
                    
                this.mutexJoystick.release();
                this.semProJoystick.release(2);
                
                //TAREJTA SD
                this.semEnsTarjetasSD.acquire(1);
                this.mutexTarjetaSD.acquire();
                    
                    Central.numTarjetasSD = Central.numTarjetasSD -1;
                    System.out.println("El valor de tarjeta es " + Central.numTarjetasSD );
                    
                this.mutexTarjetaSD.release();
                this.semProTarjetaSD.release(1);
                
                //Pantalla
                this.semEnsPantallas.acquire(2);
                this.mutexPantallas.acquire();
                    
                    Central.numPantallasNormales = Central.numTarjetasSD - 1;
                    Central.numPantallasTactiles = Central.numPantallasTactiles - 1;
                    System.out.println("El valor de r es " + Central.numPantallasTactiles );
                    System.out.println("El valor de r es " + Central.numPantallasNormales);
                    
                this.mutexPantallas.release();
                this.semProPantallas.release(2);
                
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA PRODUCI UNA CONSOLA");
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
    }
    
    




 
    
}
