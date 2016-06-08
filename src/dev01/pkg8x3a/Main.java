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

    private final float startX = 92799f;
    private final float startY = 436964f;
    private final float minX = startX - 1000f;
    private final float maxX = startX + 1000f;
    private final float minY = startY - 1000f;
    private final float maxY = startY + 1000f;
    float waterLevel = -5f;
    final Logger logger = Logger.getLogger(Main.class);
    boolean firstRun = true;

    @Override
    public void setup() {
        background(255);
        frameRate(1);
        surface.setTitle("Hoogtebestand Rotterdam Oost");

        results = parseCSV.read();  //Get all items from parseCSV
        startMap();                 //use map() method to convert RDX and RDY to pixels
    }

    @Override
    public void settings() {
        size(680, 680);
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();                      //Logger4J
        PApplet.main(new String[]{Main.class.getName()});
    }

    //Method to map xyz coordinates
    private void startMap() {
        float minZ = parseCSV.minZ;     //min value of Z ~ -16
        float maxZ = parseCSV.maxZ;     //max value of z ~ 215

        for (PVector result : results) {
            float mapX = map(result.x, minX, maxX, 0, width);       //map x
            float mapY = map(result.y, maxY, minY, 0, height);      //map y
            float mapZ = map(result.z, minZ, maxZ, 0, 216);         //map z
            PVector mappedVector = new PVector(mapX, mapY, mapZ);   //PVector holding all mapped values
            mappings.add(mappedVector);                             //ArrayList of PVectors holding mapped values
        }
    }

    private void createMap() {
        if (firstRun == true) {
            for (PVector mapping : mappings) {
                float mapX = mapping.x;
                float mapY = mapping.y;
                float mapZ = mapping.z;

                if (mapZ > 5.5f && mapZ < 22.5f) {      //Color of ground and roads
                    stroke(color(196, 193, 186));
                    fill(color(211, 208, 201));
                } else {                                //Color of top of building
                    stroke(color(247, 245, 239));
                    fill(color(242, 240, 234));
                }

                rect(mapX, mapY, 13f, 13f);            //create rect at points of mapped xy
            }

            firstRun = false;                       //Set firstRun to false to create water

        } else if (firstRun == false) {
            for (PVector mapping : mappings) {
                float mapX = mapping.x;
                float mapY = mapping.y;
                float mapZ = mapping.z;

                if (waterLevel > mapZ) {                     //Color of water
                    stroke(color(0, 153, 153));
                    fill(color(0, 153, 153));

                    ellipse(mapX, mapY, 2f, 2f);            //create ellipse at points of mapped x
                }
            }
        }
    }

    @Override
    public void draw() {
        //Draw all points
        createMap();

        //Increase water level by 0.11
        waterLevel = waterLevel + 0.11f;
    }

    @Override
    public void mouseClicked() {
        logger.info("Water Level: " + waterLevel);
    }
}
