/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class InitTravel extends Thread {
    
    private final String ip;
    private final int port;
    private final String packetNumber;
    
    public InitTravel(String ip, int port, String packetNumber){
        
        this.ip = ip;
        this.port = port;
        this.packetNumber = packetNumber;
    }
    
    @Override
    public void run(){
        
        try {
            
            // Inicia los objetos para enviar mensajes por medio del socket
            Socket so = new Socket(this.ip, this.port);
            DataOutputStream output = new DataOutputStream(so.getOutputStream());
            DataInputStream input = new DataInputStream(so.getInputStream());
            
            // Escribe el mensaje de salida
            output.writeUTF(this.packetNumber);
            output.flush();
            
        }   
        catch (IOException ex) {
            System.out.println("Error al establecer conexion.");
      
        }
    }
}
