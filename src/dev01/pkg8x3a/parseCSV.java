/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x3a;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import processing.core.PVector;

/**
 *
 * @author Johan Bos
 */
public class parseCSV {

    private static final ArrayList<PVector> points = new ArrayList();
    public static float maxZ = Float.MIN_VALUE;
    public static float minZ = Float.MAX_VALUE;
    private static final float startX = 92799f;
    private static final float startY = 436964f;
    final static Logger logger = Logger.getLogger(parseCSV.class);


    public static ArrayList<PVector> read() {
        try {
            logger.info("Reading CSV...");

            //read csv
            CSVReader reader = new CSVReader(new FileReader("C:\\dev\\oost.csv"), ',', '\'', 1);
            String[] nextLine;

            logger.info("Going through CSV...");

            long startTime = System.currentTimeMillis();

            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line

                float z = Float.parseFloat(nextLine[2]);

                PVector tempVector = new PVector(Float.parseFloat(nextLine[0]), Float.parseFloat(nextLine[1]), z);

                float limitX = (startX - tempVector.x);
                float limitY = (startY - tempVector.y);

                if (limitX < 1000 && limitY < 1000) {
                    points.add(tempVector);
                }
                if (minZ > z) {
                    minZ = z;
                }
                if (maxZ < z) {
                    maxZ = z;
                }

            }
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;

            logger.info("Done going through csv!");
            logger.info("Time elapsed: " + (elapsedTime / 1000) + " sec");
            logger.info("Amount of items in ArrayList: " + points.size());

        } catch (IOException | NumberFormatException e) {
            logger.info(e);
        }

        return points;
    }
}
