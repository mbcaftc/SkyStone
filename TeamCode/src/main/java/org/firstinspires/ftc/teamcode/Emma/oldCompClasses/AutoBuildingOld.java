package org.firstinspires.ftc.teamcode.Emma.oldCompClasses;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoMain;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;


public abstract class AutoBuildingOld extends AutoMain {



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

    public void orientBuildPlateBuild (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeLeft(midSpeed, 1.4);
            Bot.HookGrab();
            sleep(1000);
            Bot.driveForward(midSpeed, .4);
            Bot.rotateRight(midSpeed, 2);           //2
            Bot.gyroCorrection(.3, -50);        //was  50

        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, 1.4);
            Bot.HookGrab();
            sleep(1000);
            Bot.driveBackward(midSpeed, .4);
            Bot.rotateLeft(midSpeed, 2);        // was 2
            Bot.gyroCorrection(gyroSPD, 50);        //was 50
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
           Bot.driveForward(midSpeed, 2);
           Bot.gyroCorrection(gyroSPD, -90);
           Bot.driveForward(midSpeed, 1.8);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(lowSpeed, .35);
            Bot.driveBackward(midSpeed, 2.7);
            Bot.gyroCorrection(gyroSPD, 86);
            Bot.driveBackward(midSpeed, 1.5);
        }
    }



}
