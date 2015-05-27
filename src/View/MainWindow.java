/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Logic.CentralRequestListener;
import Logic.GetIpLan;
import Logic.InitTravel;
import Logic.SendRequestCentral;
import Logic.SendRequestSucursal;
import Logic.Util;
import Model.Request;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    public MainWindow() {
        initComponents();
        
        this.jLabelSucursalName.setText(Util.Sucursalname);
        this.jLabelIpLan.setText(GetIpLan.getIp());
        
        Util.console = this.jListConsole;
        Util.neighbor= this.jLabelNeighbor;
        
        // Inicia el servidor para atender solicitudes de la central *Se coloco aqui para poder utilizar los componenetes inicializados)
        CentralRequestListener centralRequest = new CentralRequestListener();
        centralRequest.start();
        try {
            
            SendRequestCentral request = new SendRequestCentral();
            String result = request.SendRequest(new Request(1));
            if (!result.equals("false") )
            {
                String[] ips = result.split("@");
                int i = 0;
                this.jComboBox1.removeAllItems();
                while(i < ips.length)
                {                    
                    this.jComboBox1.addItem(ips[i]);
                    i++;
                }
                
                Util.combo = this.jComboBox1;
            }
            else {
                JOptionPane.showMessageDialog(null, "No existen sucursales vivas");
            }
            request.closeCentral();
                
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(null, "Error conectando al servidor central");
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error conectando al servidor central");
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelIpLan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonIniciar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSpinnerPaquetes = new javax.swing.JSpinner();
        jLabelSucursalName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListConsole = new javax.swing.JList();
        jLabelNeighbor = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelIpLan.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabelIpLan.setText("xxx.xxx.xxx.xxx");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("IP de sucursal vecina:");

        jButtonIniciar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonIniciar.setText("Iniciar");
        jButtonIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tu direccion IP");

        jButton1.setText("Paquete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSpinnerPaquetes.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabelSucursalName.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabelSucursalName.setText("Nombre");

        jListConsole.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(jListConsole);

        jLabelNeighbor.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabelNeighbor.setText("xxx.xxx.xxx.xxx");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonIniciar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelSucursalName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNeighbor, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIpLan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSpinnerPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(382, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelSucursalName)
                            .addComponent(jLabelNeighbor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jSpinnerPaquetes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelIpLan)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jButtonIniciar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarActionPerformed
       
        
    }//GEN-LAST:event_jButtonIniciarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   /*     SendRequestSucursal request = null;
        if (this.jComboBox1.getItemCount()>0)
        {
            String item = String.valueOf(this.jComboBox1.getSelectedItem());
            String[]ip = item.split("!");
            try {
                
                request = new SendRequestSucursal("192.168.1.163", 9700);
                request.SendRequest(new Request(1));
                request.closeCentral();
                
            } catch (UnknownHostException ex) {
           //     request.closeCentral();
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error conectando al servidor central");
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
//                request.closeCentral();
                JOptionPane.showMessageDialog(null, "Error conectando al servidor central");
                
            } catch(Exception ex){
        //        request.closeCentral();
                JOptionPane.showMessageDialog(null, "Error conectando al servidor central");
            }
        }*/
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonIniciar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelIpLan;
    private javax.swing.JLabel jLabelNeighbor;
    private javax.swing.JLabel jLabelSucursalName;
    private javax.swing.JList jListConsole;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerPaquetes;
    // End of variables declaration//GEN-END:variables
}
