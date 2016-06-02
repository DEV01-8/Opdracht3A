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
    private static final float minX = startX - 1000;
    private static final float maxX = startX + 1000;
    private static final float minY = startY - 1000;
    private static final float maxY = startY + 1000;
    final static Logger logger = Logger.getLogger(Main.class);

    @Override
    public void setup() {
        background(255);
        surface.setTitle("Waterhoogte Rotterdam Oost");
    }

    @Override
    public void settings() {
        size(600, 600);
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();

        try {
            results = parseCSV.read();
        } catch (Exception e) {
            logger.info(e);
        }
        PApplet.main(new String[]{Main.class.getName()});
    }

    @Override
    public void draw() {
        startMap();
    }

    private void startMap() {
        float minZ = parseCSV.minZ;
        float maxZ = parseCSV.maxZ;

        for(PVector result : results) {
            float mappedX = map(result.x, minX, maxX, 0, width);
            float mappedY = map(result.y, maxY, minY, height, 0);
            //float mappedZ = map(result.z, minZ, maxZ, 0, 255);

            fill(84, 121, 255);
            ellipse(mappedX, mappedY, 10, 10);
        }
    }

}
