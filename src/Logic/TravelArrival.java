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
public class TravelArrival extends Thread{
    
    private final Socket so;

    public TravelArrival(Socket so) {
        this.so = so;
    }
    
    @Override
    public void run(){
        
        try {
            
            DataOutputStream output = new DataOutputStream(this.so.getOutputStream());
            DataInputStream input = new DataInputStream(this.so.getInputStream());
            String packet = input.readUTF();
            System.out.println(packet);
            
        } catch (IOException ex) {
            Logger.getLogger(TravelArrival.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
