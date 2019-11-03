package org.firstinspires.ftc.teamcode.MrDuVal.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

public abstract class AutoMainEncoder extends LinearOpMode {
    public final long  sleepTime = 20;
    public final double maxSpeed = 1;
    public final double highSpeed = .5;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;
    public LinearOpMode linearOp = null;
    public final double gyroSPD = .15;

    public double targetEncoders, targetAngle = 0;

    public int skystonePos = 2;

    public void setLinearOp(LinearOpMode Op) {
        linearOp = Op;
    }


    public void adjustDrive () {
        if ((gamepad1.left_stick_y < .1) || (gamepad1.left_stick_y > .1)) {
            targetEncoders -= gamepad1.left_stick_y*0.1;
            linearOp.telemetry.addData("Target Encoders: ", targetEncoders);
            telemetry.update();
        }
        if (gamepad1.left_stick_x < .1) {
            targetAngle = 45;
            linearOp.telemetry.addData("Target Angle: ", targetAngle);
            telemetry.update();
        }
        if (gamepad1.left_stick_x > .1) {
            targetAngle = 90;
            linearOp.telemetry.addData("Target Angle: ", targetAngle);
            telemetry.update();
        }
    }


//    public void grabSkyStone(EncoderBot Bot) {
//        Bot.StoneGrab();
//    }


    //  detecting the skystone
    public void vuforiaStone(MetalBot Bot, VuforiaWebcam Cam) {

//        Cam.trackObjects();
//        sleep(1000);

//        telemetry.addData("Target Y:", Cam.targetY);
        telemetry.update();

        if (Cam.targetY > 1 && Cam.targetVisible) {             //position 3
            //Bot.rotateRight(highSpeed, 1);
            Bot.driveBackward(midSpeed, 1);                                 // if servos are on left side... drive forward
            Bot.strafeLeft(highSpeed, 4);                                  // if servos are on left side... strafeLeft
            sleep(sleepTime);

            telemetry.addLine("targetY > 1... position 3");

        }
        else if (Cam.targetY < 1 && Cam.targetVisible) {        //position 2

            Bot.strafeLeft(midSpeed, 4);                                   // if servos are on the left side... strafeLeft
            sleep(sleepTime);

            telemetry.addLine(" targetY < 1 ... position 2");
            telemetry.update();

        }
        else {                                                  // position 1
            //Bot.rotateLeft(highSpeed, 1);
            Bot.driveForward(midSpeed, 1);                                  // if servos are on left side... driveBackwards
            Bot.strafeLeft(highSpeed, 4);                                  // if servos are on the left side... strafeLeft

            telemetry.addLine(" target is on the far left... position 1");
            telemetry.update();

        }

        Bot.grabStone();
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
                    Bot.strafeLeft(highSpeed, 7.7);
                    Bot.gyroCorrection(gyroSPD, -92);
//                    Bot.gyroCorrection(lowSpeed, -92);
                    break;
                case 2:
                    Bot.strafeLeft(highSpeed, 6.2);
                    Bot.gyroCorrection(gyroSPD, -91);
                    //Bot.gyroCorrection(lowSpeed, -92);
                    break;
                case 3:
                    Bot.strafeLeft(highSpeed, 5.5);
                    Bot.gyroCorrection(gyroSPD, -91);
                    //Bot.gyroCorrection(lowSpeed, -92);
                    break;

            }
//
        } else if (Alliance == "Blue") {
            switch (skystonePos) {
                case 1: // left
                    Bot.strafeLeft(highSpeed, 6);
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
            Bot.driveForward(highSpeed, 1.8);
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
            Bot.rotateRight(midSpeed, 2.5);
            Bot.gyroCorrection(.3, -155);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, .8);
            Bot.rotateLeft(midSpeed, 2.5);
            Bot.gyroCorrection(gyroSPD, 155);
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
            Bot.rotateLeft(lowSpeed, .5);
            Bot.driveForward(.8, 1.9);
            sleep(sleepTime);
        }
        else if (Alliance == "Blue" ) {

            Bot.driveBackward(highSpeed, 1.5);
            Bot.driveBackward(highSpeed, 1.5);          // was 1.5
            sleep(sleepTime);
        }


    }



//    public void driveRobot () {
//        if (gamepad1.dpad_up) {
//            Bot.driveForwardPID (targetEncoders);
////            encoders += .5;
//        }
}
