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
    
    Semaphore mutexBotones, mutexPantallasNormal,mutexPantallasTactil, 
            mutexTarjetaSD, mutexJoystick, mutexConsolas,
            semEnsBotones, semEnsPantallasNormal, semEnsPantallasTactil,
            semEnsJoystick, semEnsTarjetasSD,
            semProBotones, semProPantallas, semProJoystick, semProTarjetaSD;

    boolean activo;
    int tiempoEnsamblaje;

    public Ensamblador(Semaphore mutexBotones, Semaphore mutexPantallasNormal, Semaphore mutexPantallasTactil, 
            Semaphore mutexTarjetaSD, Semaphore mutexJoystick, Semaphore mutexConsolas, 
            Semaphore semEnsBotones,Semaphore semEnsPantallasNormales, 
            Semaphore PantallasEnsTactiles, Semaphore semEnsJoystick, 
            Semaphore semEnsTarjetasSD, Semaphore semProBotones, Semaphore semProPantallas, 
            Semaphore semProJoystick, Semaphore semProTarjetaSD) {
        this.mutexBotones = mutexBotones;
        this.mutexPantallasNormal = mutexPantallasNormal;
        this.mutexPantallasTactil = mutexPantallasTactil;
        this.mutexTarjetaSD = mutexTarjetaSD;
        this.mutexJoystick = mutexJoystick;
        this.mutexConsolas = mutexConsolas;
        this.semEnsBotones = semEnsBotones;
        this.semEnsPantallasNormal = semEnsPantallasNormales;
        this.semEnsPantallasTactil = PantallasEnsTactiles;
        this.semEnsJoystick = semEnsJoystick;
        this.semEnsTarjetasSD = semEnsTarjetasSD;
        this.semProBotones = semProBotones;
        this.semProPantallas = semProPantallas;
        this.semProJoystick = semProJoystick;
        this.semProTarjetaSD = semProTarjetaSD;
        
        this.activo = true;
        this.tiempoEnsamblaje = Central.diasEnsamblaje;
    }
    
    public void run(){
        while(activo){
            try {
                
                //Busco todas las piezas necesarias en almacenes
                this.semEnsBotones.acquire(5); 
                this.semEnsJoystick.acquire(2);
                this.semEnsTarjetasSD.acquire(1);
                this.semEnsPantallasNormal.acquire(1);
                this.semEnsPantallasTactil.acquire(1);
                
                    if (activo) {
                        //BOTONES
                        this.mutexBotones.acquire();
                            Central.numBotones = Central.numBotones - 5;
                            ProyectoSO.dashboard.setBotonesProducidos(Central.numBotones);
                        this.mutexBotones.release();
                        
                        //JOYSTICK
                        this.mutexJoystick.acquire();
                            Central.numJoystick = Central.numJoystick -2;
                            ProyectoSO.dashboard.setJoystickProducidos(Central.numJoystick);
                        this.mutexJoystick.release();
                        
                        //TAREJTA SD
                        this.mutexTarjetaSD.acquire();
                            Central.numTarjetasSD = Central.numTarjetasSD -1;
                            ProyectoSO.dashboard.setTarjetasSDProducidas(Central.numTarjetasSD);
                        this.mutexTarjetaSD.release();
                        
                        //PANTALLA NORMAL
                        this.mutexPantallasNormal.acquire();
                            Central.numPantallasNormales = Central.numPantallasNormales - 1;
                            ProyectoSO.dashboard.setPantallasNormalesProducidas(Central.numJoystick);
                        this.mutexPantallasNormal.release();

                        //PANTALLA TACTIL
                        this.mutexPantallasTactil.acquire();
                            Central.numPantallasTactiles = Central.numPantallasTactiles - 1;
                            ProyectoSO.dashboard.setPantallasTactilesProducidas(Central.numJoystick);
                        this.mutexPantallasTactil.release();
                    }
                
                //Libero espacio en almacenes
                this.semProBotones.release(5);
                this.semProJoystick.release(2);
                this.semProTarjetaSD.release(1);
                this.semProPantallas.release(2);
                
                Thread.sleep(Central.tiempoDia * this.tiempoEnsamblaje);
                
                //Cambio numero de consolas
                this.mutexConsolas.acquire();
                System.out.println("Produci una consola\n");
                    Central.consolasProducidas++;
                    ProyectoSO.dashboard.setConsolasProducidas(Central.consolasProducidas);
                this.mutexConsolas.release();
                
            } catch (InterruptedException ex) {
                System.out.println("Fallo en el ensamblador "+ ex);
            }
               
        }
    }
    
    




 
    
}
