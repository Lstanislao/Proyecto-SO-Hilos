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
    double diasProduccion;

    public ProductorBotones(Semaphore mutex, Semaphore semProBotones, Semaphore semEnsBotones) {

        this.mutex = mutex;
        this.semProBotones = semProBotones;
        this.semEnsBotones = semEnsBotones;
        this.activo = true;
        this.diasProduccion = Central.tiempoDia * Central.diasProdBotones;

    }

    public void run() {
        while (activo) {
            try {
                this.semProBotones.acquire();
                    Thread.sleep((long) this.diasProduccion);
                    this.mutex.acquire();
                        Central.numBotones++;
                        ProyectoSO.dashboard.setBotonesProducidos(Central.numBotones);
                        System.out.println("El valor de botones es " + Central.numBotones);
                    this.mutex.release();
                this.semEnsBotones.release();
  
            } catch (InterruptedException ex) {
                 System.out.println("error");
               System.out.println("Fallo en el productor de Botones"+ ex);
            }
        }

    }
}
