/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class MessgeFromCentral extends Thread {
    
    private final Socket so;

    public MessgeFromCentral(Socket so) {
        
        this.so = so;
        
    }
    
    @Override
    public void run(){
    
        
        try {
            
            DataInputStream input = new DataInputStream(this.so.getInputStream());
            String [] message = input.readUTF().split(" ");
            
            switch(message[0]){
                
                // Cambio de vecino
                case "0":
                    
                    Util.nextSucursalIp = message[1];
                    Util.nextSucursalPort = Integer.parseInt(message[2]);
                    System.out.println("Nuevo vecino asignado: "+Util.nameSucursal+", "+Util.port);
                    break;
                
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(MessgeFromCentral.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
