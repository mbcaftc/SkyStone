package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;


public abstract class AutoLoading extends AutoMain {


    public void dropSkyStone(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.rotateLeft(.3, 4);
            Bot.gyroCorrection(.15, 179);
            Bot.driveBackward(.3, .5);

            // methods for stacking arm
//
        } else if (Alliance == "Blue") {
            Bot.rotateRight(.3, 4);
            Bot.gyroCorrection(.15, -179);
            Bot.driveBackward(.3, .5);


        }
        sleep(sleepTime);;
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

    public void alignGrabPlate (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.rotateLeft(.3, 2.5);
            Bot.gyroCorrection(.2,-90 );
            linearOp.idle();
            Bot.strafeLeft(.3, 1);
        }
        else if (Alliance == "Blue") {
            Bot.rotateRight(.3, 2.5);
            Bot.gyroCorrection(.2,90 );
            linearOp.idle();
            Bot.strafeRight(.3, 1);
        }

        Bot.HookGrab();

    }


    public void orientBuildPlate (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(.3, 1);
            Bot.rotateRight(.3, 1.25);
            Bot.gyroCorrection(.2, -135);
            Bot.strafeRight(.3, 1);
            Bot.rotateRight(.3, 1.25);
            Bot.gyroCorrection(.2, -178);
            Bot.strafeLeft(midSpeed, 3);

        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(.3, 1);
            Bot.rotateLeft(.3, 1.25);
            Bot.gyroCorrection(.2, 135);
            Bot.strafeLeft(.3, 1);
            Bot.rotateLeft(.3, 1.25);
            Bot.gyroCorrection(.2, 178);
            Bot.strafeRight(midSpeed, 3);
        }


        Bot.HookRelease();


    }


    public void parkInner (MetalBot Bot, String Alliance) {
        if (Alliance  == "Red") {
            Bot.strafeRight(.4, .5);
            Bot.rotateRight(.4, 2.5);
            Bot.gyroCorrection(.2, 90);
            Bot.driveForward(.3, 2.5);
        }
        else if (Alliance == "Blue" ) {
            Bot.strafeRight(.4, .5);
            Bot.rotateLeft(.4, 2.5);
            Bot.gyroCorrection(.2, -90);
            Bot.driveForward(.3, 2.5);

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


    // two new methods for parking and dropping stone in loading auto

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
