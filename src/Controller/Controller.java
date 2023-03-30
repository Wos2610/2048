package Controller;

import Model.Matrix;
import View.ChooseLevel;
import View.GamePlay;
import View.Home;
import View.PanelRound;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.PropertySetter;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.core.animation.timing.interpolators.SplineInterpolator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

public class Controller {
    private Matrix matrix = new Matrix();
    private Matrix preMatrix = new Matrix();
    private int n = Matrix.getN();
    private boolean isMoved = false;
    private boolean isAdded = false;
    private Animator animator;
    private int newPanelIndex;
    private Rectangle pre;
    private int currentScore;
    private int highestScore;
    private Home homeState;
    private GamePlay gamePlayState;
    private ChooseLevel chooseLevelState;
    int level;
    
    private static Controller instance = null;
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    public void renewMatrix(){
        matrix = new Matrix();
    }
    
    public void loadGame(){
        
    }
    
    public void addNewNumber(){
        System.out.println("New number");
        while(true){
            int randRow = ThreadLocalRandom.current().nextInt(1, n + 1);
            int randCol = ThreadLocalRandom.current().nextInt(1, n + 1);
            if(matrix.getValue(randRow, randCol) == 0){
                int randNum = ThreadLocalRandom.current().nextInt(2);
                int randValue = (randNum == 0) ? 2 : 4;
                matrix.setValue(randRow, randCol, randValue);
                // Index của Panel : 0 -> 15
                newPanelIndex = (randRow - 1) * 4 + randCol - 1;
                return;
            }
        }          
    }
    
    
    public void moveNumber(KeyEvent e){
        isMoved = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                for(int i = 1; i <= n; i++){
                    for(int j = 1; j <= n; j++){
                        if(matrix.getValue(i, j) == 0){
                            int temp = j + 1;
                            while(temp <= n && matrix.getValue(i, temp) == 0){
                                temp++;
                            }
                            if(temp <= n){
                                isMoved = true;
                                matrix.setValue(i, j, matrix.getValue(i, temp));
                                matrix.setValue(i, temp, 0);
                            }
                        }
                    }
                }
            }
            case KeyEvent.VK_RIGHT -> {
                for(int i = 1; i <= n; i++){
                    for(int j = n; j >= 1; j--){
                        if(matrix.getValue(i, j) == 0){
                            int temp = j - 1;
                            while(temp >= 1 && matrix.getValue(i, temp) == 0){
                                temp--;
                            }
                            if(temp >= 1){
                                isMoved = true;
                                matrix.setValue(i, j, matrix.getValue(i, temp));
                                matrix.setValue(i, temp, 0);
                            }
                        }
                    }
                }
            }
            case KeyEvent.VK_UP -> {
                for(int i = 1; i <= n; i++){
                    for(int j = 1; j <= n; j++){
                        if(matrix.getValue(i, j) == 0){
                            int temp = i + 1;
                            while(temp <= n && matrix.getValue(temp, j) == 0){
                                temp++;
                            }
                            if(temp <= n){
                                isMoved = true;
                                matrix.setValue(i, j, matrix.getValue(temp, j));
                                matrix.setValue(temp, j, 0);
                            }
                        }
                    }
                }
            }
            case KeyEvent.VK_DOWN -> {
                for(int i = n; i >= 1; i--){
                    for(int j = 1; j <= n; j++){
                        if(matrix.getValue(i, j) == 0){
                            int temp = i - 1;
                            while(temp >= 1 && matrix.getValue(temp, j) == 0){
                                temp--;
                            }
                            if(temp >= 1){
                                isMoved = true;
                                matrix.setValue(i, j, matrix.getValue(temp, j));
                                matrix.setValue(temp, j, 0);
                            }
                        }
                    }
                }
            }
            default -> {
            }
        }
        System.out.println(isMoved);
    }
    
    public void sumOfValue(KeyEvent e){
        System.out.println("Sum");
        isAdded = false;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                for(int i = 1; i <= n; i++){
                    for(int j = 1; j <= n; j++){
                        if(Objects.equals(matrix.getValue(i, j), matrix.getValue(i, j + 1))){
                            isAdded = true;
                            matrix.setValue(i, j, 2 * matrix.getValue(i, j + 1));
                            currentScore += 2 * matrix.getValue(i, j + 1);
                            matrix.setValue(i, j + 1, 0);
                        }
                    }
                }
            }
            case KeyEvent.VK_RIGHT -> {
                for(int i = 1; i <= n; i++){
                    for(int j = n; j >= 1; j--){
                        if(Objects.equals(matrix.getValue(i, j), matrix.getValue(i, j - 1))){
                            isAdded = true;
                            matrix.setValue(i, j, 2 * matrix.getValue(i, j - 1));
                            currentScore += 2 * matrix.getValue(i, j - 1);
                            matrix.setValue(i, j - 1, 0);
                        }
                    }
                }
            }
            case KeyEvent.VK_UP -> {
                for(int i = 1; i <= n; i++){
                    for(int j = 1; j <= n; j++){
                        if(Objects.equals(matrix.getValue(i, j), matrix.getValue(i + 1, j))){
                            isAdded = true;
                            matrix.setValue(i, j, 2 * matrix.getValue(i + 1, j));
                            currentScore += 2 * matrix.getValue(i + 1, j);
                            matrix.setValue(i + 1, j, 0);
                        }
                    }
                }
            }
            case KeyEvent.VK_DOWN -> {
                for(int i = n; i >= 1; i--){
                    for(int j = 1; j <= n; j++){
                        if(Objects.equals(matrix.getValue(i, j), matrix.getValue(i - 1, j))){
                            isAdded = true;
                            matrix.setValue(i, j, 2 * matrix.getValue(i - 1, j));
                            currentScore += 2 * matrix.getValue(i - 1, j);
                            matrix.setValue(i - 1, j, 0);
                        }
                    }
                }
            }
            default -> {
            }
        } 
        System.out.println(isAdded);
        if(currentScore > highestScore){
            highestScore = currentScore;
        }
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
    
    public Matrix getPreMatrix() {
        return preMatrix;
    }

    public void setPreMatrix(Matrix matrix) {
        this.preMatrix = matrix;
    }

    public boolean isIsMoved() {
        return isMoved;
    }

    public void setIsMoved(boolean isMoved) {
        this.isMoved = isMoved;
    }

    public boolean isIsAdded() {
        return isAdded;
    }

    public void setIsAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }

    public int getNewPanelIndex() {
        return newPanelIndex;
    }

    public void setNewPanelIndex(int newPanelIndex) {
        this.newPanelIndex = newPanelIndex;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public Home getHomeState() {
        return homeState;
    }

    public void setHomeState(Home homeState) {
        this.homeState = homeState;
    }

    public GamePlay getGamePlayState() {
        return gamePlayState;
    }

    public void setGamePlayState(GamePlay gamePlayState) {
        this.gamePlayState = gamePlayState;
    }

    public ChooseLevel getChooseLevelState() {
        return chooseLevelState;
    }

    public void setChooseLevelState(ChooseLevel chooseLevelState) {
        this.chooseLevelState = chooseLevelState;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    
    

    public void addNewPanelAnimation(PanelRound panel){
        if(animator != null && animator.isRunning()){
            animator.stop();
        }
        panel.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int height = panel.getHeight();
                int x = panel.getX() + panel.getWidth() / 2 - width / 2;
                int y = panel.getY() + panel.getHeight() / 2 - height / 2;
                panel.setBounds(x, y, width, height);
            }
            
        });
        
        TimingSource timingSource = new SwingTimerTimingSource();
        animator = new Animator.Builder(timingSource)
                .setDuration(400, TimeUnit.MILLISECONDS)
                .setInterpolator(new SplineInterpolator(0.4, 0.0, 0.2, 1.0))
                .addTarget(PropertySetter.getTarget(panel, "size", new Dimension(0, 0), new Dimension(70, 70) ))
                .build();
        
        timingSource.init();
        animator.start();
        
    }
    
    public void loadMatrixFromFile(){
        Matrix preMatrix2 = new Matrix(IOBinary.readMatrixFromFile("Matrix.txt").getMatrixArray());
        setMatrix(preMatrix2);
    }
    
    public void addImage(String pathName, JLabel label, String text){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(pathName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setIcon(imageIcon);
        label.setText(text);
    }
    
    public void keyPressAnimation(JLabel label, KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                this.addImage("UI/white_arrow_left.png", label, "");
            }
            case KeyEvent.VK_RIGHT -> {
                this.addImage("UI/white_arrow_right.png", label, "");
            }
            case KeyEvent.VK_UP -> {
                this.addImage("UI/white_arrow_up.png", label, "");
            }
            case KeyEvent.VK_DOWN -> {
               this.addImage("UI/white_arrow_down.png", label, "");
            }
            default -> {
            }
        } 
    }
    
    public void readScoreFromFile(String pathName){
        try {
            FileReader reader = new FileReader("Score.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
            int index = 1;
            while ((line = bufferedReader.readLine()) != null && index < 3) {
                if(index == 1){
                    currentScore = Integer.parseInt(line);
                }
                else{
                    highestScore = Integer.parseInt(line);
                }
                index++;
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeScoreToFile(String pathName, int currentScore, int highestScore){
        try {
            FileOutputStream outputStream = new FileOutputStream("Score.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
             
            bufferedWriter.write(String.valueOf(currentScore));
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(highestScore));
             
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}