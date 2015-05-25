/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class RequestCentralListener extends Thread {
    
    @Override
    public void run(){
        
        try {
            
            sleep(5000);
            
            Socket so = new Socket(Util.centralIp,Util.port);
            DataOutputStream output = new DataOutputStream(so.getOutputStream());
            DataInputStream input = new DataInputStream(so.getInputStream());
            
            // Protocolo con cabecera 0, el cual indica que es un msg de saludo
            String protocolMsg = "0 "+Util.nameSucursal;
            
            output.writeUTF(protocolMsg);
            output.flush();
            
            // Respuesta de la central
            this.assignNames(input.readUTF().split(" "));           
            so.close();
            
            initCentralListener();
            
        } catch (IOException ex) {
            
            this.start();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(RequestCentralListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Hilo para escuchar mensajes del servidor centralizado
     */
    private void initCentralListener(){
    
        ServerSocket ss;
        Socket so;
        
        try {
            
            ss = new ServerSocket(Util.centralPortListener);
            while(true){
                
                System.out.println("Esperando una conexión con el servidor centralizado por el puerto: " + Util.port);
                so = ss.accept();
                System.out.println( "Mensaje de la central obtenido");
                
                // Atender mensaje
                
            }
                     
        } catch (IOException ex) { 
            Logger.getLogger(RequestCentralListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Asigna el nombre de todas las sucursales y el IP de la sucursal vecina
     * @param response respuesta de la central en array
     */
    private void assignNames(String[] response){
    
        Util.nextSucursal = response[0];
        // falta nombres
        
    }
        
        
}
    
