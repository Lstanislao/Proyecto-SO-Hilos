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
public class Gerente extends Thread {
    Semaphore mutexTiempo;
    

    public Gerente(Semaphore mutexTiempo) {
        this.mutexTiempo = mutexTiempo;
    }
    
    public void run() {
        while (true) {
            try {
                this.mutexTiempo.acquire();
//                    if (Central.diasRestantes == 0) {
//                        //Despliegue
//                        Central.diasRestantes = Central.diasDespacho;
//                    }
                this.mutexTiempo.release();
                Thread.sleep(1000);    
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
}
