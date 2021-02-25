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
                //Espero a que haya espacio en almacen
                this.semProJoystick.acquire();
                    //Se produce
                    Thread.sleep(this.diasProduccion);
                    //Aumento numero
                    this.mutex.acquire();
                        Central.numJoystick++;
                        ProyectoSO.dashboard.setJoystickProducidos(Central.numJoystick);
                    this.mutex.release();
                this.semEnsJoystick.release();

            } catch (InterruptedException ex) {
                System.out.println("Fallo en el productor de Joystick "+ ex);   
            }
        }

    }

}
