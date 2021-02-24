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
    int tiempoDormir;

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
                System.out.println("soy gerente y estoy esperando" + Central.accionGerente);
                this.mutexTiempo.acquire();
                    Central.accionGerente = "Consultando";
                    
                    System.out.println("SOY GERENTE Y ESTOY CONSULTANDO" + Central.accionGerente);
                    ProyectoSO.dashboard.setAccionGerente(Central.accionGerente);
                    if (Central.diasRestantes == 0) {
                        
                        this.mutexConsolas.acquire();
                            //Despliegue y se reinicializan contadores
                            
                            Central.accionGerente = "Desplegando";
                            ProyectoSO.dashboard.setAccionGerente(Central.accionGerente);
                            Central.consolasProducidas = 0;
                            ProyectoSO.dashboard.setConsolasProducidas(Central.consolasProducidas);
                            Central.diasRestantes = Central.diasDespacho;
                            ProyectoSO.dashboard.setDiasRestantes(Central.diasRestantes);
                        this.mutexConsolas.release();
                    }
                this.mutexTiempo.release();
                Central.accionGerente = "Durmiendo";
                ProyectoSO.dashboard.setAccionGerente(Central.accionGerente);
                System.out.println("soy gerente y me voy a dormir");
                Thread.sleep(this.tiempoDormir);    
                
            } catch (InterruptedException ex) {
                System.out.println("Fallo en el Geretnte "+ex);
            }
        }
    
    }
    
}
