/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Model.Request;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
            //String packet = input.readUTF();
            //System.out.println(packet);
            byte[] bytes = new byte[100000];
            input.read(bytes);
            ByteArrayInputStream bs= new ByteArrayInputStream(bytes); // bytes es el byte[]
            ObjectInputStream is = new ObjectInputStream(bs);
            try {
                Request request = (Request)is.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AttendCentralRequest.class.getName()).log(Level.SEVERE, null, ex);
            }
            is.close();
            
        } catch (IOException ex) {
            Logger.getLogger(TravelArrival.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
