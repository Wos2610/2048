/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author LENOVO T480s
 */
public class ResourceManager {

    private static ResourceManager instance = null;

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    private HashMap<String, BufferedImage> imageMap;
    private HashMap<String, Font> fontMap;

    public ResourceManager() {
        imageMap = new HashMap<String, BufferedImage>();
        fontMap = new HashMap<String, Font>();
    }

    public BufferedImage loadImage(String fileName, JLabel label, String text) {
        BufferedImage image = imageMap.get(fileName);
        if (image == null) {
            try {
                image = ImageIO.read(new File("UI/" + fileName + ".png"));
                imageMap.put(fileName, image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Image dimg = image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        label.setIcon(imageIcon);
        label.setText(text);
        
        return image;
    }
    

    public void loadFont(String fileName, JLabel label, float size) {
        Font font = fontMap.get(fileName);
        if (font == null) {
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, new File("UI/" + fileName + ".ttf")).deriveFont(size);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                fontMap.put(fileName, font);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
        }
        label.setFont(font.deriveFont(size));        
    }
}
