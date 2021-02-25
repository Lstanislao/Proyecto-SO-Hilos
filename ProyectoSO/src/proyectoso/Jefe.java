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
public class Jefe extends Thread {
    Semaphore mutexTiempo;
    int tiempoCambio;
    boolean primerDia;

    public Jefe(Semaphore mutexTiempo) {
        this.mutexTiempo = mutexTiempo;
        this.tiempoCambio = Central.tiempoDia * Central.diasCambioJefe;
        this.primerDia = true;
    }
    
    public void run() {
        while (true) {
            try {
                if (!primerDia) {
                    Central.accionJefe = "Esperando";
                    ProyectoSO.dashboard.setAccionJefe(Central.accionJefe);
                    System.out.println("\nEl jefe esta: " + Central.accionJefe);
                    this.mutexTiempo.acquire();
                    if (Central.diasRestantes > 0) {
                        //Se cambia el dia
                        Central.accionJefe = "Cambiando dia";
                        ProyectoSO.dashboard.setAccionJefe(Central.accionJefe);
                        System.out.println("\nEl jefe esta: " + Central.accionJefe);
                        Thread.sleep(tiempoCambio);
                        Central.diasRestantes--;
                        ProyectoSO.dashboard.setDiasRestantes(Central.diasRestantes);
                    }
                    this.mutexTiempo.release();
                    Central.accionJefe = "Durmiendo";
                    ProyectoSO.dashboard.setAccionJefe(Central.accionJefe);
                    System.out.println("\nEl jefe esta: " + Central.accionJefe);
                    Thread.sleep((Central.tiempoDia * 1000) - this.tiempoCambio);  
                } else {
                    //Se espera a que pase el primer dia
                    primerDia = false;
                    Thread.sleep(Central.tiempoDia * 1000);
                }
                
            } catch (InterruptedException ex) {
                System.out.println("Fallo en el Jefe "+ ex);
            }
        }
    
    }
}
