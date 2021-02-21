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
public class ProductorBotones extends Thread {

    Semaphore mutex;
    Semaphore semProBotones;
    Semaphore semEnsBotones;
    boolean activo;

    public ProductorBotones(Semaphore mutex, Semaphore semProBotones, Semaphore semEnsBotones) {

        this.mutex = mutex;
        this.semProBotones = semProBotones;
        this.semEnsBotones = semEnsBotones;
        this.activo = true;

    }

    public void run() {
        while (activo) {
            try {
                    this.semProBotones.acquire(2);
                    this.mutex.acquire();
                        Central.numBotones = Central.numBotones + 2;
                        ProyectoSO.dashboard.setBotonesProducidos(Central.numBotones);
                        System.out.println("El valor de botones es " + Central.numBotones);
                    this.mutex.release();
                    Thread.sleep(1000);
                    this.semEnsBotones.release(2);
            
  
            } catch (InterruptedException ex) {
                 System.out.println("error");
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
