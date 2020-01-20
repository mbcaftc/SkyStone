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
            Bot.strafeLeft(midSpeed, 2);
            Bot.driveBackward(midSpeed, .8);
            Bot.strafeLeft(midSpeed, 1.9);
            Bot.strafeLeft(lowSpeed, .4);
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
    }

    public void parkBuildingPlateInner (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(lowSpeed, .4);
            Bot.rotateRight(midSpeed, 2);
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.driveForward(midSpeed, 2.5);
            Bot.strafeRight(lowSpeed, .8);
            Bot.driveForward(midSpeed, 1);
            Bot.strafeRight(lowSpeed, .8);
            Bot.driveForward(lowSpeed, .4);

        }

        else if (Alliance == "Blue") {
            Bot.strafeRight(lowSpeed, .4);
            Bot.rotateLeft(midSpeed, 2);
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.driveBackward(midSpeed, 2.5);
            Bot.strafeRight(lowSpeed, .8);
            Bot.driveBackward(midSpeed, 1);
            Bot.strafeRight(lowSpeed, .8);
            Bot.driveForward(lowSpeed, .4);

        }
    }

    public void parkBuildingPlateOuter(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeRight(lowSpeed, .4);
            Bot.rotateRight(midSpeed, 2);
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.driveForward(midSpeed, 1.7);
            Bot.strafeLeft(midSpeed, 1.5);
            Bot.driveForward(midSpeed, 1.2);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(lowSpeed, .4);
            Bot.rotateLeft(midSpeed, 2);
            Bot.gyroCorrection(gyroSPD, 179);
            Bot.driveForward(midSpeed, 1.7);
            Bot.strafeRight(midSpeed, 1.5);
            Bot.driveForward(midSpeed, 1.2);
        }

    }






}
