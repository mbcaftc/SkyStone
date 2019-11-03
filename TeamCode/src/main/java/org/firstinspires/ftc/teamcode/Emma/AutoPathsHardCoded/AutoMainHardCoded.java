package org.firstinspires.ftc.teamcode.Emma.AutoPathsHardCoded;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

public abstract class AutoMainHardCoded extends LinearOpMode {

    // Variables and Constants used across both Buildig and Loading Locations on the Field

    public final long sleepTime = 20;
    public final double maxSpeed = 1;
    public final double highSpeed = .5;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;
    public LinearOpMode linearOp = null;
    public final double gyroSPD = .2;

    public int skystonePos = 2;

    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }

    // Methods used across both Building and Loading Locations on the Field

    public void orientToDropStone(MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, 1.4);             //
            sleep(sleepTime);
            Bot.rotateRight(midSpeed, 2);
            sleep(sleepTime);
            Bot.gyroCorrection(gyroSPD, -90);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, 1);
            sleep(sleepTime);
            Bot.rotateLeft(midSpeed, 2);
            sleep(sleepTime);
            Bot.gyroCorrection(gyroSPD, 90);
        }
        sleep(sleepTime);

    }
























    // ***************************
    //  Methods below this line were used in previous generation but no longer used and have been archived as backup
    // ***************************

    public void hardCodeVuforia ( MetalBot Bot, String Alliance) {
        if (skystonePos == 1){

            if (Alliance == "Red") {
                Bot.driveBackward(midSpeed, .5);
            }
            else if (Alliance == "Blue") {
                Bot.driveForward(midSpeed, .5);
            }
            //  was 7 if servos are on left side... drive forward
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .6 );
            skystonePos = 1;

        }
        else if (skystonePos == 2) {

            if (Alliance == "Red") {
                Bot.driveForward(midSpeed, .2);
            }
            else if (Alliance == "Blue") {
                Bot.driveBackward(midSpeed, .2);
            }
            Bot.strafeLeft(midSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .6);
            sleep(sleepTime);
            skystonePos  = 2;

        }
        else {

            if (Alliance == "Red") {
                Bot.driveForward(midSpeed, 1);
            }
            else if (Alliance == "Blue") {
                Bot.driveBackward(midSpeed, 1);
            }
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 1.8);
            Bot.strafeLeft(lowSpeed, .5);
            sleep(sleepTime);
            skystonePos = 3;

        }
        Bot.grabStone();
        sleep(1000);
        Bot.stopMotors();
        if (Alliance == "Red") {
            Bot.driveForward(lowSpeed, .4);
        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(lowSpeed, .4);
        }

    }












    // **** not being used currently*****
    public void vuforiaStone(MetalBot Bot, VuforiaWebcam Cam, String Alliance) {

        //Cam.trackObjects();
        //sleep(2000);

        telemetry.addData("Target Y:", Cam.targetY);
        telemetry.update();

        if (Cam.targetY > 1 && Cam.targetVisible) {             //position 1
            //Bot.rotateRight(highSpeed, 1);

            //Bot.rotateLeft(highSpeed, 1);
            //Bot.driveForward(midSpeed, .6);                                  // if servos are on left side... driveBackwards
            Bot.strafeLeft(midSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .6 );  // if servos are on the left side... strafeLeft

            skystonePos = 1;

        }
        else if (Cam.targetY < 1 && Cam.targetVisible) {        //position 2

            Bot.driveForward(lowSpeed, .45);
            Bot.strafeLeft(midSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .4);
            sleep(sleepTime);

            skystonePos = 2;

            telemetry.addLine(" targetY < 1 ... position 2");
            telemetry.update();

        }
        else {                                                  // position 3

            Bot.driveForward(midSpeed, .7);
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 1.8);
            Bot.strafeLeft(lowSpeed, .4);
            sleep(sleepTime);
            skystonePos = 3;

            telemetry.addLine("targetY > 1... position 3");
            telemetry.addLine(" target is on the far left... position 1");
            telemetry.update();

        }

        Bot.grabStone();
        sleep(1000);
        Bot.stopMotors();
        if (Alliance == "Red") {
            Bot.driveForward(lowSpeed, .4);
        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(lowSpeed, .4);
        }
    }





}
