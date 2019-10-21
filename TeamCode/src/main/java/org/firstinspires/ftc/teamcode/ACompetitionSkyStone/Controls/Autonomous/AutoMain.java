package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

public abstract class AutoMain extends LinearOpMode {
    public final long  sleepTime = 1000;
    public final double maxSpeed = 1;
    public final double highSpeed = .6;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;
    public LinearOpMode linearOp = null;


    public int skystonePos = 1;
    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }


    public void grabSkyStone( AckerBot Bot) {
        Bot.StoneGrab();
    }


    //  detecting the skystone
    public void vuforiaStone( WoodBot Bot, VuforiaWebcam Cam) {

        Cam.trackObjects();
        sleep(1000);

        telemetry.addData("Target Y:", Cam.targetY);
        telemetry.update();

        if (Cam.targetY > 1 && Cam.targetVisible) {             //position 3
            //Bot.rotateRight(highSpeed, 1);
            Bot.driveBackward(midSpeed, 1);                                 // if servos are on left side... drive forward
            Bot.strafeLeft(highSpeed, 4);                                  // if servos are on left side... strafeLeft
            sleep(sleepTime);

            telemetry.addLine("targetY > 1... position 3");

        }
        else if (Cam.targetY < 1 && Cam.targetVisible) {        //position 2

            Bot.strafeLeft(midSpeed, 4);                                   // if servos are on the left side... strafeLeft
            sleep(sleepTime);

            telemetry.addLine(" targetY < 1 ... position 2");
            telemetry.update();

        }
        else {                                                  // position 1
            //Bot.rotateLeft(highSpeed, 1);
            Bot.driveForward(midSpeed, 1);                                  // if servos are on left side... driveBackwards
            Bot.strafeLeft(highSpeed, 4);                                  // if servos are on the left side... strafeLeft

            telemetry.addLine(" target is on the far left... position 1");
            telemetry.update();

        }

        Bot.grabStone();
    }

    public void hardCodeVuforia ( WoodBot Bot) {
        if (skystonePos == 1){
            Bot.driveBackward(midSpeed, .5);                                 //  was 7 if servos are on left side... drive forward
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 2);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .7 );
            skystonePos = 1;

        }
        else if (skystonePos == 2) {
            Bot.driveForward(lowSpeed, .5);
            Bot.strafeLeft(midSpeed, 2);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .5);
            sleep(sleepTime);
            skystonePos  = 2;

        }
        else {
            Bot.driveForward(midSpeed, .75);
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 2);
            Bot.strafeLeft(lowSpeed, .5);
            sleep(sleepTime);
            skystonePos = 3;

        }
        Bot.grabStone();
        sleep(1000);
        Bot.stopMotors();
        Bot.driveForward(lowSpeed, .3);

    }


    //outer path - removing skystone
    public void removeSkyStoneOuterPath(WoodBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, 3.5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, 3.5);
        }
        sleep(sleepTime);
    }

    // inner plath
    public void removeSkyStoneInnerPath (WoodBot Bot,String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, 1.4);
            sleep(sleepTime);
            Bot.rotateRight(midSpeed, 2.5);
            sleep(sleepTime);
            Bot.gyroCorrection(lowSpeed, -92);
        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, 1.4);
            sleep(sleepTime);
            Bot.rotateLeft(midSpeed, 2.5);
            sleep(sleepTime);
            Bot.gyroCorrection(lowSpeed, 90);
        }
        sleep(sleepTime);


    }

    public void dropSkyStone(WoodBot Bot, String Alliance) {

        if (Alliance == "Red") {
            switch (skystonePos) {
                case 1:
                    Bot.strafeLeft(highSpeed, 4.25);
//                    Bot.gyroCorrection(lowSpeed, -92);
                    Bot.strafeLeft(highSpeed, 4.25);
                    break;
                case 2:
                    Bot.strafeLeft(highSpeed, 3.45);
                    //Bot.gyroCorrection(lowSpeed, -92);
                    Bot.strafeLeft(highSpeed, 3.45);
                    break;
                case 3:
                    Bot.strafeLeft(highSpeed, 3.3);
                    //Bot.gyroCorrection(lowSpeed, -92);
                    Bot.strafeLeft(highSpeed, 3.3);
                    break;

            }

        } else if (Alliance == "Blue") {
            switch (skystonePos) {
                case 1:
                    Bot.driveBackward(highSpeed, 7);
                    break;
                case 2:
                    Bot.driveBackward(highSpeed, 6.5);
                    break;
                case 3:
                    Bot.driveBackward(highSpeed, 7);
                    break;
            }

        }
        sleep(sleepTime);
        Bot.dropStone();
    }


    public void alignBuildPlateOuter (WoodBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.driveForward(highSpeed, 1.2);

        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(highSpeed, 1.2);
        }

    }
    public void alignGrabBuildPlateInner (WoodBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.driveBackward(highSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 1.5);
            sleep(sleepTime);
        }
        else if (Alliance == "Blue") {
            Bot.driveForward(highSpeed, 2.5);
        }
        Bot.HookGrab();
        sleep(1000);
    }



    public void orientBuildPlate (WoodBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, .5);
            Bot.rotateRight(midSpeed, 2.5);
            Bot.gyroCorrection(lowSpeed, -155);
        }
        else if (Alliance == "Blue") {
            Bot.rotateLeft(highSpeed, 2);
            Bot.gyroCorrection(lowSpeed, 138);
        }

    }

    public void pushBuildPlate (WoodBot Bot, String Alliance) {
        Bot.strafeLeft(midSpeed, 4.5);

        Bot.HookRelease();
    }

    public void park (WoodBot Bot, String ALliance) {
        Bot.driveForward(highSpeed, 3.5);
        sleep(sleepTime);

    }













}
