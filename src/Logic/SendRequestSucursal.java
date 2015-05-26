/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Model.Request;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAB_L11
 */
public class SendRequestSucursal {
    
    private String protocolMsg;
    private Socket sucursalSocket;
    private DataOutputStream output;
    private DataInputStream input;
    
    
    public SendRequestSucursal(String ip, int port) throws UnknownHostException, IOException
    {
        sucursalSocket = new Socket(ip,port);
        output = new DataOutputStream(sucursalSocket.getOutputStream());
        input = new DataInputStream(sucursalSocket.getInputStream());
    }

    public String SendRequest(Request request)
    {
        String[] response;
        try {
            
            ByteArrayOutputStream bs= new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream (bs);
            os.writeObject(request);  
            os.close();
            byte[] bytes =  bs.toByteArray(); 
            
            output.write(bytes);
            output.flush();
            response = input.readUTF().split(" ");
            
            if(response.length <= 0 ) {
                return "false";
            }
            if(response[0].equals("0"))
                return "false";
            
            return response[1];
            
        } catch (IOException ex) {
            Logger.getLogger(SendRequestCentral.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return "false";      
        
    }

    public boolean closeCentral(){
        try {
            sucursalSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(SendRequestCentral.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
