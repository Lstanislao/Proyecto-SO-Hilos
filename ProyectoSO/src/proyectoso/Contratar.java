/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.concurrent.Semaphore;

public class Contratar {

    public static ProductorBotones[] contratado = new ProductorBotones[3];
    public static int productoresBotones = 0;
    public static int maxProductoresBotones = 0;

    public static void contratar() {

//        if (productoresBotones) {
//            
//        }
        Semaphore mutexBotones = new Semaphore(1);
        Semaphore semProBotones = new Semaphore(45);
        Semaphore semEnsBotones = new Semaphore(0);
        
        
        
    }

}


