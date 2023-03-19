package pkg2048;

import Model.Matrix;
import View.GamePlay;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        //matrix.output();
        new GamePlay().setVisible(true);
    }
    
}
