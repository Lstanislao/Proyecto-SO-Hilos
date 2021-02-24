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
            mutexTarjetaSD, mutexJoystick, mutexConsolas,
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
    
    Ensamblador [] EnsambladoresAct = new Ensamblador [Central.maxEnsambladores];
    public int numEnsambladores = 0;
    
    
    public ManejadorDePersonal(Semaphore mutexBotones, Semaphore mutexPantallasNormal, Semaphore mutexPantallasTactil, Semaphore mutexTarjetaSD, Semaphore mutexJoystick, Semaphore mutexConsolas, Semaphore semEnsBotones, Semaphore semEnsPantallasNormal, Semaphore semEnsPantallasTactil, Semaphore semEnsJoystick, Semaphore semEnsTarjetasSD, Semaphore semProBotones, Semaphore semProPantallas, Semaphore semProJoystick, Semaphore semProTarjetaSD) {
        this.mutexBotones = mutexBotones;
        this.mutexPantallasNormal = mutexPantallasNormal;
        this.mutexPantallasTactil = mutexPantallasTactil;
        this.mutexTarjetaSD = mutexTarjetaSD;
        this.mutexJoystick = mutexJoystick;
        this.mutexConsolas = mutexConsolas;
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

    public void ContratarProBotones() {

        if (numProBotones < Central.maxProdBotones) {
            //Instancio el hilo
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
            ProyectoSO.dashboard.setProBotones(numProBotones);
        }else{
            JOptionPane.showMessageDialog(null, "No se puede contratar más de botones, excede el máximo "+ Central.maxProdBotones);
        }
        
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
            
            ProyectoSO.dashboard.setProBotones(numProBotones);
            //limpio el espacio del array del hilo que despedi
            ProBotonesAct[index] = null;
            
        }else{
            JOptionPane.showMessageDialog(null, "No se puede despedir mas productores de botones actualmente hay 0,"
                    + " y necesitamos seguir produciendo consolas :(");
        }
        
    }
    
    
    public void ContratarProJoystick() {
        
        System.out.println("NUMERO DE productores de joystic " + numProJoystick);
        if (numProJoystick < Central.maxProdJoystick) {
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
            ProyectoSO.dashboard.setProJoystick(numProJoystick);
        }else{
            JOptionPane.showMessageDialog(null, "No se puede contratar más productores de joystick, excede el máximo: " 
                    + Central.maxProdJoystick);
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
            ProyectoSO.dashboard.setProJoystick(numProJoystick);
            
            //limpio el espacio del array del hilo que despedi
            ProJoystickAct[index] = null;
            
        }else{
            JOptionPane.showMessageDialog(null, "No se puede despedir mas productores de botones actualmente hay 0,"
                    + " y necesitamos seguir produciendo consolas :(");
        }
        
    }
    
    
    public void ContratarProPantallas() {

        System.out.println("NUMERO DE productores de pantalla " + numProPantallas);
        if (numProPantallas < Central.maxProdPantallas) {
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
            ProyectoSO.dashboard.setProPantallas(numProPantallas);
        }else{
            JOptionPane.showMessageDialog(null, "No se puede contratar más productores de pantallas, excede el máximo: " 
                    + Central.maxProdPantallas);
        }

    }
    
    
    public void DespedirProPantallas() {
        System.out.println(numProPantallas+ "DESPEDI AL DE LAS PANTALLAS");
        if(numProPantallas > 0){
            
            //Disminuyo en numero de productores
            numProPantallas--;
            
            //El index donde voy a modificar el array de productores activos
            int index = numProPantallas;
            ProyectoSO.dashboard.setProPantallas(numProPantallas);
            System.out.println(numProPantallas+ "ESTOS SON LOS PRODUCTORES DE pantallas QUE QUEDAN ");
            
            //Saco la instacia del hilo que voy a despedir y le pongo el active false
            ProductorPantallas proDespedido = ProPantallasAct[index];
            proDespedido.activo = false;
            
            //limpio el espacio del array del hilo que despedi
            ProPantallasAct[index] = null;
        }else{
            JOptionPane.showMessageDialog(null, "No se puede despedir mas productores de pantallas actualmente hay 0,"
                    + " y necesitamos seguir produciendo consolas :(");
        }
        
    }
    
    
    
    public void ContratarProTarjetasSD() {

        System.out.println("NUMERO DE productores de TarjetasSD " + numProTarjetasSD);
        if (numProTarjetasSD < Central.maxProdTarjetas) {
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
            ProyectoSO.dashboard.setProTarjetasSD(numProTarjetasSD);
        }else{
            JOptionPane.showMessageDialog(null, "No se puede contratar más productores de tarjetas, excede el máximo: " 
                    + Central.maxProdPantallas);
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
            ProyectoSO.dashboard.setProTarjetasSD(numProTarjetasSD);
            
            //limpio el espacio del array del hilo que despedi
            ProTarjetasSDAct[index] = null;
        }else{
            JOptionPane.showMessageDialog(null, "No se puede despedir más productores de lectores de tarejta SD actualmente hay 0,"
                    + " y necesitamos seguir produciendo consolas :(");
        }
        
    }
    
    public void ContratarEnsamblador() {

        System.out.println("NUMERO DE ensambladores " + numEnsambladores);
        if (numEnsambladores < Central.maxEnsambladores) {
            //Intancio el hilo
            Ensamblador ensamblador = new Ensamblador(mutexBotones, mutexPantallasNormal, mutexPantallasTactil,
                mutexTarjetaSD, mutexJoystick, mutexConsolas,
                semEnsBotones, semEnsPantallasNormal, semEnsPantallasTactil,
                semEnsJoystick, semEnsTarjetasSD,
                semProBotones, semProPantallas, semProJoystick, semProTarjetaSD);
            
            // Es el index donde voy a modificar mi array
            int index = numEnsambladores;
            
            //Guardo el hilo en mi array de producotres activos
            EnsambladoresAct[index] = ensamblador;
            
            //Este print se puede quitar
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                System.out.println(EnsambladoresAct[i]);
            }
            
            //Pongo el hilo a correr
            ensamblador.start();
            numEnsambladores++;
            ProyectoSO.dashboard.setEnsamblador(numEnsambladores);
        }else{
            JOptionPane.showMessageDialog(null, "No se puede contratar más ensambladores, excede el máximo: " 
                    + Central.maxEnsambladores);
        }

    }
    
    public void DespedirEnsamblador() {
    
        if(numEnsambladores > 0){
            
            //Disminuyo en numero de productores
            numEnsambladores--;
            
            //El index donde voy a modificar el array de productores activos
            int index = numEnsambladores;
            
            System.out.println(numEnsambladores + "ESTOS SON LOS ensambladores QUE QUEDAN ");
            
            //Saco la instacia del hilo que voy a despedir y le pongo el active false
            Ensamblador ensDespedido = EnsambladoresAct[index];
            ensDespedido.activo = false;
            ProyectoSO.dashboard.setEnsamblador(numEnsambladores);
            
            //limpio el espacio del array del hilo que despedi
            EnsambladoresAct[index] = null;
        }else{
            JOptionPane.showMessageDialog(null, "No se puede despedir más emsambladores actualmente hay 0,"
                    + " y necesitamos seguir produciendo consolas :(");
        }
        
    }

}
