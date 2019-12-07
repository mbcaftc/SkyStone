package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;


public abstract class AutoBuilding extends AutoMain {



    public void alignBuildPlate (MetalBot Bot, String Alliance) {


        Bot.strafeLeft(highSpeed, 3);

        if (Alliance == "Red") {
            Bot.driveForward(midSpeed, 1);
        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(midSpeed, 1);
            Bot.strafeLeft(midSpeed, .4);
        }

        Bot.strafeLeft(midSpeed, .5);

        Bot.HookGrab();
        sleep(1000);

        Bot.strafeRight(midSpeed, 4.5);

        if (Alliance == "Red") {
            Bot.gyroCorrection(gyroSPD, -23);
            Bot.HookRelease();
            Bot.strafeRight(midSpeed, 1.2);

        }
        else if (Alliance == "Blue"){
            Bot.gyroCorrection(gyroSPD, 23);
            Bot.HookRelease();
            Bot.strafeRight(midSpeed, 1.2);
        }
        Bot.gyroCorrection(gyroSPD, 0);

    }

    // new method ~ Emma 12/6

    public void goToBuildPlate (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeLeft(midSpeed, 2);
            Bot.driveForward(midSpeed, .3);
            Bot.strafeLeft(midSpeed, 1.9);
            Bot.strafeLeft(lowSpeed, .3);
        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, 2);
            Bot.driveBackward(midSpeed, .8);
            Bot.strafeLeft(midSpeed, 1.9);
            Bot.strafeLeft(lowSpeed, .3);
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


    public void goToSkystones(MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.gyroCorrection(gyroSPD, -1);

            Bot.driveBackward(highSpeed, 5.5);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.strafeLeft(highSpeed, 4.5);
            sleep(sleepTime);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.driveBackward(highSpeed, 1.5);
            sleep(sleepTime);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.strafeRight(lowSpeed, .1);


        }

        else if (Alliance == "Blue") {

            Bot.gyroCorrection(lowSpeed, -1);

            Bot.driveForward(highSpeed, 5.5);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.strafeLeft(highSpeed, 2.5);
            sleep(sleepTime);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.driveForward(highSpeed, .8);
            sleep(sleepTime);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.strafeRight(highSpeed, .15);
            sleep(sleepTime);
        }

            

    }

    public void goToSkystonesOuter(MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.gyroCorrection(gyroSPD, -1);

            Bot.driveBackward(highSpeed, 5.5);

            Bot.gyroCorrection(gyroSPD, 0);


        }

        else if (Alliance == "Blue") {

            Bot.gyroCorrection(lowSpeed, -1);

            Bot.driveForward(highSpeed, 5.5);

            Bot.gyroCorrection(gyroSPD, 0);

        }

//

    }

    public void dropStone (MetalBot Bot) {

            Bot.strafeLeft(highSpeed, 5.5);
            Bot.dropStone();


    }


    public void park (MetalBot Bot, String Alliance) {
            if (Alliance == "Red") {
                Bot.strafeRight(midSpeed, 2.5);

        }
            else if (Alliance == "Blue") {
                Bot.driveBackward(midSpeed, 2.7);
            }

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
            Bot.driveForward(lowSpeed, .5);
            Bot.gyroCorrection(gyroSPD, 179);
        }

        else if (Alliance == "Blue") {
            Bot.strafeRight(lowSpeed, .4);
            Bot.rotateLeft(midSpeed, 2);
            Bot.gyroCorrection(gyroSPD, 179);
            Bot.driveBackward(midSpeed, 1.25);
            Bot.strafeRight(midSpeed, .3);
            Bot.driveBackward(midSpeed, 1.25);
            Bot.gyroCorrection(gyroSPD,-179);
            Bot.gyroCorrection(gyroSPD, 179);
            Bot.driveBackward(lowSpeed, 1.1);
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.strafeRight(lowSpeed, .4);
        }
    }



}
