/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Daniel
 */
public class Util {
    
    public static int port = 9500;
    public static int centralPort = 10000;
    public static int centralPortListener = 9700;
    
    public static String centralIp = "192.168.2.122";
    
    // Nombre de la sucursal
    public static String Sucursalname = "Su1";
    
    // El demonio de esucha para la central, se coloca aca para reiniciar la busqueda del vecino
    public static boolean centralDaemon;
    
    public static String nextSucursalIp;
    public static int nextSucursalPort;
    
    // Sucursal vecina (IP)
    public static JLabel neighbor;
    
    // Consola de la sucursal
    public static JList console;
    
    /**
     * Agregar texto a la consola de la sucursal
     * @param text texto que se quiere colocar
     */
    public static void addText(String text){
        
        DefaultListModel newText = new DefaultListModel();
        if(Util.console.getModel().getSize() > 0)
            newText = (DefaultListModel) Util.console.getModel();

        String timeStamp = new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance().getTime());
        newText.addElement(text+" - " + timeStamp);
        Util.console.setModel(newText);
        
    }   
    
    
    
}
