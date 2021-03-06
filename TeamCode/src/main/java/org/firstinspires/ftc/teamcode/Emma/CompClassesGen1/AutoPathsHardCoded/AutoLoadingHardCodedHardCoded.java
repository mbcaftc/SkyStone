package org.firstinspires.ftc.teamcode.Emma.CompClassesGen1.AutoPathsHardCoded;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

public abstract class AutoLoadingHardCodedHardCoded extends AutoMainHardCoded {

    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }


    //outer path - removing skystone
    public void removeSkyStoneOuterPath(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, 3.5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, 3.5);
        }
        sleep(sleepTime);
    }

    public void removeSkyStoneInner (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, .5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, .5);
        }
        sleep(sleepTime);
    }

    public void dropSkyStone(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            switch (skystonePos) {
                case 1:
                    Bot.strafeLeft(highSpeed, 7.4);
                    Bot.gyroCorrection(gyroSPD, -92);
//                    Bot.gyroCorrection(lowSpeed, -92);
                    break;
                case 2:
                    Bot.strafeLeft(highSpeed, 5.7);
                    Bot.gyroCorrection(gyroSPD, -91);
                    //Bot.gyroCorrection(lowSpeed, -92);
                    break;
                case 3:
                    Bot.strafeLeft(highSpeed, 5.1);
                    Bot.gyroCorrection(gyroSPD, -91);
                    //Bot.gyroCorrection(lowSpeed, -92);
                    break;

            }
//
        } else if (Alliance == "Blue") {
            switch (skystonePos) {
                case 1: // left
                    Bot.strafeLeft(highSpeed, 5.6);
                    Bot.gyroCorrection(gyroSPD, 92);
                    break;
                case 2: // right
                    Bot.strafeLeft(highSpeed, 6.6);
                    Bot.gyroCorrection(gyroSPD, 91);
                    break;
                case 3: //middle
                    Bot.strafeLeft(highSpeed, 7.8);
                    Bot.gyroCorrection(gyroSPD, 91);
                    break;
            }

        }
        sleep(sleepTime);
        Bot.dropStone();
    }


    public void alignBuildPlateOuter (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.driveBackward(highSpeed, 1.8);

        }
        else if (Alliance == "Blue") {
            Bot.driveForward(highSpeed, 2.3);
        }

    }
    public void alignGrabBuildPlateInner (MetalBot Bot, String Alliance) {
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



    public void orientBuildPlate (MetalBot Bot, String Alliance) {
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

    public void pushBuildPlate (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeLeft(midSpeed, 4.5);

        }
        else if (Alliance == "Blue"){
            Bot.strafeLeft(midSpeed,4.5 );
        }
        Bot.HookRelease();
    }

    public void park (MetalBot Bot, String Alliance) {
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
