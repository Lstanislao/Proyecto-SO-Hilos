/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.concurrent.Semaphore;

/**
 *
 * @author LStanislao
 */
public class ProyectoSO {

    /**
     * @param args the command line arguments
     */
    
    public static volatile int r;
    public static int dias = 100;
    
    public static void main(String[] args) {
            
        Dashboard ventana = new Dashboard ();
        ventana.setVisible(true);
        
        
//        Semaphore mutex = new Semaphore(1);
//        Semaphore semProductor = new  Semaphore (6);
//        Semaphore semConsumidor = new Semaphore (0);
//        
//        Productor g  = new Productor( mutex , semConsumidor , semProductor , "Garcia" );
//        Consumidor f = new Consumidor( mutex, semConsumidor, semProductor, "Fernando" );
//        g.start();
//        f.start();


        
        
    }
    
}
