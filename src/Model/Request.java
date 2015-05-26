/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author LAB_L11
 */
public class Request implements Serializable{
    
    private int codRequest;

    public Request(int codRequest) {
        this.codRequest = codRequest;
    }

    public int getCodRequest() {
        return codRequest;
    }

    public void setCodRequest(int codRequest) {
        this.codRequest = codRequest;
    }
    
}
