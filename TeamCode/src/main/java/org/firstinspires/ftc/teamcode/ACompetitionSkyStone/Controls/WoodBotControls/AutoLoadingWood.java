package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Emma.AckerBot.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

public abstract class AutoLoadingWood extends AutoMainWood {


    public void dropSkyStone(WoodBot Bot, String Alliance) {

        if (Alliance == "Red") {

                    Bot.strafeLeft(highSpeed, 5.5);
                    Bot.gyroCorrection(gyroSPD, -91);

//
        } else if (Alliance == "Blue") {

                    Bot.strafeLeft(highSpeed, 5.5);
                    Bot.gyroCorrection(gyroSPD, 91);

        }
        sleep(sleepTime);
        Bot.dropStone();
    }


    public void alignBuildPlateOuter (WoodBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.driveBackward(highSpeed, 1.8);

        }
        else if (Alliance == "Blue") {
            Bot.driveForward(highSpeed, 2.3);
        }

    }
    public void alignGrabBuildPlateInner (WoodBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.driveBackward(midSpeed, 2);
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 2);
            sleep(sleepTime);
        }
        else if (Alliance == "Blue") {
            Bot.driveForward(highSpeed, 2.7);
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 2);
            sleep(sleepTime);
        }
        Bot.HookGrab();
        sleep(1000);
    }



    public void orientBuildPlate (WoodBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, .8);
            Bot.rotateRight(midSpeed, 2);
            Bot.gyroCorrection(.3, -145);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, .8);
            Bot.rotateLeft(midSpeed, 2.5);
            Bot.gyroCorrection(gyroSPD, 145);
        }

    }

    public void pushBuildPlate (WoodBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeLeft(midSpeed, 4.5);

        }
        else if (Alliance == "Blue"){
            Bot.strafeLeft(midSpeed,4.5 );
        }
        Bot.HookRelease();
    }

    public void park (WoodBot Bot, String Alliance) {
        if (Alliance  == "Red") {
            Bot.driveForward(.8, 1.5);
            Bot.strafeRight(lowSpeed, .8);
            Bot.rotateRight(lowSpeed,.8 );
            Bot.driveForward(.8, 1.9);
            sleep(sleepTime);
        }
        else if (Alliance == "Blue" ) {

            Bot.driveBackward(highSpeed, 1.5);
            Bot.driveBackward(highSpeed, 1.5);          // was 1.5
            sleep(sleepTime);
        }


    }

}
