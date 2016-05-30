/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x3a;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import processing.core.PApplet;

/**
 *
 * @author Johan Bos
 */
public class DEV018x3A extends PApplet {

    public static ArrayList<Point> points = new ArrayList<>();
    
    //Read text file and place columns in Arraylists
    public static void ReadText() {
        try {
            //Read text file
            String encoding = "UTF-8";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\dev\\oost.csv"), encoding));

            br.readLine(); // This will read the first line
            String line1 = null;    //Skip first line

            //Clear Arraylists for correct order
            points.clear();

            Point point = new Point();
                            
            while ((line1 = br.readLine()) != null) {
                String[] columns = line1.split(",");
                point.setX(Double.parseDouble(columns[0]));
                point.setY(Double.parseDouble(columns[1]));
                point.setZ(Double.parseDouble(columns[2]));
                
                points.add(point);
            }
            
            System.out.println("I'm done!");
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*-------------------------------------------------------------*/
    
    //Find maximum in X
    public static int getMaxX(ArrayList<Point> numbers) {
        int maxValue = numbers.get(0).getX().intValue();
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).getX() > maxValue) {
                maxValue = numbers.get(i).getX().intValue();
            }
        }
        return maxValue;
    }

    //Find minimum in X
    public static int getMinX(ArrayList<Point> numbers) {
        int minValue = numbers.get(0).getX().intValue();
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).getX() < minValue) {
                minValue = numbers.get(i).getX().intValue();
            }
        }
        return minValue;
    }
    
    /*-------------------------------------------------------------*/

    //Find maximum in Y
    public static int getMaxY(ArrayList<Point> numbers) {
        int maxValue = numbers.get(0).getY().intValue();
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).getY() > maxValue) {
                maxValue = numbers.get(i).getY().intValue();
            }
        }
        return maxValue;
    }

    //Find minimum in Y
    public static int getMinY(ArrayList<Point> numbers) {
        int minValue = numbers.get(0).getY().intValue();
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).getY() < minValue) {
                minValue = numbers.get(i).getY().intValue();
            }
        }
        return minValue;
    }
    
    /*-------------------------------------------------------------*/
    
    //Find maximum in Z
    public static int getMaxZ(ArrayList<Point> numbers) {
        int maxValue = numbers.get(0).getZ().intValue();
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).getZ() > maxValue) {
                maxValue = numbers.get(i).getZ().intValue();
            }
        }
        return maxValue;
    }

    //Find minimum in Z
    public static int getMinZ(ArrayList<Point> numbers) {
        int minValue = numbers.get(0).getZ().intValue();
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).getZ() < minValue) {
                minValue = numbers.get(i).getZ().intValue();
            }
        }
        return minValue;
    }
    
    /*-------------------------------------------------------------*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PApplet.main(new String[]{DEV018x3A.class.getName()});
        ReadText();
    }

    @Override
    public void settings() {
        size(500, 500);
        smooth(4);
    }

    @Override
    public void setup() {
        //Set Title
        surface.setTitle("Overstroming");
    }

    @Override
    public void draw() {

    }

    @Override
    public void mouseClicked() {
        fill(0, 0, 0);
        textSize(11);
        text(mouseX + ":" + mouseY, mouseX, mouseY);
    }

}
