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

    public ProductorBotones(Semaphore mutex, Semaphore semProBotones, Semaphore semEnsBotones) {

        this.mutex = mutex;
        this.semProBotones = semProBotones;
        this.semEnsBotones = semEnsBotones;

    }

    public void run() {
        while (true) {
            try {
                if (Central.almacenBotones > Central.numBotones) {
                    
                    this.semProBotones.acquire();
                    this.mutex.acquire(2);
                    Central.numBotones++;
                    System.out.println("El valor de botones es " + Central.numBotones);
                    this.mutex.release();
                    Thread.sleep(1000);
                    this.semEnsBotones.release(2);
                    
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
