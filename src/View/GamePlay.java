package View;

import Controller.Controller;
import Model.Matrix;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

public class GamePlay extends JFrame{
    
    private Controller controller = Controller.getInstance();
    private ArrayList<JLabel> boardLabel = new ArrayList<>();
    private ArrayList<PanelRound> boardPanel = new ArrayList<>();
    int n = controller.getMatrix().getN();
    private int newPanelIndex;
    
    /**
     * Creates new form GamePlay
     */
    public GamePlay() {
        initComponents();
        initUI();
        initBoard();
        renderBoard();
        
    }
                
    void initUI(){
        this.setSize(650, 480);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        //panelRound1.setColor(251, 96, 127, 253, 188, 180);
        //panelRound2.setColor(251, 96, 127, 253, 188, 180);
    }
    
    
    void initBoard(){
        boardLabel.add(label1);
        boardLabel.add(label2);
        boardLabel.add(label3);
        boardLabel.add(label4);
        boardLabel.add(label5);
        boardLabel.add(label6);
        boardLabel.add(label7);
        boardLabel.add(label8);
        boardLabel.add(label9);
        boardLabel.add(label10);
        boardLabel.add(label11);
        boardLabel.add(label12);
        boardLabel.add(label13);
        boardLabel.add(label14);
        boardLabel.add(label15);
        boardLabel.add(label16);

        boardPanel.add(panelRound1);
        boardPanel.add(panelRound2);
        boardPanel.add(panelRound3);
        boardPanel.add(panelRound4);
        boardPanel.add(panelRound5);
        boardPanel.add(panelRound6);
        boardPanel.add(panelRound7);
        boardPanel.add(panelRound8);
        boardPanel.add(panelRound9);
        boardPanel.add(panelRound10);
        boardPanel.add(panelRound11);
        boardPanel.add(panelRound12);
        boardPanel.add(panelRound13);
        boardPanel.add(panelRound14);
        boardPanel.add(panelRound15);
        boardPanel.add(panelRound16);
        
        panel4.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
//                Matrix preMatrix1 = new Matrix(controller.getMatrix().getMatrixArray());
//                controller.setPreMatrix(preMatrix1);
//                controller.moveNumber(e);
//                Matrix preMatrix2 = new Matrix(controller.getMatrix().getMatrixArray());
//                controller.setPreMatrix(preMatrix2);
//                controller.sumOfValue(e);
//                controller.moveNumber(e);
//                renderBoard();

//                int delay = 300; // Thời gian trễ là 1 giây
//                Timer timer = new Timer(delay, new ActionListener() {
//                    boolean isFirstTime = true;
//
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        if (isFirstTime == true) {
//                            System.out.println("Delay");
//                            isFirstTime = false;
//                            if (controller.isIsMoved() == true || controller.isIsAdded() == true) {
//                                Matrix preMatrix3 = new Matrix(controller.getMatrix().getMatrixArray());
//                                controller.setPreMatrix(preMatrix3);
//                                controller.addNewNumber();
//                                controller.addNewPanelAnimation(boardPanel.get(controller.getNewPanelIndex()));
//                                controller.setIsMoved(false);
//                                renderBoard();
//                                controller.getMatrix().output();
//                            } else {
//                                // hien thi thong bao
//                            }
//                        } else {
//                            ((Timer) e.getSource()).stop();
//                        }
//                    }
//                });
//                timer.start(); // Bắt đầu thực hiện Timer

                TimingSource timingSource = new SwingTimerTimingSource();
                Animator animator1 = new Animator.Builder(timingSource)
                        .setDuration(1, TimeUnit.MILLISECONDS)
                        .addTarget(new TimingTargetAdapter(){
                            @Override
                            public void timingEvent(Animator source, double fraction) {
                                Matrix preMatrix1 = new Matrix(controller.getMatrix().getMatrixArray());
                                controller.setPreMatrix(preMatrix1);
                                controller.moveNumber(e);
                                Matrix preMatrix2 = new Matrix(controller.getMatrix().getMatrixArray());
                                controller.setPreMatrix(preMatrix2);
                                controller.sumOfValue(e);
                                controller.moveNumber(e);
                                renderBoard();
                            }

                    @Override
                    public void end(Animator animator1) {
                        animator1.stop();
                    }
                            
                            
                        })
                        .build();
                
                Animator animator2 = new Animator.Builder(timingSource)
                        .setDuration(10, TimeUnit.MILLISECONDS)
                        .addTarget(new TimingTargetAdapter(){
                            @Override
                            public void timingEvent(Animator source, double fraction) {
                                System.out.println("Delay");
                                if (controller.isIsMoved() == true || controller.isIsAdded() == true) {
                                    Matrix preMatrix3 = new Matrix(controller.getMatrix().getMatrixArray());
                                    controller.setPreMatrix(preMatrix3);
                                    controller.addNewNumber();
                                    controller.addNewPanelAnimation(boardPanel.get(controller.getNewPanelIndex()));
                                    controller.setIsMoved(false);
                                    renderBoard();
                                    controller.getMatrix().output();
                                } else {
                                    // hien thi thong bao
                                }
                            }

                            @Override
                            public void end(Animator animator2) {
                                animator2.stop();
                            }  
                        })
                        .build();
                
                timingSource.init();
                animator1.start();
                animator2.start();
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Release");
            }

            @Override
            public void keyTyped(KeyEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        
        panel4.requestFocus();
    }
    
    void renderBoard(){
        controller.getMatrix().output();
        int index = 0;
        //int value = 1;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                //value *= 2;
                int value = controller.getMatrix().getValue(i, j);
                //boardPanel.get(index).setOpaque(true);
                switch (value) {
                    case 2 -> boardPanel.get(index).setColor(254, 221, 228, 254, 221, 228);
                    case 4 -> boardPanel.get(index).setColor(253, 175, 191, 253, 175, 191);
                    case 8 -> boardPanel.get(index).setColor(252, 130, 154, 252, 130, 154);
                    case 16 -> boardPanel.get(index).setColor(245, 173, 173, 245, 173, 173);
                    case 32 -> boardPanel.get(index).setColor(241, 132, 132, 241, 132, 132);
                    case 64 -> boardPanel.get(index).setColor(237, 104, 104, 237, 104, 104);
                    case 128-> boardPanel.get(index).setColor(228, 211, 232, 228, 211, 232);
                    case 256 -> boardPanel.get(index).setColor(217, 193, 222, 217, 193, 225);
                    case 512 -> boardPanel.get(index).setColor(206, 176, 213, 206, 176, 213);
                    case 1024 -> boardPanel.get(index).setColor(189, 149, 199, 189, 149, 199);
                    case 2048 -> boardPanel.get(index).setColor(169, 116, 182,169, 116, 182);
                    case 4096 -> boardPanel.get(index).setColor(200, 219, 237, 200, 219, 237);
                    case 8192 -> boardPanel.get(index).setColor(168, 197, 226, 168, 197, 226);
                    case 16384 -> boardPanel.get(index).setColor(135, 175, 215,135, 175, 215);
                    case 32768 -> boardPanel.get(index).setColor(96, 149, 202, 96, 149, 202);
                    case 65536 -> boardPanel.get(index).setColor(71, 133, 194, 71, 133, 194);
                    default -> {
                        boardPanel.get(index).setColor(229, 228, 226, 213, 211, 209);
                        //boardPanel.get(index).setOpaque(false);
                    }
                }
                if(value == 0){
                    boardLabel.get(index).setVisible(false);
                }
                else{
                    boardLabel.get(index).setVisible(true);
                }
                boardLabel.get(index).setText(String.valueOf(value));
                index++;
            }
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

        panel1 = new javax.swing.JPanel();
        homeButton = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        restartButton = new javax.swing.JButton();
        panel2 = new javax.swing.JPanel();
        panel3 = new javax.swing.JPanel();
        panel4 = new javax.swing.JPanel();
        panelRound1 = new View.PanelRound();
        label1 = new javax.swing.JLabel();
        panelRound2 = new View.PanelRound();
        label2 = new javax.swing.JLabel();
        panelRound3 = new View.PanelRound();
        label3 = new javax.swing.JLabel();
        panelRound4 = new View.PanelRound();
        label4 = new javax.swing.JLabel();
        panelRound5 = new View.PanelRound();
        label5 = new javax.swing.JLabel();
        panelRound6 = new View.PanelRound();
        label6 = new javax.swing.JLabel();
        panelRound7 = new View.PanelRound();
        label7 = new javax.swing.JLabel();
        panelRound8 = new View.PanelRound();
        label8 = new javax.swing.JLabel();
        panelRound9 = new View.PanelRound();
        label9 = new javax.swing.JLabel();
        panelRound10 = new View.PanelRound();
        label10 = new javax.swing.JLabel();
        panelRound11 = new View.PanelRound();
        label11 = new javax.swing.JLabel();
        panelRound12 = new View.PanelRound();
        label12 = new javax.swing.JLabel();
        panelRound13 = new View.PanelRound();
        label13 = new javax.swing.JLabel();
        panelRound14 = new View.PanelRound();
        label14 = new javax.swing.JLabel();
        panelRound15 = new View.PanelRound();
        label15 = new javax.swing.JLabel();
        panelRound16 = new View.PanelRound();
        label16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        homeButton.setText("Home");
        homeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeButtonMouseClicked(evt);
            }
        });

        undoButton.setText("Undo");
        undoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                undoButtonMouseClicked(evt);
            }
        });

        restartButton.setText("Restart");
        restartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                restartButtonMouseClicked(evt);
            }
        });
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(371, Short.MAX_VALUE)
                .addComponent(homeButton)
                .addGap(18, 18, 18)
                .addComponent(undoButton)
                .addGap(18, 18, 18)
                .addComponent(restartButton)
                .addGap(27, 27, 27))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(homeButton)
                    .addComponent(undoButton)
                    .addComponent(restartButton))
                .addGap(33, 33, 33))
        );

        getContentPane().add(panel1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 79, Short.MAX_VALUE)
        );

        getContentPane().add(panel2, java.awt.BorderLayout.PAGE_END);

        panel4.setLayout(new java.awt.GridLayout(4, 0, 10, 10));

        panelRound1.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound1.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound1.setRoundBottomLeft(15);
        panelRound1.setRoundBottomRight(15);
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);
        panelRound1.setLayout(new java.awt.BorderLayout());

        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("jLabel2");
        panelRound1.add(label1, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound1);

        panelRound2.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound2.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound2.setRoundBottomLeft(15);
        panelRound2.setRoundBottomRight(15);
        panelRound2.setRoundTopLeft(15);
        panelRound2.setRoundTopRight(15);
        panelRound2.setLayout(new java.awt.BorderLayout());

        label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label2.setText("jLabel1");
        panelRound2.add(label2, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound2);

        panelRound3.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound3.setMinimumSize(new java.awt.Dimension(0, 0));
        panelRound3.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound3.setRoundBottomLeft(15);
        panelRound3.setRoundBottomRight(15);
        panelRound3.setRoundTopLeft(15);
        panelRound3.setRoundTopRight(15);
        panelRound3.setLayout(new java.awt.BorderLayout());

        label3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label3.setText("jLabel1");
        panelRound3.add(label3, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound3);

        panelRound4.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound4.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound4.setRoundBottomLeft(15);
        panelRound4.setRoundBottomRight(15);
        panelRound4.setRoundTopLeft(15);
        panelRound4.setRoundTopRight(15);
        panelRound4.setLayout(new java.awt.BorderLayout());

        label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label4.setText("jLabel1");
        panelRound4.add(label4, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound4);

        panelRound5.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound5.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound5.setRoundBottomLeft(15);
        panelRound5.setRoundBottomRight(15);
        panelRound5.setRoundTopLeft(15);
        panelRound5.setRoundTopRight(15);
        panelRound5.setLayout(new java.awt.BorderLayout());

        label5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label5.setText("jLabel1");
        panelRound5.add(label5, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound5);

        panelRound6.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound6.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound6.setRoundBottomLeft(15);
        panelRound6.setRoundBottomRight(15);
        panelRound6.setRoundTopLeft(15);
        panelRound6.setRoundTopRight(15);
        panelRound6.setLayout(new java.awt.BorderLayout());

        label6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label6.setText("jLabel1");
        panelRound6.add(label6, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound6);

        panelRound7.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound7.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound7.setRoundBottomLeft(15);
        panelRound7.setRoundBottomRight(15);
        panelRound7.setRoundTopLeft(15);
        panelRound7.setRoundTopRight(15);
        panelRound7.setLayout(new java.awt.BorderLayout());

        label7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label7.setText("jLabel1");
        panelRound7.add(label7, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound7);

        panelRound8.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound8.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound8.setRoundBottomLeft(15);
        panelRound8.setRoundBottomRight(15);
        panelRound8.setRoundTopLeft(15);
        panelRound8.setRoundTopRight(15);
        panelRound8.setLayout(new java.awt.BorderLayout());

        label8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label8.setText("jLabel1");
        panelRound8.add(label8, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound8);

        panelRound9.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound9.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound9.setRoundBottomLeft(15);
        panelRound9.setRoundBottomRight(15);
        panelRound9.setRoundTopLeft(15);
        panelRound9.setRoundTopRight(15);
        panelRound9.setLayout(new java.awt.BorderLayout());

        label9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label9.setText("jLabel1");
        panelRound9.add(label9, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound9);

        panelRound10.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound10.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound10.setRoundBottomLeft(15);
        panelRound10.setRoundBottomRight(15);
        panelRound10.setRoundTopLeft(15);
        panelRound10.setRoundTopRight(15);
        panelRound10.setLayout(new java.awt.BorderLayout());

        label10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label10.setText("jLabel1");
        panelRound10.add(label10, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound10);

        panelRound11.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound11.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound11.setRoundBottomLeft(15);
        panelRound11.setRoundBottomRight(15);
        panelRound11.setRoundTopLeft(15);
        panelRound11.setRoundTopRight(15);
        panelRound11.setLayout(new java.awt.BorderLayout());

        label11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label11.setText("jLabel1");
        panelRound11.add(label11, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound11);

        panelRound12.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound12.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound12.setRoundBottomLeft(15);
        panelRound12.setRoundBottomRight(15);
        panelRound12.setRoundTopLeft(15);
        panelRound12.setRoundTopRight(15);
        panelRound12.setLayout(new java.awt.BorderLayout());

        label12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label12.setText("jLabel1");
        panelRound12.add(label12, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound12);

        panelRound13.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound13.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound13.setRoundBottomLeft(15);
        panelRound13.setRoundBottomRight(15);
        panelRound13.setRoundTopLeft(15);
        panelRound13.setRoundTopRight(15);
        panelRound13.setLayout(new java.awt.BorderLayout());

        label13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label13.setText("jLabel1");
        panelRound13.add(label13, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound13);

        panelRound14.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound14.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound14.setRoundBottomLeft(15);
        panelRound14.setRoundBottomRight(15);
        panelRound14.setRoundTopLeft(15);
        panelRound14.setRoundTopRight(15);
        panelRound14.setLayout(new java.awt.BorderLayout());

        label14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label14.setText("jLabel1");
        panelRound14.add(label14, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound14);

        panelRound15.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound15.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound15.setRoundBottomLeft(15);
        panelRound15.setRoundBottomRight(15);
        panelRound15.setRoundTopLeft(15);
        panelRound15.setRoundTopRight(15);
        panelRound15.setLayout(new java.awt.BorderLayout());

        label15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label15.setText("jLabel1");
        panelRound15.add(label15, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound15);

        panelRound16.setToolTipText("12");
        panelRound16.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound16.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound16.setRoundBottomLeft(15);
        panelRound16.setRoundBottomRight(15);
        panelRound16.setRoundTopLeft(15);
        panelRound16.setRoundTopRight(15);
        panelRound16.setLayout(new java.awt.BorderLayout());

        label16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label16.setText("jLabel1");
        panelRound16.add(label16, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound16);

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        );

        getContentPane().add(panel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_restartButtonActionPerformed

    private void undoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_undoButtonMouseClicked
        System.out.println("Undo");
        controller.setMatrix(controller.getPreMatrix());
        renderBoard();
        panel4.requestFocus();
    }//GEN-LAST:event_undoButtonMouseClicked

    private void restartButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restartButtonMouseClicked
        System.out.println("Restart");
        controller.setMatrix(new Matrix());
        renderBoard();
        panel4.requestFocus();
    }//GEN-LAST:event_restartButtonMouseClicked

    private void homeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeButtonMouseClicked
        new Home().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_homeButtonMouseClicked

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
            java.util.logging.Logger.getLogger(GamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GamePlay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GamePlay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label10;
    private javax.swing.JLabel label11;
    private javax.swing.JLabel label12;
    private javax.swing.JLabel label13;
    private javax.swing.JLabel label14;
    private javax.swing.JLabel label15;
    private javax.swing.JLabel label16;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel label9;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private View.PanelRound panelRound1;
    private View.PanelRound panelRound10;
    private View.PanelRound panelRound11;
    private View.PanelRound panelRound12;
    private View.PanelRound panelRound13;
    private View.PanelRound panelRound14;
    private View.PanelRound panelRound15;
    private View.PanelRound panelRound16;
    private View.PanelRound panelRound2;
    private View.PanelRound panelRound3;
    private View.PanelRound panelRound4;
    private View.PanelRound panelRound5;
    private View.PanelRound panelRound6;
    private View.PanelRound panelRound7;
    private View.PanelRound panelRound8;
    private View.PanelRound panelRound9;
    private javax.swing.JButton restartButton;
    private javax.swing.JButton undoButton;
    // End of variables declaration//GEN-END:variables
}
