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
    public static volatile int almacenBotones = 45;
    public static volatile int numBotones = 0;
    public static int maxProBotones = 5;
    

    public ProductorBotones(Semaphore mutex, Semaphore semProBotones, Semaphore semEnsBotones ) {
        
        this.mutex = mutex;
        this.semProBotones = semProBotones;
        this.semEnsBotones = semEnsBotones;
  
    }

    public void run() {
        while (true) {
            try {
                this.semProBotones.acquire();
                this.mutex.acquire();
                    numBotones++;
                    almacenBotones--;
                    System.out.println("El valor de botones es " + numBotones );
                this.mutex.release();
                Thread.sleep(1000); 
                this.semEnsBotones.release(); 

            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
