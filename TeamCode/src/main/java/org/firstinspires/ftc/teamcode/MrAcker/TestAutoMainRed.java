package org.firstinspires.ftc.teamcode.MrAcker;


import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous.AutoMain;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;


public abstract class TestAutoMainRed extends TestAutoMain {




    public void removeSkyStoneInnerPath(TestAckerBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeLeft(midSpeed, 0.5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, 0.5);
        }
        sleep(sleepTime);
    }


    public void removeSkyStoneOuterPath(TestAckerBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeLeft(midSpeed, 2.5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, 2.5);
        }
        sleep(sleepTime);
    }

    public void dropSkyStone(TestAckerBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.driveForward(highSpeed, 6.5);

        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(highSpeed, 6.5);
        }

        sleep(sleepTime);
        Bot.StoneRelease();
    }

    public void returnBacktoSkyStones(TestAckerBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.driveBackward(highSpeed, 6.8);
        }
        else if (Alliance == "Blue") {
            Bot.driveForward(highSpeed, 6.8);
        }

        sleep(sleepTime);
    }


    public void alignBuildPlate (TestAckerBot Bot) {
        Bot.rotateRight(highSpeed, 2.5);
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 90);
        Bot.stopMotors();
        sleep(sleepTime);

        Bot.driveBackward(highSpeed,3.4);
        sleep(sleepTime);
    }


    public void placeBuildPlate ( TestAckerBot Bot, String Alliance) {

        Bot.HookRelease(.1, .1);
        sleep(sleepTime);

        if (Alliance == "Red") {

            Bot.strafeLeft(highSpeed, 1.2);
            sleep(sleepTime);

            Bot.HookGrab(.9, .9);
            sleep(sleepTime);

            Bot.rotateRight(midSpeed, 2);
            // ********** Need GYRO Correction***********

            Bot.strafeLeft(highSpeed, 3.5);

        }
        else if (Alliance == "Blue") {

            Bot.strafeRight(highSpeed, 1.2);
            sleep(sleepTime);

            Bot.HookGrab(.9, .9);
            sleep(sleepTime);

            Bot.rotateLeft(midSpeed, 2);
            // ********** Need GYRO Correction***********

            Bot.strafeRight(highSpeed, 3.5);


        }


    }

    public void releaseBuildPlate (TestAckerBot Bot) {

        Bot.HookRelease(.1, .1);

        Bot.strafeRight(highSpeed, 1);

        Bot.rotateLeft(highSpeed, 2);
    }

    public void park() {
        //rev color sensor
    }




}
