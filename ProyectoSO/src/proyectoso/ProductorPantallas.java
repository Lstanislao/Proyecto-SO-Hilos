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
public class ProductorPantallas extends Thread {

    Semaphore mutex;
    Semaphore semProPantallas;
    Semaphore semEnsPantallas;

    public ProductorPantallas(Semaphore mutex, Semaphore semProPantallas, Semaphore semEnsPantallas) {

        this.mutex = mutex;
        this.semProPantallas = semProPantallas;
        this.semEnsPantallas = semEnsPantallas;

    }

    public void run() {
        while (true) {
            
            try {
                if (Central.almacenPantallas > (Central.numPantallasNormales + Central.numPantallasTactil)) {
                    
                    this.semProPantallas.acquire();
                    this.mutex.acquire();
                    Central.numPantallasNormales++;
                    Central.numPantallasTactil++;
                    System.out.println("El valor de pantallas normales es " + Central.numPantallasNormales);
                    System.out.println("El valor de pantallas tactil es " + Central.numPantallasTactil );
                    this.mutex.release();
                    Thread.sleep(3000);//3 dias 
                    this.semEnsPantallas.release();
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
