package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

public abstract class AutoMainLoadingWood extends LinearOpMode {
    public final long  sleepTime = 20;
    public final double maxSpeed = 1;
    public final double highSpeed = .5;
    public final double midSpeed = .4;
    public final double lowSpeed = .2;
    public LinearOpMode linearOp = null;
    public final double gyroSPD = .2;
    public final int colorImage = 15;
    public final int colorYellow = 40;
    public final int colorNoBackground = 60;




    public int skystonePos = 2;
    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }


    public void grabSkyStone( AckerBot Bot) {
        Bot.StoneGrab();
    }


    public void hardCodeVuforia ( MetalBot Bot, String Alliance) {
        if (skystonePos == 1){
            Bot.driveBackward(midSpeed, .6);                                 //  was 7 if servos are on left side... drive forward
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .6 );
            skystonePos = 1;

        }
        else if (skystonePos == 2) {
            Bot.driveForward(lowSpeed, .35);
            Bot.strafeLeft(midSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .4);
            sleep(sleepTime);
            skystonePos  = 2;

        }
        else {
            Bot.driveForward(midSpeed, .7);
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 1.8);
            Bot.strafeLeft(lowSpeed, .4);
            sleep(sleepTime);
            skystonePos = 3;

        }
        Bot.grabStone();
        sleep(1000);
        Bot.stopMotors();
        if (Alliance == "Red") {
            Bot.driveForward(lowSpeed, .4);
        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(lowSpeed, .4);
        }

    }

    public void detectStone (WoodBot Bot, String Alliance) {
        while ((Bot.checkColor() > colorImage && Bot.checkColor() < colorYellow) && linearOp.opModeIsActive()){
            if (Alliance == "Red") {
                Bot.strafeLeft(lowSpeed);
            }
            else if (Alliance == "Blue") {
                Bot.strafeRight(lowSpeed);
            }
            linearOp.telemetry.addData("Color Sensor Red Value: ", Bot.checkColor());
            linearOp.telemetry.addLine("Drive Towards Block!");
            linearOp.telemetry.update();
        }
        Bot.stopMotors();
        idle();
        Bot.gyroCorrection(gyroSPD, 0);
        idle();
//        sleep(100);
//        Bot.HookGrab();
    }

    public void detectSkyStone (WoodBot Bot, String Alliance) {
        while ((Bot.checkColor() > colorImage) && linearOp.opModeIsActive()){
            if (Alliance == "Red") {
                Bot.driveBackward(lowSpeed);
            }
            else if (Alliance == "Blue") {
                Bot.driveBackward(lowSpeed);
            }
            linearOp.telemetry.addData("Color Sensor Red Value: ", Bot.checkColor());
            linearOp.telemetry.addLine("Finding SkyStone!");
            linearOp.telemetry.update();
        }
        Bot.stopMotors();
        idle();
        Bot.strafeRight(.1,lowSpeed);
        idle();
        Bot.gyroCorrection(gyroSPD, 0);
        idle();
//        sleep(100);
//        Bot.HookGrab();  //moved to own function
    }

    public void manipulateStone (WoodBot Bot, String manipulate) {
        if (manipulate == "grab") {
            Bot.grabStone();
        }
        if (manipulate == "release") {
            Bot.dropStone();
        }
        idle();
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

    // inner plath
    public void removeSkyStoneInnerPath (MetalBot Bot,String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, .8);             //
            sleep(sleepTime);
            Bot.rotateRight(midSpeed, 2);
            sleep(sleepTime);
            Bot.gyroCorrection(gyroSPD, -90);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, .8);
            sleep(sleepTime);
            Bot.rotateLeft(midSpeed, 2);
            sleep(sleepTime);
            Bot.gyroCorrection(gyroSPD, 90);
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
