/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Model.Request;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAB_L11
 */
public class SendRequestCentral {
    
    private String protocolMsg;
    private Socket serverSocket;
    private DataOutputStream output;
    private DataInputStream input;
    
    
    public SendRequestCentral() throws UnknownHostException, IOException
    {
        serverSocket = new Socket(Util.centralIp,Util.centralPort);
        output = new DataOutputStream(serverSocket.getOutputStream());
        input = new DataInputStream(serverSocket.getInputStream());
    }

    public String SendRequest(Request request)
    {
        String[] response;
        switch (request.getCodRequest())
        {
            //Solicitar ips de las maquinas conectadas
            case 1:
            {
                this.protocolMsg = String.valueOf(request.getCodRequest());
                break;
            }
        }
        try {
            output.writeUTF(protocolMsg);
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
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(SendRequestCentral.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
