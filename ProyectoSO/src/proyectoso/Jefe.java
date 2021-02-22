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
    double tiempoCambio;

    public Jefe(Semaphore mutexTiempo) {
        this.mutexTiempo = mutexTiempo;
        this.tiempoCambio = Central.tiempoDia * Central.diasCambioJefe;
    }
    
    public void run() {
        while (true) {
            try {
                Central.accionJefe = "Esperando";
                ProyectoSO.dashboard.setAccionJefe(Central.accionJefe);
                this.mutexTiempo.acquire();
                System.out.println("Cambiando dia");
                    Central.accionJefe = "Cambiando dia";
                    ProyectoSO.dashboard.setAccionJefe(Central.accionJefe);
                    Thread.sleep((long) tiempoCambio);
                    Central.diasRestantes--;
                    ProyectoSO.dashboard.setDiasRestantes(Central.diasRestantes);
                this.mutexTiempo.release();
                System.out.println("duermo resto del dia");
                Central.accionJefe = "Durmiendo";
                ProyectoSO.dashboard.setAccionJefe(Central.accionJefe);
                Thread.sleep((long) (Central.tiempoDia - this.tiempoCambio));    
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
}
