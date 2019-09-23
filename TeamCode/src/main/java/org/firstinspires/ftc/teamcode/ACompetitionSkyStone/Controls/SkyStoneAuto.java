package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.android.dx.dex.file.MethodAnnotationStruct;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.DriveTrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.IntakeSpinner;

public class SkyStoneAuto {

    MecanumDrive mechDrive;
    SensorREVColorDistance colorSensor;

    LinearOpMode linearOp;

    public void setLinearOp(LinearOpMode linearOp) {
        this.linearOp = linearOp;
    }

    final long sleepTime = 200;






    public void sampleSkyStone () {

        mechDrive.driveForward(1, 2.5);             // drive forward toward skystones
        linearOp.sleep(sleepTime);

        //sample another sky stone

        mechDrive.driveBackward(1, 2);              // drive backward with stone
        linearOp.sleep(sleepTime);

    }

    public void dropOffMineral () {

        mechDrive.rotateRight(1, .5);               // rotate toward build site with stone
        linearOp.sleep(sleepTime);


        mechDrive.driveForward(1,5);                // drive toward build site with the skystone
        linearOp.sleep(sleepTime);

        // drop off the sky stone
    }

    public void adjustBuildPlate() {

        mechDrive.strafeRight(1, 1);                //strafe to the side of the build site
        linearOp.sleep(sleepTime);

        mechDrive.rotateLeft(1, .5);                // rotate left to face the build plate
        linearOp.sleep(sleepTime);

        mechDrive.strafeRight (1, 1);               // strafe between the wall and the build plate
        linearOp.sleep(sleepTime);
        // will use distance sensor
        // pull plate

        mechDrive.strafeLeft(1, 1);

    }

    public void goToSkyStones() {

        mechDrive.strafeLeft(1, 6);             // strafe out (away from the wall and build site towards the skystones)
        linearOp.sleep(sleepTime);

    }

    public void parkOnLine () {
        // will implement color sensor

        mechDrive.rotateLeft(1, 1);
        mechDrive.driveForward(1, 2.5);
    }





        }
