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

    public static ArrayList<PVector> results = new ArrayList();
    private final float startX = 92799f;
    private final float startY = 436964f;
    private final float minX = startX - 1000f;
    private final float maxX = startX + 1000f;
    private final float minY = startY - 1000f;
    private final float maxY = startY + 1000f;
    final static Logger logger = Logger.getLogger(Main.class);
    float waterline = 80f;

    @Override
    public void setup() {
        background(255);
        surface.setTitle("Hoogtebestand Rotterdam Oost");

        results = parseCSV.read();  //Get all items from parseCSV
    }

    @Override
    public void settings() {
        size(680, 680);

    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        PApplet.main(new String[]{Main.class.getName()});
    }

    //Method to map xyz coordinates
    private void startMap(float x, float y, float z) {
        try {
            float minZ = parseCSV.minZ;     //min value of Z ~ -16
            float maxZ = parseCSV.maxZ;     //max value of z ~ 215

            float mapX = map(x, minX, maxX, 0, width);   //map x
            float mapY = map(y, maxY, minY, 0, height);  //map y
            float mapZ = map(z, minZ, maxZ, 0, 216);     //map z

            //ground
            if (mapZ > 4.6f && mapZ < 23f) {
                stroke(color(mapZ, mapZ, mapZ));
                fill(color(mapZ, mapZ, mapZ));
            }

            //top of buildings
            if (mapZ > 23f && mapZ < 215f) {
                stroke(color(mapZ, mapZ, mapZ));
                fill(color(mapZ, mapZ, mapZ));
            }

            //water
            if (waterline > mapZ) {
                stroke(color(0, 0, 255));
                fill(color(0, 0, 255));
            }

            rect(mapX, mapY, 13f, 13f);            //create ellipse at points of mapped xy

        } catch (Exception e) {
            logger.info(e);
        }
    }

    //Method to create ellipses from startMap
    public void createPoints() {
        for (int i = 0; i < results.size(); i++) {
            startMap(results.get(i).x, results.get(i).y, results.get(i).z);
        }
    }

    @Override
    public void draw() {
        //Draw all points
        createPoints();
    }
    
    @Override
    public void mouseClicked(){
        waterline = waterline + 0.30f;
        redraw();
    }
}
