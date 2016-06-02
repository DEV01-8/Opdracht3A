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
    private static final float startX = 92799f;
    private static final float startY = 436964f;
    private static final float minX = startX - 1000f;
    private static final float maxX = startX + 1000f;
    private static final float minY = startY - 1000f;
    private static final float maxY = startY + 1000f;
    final static Logger logger = Logger.getLogger(Main.class);

    @Override
    public void setup() {
        background(255);
        surface.setTitle("Waterhoogte Rotterdam Oost");
    }

    @Override
    public void settings() {
        size(1000, 680);
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();

        try {
            results = parseCSV.read();  //Get all items from parseCSV
        } catch (Exception e) {
            logger.info(e);
        }
        PApplet.main(new String[]{Main.class.getName()});
    }

    private void startMap(float x, float y, float z) {
        try {
            float minZ = parseCSV.minZ;     //min value of Z ~ -16
            float maxZ = parseCSV.maxZ;     //max value of z ~ 215

            float mapX = map(x, minX, maxX, 0, width);   //map x
            float mapY = map(y, maxY, minY, 0, height);  //map y
            float mapZ = map(z, minZ, maxZ, 0, 216);     //map z

            /*
            if(mapZ > -16f && mapZ < 3f){
                stroke(color(mapZ, mapZ, 255));
                fill(color(mapZ, mapZ, 255));
            }
            */
            //water
            if(mapZ > -16f && mapZ < -3f){
                stroke(color(0, 0, 255));
                fill(color(0, 0, 255));
            }
            
            //ground
            if(mapZ > -3f && mapZ < 20f){
                stroke(color((mapZ*3), (mapZ*3), (mapZ*3)));
                fill(color((mapZ*3), (mapZ*3), (mapZ*3)));
            }
            
            //top of buildings
            if(mapZ > 20f && mapZ < 215f){
                stroke(color(mapZ, mapZ, 0));
                fill(color(mapZ, mapZ, 0));
            }
            //water
            else{
            stroke(color(50, 50, 50));
            fill(color(50, 50, 50));
            }
            
            ellipse(mapX, mapY, 13, 13);            //create ellipse at points of mapped xy
        } catch (Exception e) {
            logger.info(e);
        }
    }

    @Override
    public void draw() {
        //Draw all points
        for (int i = 0; i < results.size(); i++) {
            startMap(results.get(i).x, results.get(i).y, results.get(i).z);
        }
    }

}
