/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.Controller;
import Controller.ResourceManager;
import javax.swing.JLabel;

/**
 *
 * @author LENOVO T480s
 */
public class Message extends javax.swing.JFrame {

    Controller controller = Controller.getInstance();
    private ResourceManager resourceManager = ResourceManager.getInstance();
    private int isWin = 0;
    /**
     * Creates new form Message
     */
    public Message() {
        this.setVisible(false);
        initComponents();
        initUI();
    }
    
    void initUI(){
        resourceManager.loadFont("gothicb", messageLabel, 20);
        resourceManager.loadImage("OK1", buttonLabel, "");
        resourceManager.loadImage("crown", imageLabel, "");
    }

    public int getIsWin() {
        return isWin;
    }

    public void setIsWin(int isWin) {
        this.isWin = isWin;
    }
    

    public JLabel getGuideLabel() {
        return guideLabel;
    }

    public void setGuideLabel(JLabel guideLabel) {
        this.guideLabel = guideLabel;
    }

    public JLabel getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(JLabel messageLabel) {
        this.messageLabel = messageLabel;
    }
    
    
    void setMessageLabel(String pathName, String m){
        resourceManager.loadFont(pathName, messageLabel, 20);
        messageLabel.setText(m);
    }
    
    void setGuideLabel(String pathName, String m){
        resourceManager.loadFont(pathName, guideLabel, 16);
        guideLabel.setText(m);
    }
    
    void setImageLabel(String pathName){
        resourceManager.loadImage(pathName, imageLabel, "");
    }
    
    void winButtonLabel(){
        controller.setIsFirst2048(0);
        this.setVisible(false);
    }
    
    void loseButtonLabel(){
        this.setVisible(false);
        controller.getChooseLevelState().restart();
        controller.getGamePlayState().setVisible(false);
        controller.getChooseLevelState().setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        messageLabel = new javax.swing.JLabel();
        buttonLabel = new javax.swing.JLabel();
        guideLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setText("jLabel1");
        messageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        buttonLabel.setText("jLabel2");
        buttonLabel.setPreferredSize(new java.awt.Dimension(64, 32));
        buttonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonLabelMouseExited(evt);
            }
        });

        guideLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        guideLabel.setText("jLabel1");
        guideLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setText("jLabel1");
        imageLabel.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(buttonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(guideLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guideLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLabelMouseClicked
        if(isWin == 1){
            winButtonLabel();
        }
        else if(isWin == 0){
            loseButtonLabel();
        }
    }//GEN-LAST:event_buttonLabelMouseClicked

    private void buttonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLabelMouseEntered
        resourceManager.loadImage("OK", buttonLabel, "");
    }//GEN-LAST:event_buttonLabelMouseEntered

    private void buttonLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLabelMouseExited
        resourceManager.loadImage("OK1", buttonLabel, "");
    }//GEN-LAST:event_buttonLabelMouseExited

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
            java.util.logging.Logger.getLogger(Message.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Message.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Message.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Message.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Message().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buttonLabel;
    private javax.swing.JLabel guideLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel messageLabel;
    // End of variables declaration//GEN-END:variables
}
