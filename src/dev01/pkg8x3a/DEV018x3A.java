/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x3a;

import de.fhpotsdam.unfolding.UnfoldingMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Johan Bos
 */
public class DEV018x3A extends PApplet {

    public static ArrayList<PVector> points = new ArrayList<>();
    static float minX;
    static float maxX;
    static float minY;
    static float maxY;

    //Read text file and place columns in Arraylists
    public static void ReadText() {
        try {
            //Read text file
            BufferedReader br = new BufferedReader(new FileReader("C:\\dev\\oost.csv"));

            System.out.println("Reading CSV file....");
            br.readLine(); // This will read the first line
            String line1 = null;    //Skip first line

            //Clear Arraylists for correct order
            points.clear();

            System.out.println("Splitting items in CSV....");
            while ((line1 = br.readLine()) != null) {
                String[] columns = line1.split(",");
                PVector vector = new PVector();
                vector.add(Float.parseFloat(columns[0]), Float.parseFloat(columns[1]), Float.parseFloat(columns[2]));
                points.add(vector);
            }

            System.out.println("Done placing items in ArrayLists");
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Find maximum (largest) value in array using loop
    public static float getMaxX(ArrayList<PVector> numbers) {
        float maxValue = numbers.get(0).x;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).x > maxValue) {
                maxValue = numbers.get(i).x;
            }
        }
        return maxValue;
    }

    public static float getMaxY(ArrayList<PVector> numbers) {
        float maxValue = numbers.get(0).y;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).y > maxValue) {
                maxValue = numbers.get(i).y;
            }
        }
        return maxValue;
    }

    //Find minimum (lowest) value in array using loop
    public static float getMinX(ArrayList<PVector> numbers) {
        float minValue = numbers.get(0).x;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).x < minValue) {
                minValue = numbers.get(i).x;
            }
        }
        return minValue;
    }

    //Find minimum (lowest) value in array using loop
    public static float getMinY(ArrayList<PVector> numbers) {
        float minValue = numbers.get(0).y;
        for (int i = 1; i < numbers.size(); i++) {
            if (numbers.get(i).y < minValue) {
                minValue = numbers.get(i).y;
            }
        }
        return minValue;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ReadText();
        minX = getMinX(points);
        maxX = getMaxX(points);
        minY = getMinY(points);
        maxY = getMaxY(points);

        System.out.println("Min X: " + minX);
        System.out.println("Max X: " + maxX);
        System.out.println("Min Y: " + minY);
        System.out.println("Max Y: " + maxY);

        PApplet.main(new String[]{DEV018x3A.class.getName()});
    }

    public void goMap(float vectX, float vectY) {
        try {

            float pointX = map(vectX, minX, maxX, 0, 600);
            float pointY = map(vectY, maxY, minY, 800, 0);

            //Create point on map with x and y
            fill(0, 0, 0);
            ellipse(pointX, pointY, 4, 4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void settings() {
        size(800, 600);
        smooth(4);
    }

    @Override
    public void setup() {
        //Set Title
        surface.setTitle("Overstroming");
    }

    @Override
    public void draw() {
        for (int i = 0; i < points.size(); i++) {
            goMap(points.get(i).x, points.get(i).y);
        }
    }
}
