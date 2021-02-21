/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoso;

import ventanas.Dashboard;
import java.util.concurrent.Semaphore;
import ventanas.PersonalDeLaFabrica;

/**
 *
 * @author LStanislao
 */
public class ProyectoSO {

    /**
     * @param args the command line arguments
     */

    public static int dias = 100;
    public static Dashboard dashboard = new Dashboard ();


    public static void main(String[] args) {
        
        dashboard.setVisible(true);
        
        
        Central.CargarInfomacionInicial();
        
        
        
        Central.IniciarSimulacion();

       
        
      
    }

}
