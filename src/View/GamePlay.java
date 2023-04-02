package View;

import Controller.Controller;
import Controller.IOBinary;
import Model.Matrix;
import java.awt.Color;
import java.awt.Dimension;
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
    private int sec;
    private int min;
    private Timer timer;
    
    /**
     * Creates new form GamePlay
     */
    public GamePlay() {
        initComponents();
        //controller.renewMatrix();
        initUI();
        initBoard();
        renderBoard();
       //System.out.println(this.getWidth() + " " + this.getHeight());
    }
    
    public GamePlay(Matrix matrix){
        initComponents();
        Matrix preMatrix2 = new Matrix(matrix.getMatrixArray());
        controller.setMatrix(preMatrix2);
        initUI();
        initBoard();
        renderBoard();
    }
                
    void initUI(){
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        controller.addImage("UI/black_arrow_left.png", leftLabel, "");
        controller.addImage("UI/black_arrow_right.png", rightLabel, "");
        controller.addImage("UI/black_arrow_up.png", topLabel, "");
        controller.addImage("UI/black_arrow_down.png", bottomLabel, "");
        //controller.addImage("UI/left2.png", leftLabel, "");
        
        controller.addImage("UI/Icon_Home1.png", homeLabel, "");
        controller.addImage("UI/Icon_Left1.png", undoLabel, "");
        controller.addImage("UI/Icon_Repeat1.png", restartLabel, "");
        
        controller.setFont("gothicb", jLabel1, 16);
        jLabel1.setForeground(Color.WHITE);
        controller.setFont("gothicb", jLabel2, 16);
        jLabel2.setForeground(Color.WHITE);
        controller.setFont("RobotoMono-Medium", currentScoreLabel, 22);
        currentScoreLabel.setForeground(Color.WHITE);
        controller.setFont("RobotoMono-Medium", highestScoreLabel, 22);
        highestScoreLabel.setForeground(Color.WHITE);
        
        panelRound17.setColor(204, 204, 204,204, 204, 204);
        panelRound18.setColor(204, 204, 204,204, 204, 204);
        
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
        
        for(int i = 0; i < 16; i++){
            controller.setFont("RobotoMono-Medium", boardLabel.get(i), 20);
            boardLabel.get(i).setForeground(Color.WHITE);
        }

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
        
        for(PanelRound i : boardPanel){
            i.setPreferredSize(new Dimension(70, 70));
            i.setMinimumSize(new Dimension(60, 60));
            i.setMaximumSize(new Dimension(80, 80));
        }
        // Set size cua JFrame phu hop voi Component ben trong no
        this.pack();
        
        panel4.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
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
                                
                                switch (e.getKeyCode()) {
                                    case KeyEvent.VK_LEFT -> {
                                        controller.addImage("UI/white_arrow_left.png", leftLabel, "");
                                    }
                                    case KeyEvent.VK_RIGHT -> {
                                        controller.addImage("UI/white_arrow_right.png", rightLabel, "");
                                    }
                                    case KeyEvent.VK_UP -> {
                                        controller.addImage("UI/white_arrow_up.png", topLabel, "");
                                    }
                                    case KeyEvent.VK_DOWN -> {
                                       controller.addImage("UI/white_arrow_down.png", bottomLabel, "");
                                    }
                                    default -> {
                                    }
                                } 
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
                                    //controller.returnPrePosition(boardPanel.get(controller.getNewPanelIndex()));
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
                
                
//                Animator animator3 = new Animator.Builder(timingSource)
//                        .setDuration(3000, TimeUnit.MILLISECONDS)
//                        .addTarget(new TimingTargetAdapter(){
//                            @Override
//                            public void timingEvent(Animator source, double fraction) {
//                                switch (e.getKeyCode()) {
//                                    case KeyEvent.VK_LEFT -> {
//                                        controller.addImage("UI/black_arrow_left.png", leftLabel, "");
//                                    }
//                                    case KeyEvent.VK_RIGHT -> {
//                                        controller.addImage("UI/black_arrow_right.png", rightLabel, "");
//                                    }
//                                    case KeyEvent.VK_UP -> {
//                                        controller.addImage("UI/black_arrow_up.png", topLabel, "");
//                                    }
//                                    case KeyEvent.VK_DOWN -> {
//                                       controller.addImage("UI/black_arrow_down.png", bottomLabel, "");
//                                    }
//                                    default -> {
//                                    }
//                                } 
//                            }
//
//                            @Override
//                            public void end(Animator animator3) {
//                                animator3.stop();
//                            }  
//                        })
//                        .build();
                        
                timingSource.init();
                animator1.start();
                animator2.start();
                
                if(animator2.isRunning() == false && animator1.isRunning() == false){
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> {
                            controller.addImage("UI/black_arrow_left.png", leftLabel, "");
                        }
                        case KeyEvent.VK_RIGHT -> {
                            controller.addImage("UI/black_arrow_right.png", rightLabel, "");
                        }
                        case KeyEvent.VK_UP -> {
                            controller.addImage("UI/black_arrow_up.png", topLabel, "");
                        }
                        case KeyEvent.VK_DOWN -> {
                            controller.addImage("UI/black_arrow_down.png", bottomLabel, "");
                        }
                        default -> {
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("Release");
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> {
                        controller.addImage("UI/black_arrow_left.png", leftLabel, "");
                    }
                    case KeyEvent.VK_RIGHT -> {
                        controller.addImage("UI/black_arrow_right.png", rightLabel, "");
                    }
                    case KeyEvent.VK_UP -> {
                        controller.addImage("UI/black_arrow_up.png", topLabel, "");
                    }
                    case KeyEvent.VK_DOWN -> {
                        controller.addImage("UI/black_arrow_down.png", bottomLabel, "");
                    }
                    default -> {
                    }
                } 
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
        renderScore();
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                //value *= 2;
                int value = controller.getMatrix().getValue(i, j);
                //boardPanel.get(index).setOpaque(true);
                switch (value) {
                    case 2 -> boardPanel.get(index).setColor(255, 215, 226, 255, 215, 226);
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
                        //boardPanel.get(index).setSize(65, 65);
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
    
    void renderScore(){
        currentScoreLabel.setText(Integer.toString(controller.getCurrentScore()));
        highestScoreLabel.setText(Integer.toString(controller.getHighestScore()));
    }
    void resetMatrix(){
        Matrix preMatrix1 = new Matrix();
        controller.setPreMatrix(preMatrix1);
    }
    
    void setDisplayCounterTime(boolean x){
        minLabel.setVisible(x);
        secLabel.setVisible(x);
        colonLabel.setVisible(x);
    }
    
    void setCounterTime(int min, int sec){
        this.min = min;
        this.sec = sec;
    }
    
    Map.Entry<Integer, Integer> getCounterTime(){
        Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(min, sec);
        return entry;
    }
    
    Timer getTimer(){
        return timer;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
    
    
    void setDefaultCounterTime(int m, int s){
        this.sec = s;
        this.min = m;
        if(s < 10){
            secLabel.setText("0" + sec);
        }
        else{
            secLabel.setText("" + sec);
        }
        if(m < 10){
            minLabel.setText("0" + min);
        }
        else{
            minLabel.setText("" + min);
        }

        timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sec == 0){
                   sec = 60;
                   min--;
                }
                if(min < 0){
                    min = 0;
                    sec = 0;
                    timer.stop();
                }
                else{
                    sec--;
                    if(min < 10){
                        minLabel.setText("0" + min);
                    }
                    else{
                        minLabel.setText("" + min);
                    }
                    
                    if(sec < 10){
                        secLabel.setText("0" + sec);
                    }
                    else{
                        secLabel.setText("" + sec);
                    }
                    
                    if(min == 0 && sec <= 30){
                        minLabel.setForeground(Color.RED);
                        secLabel.setForeground(Color.RED);
                        colonLabel.setForeground(Color.RED);
                    }
                }
                
            }
            
        });
        
    }
    
    
    void renderCounterTime(){
        System.out.println("render ");
        System.out.println(min + " " + sec);
        setDisplayCounterTime(true);
        timer.start();
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
        homeLabel = new javax.swing.JLabel();
        undoLabel = new javax.swing.JLabel();
        restartLabel = new javax.swing.JLabel();
        minLabel = new javax.swing.JLabel();
        colonLabel = new javax.swing.JLabel();
        secLabel = new javax.swing.JLabel();
        panelRound18 = new View.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        currentScoreLabel = new javax.swing.JLabel();
        panelRound17 = new View.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        highestScoreLabel = new javax.swing.JLabel();
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
        panel2 = new javax.swing.JPanel();
        rightLabel = new javax.swing.JLabel();
        leftLabel = new javax.swing.JLabel();
        bottomLabel = new javax.swing.JLabel();
        topLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(664, 550));
        setResizable(false);

        homeLabel.setText("jLabel1");
        homeLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        homeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeLabelMouseExited(evt);
            }
        });

        undoLabel.setText("jLabel2");
        undoLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        undoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                undoLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                undoLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                undoLabelMouseExited(evt);
            }
        });

        restartLabel.setText("jLabel3");
        restartLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        restartLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                restartLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                restartLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                restartLabelMouseExited(evt);
            }
        });

        minLabel.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        minLabel.setText("00");

        colonLabel.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        colonLabel.setText(":");

        secLabel.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        secLabel.setText("00");

        panelRound18.setPreferredSize(new java.awt.Dimension(100, 52));
        panelRound18.setRoundBottomLeft(15);
        panelRound18.setRoundBottomRight(15);
        panelRound18.setRoundTopLeft(15);
        panelRound18.setRoundTopRight(15);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Score");
        jLabel1.setPreferredSize(new java.awt.Dimension(42, 22));

        currentScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentScoreLabel.setText("jLabel1");

        javax.swing.GroupLayout panelRound18Layout = new javax.swing.GroupLayout(panelRound18);
        panelRound18.setLayout(panelRound18Layout);
        panelRound18Layout.setHorizontalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound18Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
            .addComponent(currentScoreLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelRound18Layout.setVerticalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound18Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(currentScoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        panelRound17.setPreferredSize(new java.awt.Dimension(120, 52));
        panelRound17.setRoundBottomLeft(15);
        panelRound17.setRoundBottomRight(15);
        panelRound17.setRoundTopLeft(15);
        panelRound17.setRoundTopRight(15);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Highest Score");

        highestScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        highestScoreLabel.setText("jLabel2");

        javax.swing.GroupLayout panelRound17Layout = new javax.swing.GroupLayout(panelRound17);
        panelRound17.setLayout(panelRound17Layout);
        panelRound17Layout.setHorizontalGroup(
            panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound17Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addGap(4, 4, 4))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(highestScoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRound17Layout.setVerticalGroup(
            panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound17Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(highestScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(minLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(colonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(secLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 372, Short.MAX_VALUE)
                        .addComponent(panelRound18, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelRound17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(homeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(undoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(restartLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelRound18, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelRound17, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(restartLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(undoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(homeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minLabel)
                            .addComponent(colonLabel)
                            .addComponent(secLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        getContentPane().add(panel1, java.awt.BorderLayout.PAGE_START);

        panel3.setMaximumSize(new java.awt.Dimension(1000, 1000));
        panel3.setMinimumSize(new java.awt.Dimension(400, 400));
        panel3.setPreferredSize(new java.awt.Dimension(650, 310));

        panel4.setMaximumSize(new java.awt.Dimension(450, 450));
        panel4.setMinimumSize(new java.awt.Dimension(400, 400));
        panel4.setLayout(new java.awt.GridLayout(4, 0, 10, 10));

        panelRound1.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound1.setMinimumSize(new java.awt.Dimension(100, 100));
        panelRound1.setPreferredSize(new java.awt.Dimension(100, 100));
        panelRound1.setRoundBottomLeft(15);
        panelRound1.setRoundBottomRight(15);
        panelRound1.setRoundTopLeft(15);
        panelRound1.setRoundTopRight(15);
        panelRound1.setLayout(new java.awt.BorderLayout());

        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("jLabel2");
        label1.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound1.add(label1, java.awt.BorderLayout.CENTER);

        panel4.add(panelRound1);

        panelRound2.setMaximumSize(new java.awt.Dimension(100, 100));
        panelRound2.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound3.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound4.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound5.setMinimumSize(new java.awt.Dimension(100, 100));
        panelRound5.setName(""); // NOI18N
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
        panelRound6.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound7.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound8.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound9.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound10.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound11.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound12.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound13.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound14.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound15.setMinimumSize(new java.awt.Dimension(100, 100));
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
        panelRound16.setMinimumSize(new java.awt.Dimension(100, 100));
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

        panel2.setPreferredSize(new java.awt.Dimension(40, 40));

        rightLabel.setText("jLabel2");
        rightLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rightLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rightLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rightLabelMousePressed(evt);
            }
        });

        leftLabel.setText("jLabel3");
        leftLabel.setMaximumSize(new java.awt.Dimension(300, 300));
        leftLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        leftLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                leftLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                leftLabelMouseExited(evt);
            }
        });

        bottomLabel.setText("jLabel4");
        bottomLabel.setMaximumSize(new java.awt.Dimension(100, 100));
        bottomLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        bottomLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bottomLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bottomLabelMouseExited(evt);
            }
        });

        topLabel.setText("jLabel5");
        topLabel.setMaximumSize(new java.awt.Dimension(100, 100));
        topLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        topLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                topLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                topLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(leftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bottomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(topLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rightLabel)
                .addContainerGap(352, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(topLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(leftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(rightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rightLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightLabelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rightLabelMousePressed

    private void restartLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restartLabelMouseClicked
        System.out.println("Restart");
        this.setCounterTime(5, 0);
        controller.setCurrentScore(0);
        controller.setMatrix(new Matrix());
        renderBoard();
        panel4.requestFocus();
    }//GEN-LAST:event_restartLabelMouseClicked

    private void undoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_undoLabelMouseClicked
        System.out.println("Undo");
        controller.setMatrix(controller.getPreMatrix());
        renderBoard();
        panel4.requestFocus();
    }//GEN-LAST:event_undoLabelMouseClicked

    private void homeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLabelMouseClicked
        System.out.println("Home");
        IOBinary.writeMatrixToFile(controller.getMatrix(), "Matrix.txt");
        controller.writeScoreToFile("Score");
        if(controller.getLevel() == 2){
            controller.writeTimerToFile();
            timer.stop();
        }

        controller.getHomeState().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_homeLabelMouseClicked

    private void homeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLabelMouseEntered
        controller.addImage("UI/Icon_Home.png", homeLabel, "");
    }//GEN-LAST:event_homeLabelMouseEntered

    private void homeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLabelMouseExited
        controller.addImage("UI/Icon_Home1.png", homeLabel, "");
    }//GEN-LAST:event_homeLabelMouseExited

    private void undoLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_undoLabelMouseEntered
        controller.addImage("UI/Icon_Left.png", undoLabel, "");
    }//GEN-LAST:event_undoLabelMouseEntered

    private void undoLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_undoLabelMouseExited
        controller.addImage("UI/Icon_Left1.png", undoLabel, "");
    }//GEN-LAST:event_undoLabelMouseExited

    private void restartLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restartLabelMouseEntered
        controller.addImage("UI/Icon_Repeat.png", restartLabel, "");
    }//GEN-LAST:event_restartLabelMouseEntered

    private void restartLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_restartLabelMouseExited
         controller.addImage("UI/Icon_Repeat1.png", restartLabel, "");
    }//GEN-LAST:event_restartLabelMouseExited

    private void topLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topLabelMouseEntered
        controller.addImage("UI/white_arrow_up.png", topLabel, "");
    }//GEN-LAST:event_topLabelMouseEntered

    private void topLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topLabelMouseExited
        controller.addImage("UI/black_arrow_up.png", topLabel, "");
    }//GEN-LAST:event_topLabelMouseExited

    private void rightLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightLabelMouseEntered
        controller.addImage("UI/white_arrow_right.png", rightLabel, "");
    }//GEN-LAST:event_rightLabelMouseEntered

    private void rightLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightLabelMouseExited
        controller.addImage("UI/black_arrow_right.png", rightLabel, "");
    }//GEN-LAST:event_rightLabelMouseExited

    private void bottomLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bottomLabelMouseEntered
        controller.addImage("UI/white_arrow_down.png", bottomLabel, "");
    }//GEN-LAST:event_bottomLabelMouseEntered

    private void bottomLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bottomLabelMouseExited
        controller.addImage("UI/black_arrow_down.png", bottomLabel, "");
    }//GEN-LAST:event_bottomLabelMouseExited

    private void leftLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftLabelMouseEntered
        controller.addImage("UI/white_arrow_left.png", leftLabel, "");
    }//GEN-LAST:event_leftLabelMouseEntered

    private void leftLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftLabelMouseExited
        controller.addImage("UI/black_arrow_left.png", leftLabel, "");
    }//GEN-LAST:event_leftLabelMouseExited

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
    private javax.swing.JLabel bottomLabel;
    private javax.swing.JLabel colonLabel;
    private javax.swing.JLabel currentScoreLabel;
    private javax.swing.JLabel highestScoreLabel;
    private javax.swing.JLabel homeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel leftLabel;
    private javax.swing.JLabel minLabel;
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
    private View.PanelRound panelRound17;
    private View.PanelRound panelRound18;
    private View.PanelRound panelRound2;
    private View.PanelRound panelRound3;
    private View.PanelRound panelRound4;
    private View.PanelRound panelRound5;
    private View.PanelRound panelRound6;
    private View.PanelRound panelRound7;
    private View.PanelRound panelRound8;
    private View.PanelRound panelRound9;
    private javax.swing.JLabel restartLabel;
    private javax.swing.JLabel rightLabel;
    private javax.swing.JLabel secLabel;
    private javax.swing.JLabel topLabel;
    private javax.swing.JLabel undoLabel;
    // End of variables declaration//GEN-END:variables
}
