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
    private String msg;
    
    public InitTravel(String ip, int port, String packetNumber, String msg){
        
        this.ip = ip;
        this.port = port;
        this.packetNumber = packetNumber;
        this.msg = msg;
    }
    
    @Override
    public void run(){
        
        try {
            
            int time = 15000;
            if(7 == Integer.parseInt(this.packetNumber)){
                time += 1000; 
            }
            else if(8 == Integer.parseInt(this.packetNumber)){
                time += 2000;
            }
            else if(9 == Integer.parseInt(this.packetNumber)){
                time += 3000;
            }
            else if(10 == Integer.parseInt(this.packetNumber)){
                time += 4000;
            }
            
            Util.addText("El transporte se encuentra en camino con una carga de "+this.packetNumber+" paquetes... ");
            //sleep(time);
            // Inicia los objetos para enviar mensajes por medio del socket
            Socket so = new Socket(this.ip, 9500);
            DataOutputStream output = new DataOutputStream(so.getOutputStream());
            DataInputStream input = new DataInputStream(so.getInputStream());
            
            // Escribe el mensaje de salida
            output.writeUTF("5 "+msg);
            output.flush();
            
        }   
        catch (IOException ex) {
            System.out.println("Error al establecer conexion.");
      
        } /*catch (InterruptedException ex) {
            Logger.getLogger(InitTravel.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
