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

    Semaphore mutexNormal;
    Semaphore mutexTactil;
    Semaphore semProPantallas;
    Semaphore semEnsPantallasNormal;
    Semaphore semEnsPantallasTactil;
    boolean activo = true;

    public ProductorPantallas(
            Semaphore mutexNormal, Semaphore mutexTactil,
            Semaphore semProPantallasNormal, Semaphore semEnsPantallasNormal,
            Semaphore semEnsPantallasTactil) {

        this.mutexNormal = mutexNormal;
        this.mutexTactil = mutexTactil;
        this.semProPantallas = semProPantallasNormal;
        this.semEnsPantallasNormal = semEnsPantallasNormal;
        this.semEnsPantallasTactil = semEnsPantallasTactil;
        this.activo = true;

    }

    public void run() {
        while (true) {

            try {
                //if ( (Central.maxAlmacenPantallas - (Central.numPantallasNormales + Central.numPantallasNormales)) > 0) {
                    //Pantalla normal
                    this.semProPantallas.acquire(2);
                    this.mutexNormal.acquire();
                        Central.numPantallasNormales++;
                        System.out.println("El valor de pantallas normales es " + Central.numPantallasNormales);
                    this.mutexNormal.release();
                    Thread.sleep(1000);//1 dias 
                    this.semEnsPantallasNormal.release();

                    //Pantalla tactil
                    this.mutexTactil.acquire();
                        Central.numPantallasTactiles++;
                        System.out.println("El valor de pantallas tactiles es " + Central.numPantallasNormales);
                    this.mutexTactil.release();
                    Thread.sleep(2000);//2 dias 
                    this.semEnsPantallasTactil.release();
                //}

            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
