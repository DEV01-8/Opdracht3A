/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x3a;

/**
 *
 * @author Johan Bos
 */
public class RDtoGPS {

    public static double[] Convert(double rdx, double rdy) {
        int referenceRdX = 155000;
        int referenceRdY = 463000;

        double dX = (double) (rdx - referenceRdX) * (double) (Math.pow(10, -5));
        double dY = (double) (rdy - referenceRdY) * (double) (Math.pow(10, -5));

        double sumN
                = (3235.65389 * dY)
                + (-32.58297 * Math.pow(dX, 2))
                + (-0.2475 * Math.pow(dY, 2))
                + (-0.84978 * Math.pow(dX, 2) * dY)
                + (-0.0655 * Math.pow(dY, 3))
                + (-0.01709 * Math.pow(dX, 2) * Math.pow(dY, 2))
                + (-0.00738 * dX)
                + (0.0053 * Math.pow(dX, 4))
                + (-0.00039 * Math.pow(dX, 2) * Math.pow(dY, 3))
                + (0.00033 * Math.pow(dX, 4) * dY)
                + (-0.00012 * dX * dY);
        double sumE
                = (5260.52916 * dX)
                + (105.94684 * dX * dY)
                + (2.45656 * dX * Math.pow(dY, 2))
                + (-0.81885 * Math.pow(dX, 3))
                + (0.05594 * dX * Math.pow(dY, 3))
                + (-0.05607 * Math.pow(dX, 3) * dY)
                + (0.01199 * dY)
                + (-0.00256 * Math.pow(dX, 3) * Math.pow(dY, 2))
                + (0.00128 * dX * Math.pow(dY, 4))
                + (0.00022 * Math.pow(dY, 2))
                + (-0.00022 * Math.pow(dX, 2))
                + (0.00026 * Math.pow(dX, 5));

        double referenceWgs84X = 52.15517;
        double referenceWgs84Y = 5.387206;

        double latitude = referenceWgs84X + (sumN / 3600);
        double longitude = referenceWgs84Y + (sumE / 3600);

        double[] result = new double[2];
        result[0] = latitude;
        result[1] = longitude;

        return result;
    }
    
}
