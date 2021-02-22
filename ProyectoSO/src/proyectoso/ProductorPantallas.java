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
    boolean activo;
    int diasProduccionTactiles, diasProduccionNormales;
    

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
        this.diasProduccionTactiles = Central.tiempoDia * Central.diasProdPantallasTactiles;
        this.diasProduccionNormales = Central.tiempoDia * Central.diasProdPantallasNormales;
    }

    public void run() {
        while (activo) {

            try {
                //Pantalla normal
                this.semProPantallas.acquire(2);
                    Thread.sleep(this.diasProduccionNormales);
                    this.mutexNormal.acquire();
                        Central.numPantallasNormales++;
                        System.out.println("El valor de pantallas normales es " + Central.numPantallasNormales);
                        ProyectoSO.dashboard.setPantallasNormalesProducidas(Central.numJoystick);
                    this.mutexNormal.release();
                    this.semEnsPantallasNormal.release();

                    //Pantalla tactil
                    this.mutexTactil.acquire();
                    Thread.sleep(this.diasProduccionTactiles);
                        Central.numPantallasTactiles++;
                        System.out.println("El valor de pantallas tactiles es " + Central.numPantallasNormales);
                        ProyectoSO.dashboard.setPantallasTactilesProducidas(Central.numJoystick);
                    this.mutexTactil.release();
                    this.semEnsPantallasTactil.release();

            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
