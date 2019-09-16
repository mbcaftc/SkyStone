package org.firstinspires.ftc.teamcode.MrAcker.base.Subsystems;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;


public class ColorDistanceSensor {

    //Instance Variables

    public ColorSensor revColorSensor;
    public DistanceSensor revDistanceSensor;

    public final int RED_THRESHOLD = 30;                //maybe a higher threshold (12.5)
    public final int BLUE_THRESHOLD = 100;              //maybe a higher threshold (12.5)
    public float hsvValues[] = {0F, 0F, 0F};


    public int thresholdNothing = 0;       // Original was 180, adjusted for red hue
    public int threshholdColor= 7;          // Original was 270
    public final double SCALE_FACTOR = 255;


    // FTC Required Lines

    public LinearOpMode linearOp = null;
    public void setLinearOp(LinearOpMode Op) {
        linearOp = Op;
    }


    // Constructors

    public ColorDistanceSensor(ColorSensor rCS, DistanceSensor rDS) {
        revColorSensor = rCS;
        revDistanceSensor = rDS;

    }

    public boolean checkColor(int threshNothing, int threshColor) {

        thresholdNothing = threshNothing;
        threshholdColor= threshColor;


        Color.RGBToHSV((int) (revColorSensor.red() * SCALE_FACTOR),
                (int) (revColorSensor.green() * SCALE_FACTOR),
                (int) (revColorSensor.blue() * SCALE_FACTOR),
                hsvValues);


        if (hsvValues[0] >= thresholdNothing && hsvValues[0] <  threshholdColor) {

            return true;

        }
        else {

            return false;
        }

    }



}
