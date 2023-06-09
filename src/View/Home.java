package View;

import Controller.Controller;
import Controller.IOBinary;
import Controller.ResourceManager;
import Model.Matrix;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author LENOVO T480s
 */
public class Home extends javax.swing.JFrame {
    
    /**
     * Creates new form Home
     */
    private Controller controller = Controller.getInstance();
    private ResourceManager resourceManager = ResourceManager.getInstance();
    public Home() {
        initComponents();
        initUI();
        initState();
    }
    
    void loadData(){
        
    }
    void initState(){
        controller.setHomeState(this);
        controller.loadMatrixFromFile();
        controller.setGamePlayState(new GamePlay());
        controller.setMessageState(new Message());
        controller.setChooseLevelState(new ChooseLevel());
    }
     
   
    void initUI(){
        this.setSize(664, 550);
        this.setLocationRelativeTo(null);
        //System.out.println(this.getWidth() + " " + this.getHeight());
        resourceManager.loadImage("Button_02", label2, "CONTINUE");
        resourceManager.loadImage("Button_02", label3, "NEW GAME");
        resourceManager.loadImage("Button_02", label4, "QUIT");
//        addImagePanel("Frame", panel3);
        resourceManager.loadImage("cute", titleLabel, "");
        titleLabel.setText("");
        resourceManager.loadFont("gothicb", label2, 26);
        label2.setForeground(new Color(217,242,246));
        resourceManager.loadFont("gothicb", label3, 26);
        label3.setForeground(new Color(217,242,246));
        resourceManager.loadFont("gothicb", label4, 26);
        label4.setForeground(new Color(217,242,246));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pictureBox1 = new View.PictureBox();
        pictureBox2 = new View.PictureBox();
        panel3 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(41, 356, 18, 361);
        jPanel2.add(jLabel1, gridBagConstraints);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 809, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 301, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        pictureBox1.setImage(new javax.swing.ImageIcon(getClass().getResource("/UI/Background1.png"))); // NOI18N

        pictureBox2.setImage(new javax.swing.ImageIcon(getClass().getResource("/UI/Frame.png"))); // NOI18N

        panel3.setMaximumSize(new java.awt.Dimension(800, 800));
        panel3.setMinimumSize(new java.awt.Dimension(0, 0));
        panel3.setPreferredSize(new java.awt.Dimension(180, 200));
        panel3.setLayout(new java.awt.GridLayout(3, 0, 0, 10));

        label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/StartButtonTextless.png"))); // NOI18N
        label2.setText("CONTINUE");
        label2.setAlignmentY(0.0F);
        label2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label2.setOpaque(true);
        label2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label2MouseExited(evt);
            }
        });
        panel3.add(label2);

        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Button_02.png"))); // NOI18N
        label3.setText("jLabel3");
        label3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label3.setMinimumSize(new java.awt.Dimension(0, 0));
        label3.setPreferredSize(new java.awt.Dimension(180, 60));
        label3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label3MouseExited(evt);
            }
        });
        panel3.add(label3);

        label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Button_02.png"))); // NOI18N
        label4.setText("jLabel5");
        label4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        label4.setMinimumSize(new java.awt.Dimension(0, 0));
        label4.setPreferredSize(new java.awt.Dimension(180, 60));
        label4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label4MouseExited(evt);
            }
        });
        panel3.add(label4);

        pictureBox2.add(panel3);
        panel3.setBounds(20, 40, 180, 200);

        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("jLabel2");
        titleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        titleLabel.setPreferredSize(new java.awt.Dimension(400, 100));

        pictureBox1.setLayer(pictureBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pictureBox1.setLayer(titleLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pictureBox1Layout = new javax.swing.GroupLayout(pictureBox1);
        pictureBox1.setLayout(pictureBox1Layout);
        pictureBox1Layout.setHorizontalGroup(
            pictureBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pictureBox1Layout.createSequentialGroup()
                .addGroup(pictureBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pictureBox1Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pictureBox1Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        pictureBox1Layout.setVerticalGroup(
            pictureBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pictureBox1Layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pictureBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pictureBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void label2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label2MouseClicked

        controller.loadMatrixFromFile();
        controller.readTimerFromFile();
        controller.readScoreFromFile("Score");
        controller.getGamePlayState().renderScore();
        controller.getGamePlayState().setNoti("");
        if(controller.getLevel() == 2){
            controller.getGamePlayState().setDefaultCounterTime(controller.getGamePlayState().getMin(), controller.getGamePlayState().getSec());
            controller.getGamePlayState().getTimer().restart();
            //controller.getGamePlayState().renderCounterTime();
        }
        else{
            controller.getGamePlayState().setDisplayCounterTime(false);
        }
        controller.getGamePlayState().setVisible(true);
        controller.getHomeState().setVisible(false);
    }//GEN-LAST:event_label2MouseClicked

    private void label3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label3MouseClicked
        controller.getChooseLevelState().restart();
        controller.getChooseLevelState().setVisible(true);
    }//GEN-LAST:event_label3MouseClicked

    private void label4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label4MouseClicked
        IOBinary.writeMatrixToFile(controller.getMatrix(), "Matrix.txt");
        controller.writeTimerToFile();
        System.exit(0);
    }//GEN-LAST:event_label4MouseClicked

    private void label2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label2MouseEntered
        resourceManager.loadImage("Button_01", label2, "CONTINUE");
    }//GEN-LAST:event_label2MouseEntered

    private void label2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label2MouseExited
        resourceManager.loadImage("Button_02", label2, "CONTINUE");
    }//GEN-LAST:event_label2MouseExited

    private void label3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label3MouseEntered
        resourceManager.loadImage("Button_01", label3, "NEW GAME");
    }//GEN-LAST:event_label3MouseEntered

    private void label3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label3MouseExited
        resourceManager.loadImage("Button_02", label3, "NEW GAME");
    }//GEN-LAST:event_label3MouseExited

    private void label4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label4MouseEntered
        resourceManager.loadImage("Button_01", label4, "QUIT");
    }//GEN-LAST:event_label4MouseEntered

    private void label4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label4MouseExited
        resourceManager.loadImage("Button_02", label4, "QUIT");
    }//GEN-LAST:event_label4MouseExited

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JPanel panel3;
    private View.PictureBox pictureBox1;
    private View.PictureBox pictureBox2;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
