/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Logic.RequestCentralListener;
import Logic.RequestListener;

/**
 *
 * @author Daniel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Levanta el hilo de escucha
        RequestListener request = new RequestListener();
        request.start();
        
        RequestCentralListener centralRequest = new RequestCentralListener();
        centralRequest.start();
        
        // Inicia la ventana
        MainWindow myWindow = new MainWindow();
        myWindow.setVisible(true);
        
        
    }
    
}
