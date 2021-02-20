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
    
    public ProductorTarjetasSD(Semaphore mutex, Semaphore semProTarjetasSD) {
        this.mutex = mutex;
        this.semProTarjetasSD = semProTarjetasSD;
    }
    
    public void run() {
        while (true) {
            try {
                if (Central.numTarjetasSD < Central.almacenTarjetasSD) {
                    this.semProTarjetasSD.acquire();
                        this.mutex.acquire();
                            Central.numTarjetasSD++;
                            System.out.println("El valor de tarjetaSD es " + Central.numTarjetasSD);
                        this.mutex.release();
                    Thread.sleep(1000);    
                }
                

            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
