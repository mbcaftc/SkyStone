package org.firstinspires.ftc.teamcode.Emma.CompClassesGen1.AutoPathsHardCoded.oldCompClasses;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoMain;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;


public abstract class AutoLoadingOld extends AutoMain {


    public void dropSkyStone(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {

                    Bot.strafeLeft(highSpeed, 5.5);
                    Bot.gyroCorrection(gyroSPD, -91);

//
        } else if (Alliance == "Blue") {

                    Bot.strafeLeft(highSpeed, 5.7);
                    Bot.gyroCorrection(gyroSPD, 91);

        }
        sleep(sleepTime);
        Bot.dropStone();
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