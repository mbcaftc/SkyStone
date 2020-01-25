package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;


public abstract class AutoLoading extends AutoMain {


    // *********   Methods to Rotate from Loading REA to drive to Building Area

    public void rotateToDriveDropStone (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.rotateLeft(lowSpeed, 2.3);      //2.5 to 2.3 after watching video 11:38 jan 20
            sleep(sleepTime);
            Bot.gyroCorrection(gyroSPD, 91);     // was 92
        }
        else if (Alliance == "Blue") {
            Bot.rotateRight(lowSpeed, 2.3);      //2.5 to 2.3 after watching video 11:38 jan 20
            sleep(sleepTime);
            Bot.gyroCorrection(gyroSPD, -90);     // was 91
        }

    }


    // ***********  Methods for Dropping the Skystone on the Build Plate

    public void dropSkyStone(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {


            Bot.rotateLeft(highSpeed, 2);
            Bot.gyroCorrection(gyroSPD, 178);
            Bot.driveBackward(.3, 1.2);

            // methods for stacking arm

        } else if (Alliance == "Blue") {
            Bot.rotateRight(.3, 2);
            Bot.gyroCorrection(gyroSPD, -178);
            Bot.driveBackward(.3, .8);      // was .1

        }

        Bot.intakePushIn();
        sleep(sleepTime);
        Bot.intakePushNeutral();

        Bot.clawGrabberGrab();
        sleep(100);

        Bot.stackingArmDown();                   // This is physically up
        sleep(400);                 //reduced 800 down to 300- 11:45 jan20 boone

        Bot.stackingArmOff();

        Bot.clawExtenderExtend();
        sleep(1800);                //adjusted for continous rotation servo that has been geared up (should be 1 1/2 seconds)
        Bot.clawExtenderStop();

        Bot.clawExtenderExtend();
        sleep(300);
        Bot.clawExtenderStop();
        sleep(50);

        Bot.clawExtenderExtend();
        sleep(300);
        Bot.clawExtenderStop();
        sleep(50);
        Bot.clawExtenderExtend();
        sleep(300);
        Bot.clawExtenderStop();
        sleep(50);
        Bot.clawGrabberRelease();
        sleep(50);

        Bot.stackingArmDown();
        sleep(250);

        Bot.stackingArmOff();

        Bot.driveForward(lowSpeed, .5);

        Bot.clawExtenderRetract();
        sleep(1750);                //same time as extension
        Bot.clawExtenderStop();

        Bot.stackingArmUp();                     // This is physically down
        sleep(800);

        Bot.stackingArmOff();

    }

    public void dropSkyStonePostPlate (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.rotateLeft(midSpeed, 1.25);
            Bot.gyroCorrection(gyroSPD, -90);
        }
        else if (Alliance == "Blue") {
            Bot.rotateRight(midSpeed, 1.25);
            Bot.gyroCorrection(gyroSPD, 90);
        }

    }

    // ****** Methods to Align & Move the Build Plate to the corner

    public void alignGrabPlate (MetalBot Bot, String Alliance){
        if (Alliance == "Red") {


            Bot.rotateLeft(lowSpeed, 2);
            Bot.gyroCorrection(gyroSPD,-90 );
            linearOp.idle();
            Bot.strafeLeft(.3, 1);
        }
        else if (Alliance == "Blue") {
            Bot.rotateLeft(.3, 2);
            Bot.gyroCorrection(gyroSPD,-90 );
            linearOp.idle();
            Bot.strafeLeft(.3, 1);
        }

        Bot.HookGrab();
        sleep(500);                 // adjusted from 500 to save time

    }



    public void orientBuildPlate (MetalBot Bot, String Alliance) throws InterruptedException {
        if (Alliance == "Red") {

            //Bot.driveGyroStrafeAngle (1000,.3,"left",-175);  // Gyro stafe at angle
            //Bot.strafeLeft(midSpeed,2);   // slam into the wall


            //Emma's original code
            Bot.strafeRight(midSpeed, .3);
            Bot.rotateRight(midSpeed, .4);
            Bot.strafeRight(midSpeed, 2);     // was 2.2
            Bot.rotateRight(midSpeed, 1.2);     // was .6
            Bot.strafeRight(midSpeed, 1.3);       // was 1.5
            Bot.rotateRight(midSpeed, 1.5);     // was 1
            Bot.strafeLeft(midSpeed, 2.9);      // was 2

        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, .3);
            Bot.rotateLeft(midSpeed, .2);
            Bot.strafeRight(midSpeed, 2);     // was 2.2
            Bot.rotateLeft(midSpeed, 1.2);     // was .6
            Bot.strafeRight(midSpeed, 1.5);       // was 1.5
            Bot.rotateLeft(midSpeed, .8);     // was 1
            Bot.strafeLeft(midSpeed, 2.9);      // was 2
        }


        Bot.HookRelease();


    }


    // *********   Methods to Park in different locations

    public void parkInner (MetalBot Bot, String Alliance) throws InterruptedException {
        if (Alliance  == "Red") {

            Bot.strafeRight(highSpeed, 4.8);

        }
        else if (Alliance == "Blue" ) {
            Bot.strafeRight(highSpeed, 4.8);

        }
    }


    public void parkOuter (MetalBot Bot, String Alliance) {
        if (Alliance  == "Red") {

        }
        else if (Alliance == "Blue" ) {

        }
    }

    public void parkSkyStoneInner (MetalBot Bot) {
        Bot.driveForward(midSpeed, 4);
    }

    public void parkSkyStoneOuter (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.driveForward(midSpeed, 3);
            Bot.strafeLeft(midSpeed, 1.5);
            Bot.driveForward(midSpeed, 1.5);
        }
        else if (Alliance == "Blue") {
            Bot.driveForward(midSpeed, 3);
            Bot.strafeRight(midSpeed, 1.5);
            Bot.driveForward(midSpeed, 1.5);
        }
    }


    // Additional placeholder methods for parking and dropping stone in loading auto

     public void preBuildPlateMove (MetalBot Bot, String Alliance ) {
         if (Alliance == "Red") {

         }
         else if (Alliance == "Blue") {


         }
     }

     public void postBuildPlateMove (MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {

        }
        else if (Alliance == "Blue") {

        }

     }

     public void postPlatePark (MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {

        }
        else if (Alliance == "Blue") {

        }

     }

    public void postPlateParkOuter (MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {

        }
        else if (Alliance == "Blue") {


        }

    }


    public void parkSkyStone (MetalBot Bot, String Alliance) {


        if (Alliance == "Red") {

        }

        else if (Alliance == "Blue") {

        }


    }

}
