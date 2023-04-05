package Controller;

import Model.Matrix;
import View.ChooseLevel;
import View.GamePlay;
import View.Home;
import View.IntroState;
import View.Message;
import View.PanelRound;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
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
    private int newPanelIndex;
    private Rectangle pre;
    private int currentScore;
    private int highestScore;
    private Home homeState;
    private GamePlay gamePlayState;
    private ChooseLevel chooseLevelState;
    private IntroState introState;
    private Message messageState;
    private int level;
    private int secOfTimer;
    private int minOfTimer;
    private int isFirst2048;
    private ResourceManager resourceManager;
    
    
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
                int randNum = ThreadLocalRandom.current().nextInt(5);
                int randValue = (randNum == 0) ? 4 : 2;
                matrix.setValue(randRow, randCol, randValue);
                // Index của Panel : 0 -> 15
                newPanelIndex = (randRow - 1) * 4 + randCol - 1;
                return;
            }
        }          
    }
    
    
    public void moveNumber(KeyEvent e){
        System.out.println("Move");
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

    public IntroState getIntroState() {
        return introState;
    }

    public void setIntroState(IntroState introState) {
        this.introState = introState;
    }

    public Message getMessageState() {
        return messageState;
    }

    public void setMessageState(Message messageState) {
        this.messageState = messageState;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int isIsFirst2048() {
        return isFirst2048;
    }

    public void setIsFirst2048(int isFirst2048) {
        this.isFirst2048 = isFirst2048;
    }
    
    TimingSource timingSource = new SwingTimerTimingSource();
    private Animator animator;
    public void addNewPanelAnimation(PanelRound panel){
        if(animator != null && animator.isRunning()){
            animator.stop();
        }
        animator = new Animator.Builder(timingSource)
                .setDuration(50, TimeUnit.MILLISECONDS)
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
   
    
    public void keyPressAnimation(JLabel label, KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                resourceManager.loadImage("UI/white_arrow_left.png", label, "");
            }
            case KeyEvent.VK_RIGHT -> {
                resourceManager.loadImage("UI/white_arrow_right.png", label, "");
            }
            case KeyEvent.VK_UP -> {
                resourceManager.loadImage("UI/white_arrow_up.png", label, "");
            }
            case KeyEvent.VK_DOWN -> {
               resourceManager.loadImage("UI/white_arrow_down.png", label, "");
            }
            default -> {
            }
        } 
    }
    
    public void readScoreFromFile(String pathName){
        try {
            FileReader reader = new FileReader(pathName + ".txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
            int index = 1;
            while ((line = bufferedReader.readLine()) != null && index < 4) {
                switch (index) {
                    case 1 -> currentScore = Integer.parseInt(line);
                    case 2 -> highestScore = Integer.parseInt(line);
                    case 3 -> isFirst2048 = Integer.parseInt(line);
                }
                index++;
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeScoreToFile(String pathName){
        try {
            FileOutputStream outputStream = new FileOutputStream(pathName + ".txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
             
            bufferedWriter.write(Integer.toString(currentScore));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(highestScore));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(isFirst2048));
            
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void readTimerFromFile(){
        try {
            FileReader reader = new FileReader("Time.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String i;
            int index = 1;
            while ((i = bufferedReader.readLine()) != null && index < 4) {
                i.trim();
                if(index == 1){
                    level = Integer.parseInt(i);
                    
                    if(level == 1){
                        break;
                    }
                }
                else if(index == 2){
                    this.getGamePlayState().setMin(Integer.parseInt(i));
                    
                }
                else{
                    this.getGamePlayState().setSec(Integer.parseInt(i));
                    
                }
                index++;
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeTimerToFile(){
        try {
            FileOutputStream outputStream = new FileOutputStream("Time.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
             
            bufferedWriter.write(Integer.toString(level));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(this.getGamePlayState().getMin()));
            bufferedWriter.newLine();
            bufferedWriter.write(Integer.toString(this.getGamePlayState().getSec()));
            
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}