/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

/**
 *
 * @author LStanislao
 */
public class ManejadorDePersonal {

    Semaphore mutexBotones, mutexPantallasNormal, mutexPantallasTactil,
            mutexTarjetaSD, mutexJoystick,
            semEnsBotones, semEnsPantallasNormal, semEnsPantallasTactil,
            semEnsJoystick, semEnsTarjetasSD,
            semProBotones, semProPantallas, semProJoystick, semProTarjetaSD;

    ProductorBotones [] ProBotonesAct = new ProductorBotones [Central.maxProdBotones];
    public int numProBotones = 0;
    
    ProductorJoystick [] ProJoystickAct = new ProductorJoystick [Central.maxProdJoystick];
    public int numProJoystick = 0;
    
    ProductorPantallas [] ProPantallasAct = new ProductorPantallas [Central.maxProdPantallas];
    public int numProPantallas = 0;
    
    ProductorTarjetasSD [] ProTarjetasSDAct = new ProductorTarjetasSD [Central.maxAlmacenTarjetas];
    public int numProTarjetasSD = 0;
    
    
    public ManejadorDePersonal(Semaphore mutexBotones, Semaphore mutexPantallasNormal, Semaphore mutexPantallasTactil, Semaphore mutexTarjetaSD, Semaphore mutexJoystick, Semaphore semEnsBotones, Semaphore semEnsPantallasNormal, Semaphore semEnsPantallasTactil, Semaphore semEnsJoystick, Semaphore semEnsTarjetasSD, Semaphore semProBotones, Semaphore semProPantallas, Semaphore semProJoystick, Semaphore semProTarjetaSD) {
        this.mutexBotones = mutexBotones;
        this.mutexPantallasNormal = mutexPantallasNormal;
        this.mutexPantallasTactil = mutexPantallasTactil;
        this.mutexTarjetaSD = mutexTarjetaSD;
        this.mutexJoystick = mutexJoystick;
        this.semEnsBotones = semEnsBotones;
        this.semEnsPantallasNormal = semEnsPantallasNormal;
        this.semEnsPantallasTactil = semEnsPantallasTactil;
        this.semEnsJoystick = semEnsJoystick;
        this.semEnsTarjetasSD = semEnsTarjetasSD;
        this.semProBotones = semProBotones;
        this.semProPantallas = semProPantallas;
        this.semProJoystick = semProJoystick;
        this.semProTarjetaSD = semProTarjetaSD;
    }

    public boolean ContratarProBotones() {

        System.out.println("NUMERO DE productores de botones  " + numProBotones);
        if (numProBotones < Central.maxProBotones) {
            //Intancio el hilo
            ProductorBotones proBotones = new ProductorBotones(mutexBotones, semProBotones, semEnsBotones);
            
            // Es el index donde voy a modificar mi array
            int index = numProBotones;
            
            //Guardo el hilo en mi array de producotres activos
            ProBotonesAct[index] = proBotones;
            
            //Este print se puede quitar
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                System.out.println(ProBotonesAct[i]);
            }
            
            //Pongo el hilo a correr
            proBotones.start();
            numProBotones++;
            return true;
        }
        JOptionPane.showMessageDialog(null, "No se puede contratar mas , excede el maximo");
        return false;
    }
    
    public void DespedirProBotones() {
    
        if(numProBotones > 0){
            
            //Disminuyo en numero de productores
            numProBotones--;
            
            //El index donde voy a modificar el array de productores activos
            int index = numProBotones;
            
            System.out.println(numProBotones+ "ESTOS SON LOS PRODUCTORES DE BOTONES QUE QUEDAN "+ ProBotonesAct[index]);
            
            //Saco la instacia del hilo que voy a despedir y le pongo el active false
            ProductorBotones proDespedido = ProBotonesAct[index];
            proDespedido.activo = false;
            
            //limpio el espacio del array del hilo que despedi
            ProBotonesAct[index] = null;
        }
        
    }
    
    
    public void ContratarProJoystick() {

        System.out.println("NUMERO DE productores de joystic " + numProJoystick);
        if (numProBotones < Central.maxProJoystick) {
            //Intancio el hilo
            ProductorJoystick proJoystick = new ProductorJoystick(mutexJoystick, semProJoystick, semEnsJoystick);
            
            // Es el index donde voy a modificar mi array
            int index = numProJoystick;
            
            //Guardo el hilo en mi array de producotres activos
            ProJoystickAct[index] = proJoystick;
            
            //Este print se puede quitar
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                System.out.println(ProJoystickAct[i]);
            }
            
            //Pongo el hilo a correr
            proJoystick.start();
            numProJoystick++;
        }

    }
    
    public void DespedirProJoystick() {
    
        if(numProJoystick > 0){
            
            //Disminuyo en numero de productores
            numProJoystick--;
            
            //El index donde voy a modificar el array de productores activos
            int index = numProJoystick;
            
            System.out.println(numProJoystick+ "ESTOS SON LOS PRODUCTORES DE JOYSTICK QUE QUEDAN ");
            
            //Saco la instacia del hilo que voy a despedir y le pongo el active false
            ProductorJoystick proDespedido = ProJoystickAct[index];
            proDespedido.activo = false;
            
            //limpio el espacio del array del hilo que despedi
            ProJoystickAct[index] = null;
        }
        
    }
    
    
    public void ContratarProPantallas() {

        System.out.println("NUMERO DE productores de pantalla " + numProPantallas);
        if (numProPantallas < Central.maxProPantalla) {
            //Intancio el hilo
            ProductorPantallas proPantallas = new ProductorPantallas(
                mutexPantallasNormal, mutexPantallasTactil,
                semProPantallas, semEnsPantallasTactil, semEnsPantallasNormal);
            
            // Es el index donde voy a modificar mi array
            int index = numProPantallas;
            
            //Guardo el hilo en mi array de producotres activos
            ProPantallasAct[index] = proPantallas;
            
            //Este print se puede quitar
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                System.out.println(ProPantallasAct[i]);
            }
            
            //Pongo el hilo a correr
            proPantallas.start();
            numProPantallas++;
        }

    }
    
    
    public void DespedirProPantallas() {
        System.out.println(numProPantallas+ "DESPEDI AL DE LAS PANTALLAS");
        if(numProPantallas > 0){
            
            //Disminuyo en numero de productores
            numProPantallas--;
            
            //El index donde voy a modificar el array de productores activos
            int index = numProPantallas;
            
            System.out.println(numProPantallas+ "ESTOS SON LOS PRODUCTORES DE pantallas QUE QUEDAN ");
            
            //Saco la instacia del hilo que voy a despedir y le pongo el active false
            ProductorPantallas proDespedido = ProPantallasAct[index];
            proDespedido.activo = false;
            
            //limpio el espacio del array del hilo que despedi
            ProPantallasAct[index] = null;
        }
        
    }
    
    
    
    public void ContratarProTarjetasSD() {

        System.out.println("NUMERO DE productores de TarjetasSD " + numProTarjetasSD);
        if (numProTarjetasSD < Central.maxProTarjetasSD) {
            //Intancio el hilo
            ProductorTarjetasSD proTarjetasSD = new ProductorTarjetasSD( mutexTarjetaSD, semProTarjetaSD, semEnsTarjetasSD);
            
            // Es el index donde voy a modificar mi array
            int index = numProTarjetasSD;
            
            //Guardo el hilo en mi array de producotres activos
            ProTarjetasSDAct[index] = proTarjetasSD;
            
            //Este print se puede quitar
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                System.out.println(ProTarjetasSDAct[i]);
            }
            
            //Pongo el hilo a correr
            proTarjetasSD.start();
            numProTarjetasSD++;
        }

    }
    
    public void DespedirProTarjetasSD() {
    
        if(numProTarjetasSD > 0){
            
            //Disminuyo en numero de productores
            numProTarjetasSD--;
            
            //El index donde voy a modificar el array de productores activos
            int index = numProTarjetasSD;
            
            System.out.println(numProTarjetasSD+ "ESTOS SON LOS PRODUCTORES DE TarjetasSD QUE QUEDAN ");
            
            //Saco la instacia del hilo que voy a despedir y le pongo el active false
            ProductorTarjetasSD proDespedido = ProTarjetasSDAct[index];
            proDespedido.activo = false;
            
            //limpio el espacio del array del hilo que despedi
            ProTarjetasSDAct[index] = null;
        }
        
    }

}
