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
 * @author Fernando Baladi
 */
public class Consumidor extends Thread{
    
    Semaphore mutex, semConsumidor, semProductor;
    String name;

    public Consumidor(Semaphore mutex, Semaphore semConsumidor, Semaphore semProductor, String name) {
        this.mutex = mutex;
        this.semConsumidor = semConsumidor;
        this.semProductor = semProductor;
        this.name = name;
    }
    
    public void run(){
        while(true){
            try {
                this.semConsumidor.acquire(3);
                this.mutex.acquire();
                    ProyectoSO.r--;
                    System.out.println("El valor de r es " +ProyectoSO.r+ " y lo cambió "+this.name);
                this.mutex.release();
                Thread.sleep(100);
                this.semProductor.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        }
    }
}
