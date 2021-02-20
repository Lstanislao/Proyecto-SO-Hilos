/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import ventanas.Dashboard;
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
//            
//        Dashboard ventana = new Dashboard ();
//        ventana.setVisible(true);
//        

          Central.CargarInfomacionInicial();
        Semaphore mutexBotones = new Semaphore(1);
        System.out.println("iniciando semProBotones" + Central.maxAlmacenBotones); 
        
        Semaphore semProBotones = new Semaphore(Central.maxAlmacenBotones);
        Semaphore semEnsBotones = new Semaphore(0);
        
        
        Semaphore mutexPantallas = new Semaphore(1);
        Semaphore semProPantallas = new Semaphore(Central.maxAlmacenPantallas);
        Semaphore semEnsPantallas = new Semaphore(0);
        
        
        Semaphore mutexTarjetaSD = new Semaphore(1);
        Semaphore mutexJoystick = new Semaphore(1);
        

        Semaphore semEnsJoystick = new Semaphore(0);
        Semaphore semEnsTarjetaSD = new Semaphore(0);
        
        
        Semaphore semProJoystick = new Semaphore(Central.maxAlmacenJoystick);
        Semaphore semProTarjetaSD = new Semaphore(Central.maxAlmacenTarjetas);

        ProductorBotones a = new ProductorBotones(mutexBotones, semProBotones, semEnsBotones);
        ProductorPantallas b = new ProductorPantallas(mutexPantallas, semProPantallas, semEnsPantallas);
        ProductorTarjetasSD c = new ProductorTarjetasSD(mutexTarjetaSD,  semProTarjetaSD, semEnsTarjetaSD );
        ProductorJoystick d = new ProductorJoystick(mutexJoystick, semProJoystick, semEnsJoystick );

        Ensamblador e = new Ensamblador(mutexBotones, mutexPantallas, mutexTarjetaSD, mutexJoystick,
                semEnsBotones, semEnsPantallas, semEnsJoystick, semEnsTarjetaSD,
                semProBotones, semProPantallas, semProJoystick, semProTarjetaSD);

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();

       
        
        
        
//        Semaphore mutex = new Semaphore(1);
//        Semaphore semProductor = new  Semaphore (6);
//        Semaphore semConsumidor = new Semaphore (0);
//        
//        Productor g  = new Productor( mutex , semConsumidor , semProductor , "Garcia" );
//        Consumidor f = new Consumidor( mutex, semConsumidor, semProductor, "Fernando" );
//        g.start();
//        f.start();
//    public static volatile int almacenTarjetasSD = 15;
//    public static volatile int numTarjetasSD = 0;
//    public static int maxProTarjetasSD = 4;
//    
//    public static volatile int almacenJoystick = 20;
//    public static volatile int numJoystick = 0;
//    public static int maxProJoystick = 4;
    }

}
