/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;


public class RequestTransporte extends Request implements Serializable{
    
    private Transporte transporte;
    
    public RequestTransporte(int codRequest)
    {
        super(codRequest);    
    }
    
    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }
}
