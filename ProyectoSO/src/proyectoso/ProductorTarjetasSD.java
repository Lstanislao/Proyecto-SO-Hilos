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
 * @author orian
 */
public class ProductorTarjetasSD extends Thread{
    Semaphore mutex;
    Semaphore semProTarjetasSD;
    Semaphore semEnsTarjetasSD;

    public ProductorTarjetasSD(Semaphore mutex, Semaphore semProTarjetasSD, Semaphore semEnsTarjetasSD) {
        this.mutex = mutex;
        this.semProTarjetasSD = semProTarjetasSD;
        this.semEnsTarjetasSD = semEnsTarjetasSD;
    }
    
    

    
    public void run() {
        while (true) {
            try {
               
                this.semProTarjetasSD.acquire();
                this.mutex.acquire();
                Central.numTarjetasSD++;
                System.out.println("El valor de tarjetaSD es " + Central.numTarjetasSD);
                this.mutex.release();
                Thread.sleep(1000);
                this.semEnsTarjetasSD.release(2);
                
                

            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
