/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.FileOutputStream;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author LAB_A112
 */
public class ListManagement {
    
    
    public boolean initTransport(){
    
        Document doc;
        Element root,child;
        List <Element> rootChildrens;
       
        int pos = 0;
        SAXBuilder builder = new SAXBuilder();

        try
        {
            doc = builder.build("src/XmlFiles/ListaPaquetes.xml");
            root = doc.getRootElement();
            rootChildrens = root.getChildren();
            
            while(pos < rootChildrens.size()){
                
                root.removeContent(pos);
                
                pos++;
            }
            
            try
                {
                    Format format = Format.getPrettyFormat();
                    // Se genera un flujo de salida de datos XML
                    XMLOutputter out = new XMLOutputter(format);
                    // Se asocia el flujo de salida con el archivo donde se guardaran los datos
                    FileOutputStream file = new FileOutputStream("src/XmlFiles/ListaPaquetes.xml");
                    // Se manda el documento generado hacia el archivo XML 
                    out.output(doc,file);
                    // Se limpia el buffer ocupado por el objeto file y se manda a cerrar el archivo
                    file.flush();
                    file.close();
                }
                catch(Exception e)
                {
                    throw e;
                }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return false;
    }

    private boolean loadPaquetes(){
        
        
        
        return false;
    }
    
    public String [] readPaquetes(int pos){
        
        Document doc;
        Element root,child;
        List <Element> rootChildrens;
        String [] list = new String[3];
       
        SAXBuilder builder = new SAXBuilder();

        try
        {
            doc = builder.build("src/XmlFiles/ListaPaquetes.xml");
            root = doc.getRootElement();
            rootChildrens = root.getChildren();
            
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public boolean savePaquetes(int ite, String ipDestiny, String portDestiny, String sucursalOrigin){
        
        if(this.maxCapPaquetes()){
            Document    doc;
            Element     root, newChild;
            SAXBuilder  builder = new SAXBuilder();

            try
            {
                doc = builder.build("src/XmlFiles/ListaPaquetes.xml");
                root = doc.getRootElement();
                // Creamos una nueva etiqueta
                int i = 0;

                while(i < ite){
                    newChild = new Element("Paquete");

                    // Añadimos un atributo
                    newChild.setAttribute("IpDestiny", ipDestiny);
                    newChild.setAttribute("PortDestiny", portDestiny);   
                    newChild.setAttribute("SucursalOrigen", sucursalOrigin);   

                    // La añadimos como hija a una etiqueta ya existente
                    root.addContent(newChild);
                    i++;
                }
                try
                {
                    Format format = Format.getPrettyFormat();
                    // Se genera un flujo de salida de datos XML
                    XMLOutputter out = new XMLOutputter(format);
                    // Se asocia el flujo de salida con el archivo donde se guardaran los datos
                    FileOutputStream file = new FileOutputStream("src/XmlFiles/ListaPaquetes.xml");
                    // Se manda el documento generado hacia el archivo XML 
                    out.output(doc,file);
                    // Se limpia el buffer ocupado por el objeto file y se manda a cerrar el archivo
                    file.flush();
                    file.close();
                }
                catch(Exception e)
                {
                    throw e;
                }
                
                return true;

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return false;
        
    } 
    
    private boolean maxCapPaquetes(){
        
        Document doc;
        Element root,child;
        List <Element> rootChildrens;
       
        SAXBuilder builder = new SAXBuilder();

        try
        {
            doc = builder.build("src/XmlFiles/ListaPaquetes.xml");
            root = doc.getRootElement();
            rootChildrens = root.getChildren();
            
            if(rootChildrens.size() < 10) {
                return true;
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
   
        return false;
    }
    
}
