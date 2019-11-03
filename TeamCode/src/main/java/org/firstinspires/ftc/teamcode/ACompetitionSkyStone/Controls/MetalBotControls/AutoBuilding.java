package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoMainWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

public abstract class AutoBuilding extends AutoMain {



    public void alignBuildPlate (MetalBot Bot, String Alliance) {


        Bot.strafeLeft(highSpeed, 3);

        if (Alliance == "Red") {
            Bot.driveForward(midSpeed, 1);
        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(midSpeed, 1);
            Bot.strafeLeft(lowSpeed, .4);
        }

        Bot.strafeLeft(lowSpeed, .5);

        Bot.HookGrab();
        sleep(1000);

        Bot.strafeRight(midSpeed, 4.8);

        Bot.HookRelease();

    }


    public void goToSkystones(MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.gyroCorrection(gyroSPD, -1);

            Bot.driveBackward(highSpeed, 5.5);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.strafeLeft(highSpeed, 2.5);
            sleep(sleepTime);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.driveBackward(highSpeed, .5);
            sleep(sleepTime);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.strafeLeft(lowSpeed, .15);


        }

        else if (Alliance == "Blue") {

            Bot.gyroCorrection(lowSpeed, -1);

            Bot.driveForward(highSpeed, 5.5);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.strafeLeft(highSpeed, 2.5);
            sleep(sleepTime);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.driveForward(highSpeed, 3.9);
            sleep(sleepTime);

            Bot.gyroCorrection(gyroSPD, 0);

            Bot.strafeRight(highSpeed, 1.2);
            sleep(sleepTime);
        }

            

    }

    public void dropStone (MetalBot Bot) {

            Bot.strafeLeft(highSpeed, 8.8);
            Bot.dropStone();


    }


    public void park (MetalBot Bot) {
        switch (skystonePos) {
            case 1:
                Bot.strafeRight(highSpeed, .2);

            case 2:
                Bot.strafeRight(highSpeed, .7);

            case 3:
                Bot.strafeRight(highSpeed, 2.0);

        }

    }

}
