/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x3a;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.mapdisplay.Java2DMapDisplay;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import processing.core.PApplet;
import dev01.pkg8x3a.RDtoGPS;
import processing.event.MouseEvent;

/**
 *
 * @author Johan Bos
 */
public class DEV018x3A extends PApplet {

    public static ArrayList<Point> points = new ArrayList<>();
    public static ArrayList<Point> xy = new ArrayList<>();
    UnfoldingMap currentMap;

    //Read text file and place columns in Arraylists
    public static void ReadText() {
        try {
            //Read text file
            String encoding = "UTF-8";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\dev\\oost.csv"), encoding));

            System.out.println("Reading CSV file....");
            br.readLine(); // This will read the first line
            String line1 = null;    //Skip first line

            //Clear Arraylists for correct order
            points.clear();

            Point point = new Point();

            System.out.println("Splitting items in CSV....");
            while ((line1 = br.readLine()) != null) {
                String[] columns = line1.split(",");
                double x = Double.parseDouble(columns[0]);
                double y = Double.parseDouble(columns[1]);
                double z = Double.parseDouble(columns[2]);

                double[] converted = RDtoGPS.Convert(x, y);

                point.setX(converted[0]);
                point.setY(converted[1]);
                point.setZ(z);

                points.add(point);
            }

            System.out.println("Done placing items in ArrayLists");
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double[] convert(double x, double y) {
        double[] c = RDtoGPS.Convert(x, y);

        return c;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PApplet.main(new String[]{DEV018x3A.class.getName()});
        ReadText();
    }

    
    @Override
    public void settings() {
        size(800, 600, Java2DMapDisplay.FX2D);
        smooth(4);

        currentMap = new UnfoldingMap(this, new Google.GoogleMapProvider());
        MapUtils.createDefaultEventDispatcher(this, currentMap);
        currentMap.zoomAndPanTo(new Location(51.917822, 4.480970), 10);
    }

    @Override
    public void setup() {
        //Set Title
        surface.setTitle("Overstroming");

        for (int i = 0; i < 5000; i++) {
            Location loc = new Location(points.get(i).getX(), points.get(i).getY());
            SimplePointMarker markers = new SimplePointMarker(loc);
            markers.setColor(color(255, 0, 0));
            currentMap.addMarker(markers);
        }

    }

    @Override
    public void draw() {
        currentMap.draw();

    }
    
    @Override
    public void keyPressed() {
        if (currentMap != null) {
            if (key == '+') {
                currentMap.zoomLevelIn();
            }
            if (key == '-') {
                currentMap.zoomLevelOut();
            }
            if (key == 's') {
                this.redraw();
            }
        }
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        float e = event.getCount();
        if (e > 0) {
            currentMap.zoomLevelIn();
        } else {
            currentMap.zoomLevelOut();
        }
    }

}
