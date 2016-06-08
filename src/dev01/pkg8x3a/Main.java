/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x3a;

import java.util.ArrayList;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import processing.core.PApplet;
import static processing.core.PApplet.map;
import processing.core.PVector;

/**
 *
 * @author Johan Bos
 */
public class Main extends PApplet {

    private ArrayList<PVector> results = new ArrayList();
    private final ArrayList<PVector> mappings = new ArrayList();

    private final float START_X = 92799f;
    private final float START_Y = 436964f;
    private final float MIN_X = START_X - 1000f;
    private final float MAX_X = START_X + 1000f;
    private final float MIN_Y = START_Y - 1000f;
    private final float MAX_Y = START_Y + 1000f;
    final Logger logger = Logger.getLogger(Main.class);

    @Override
    public void setup() {
        background(255, 255, 255);
        surface.setTitle("Hoogtebestand Rotterdam Oost");

        results = CSVParser.read();  //Get all items from parseCSV
    }

    @Override
    public void settings() {
        size(680, 680);
    }

    public static void main(String[] args) {
        //Logger4J
        BasicConfigurator.configure();
        PApplet.main(new String[]{Main.class.getName()});
    }

    //Method to map xyz coordinates
    private void startMap() {
        float MIN_Z = CSVParser.MIN_Z;     //min value of Z ~ -16
        float MAX_Z = CSVParser.MAX_Z;     //max value of z ~ 215

        for (PVector result : results) {
            float mapX = map(result.x, MIN_X, MAX_X, 0, width);         //map x
            float mapY = map(result.y, MAX_Y, MIN_Y, 0, height);        //map y
            float mapZ = map(result.z, MIN_Z, MAX_Z, 0, 216);           //map z
            PVector mappedVector = new PVector(mapX, mapY, mapZ);       //PVector holding all mapped values
            mappings.add(mappedVector);                                 //ArrayList of PVectors holding mapped values
        }
    }

    
    @Override
    public void draw() {
    }

  
}
