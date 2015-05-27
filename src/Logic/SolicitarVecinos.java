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
 * @author Redes
 */
public class SolicitarVecinos {

    public SolicitarVecinos() {
    }
    
    public boolean obtenerVecino(){
        
        Socket so;
        try {
            so = new Socket(Util.centralIp, Util.centralPort);

        DataOutputStream output = new DataOutputStream(so.getOutputStream());
        DataInputStream input = new DataInputStream(so.getInputStream());

        // Escribe el mensaje de salida
        output.writeUTF("2 ");
        output.flush();
        
        String message = input.readUTF();
       
        this.actualizarVecino(message);
        
        } catch (IOException ex) {
            Logger.getLogger(SolicitarVecinos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return false;
    }
    
    
    private void actualizarVecino(String msg){
        
        String [] datos = msg.split("@");
        
        Util.nextSucursalIp = datos[0];
        Util.nextSucursalPort = Integer.parseInt(datos[1]);
        
        Util.neighbor.setText(datos[0]);
        
    }
    
}
