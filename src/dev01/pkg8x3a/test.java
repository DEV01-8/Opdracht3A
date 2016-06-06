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
import g4p_controls.GSlider;
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
public class test extends PApplet {
    float x = 50;

    @Override
    public void setup() {
        background(255);
        surface.setTitle("Hoogtebestand Rotterdam Oost");

    }

    @Override
    public void settings() {
        size(500, 500);
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        try {
            PApplet.main(new String[]{test.class.getName()});
        } catch (Exception e) {
        
        }
    }
    
    @Override
    public void draw(){    
        int y = 50;
        
        fill(255, 0, 0);
        rect(x, y, 100, 100);
        
        if (keyPressed == true) {x = x + 0.25f;}

            
    }
    
}
