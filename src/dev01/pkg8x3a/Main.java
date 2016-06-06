/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x3a;

import g4p_controls.GButton;
import g4p_controls.GEvent;
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

    final Logger logger = Logger.getLogger(Main.class);
    float waterline = -16f;
    boolean first;
    boolean loop = true;
    GButton startButton;
    GButton stopButton;

    @Override
    public void setup() {
        background(255);
        frameRate(1);
        surface.setTitle("Hoogtebestand Rotterdam Oost");

        results = parseCSV.read();  //Get all items from parseCSV
        startMap();

        startButton = new GButton(this, 30, 30, 200, 40, "Start");
        stopButton = new GButton(this, 250, 30, 200, 40, "Stop");

        startButton.addEventHandler(this, "handleStartButtonEvent");
        stopButton.addEventHandler(this, "handleStopButtonEvent");

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
    private void startMap() {
        float minZ = parseCSV.minZ;     //min value of Z ~ -16
        float maxZ = parseCSV.maxZ;     //max value of z ~ 215
        
        for (int i = 0; i < results.size(); i++) {
            float mapX = map(results.get(i).x, minX, maxX, 0, width);   //map x
            float mapY = map(results.get(i).y, maxY, minY, 0, height);  //map y
            float mapZ = map(results.get(i).z, minZ, maxZ, 0, 216);     //map z

            PVector mappedVector = new PVector(mapX, mapY, mapZ);
            mappings.add(mappedVector);
        }

    }

    private void createMap(float mapX, float mapY, float mapZ) {
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

    }

    //Method to create ellipses from startMap and createMap
    private void createPoints() {
        for (int i = 0; i < mappings.size(); i++) {
            createMap(mappings.get(i).x, mappings.get(i).y, mappings.get(i).z);
        }
    }

    @Override
    public void draw() {
        //Draw all points
        createPoints();
        waterline = waterline + 0.25f;

        startButton.draw();
        stopButton.draw();
    }

    public void handleStartButtonEvent(GButton button, GEvent event) {
        if (button == startButton && event == GEvent.CLICKED) {
            logger.info("StartButton is pressed!");
        }
    }

    public void handleStopButtonEvent(GButton button, GEvent event) {
        if (button == stopButton && event == GEvent.CLICKED) {
            logger.info("StopButton is pressed!");
        }
    }
}
