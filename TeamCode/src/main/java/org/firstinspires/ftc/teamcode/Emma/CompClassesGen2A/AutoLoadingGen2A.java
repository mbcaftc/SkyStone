package org.firstinspires.ftc.teamcode.Emma.CompClassesGen2A;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoMain;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;


public abstract class AutoLoadingGen2A extends AutoMain {


    public void encoderAdditionDetection (MetalBot Bot, String Alliance) {
       if (Alliance == "Red") {
           Bot.driveForward(midSpeed, .25);
       }
       else if (Alliance == "Blue") {
           Bot.driveForward(midSpeed, .4);
       }
    }

    public void dropSkyStone(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {

                    Bot.driveForward(highSpeed, 8);
                    Bot.gyroCorrection(gyroSPD, -91);

//
        } else if (Alliance == "Blue") {

                    Bot.driveBackward(highSpeed, 8);
                    Bot.gyroCorrection(gyroSPD, 91);

        }
        sleep(sleepTime);;
    }


    public void alignBuildPlateOuter (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.driveBackward(highSpeed, 3.7
            );
            Bot.strafeLeft(midSpeed, 2);
            sleep(sleepTime);

        }
        else if (Alliance == "Blue") {
            Bot.driveForward(highSpeed, 3.7);
            Bot.strafeLeft(midSpeed, 2);
            sleep(sleepTime);

        }
        Bot.HookGrab();
        sleep(1000);

    }
    public void alignGrabBuildPlateInner (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.driveBackward(midSpeed, 2);
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 2);
            sleep(sleepTime);
        }
        else if (Alliance == "Blue") {
            Bot.driveForward(highSpeed, 2.5);
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 2);
            sleep(sleepTime);
        }
        Bot.HookGrab();
        sleep(1000);
    }



    public void orientBuildPlate (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, .8);
            Bot.rotateRight(midSpeed, 2);
            Bot.gyroCorrection(.3, -135);

        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, .8);
            Bot.rotateLeft(midSpeed, 2);
            Bot.gyroCorrection(gyroSPD, 135);
        }


    }



    public void parkInner (MetalBot Bot, String Alliance) {
        if (Alliance  == "Red") {
            Bot.driveForward(.8, 1.7);
            Bot.strafeLeft(lowSpeed, .8);
            Bot.rotateRight(lowSpeed,.8 );
            Bot.driveForward(.8, 1.9);
            Bot.strafeRight(lowSpeed, .2);
            sleep(sleepTime);
        }
        else if (Alliance == "Blue" ) {

            Bot.driveBackward(.8, 1.7);
            Bot.strafeLeft(lowSpeed, .8);
            Bot.rotateLeft(lowSpeed,.8 );
            Bot.driveBackward(.8, 1.9);
            Bot.strafeRight(lowSpeed, .2);
            sleep(sleepTime);
        }


    }

    public void parkOuter (MetalBot Bot, String Alliance) {
        if (Alliance  == "Red") {
            Bot.driveForward(.8, 1.7);
            Bot.strafeLeft(lowSpeed, .8);
            Bot.driveForward(.8, 1);
            Bot.rotateRight(lowSpeed,.8 );
            Bot.strafeLeft(lowSpeed, .8);
            Bot.driveForward(1.2, 1);

            sleep(sleepTime);
        }
        else if (Alliance == "Blue" ) {

            Bot.driveBackward(.8, 1.7);
            Bot.strafeLeft(lowSpeed, .8);
            Bot.driveBackward(.8, 1);
            Bot.rotateLeft(lowSpeed,.8 );
            Bot.strafeLeft(lowSpeed, .8);
            Bot.driveBackward(.8, 1);
        }


    }


    // two new methods for parking and dropping stone in loading auto

     public void preBuildPlateMove (MetalBot Bot, String Alliance ) {
         if (Alliance == "Red") {
             Bot.driveForward(midSpeed, 3);
             Bot.rotateRight(midSpeed, 2.5);
             Bot.gyroCorrection(gyroSPD, 90);
             Bot.driveBackward(midSpeed, 2);
         }
         else if (Alliance == "Blue") {
             Bot.driveBackward(midSpeed, 3);
             Bot.rotateLeft(midSpeed, 2.5);
             Bot.gyroCorrection(gyroSPD, -90);
             Bot.driveBackward(midSpeed, 2);

         }
     }

     public void postBuildPlateMove (MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.driveForward(midSpeed, 4.5);
            Bot.rotateRight(midSpeed, 2.5);
            Bot.gyroCorrection(gyroSPD, -90);
            Bot.gyroCorrection(gyroSPD, -90);
            Bot.strafeLeft(midSpeed, 2.9);
            Bot.gyroCorrection(gyroSPD, -90);
            Bot.strafeLeft(midSpeed,2.7);
        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(midSpeed, 4.5);
            Bot.rotateLeft(midSpeed, 2.5);
            Bot.gyroCorrection(gyroSPD, 90);
            Bot.gyroCorrection(gyroSPD, 90);
            Bot.strafeLeft(midSpeed, 2.9);
            Bot.gyroCorrection(gyroSPD, 90);
            Bot.strafeLeft(midSpeed,2.7);
        }

     }

     public void postPlatePark (MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, 1);
            Bot.rotateRight(midSpeed, 2.3);
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.driveForward(midSpeed, 1.8);
            Bot.strafeRight(lowSpeed, 1);
            Bot.driveForward(midSpeed, 2);
            //Bot.strafeLeft(lowSpeed, .5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, 1);
            Bot.rotateLeft(midSpeed, 2.4);
            //Bot.gyroCorrection(gyroSPD, 179);
            Bot.driveBackward(midSpeed, 1.8);
            Bot.strafeLeft(lowSpeed, 1);
            Bot.driveBackward(midSpeed, 2);
            //Bot.strafeLeft(lowSpeed, 1.5);

        }

     }

    public void postPlateParkOuter (MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, 1);
            Bot.rotateRight(midSpeed, 2.5);
            Bot.gyroCorrection(gyroSPD, 179);
            Bot.driveForward(midSpeed, 1.5);
            Bot.strafeRight(midSpeed, 3);
            Bot.driveForward(midSpeed, 1.5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, 1);
            Bot.rotateLeft(midSpeed, 2.5);
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.driveBackward(midSpeed, 1.5);
            Bot.strafeRight(midSpeed, 3);
            Bot.driveBackward(midSpeed, 1.5);

        }

    }












    public void parkSkyStone (MetalBot Bot, String Alliance) {


        if (Alliance == "Red") {
            Bot.strafeLeft(midSpeed, 6.8);
            sleep(sleepTime);

            Bot.dropStone();
            sleep(1000);

            Bot.strafeRight(midSpeed, 2.3);
        }

        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, 6.7);
            sleep(sleepTime);

            Bot.dropStone();
            sleep(1000);

            Bot.strafeRight(midSpeed, 2.3);
        }


    }

}
