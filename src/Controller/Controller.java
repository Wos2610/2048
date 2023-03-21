package Controller;

import Model.Matrix;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Controller {
    private Matrix matrix = new Matrix();
    private Matrix preMatrix = null;
    private int n = Matrix.getN();
    private boolean isMoved = false;
    private boolean isAdded = false;
    
    
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
                return;
            }
        }          
    }
    
    public void moveNumber(KeyEvent e){
        System.out.println("Pre");
        preMatrix.output();
        System.out.println("Move");
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
                // Hien thi message "Nhap sai"
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
    
    
    
}
