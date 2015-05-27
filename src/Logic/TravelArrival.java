/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Model.Paquete;
import Model.Request;
import Model.RequestTransporte;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
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
            
            // Lee la peticion de la sucursal
            String[] llegadaTransporte = input.readUTF().split(" ");
            
            // Respuesta a la sucursal
            String sendResponse = "";
            
            switch(llegadaTransporte[0]){
                
                // Saludo de una sucursal
                case "5":
                    
                    System.out.println(llegadaTransporte[1]);
                    ListManagement transporte = new ListManagement();
                    
                    
                    break;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(TravelArrival.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }  
    
    private Request ConvertToRequest(byte[] bytes){
        
        Request request = null;
        ObjectInputStream is= null;
        ByteArrayInputStream bs= new ByteArrayInputStream(bytes); // bytes es el byte[]

        try {
             is = new ObjectInputStream(bs);
             request = (Request)is.readObject();
             is.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AttendCentralRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TravelArrival.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return request;
            
    }
    
    private void LLegadaTransporte(Request request){
        ArrayList<Paquete> listaTransporte =null;
        ArrayList<Paquete> listaEntrada = new ArrayList<Paquete>();
        ArrayList<Paquete> listaTemp = new ArrayList<Paquete>();
        if(request == null)
            return;
        
        RequestTransporte requestTran = (RequestTransporte)request;
        listaTransporte = requestTran.getTransporte().paquetes;
        
        //Notifico Hora de llegada de transporte al servidor 
        // Le paso el id del recorrido y la hora que llego
        
        
        int i;
        // Verfica si tiene algun paquete para la sucursal
        for (i= 0; i<listaTransporte.size(); i++)
        {
            if (GetIpLan.getIp().equals( listaTransporte.get(i).ipDestino))           
               listaEntrada.add(listaTransporte.get(i));
        }
        //Fin de verificacion
        //Si es que tiene algun paquete para mi y que pueda recibir algun paquete
        if ((listaEntrada.size()>0) && Util.listaEspera.size() < 5)
        {
            int j = 0;
            //Recibo los paquetes que pueda recibir y los quito de la lista del transporte y lista entrada
            for (i=Util.listaEspera.size(); i<=5;i++)
            {
                Paquete temp = listaEntrada.get(j);
                Util.listaEspera.add(temp);
                listaTransporte.remove(temp);
                listaTemp.add(temp);                
                j++;
                try {//Sleep por dos segundos tiempo que demora en bajar del transporte
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TravelArrival.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Aqui se debe notificar al servidor que el paquete fue recibido por la sucursal, actualizar hora de llegada
                //El paquete se encuentra en la variable temp
            }
            //Limpio la lista de entrada 
            if (!listaTemp.isEmpty())
            {
                for (i=0; i<=listaTemp.size();i++){
                    listaEntrada.remove(listaTemp.get(i));
                }
            }
            
            //Si es que quedo algun paquete sin recibir notifico al servidor rechazado por recepcion            
            if(listaEntrada.size() <0)
            {                
                for (i = 0 ; i<listaEntrada.size(); i++)
                    System.out.println("Aqui se deberia notificar al servidor que se rechazo por recepcion");
            }
            
        }
        //Verifico si tengo paquetes para enviar
        if (Util.listaEnvio.size()>0)
        {
            listaTemp = new ArrayList<Paquete>();
            for(i=0 ; i<Util.listaEnvio.size() ; i++)
            {
                Paquete tempEnvio = Util.listaEnvio.get(i);
                //Verifico si puedo ingresarlo al transporte
                if (listaTransporte.size()<10)
                {
                    listaTransporte.add(tempEnvio);
                    listaTemp.add(tempEnvio);          
                    try {//Sleep por dos segundos tiempo que demora en subir al transporte
                        Thread.sleep(2000);
                        //Aqui se debe notificar al servidor que el transporte recibio un nuevo paquete para el envio
                        //El paquete se encuentra en tempEnvio
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TravelArrival.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            //Limpio la lista envio
            if (!listaTemp.isEmpty())
            {
                for (i=0; i<=listaTemp.size();i++){
                    Util.listaEnvio.remove(listaTemp.get(i));
                }
            }
            //Si es que quedo algun paquete en la lista de envio notifico al servidor que fue rechazo por transporte lleno
            if (!Util.listaEnvio.isEmpty())
            {
                for (i=0; i<=Util.listaEnvio.size();i++)   
                {
                    //Aqui notififico al servidor que paquete fue rechazo por el transporte
                }
            }
        }
        
        //Solicitar al servidor central el nuevo recorrido del transporte 
        //Esto es notificar salida del transporte
        //Recibo la variable y modifico en requestTran el recorrido.
        
        //SendRequestSucursal enviar = new SendRequestSucursal(ip del proximo, puerto del proximo);
        //enviar.SendRequest(requestTran);
        //enviar.closeCentral();
        
        
    }
}
