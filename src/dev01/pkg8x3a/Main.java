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

    private void printAll(){
        for(PVector vector: results){
            logger.info("Size results: " + results.size());
            logger.info("X: " + vector.x);
            logger.info("Y: " + vector.y);
            logger.info("Z: " + vector.z);
        }
    }
    
    @Override
    public void draw() {
    }

  
}
