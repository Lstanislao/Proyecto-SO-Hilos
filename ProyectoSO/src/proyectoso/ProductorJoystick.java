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
    Semaphore semEnsJoystick;
    boolean activo;
    int diasProduccion;

    public ProductorJoystick(Semaphore mutex, Semaphore semProJoystick, Semaphore semEnsJoystick) {
        this.mutex = mutex;
        this.semProJoystick = semProJoystick;
        this.semEnsJoystick = semEnsJoystick;
        this.activo = true;
        this.diasProduccion = Central.tiempoDia * Central.diasProdJoystick;
    }

    public void run() {
        while (activo) {
            try {
                this.semProJoystick.acquire();
                    Thread.sleep(this.diasProduccion);
                    this.mutex.acquire();
                        Central.numJoystick++;
                        System.out.println("El valor de joystick es " + Central.numJoystick);
                        ProyectoSO.dashboard.setJoystickProducidos(Central.numJoystick);
                    this.mutex.release();
                this.semEnsJoystick.release();

            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
