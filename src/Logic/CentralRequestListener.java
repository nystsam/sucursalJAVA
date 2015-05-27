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
public class CentralRequestListener extends Thread {
    
    @Override
    public void run(){
        
        try {
             
            Socket so = new Socket(Util.centralIp,Util.centralPort);
            DataOutputStream output = new DataOutputStream(so.getOutputStream());
            DataInputStream input = new DataInputStream(so.getInputStream());
            
            // Protocolo con cabecera 0, el cual indica que es un msg de saludo
            String protocolMsg = "0 "+Util.Sucursalname + " " + String.valueOf(Util.centralPortListener);
            
            output.writeUTF(protocolMsg);
            output.flush();
            
            // Respuesta de la central
            this.assignNames(input.readUTF().split(" "));           
            so.close();
            
            initCentralListener();
            
        } catch (IOException e) {
            
            try{
                
                sleep(5000);
                
                System.out.println("Error al contactar con el servidor centralizado, reconectando...");
                this.run();
            } 
            catch (InterruptedException ex) {
                Logger.getLogger(CentralRequestListener.class.getName()).log(Level.SEVERE, null, ex);
             }
        } 
        
    }
    
    /**
     * Hilo para escuchar mensajes del servidor centralizado
     */
    private void initCentralListener(){
    
        ServerSocket ss;
        Socket so;
        Util.centralDaemon = true;
        
        try {
            
            ss = new ServerSocket(Util.centralPortListener);
            while(Util.centralDaemon){
                
                System.out.println("Esperando una conexión con el servidor centralizado por el puerto: " + Util.centralPortListener);
                so = ss.accept();
                System.out.println( "Mensaje de la central obtenido");
                
                // Atender mensaje de la central
                AttendCentralRequest newRequest = new AttendCentralRequest(so);
                newRequest.start();
                
                
            }
                     
        } catch (IOException ex) { 
            Logger.getLogger(CentralRequestListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Asigna el nombre de todas las sucursales y el IP de la sucursal vecina
     * @param response respuesta de la central en array
     */
    private void assignNames(String[] response){
            /*
        Util.nextSucursalIp = response[0];
        Util.nextSucursalPort = Integer.parseInt(response[1]);
        
        // Asigna en pantalla la IP vecina
        Util.neighbor.setText(response[0]);    
        System.out.println("Sucursal vecina: "+ Util.nextSucursalIp);      
        Util.addText("Sucursal vecina: "+Util.nextSucursalIp);
        
        */
        
        
    }
        
        
}
    
