package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;


public abstract class AutoBuilding extends AutoMain {


    public void goToBuildPlate (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeLeft(midSpeed, 2);
            Bot.driveForward(midSpeed, .8);
            Bot.strafeLeft(midSpeed, 1.9);
            Bot.strafeLeft(lowSpeed, .4);
        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, 2.4);
            Bot.driveBackward(midSpeed, .4);
            Bot.strafeLeft(midSpeed, 1.9);
            Bot.strafeLeft(lowSpeed, .65);
        }

    }

    public void orientBuildPlateBuild (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.HookGrab();
            sleep(1000);
            Bot.strafeRight(midSpeed, 2);
            Bot.rotateRight(midSpeed, .5);
            Bot.gyroCorrection(.3, -30);
            Bot.strafeRight(.4, 1.5);
            Bot.rotateRight(midSpeed, .5);
            Bot.gyroCorrection(gyroSPD, -90);

        }
        else if (Alliance == "Blue") {
            Bot.HookGrab();
            sleep(1000);
            Bot.strafeRight(midSpeed, 2);
            Bot.rotateLeft(midSpeed, .5);
            Bot.gyroCorrection(.3, 30);
            Bot.strafeRight(.4, 1.5);
            Bot.rotateLeft(midSpeed, .5);
            Bot.gyroCorrection(gyroSPD, 90);

        }
    }

    public void pushBuildPlate (MetalBot Bot, String Alliance) {
        Bot.strafeLeft(midSpeed, 4.5);
        Bot.HookRelease();
        sleep(100);
    }

    public void parkBuildingPlateInner (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(lowSpeed, .4);
            Bot.rotateRight(midSpeed, 2);
            Bot.gyroCorrection(gyroSPD, -179);      // was 179
            Bot.rotateLeft(midSpeed, .1);
            Bot.driveForward(midSpeed, 3.6);

//            Bot.strafeRight(lowSpeed, .8);
//            Bot.driveForward(midSpeed, 1);
//            Bot.strafeRight(lowSpeed, .8);

        }

        else if (Alliance == "Blue") {
            Bot.strafeRight(lowSpeed, .4);
            Bot.rotateLeft(midSpeed, 2);
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.rotateRight(midSpeed, .1);
            Bot.strafeRight(midSpeed, 1.3);
            Bot.driveBackward(midSpeed, 3.5);

        }
    }

    public void parkBuildingPlateOuter(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeRight(lowSpeed, .4);
            Bot.rotateRight(midSpeed, 1.5);
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.driveForward(midSpeed, 1.7);
            Bot.strafeLeft(midSpeed, 3);
            Bot.driveForward(midSpeed, 1.2);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(lowSpeed, .4);
            Bot.rotateLeft(midSpeed, 1.5);
            Bot.gyroCorrection(gyroSPD, 179);
            Bot.driveBackward(midSpeed, 1.7);
            Bot.strafeLeft(midSpeed, 3);
            Bot.driveBackward(midSpeed, 1.2);
        }

    }






}
