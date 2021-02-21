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
        
        
        Semaphore mutexPantallasNormal = new Semaphore(1);
        Semaphore mutexPantallasTactil= new Semaphore(1);
        Semaphore semProPantallas = new Semaphore(Central.maxAlmacenPantallas);
        Semaphore semEnsPantallasNormal = new Semaphore(0);
        Semaphore semEnsPantallasTactil = new Semaphore(0);
        
        Semaphore mutexTarjetaSD = new Semaphore(1);
        Semaphore mutexJoystick = new Semaphore(1);
        
        Semaphore semEnsJoystick = new Semaphore(0);
        Semaphore semEnsTarjetaSD = new Semaphore(0);
        
        Semaphore semProJoystick = new Semaphore(Central.maxAlmacenJoystick);
        Semaphore semProTarjetaSD = new Semaphore(Central.maxAlmacenTarjetas);
        
        
        
        ProductorBotones a = new ProductorBotones( mutexBotones, semProBotones, semEnsBotones);
       
        ProductorPantallasNormales b = new ProductorPantallasNormales( 
              mutexPantallasNormal, mutexPantallasTactil,
              semProPantallas,  semEnsPantallasTactil, semEnsPantallasNormal );
        
        ProductorTarjetasSD c = new ProductorTarjetasSD( mutexTarjetaSD,  semProTarjetaSD, semEnsTarjetaSD );
        
        ProductorJoystick d = new ProductorJoystick( mutexJoystick, semProJoystick, semEnsJoystick );


        Ensamblador e = new Ensamblador( mutexBotones, mutexPantallasNormal,mutexPantallasTactil, 
            mutexTarjetaSD, mutexJoystick, 
            semEnsBotones, semEnsPantallasNormal, semEnsPantallasTactil,
            semEnsJoystick, semEnsTarjetaSD,
            semProBotones, semProPantallas, semProJoystick, semProTarjetaSD);

        
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();

       
        
      
    }

}
