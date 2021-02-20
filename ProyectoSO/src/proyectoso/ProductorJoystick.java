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
public class ProductorJoystick extends Thread {
    Semaphore mutex;
    Semaphore semProJoystick;
    
    public ProductorJoystick(Semaphore mutex, Semaphore semProJoystick) {
        this.mutex = mutex;
        this.semProJoystick = semProJoystick;
    }
    
    public void run() {
        while (true) {
            try {
                if (Central.numJoystick < Central.almacenJoystick) {
                    this.semProJoystick.acquire();
                        this.mutex.acquire();
                            Central.numJoystick++;
                            Central.almacenJoystick--;
                            System.out.println("El valor de joystick es " + Central.numJoystick);
                        this.mutex.release();
                    Thread.sleep(1000);    
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
}
