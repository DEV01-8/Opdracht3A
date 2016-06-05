/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x3a;

import g4p_controls.G4P;
import g4p_controls.GButton;
import g4p_controls.GCScheme;
import g4p_controls.GEvent;
import g4p_controls.GLabel;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    boolean paused = false;
    GButton startButton;
    GButton stopButton;
    
    @Override
    public void setup() {
        background(255);
        surface.setTitle("Waterhoogte Rotterdam Oost");

        G4P.setGlobalColorScheme(GCScheme.GREEN_SCHEME);

        startButton = new GButton(this, 30, 30, 200, 30, "Start");
        stopButton = new GButton(this, 250, 30, 200, 30, "Stop");
        startButton.addEventHandler(this, "handleStartButtonEvents");
        stopButton.addEventHandler(this, "handleStopButtonEvents");
    }

    @Override
    public void settings() {
        size(1000, 680);

    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        try {
            PApplet.main(new String[]{Main.class.getName()});
            results = parseCSV.read();  //Get all items from parseCSV
        } catch (Exception e) {
            logger.info(e);
        }
    }

    //Method to map xyz coordinates
    private void startMap(float x, float y, float z) {
        try {
            float minZ = parseCSV.minZ;     //min value of Z ~ -16
            float maxZ = parseCSV.maxZ;     //max value of z ~ 215

            float mapX = map(x, minX, maxX, 0, width);   //map x
            float mapY = map(y, maxY, minY, 0, height);  //map y
            float mapZ = map(z, minZ, maxZ, 0, 216);     //map z

            //water
            if (mapZ > -16f && mapZ < 4.6f) {
                stroke(color(mapZ, mapZ, mapZ));
                fill(color(mapZ, mapZ, mapZ));
            }

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
        if (!paused) {
            createPoints();
        }
        
        startButton.draw();
        stopButton.draw();
    }

    public void handleStartButtonEvents(GButton button, GEvent event) {
        // If we have more than one button then this is how we
        // check which one was cllicked.
        if (button == startButton && event == GEvent.CLICKED) {
            paused = false;
            JOptionPane.showMessageDialog(null, "Resuming Program....", "Resumed", JOptionPane.INFORMATION_MESSAGE);
            this.resume();
            logger.info("Resuming....");
        }
    }

    public void handleStopButtonEvents(GButton button, GEvent event) {
        // If we have more than one button then this is how we
        // check which one was cllicked.
        if (button == stopButton && event == GEvent.CLICKED) {
            paused = true;
            JOptionPane.showMessageDialog(null, "Pausing Program....", "Paused", JOptionPane.INFORMATION_MESSAGE);
            this.pause();
            logger.info("Paused....");
        }
    }

}
