/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orian
 */
public class Gerente extends Thread {
    Semaphore mutexTiempo;
    

    public Gerente(Semaphore mutexTiempo) {
        this.mutexTiempo = mutexTiempo;
    }
    
    public void run() {
        while (true) {
            try {
                this.mutexTiempo.acquire();
//                    if (Central.diasRestantes == 0) {
//                        //Despliegue
//                        Central.diasRestantes = Central.diasDespacho;
//                    }
                this.mutexTiempo.release();
                Thread.sleep(1000);    
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    public static void CargarInfomacionInicial() {
        File miArchivo;
        String miLinea[];
        int numero;
        
    String nombre, calle, urb, line, cadena[];
    int v1, v2, distancia;
        miArchivo = new File("informacionInicial.txt");
        
        try {
            FileReader fileReader
                    = new FileReader(miArchivo);
            BufferedReader bufferedReader
                    = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.equals("")) {
                    
                    if (line.toLowerCase().equals("clientes")) {
                        line = bufferedReader.readLine();
                    }
                    if (line.toLowerCase().equals("caminos")) {
                        line = bufferedReader.readLine();
                    }

                    // Asignando valores
                    miLinea = line.split(" ");
                    System.out.println("Mi linea es" + miLinea[0]);
                    numero = Integer.parseInt(miLinea[1]);
                    System.out.println("Mi numero es" + numero);
                       
                }
            }
            
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "No se puede abrir este archivo'"
                    + miArchivo + "'");
        } catch (IOException ex) {
            System.out.println(
                    "No se puede leer este archivo '"
                    + miArchivo + "'");
            
        } catch (IndexOutOfBoundsException ex) {
            
        }
    }
    
}
