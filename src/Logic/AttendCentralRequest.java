/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Model.Request;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class AttendCentralRequest extends Thread {
    
    private final Socket so;

    public AttendCentralRequest(Socket so) {
        
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
                    
                    Util.neighbor.setText(message[1]);
                    Util.nextSucursalIp = message[1];
                    
                    Util.nextSucursalPort = Integer.parseInt(message[2]);
                    System.out.println("Nuevo vecino asignado: "+Util.nextSucursalIp+", "+Util.nextSucursalPort);
                    Util.addText("Se cambio de vecino a: "+Util.nextSucursalIp);
                    
                    break;
                    
                // Fin Cambio de vecino    
                case "1":
                    
                    
                    
                    break;
               
                    
                    
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(AttendCentralRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
