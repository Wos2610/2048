package Controller;

import Model.Matrix;
import View.PanelRound;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class IOBinary {
    public static void writeMatrixToFile(Matrix matrix, String filename) {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filename));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(matrix);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static Matrix readMatrixFromFile(String filename) {
        Matrix matrix = new Matrix();

        try {
            FileInputStream fi = new FileInputStream(new File(filename));
            ObjectInputStream oi = new ObjectInputStream(fi);

            matrix = (Matrix) oi.readObject();

            oi.close();
            fi.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }

        return matrix;
    }
    
}
