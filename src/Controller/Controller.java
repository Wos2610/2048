package Controller;

import Model.Matrix;
import View.PanelRound;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
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
    
    private static Controller instance = null;
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
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
        System.out.println("After");
        preMatrix.output();
    }
    
    public void sumOfValue(KeyEvent e){
        System.out.println("Sum");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                for(int i = 1; i <= n; i++){
                    for(int j = 1; j <= n; j++){
                        if(Objects.equals(matrix.getValue(i, j), matrix.getValue(i, j + 1))){
                            isAdded = true;
                            matrix.setValue(i, j, 2 * matrix.getValue(i, j + 1));
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
                            matrix.setValue(i - 1, j, 0);
                        }
                    }
                }
            }
            default -> {
            }
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
                .setDuration(300, TimeUnit.MILLISECONDS)
                .setInterpolator(new SplineInterpolator(0.4, 0.0, 0.2, 1.0))
                .addTarget(PropertySetter.getTarget(panel, "size", new Dimension(0, 0), new Dimension(70, 70) ))
                .build();
        
        timingSource.init();
        animator.start();
        
    }
    
    
}