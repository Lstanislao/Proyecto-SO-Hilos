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
                //Espero a que haya espacio en almacen
                this.semProPantallas.acquire(2);
                    //Pantalla normal
                    //Se produce
                    Thread.sleep(this.diasProduccionNormales);
                    this.mutexNormal.acquire();
                        Central.numPantallasNormales++;
                        ProyectoSO.dashboard.setPantallasNormalesProducidas(Central.numJoystick);
                    this.mutexNormal.release();
                    this.semEnsPantallasNormal.release();

                    //Pantalla tactil
                    //Se produce
                    Thread.sleep(this.diasProduccionTactiles);
                    //Aumento numero
                    this.mutexTactil.acquire();
                        Central.numPantallasTactiles++;
                        ProyectoSO.dashboard.setPantallasTactilesProducidas(Central.numJoystick);
                    this.mutexTactil.release();
                    this.semEnsPantallasTactil.release();

            } catch (InterruptedException ex) {
                System.out.println("Fallo en el productor de Pantallas "+ ex);
            }
        }

    }
}
