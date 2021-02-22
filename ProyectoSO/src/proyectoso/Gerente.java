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
    Semaphore mutexTiempo, mutexConsolas;
    double tiempoDormir;

    public Gerente(Semaphore mutexTiempo, Semaphore mutexConsolas) {
        this.mutexTiempo = mutexTiempo;
        this.mutexConsolas = mutexConsolas;
        this.tiempoDormir = Central.tiempoDia * Central.diasDormirGerente;
    }
    
    public void run() {
        while (true) {
            try {
                Central.accionGerente = "Esperando";
                ProyectoSO.dashboard.setAccionGerente(Central.accionGerente);
                this.mutexTiempo.acquire();
                    if (Central.diasRestantes == 0) {
                        this.mutexConsolas.acquire();
                            //Despliegue y se reinicializan contadores
                            Central.accionGerente = "Desplegando";
                            ProyectoSO.dashboard.setAccionGerente(Central.accionGerente);
                            Central.consolasProducidas = 0;
                            Central.diasRestantes = Central.diasDespacho;
                            ProyectoSO.dashboard.setDiasRestantes(Central.diasRestantes);
                        this.mutexConsolas.release();
                    }
                this.mutexTiempo.release();
                Central.accionGerente = "Durmiendo";
                ProyectoSO.dashboard.setAccionGerente(Central.accionGerente);
                Thread.sleep((long) this.tiempoDormir);    
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
}
