package org.firstinspires.ftc.teamcode.Emma.CompClassesGen2A;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

public abstract class AutoMainGen2A extends LinearOpMode {

    // Variables & Constants used for MetalBot across both Building and Loading Locations on the field

    public final long  sleepTime = 15;
    public final double maxSpeed = 1;
    public final double highSpeed = .6;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;
    public LinearOpMode linearOp = null;
    public final double gyroSPD = .25;

    public final int colorImage = 20;       // was 15 ... color of image is 18, 27 in MBCA BNI room  15 comp = 20
    public final int colorYellow = 40; //40 in MBCA BNI room b  30
    public final int colorNoBackground = 35; //is 35 ... was 60 in MBCA BNI room
    public double tracker = 0;

    public double timeThreshold = 0;
    public ElapsedTime skyStoneTime = new ElapsedTime();

    public int skystonePos = 2;


    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }

    // Methods used for both Building and Loading on the Field


    public void manipulateStone (MetalBot Bot, String manipulate) {
        if (manipulate == "grab") {
            Bot.dropStone();
            sleep(1000);
            Bot.grabStone();
            //Bot.setServos();
        }
        if (manipulate == "release") {
            Bot.dropStone();
            sleep(1000);
            Bot.releaseStone();
            sleep(1000);
//            Bot.raiseStone();
            //Bot.setServos();
        }
        if (manipulate =="release rotator") {
            Bot.autoRaiseStone();
            sleep(sleepTime);
            //Bot.setServos();
        }
        if (manipulate == "lower rotator") {
            Bot.dropStone();
            sleep(1000);
            //Bot.setServos();
        }
        if (manipulate == "release grabber") {
            Bot.releaseStone();
            sleep(1000);
            //Bot.setServos();
        }

        idle();
    }

    public void removeSkyStoneOuter (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, 2.5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, 2.5);
        }
    }

    public void removeSkyStoneInner (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, 1);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, 1);
        }
    }



    public void adjustToDropSkyStone (MetalBot Bot,String Alliance) { //could be turnToDropSkyStone

        if (Alliance == "Red") {
            Bot.rotateRight(lowSpeed, 2);
            sleep(sleepTime);
            Bot.gyroCorrection(gyroSPD, -90);
        }
        else if (Alliance == "Blue") {
            Bot.rotateLeft(lowSpeed, 2);
            sleep(sleepTime);
            Bot.gyroCorrection(gyroSPD, 91);
        }
        sleep(sleepTime);


    }

    public void parkPlateOnly( MetalBot Bot , String Alliance) {
        if (Alliance == "Red") {
            Bot.driveBackward(midSpeed, 4);

        }
        else if (Alliance == "Blue") {
            Bot.driveForward(midSpeed, 4);
        }
    }

    public void detectStoneDistance (MetalBot Bot) {

        while (!(Bot.checkDistance() < 5) && linearOp.opModeIsActive() )  {
            Bot.strafeLeft(lowSpeed);

        }
        linearOp.telemetry.addData("Distance Sensor (inches)", Bot.checkDistance());
        Bot.stopMotors();


    }

    public void pushBuildPlate (MetalBot Bot, String Alliance) {
        Bot.strafeLeft(midSpeed, 4.5);
        Bot.HookRelease();
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
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.strafeRight(lowSpeed, .5);
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

    public void parkBuildingPlateOuter (MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.strafeRight(lowSpeed, .4);
            Bot.rotateRight(midSpeed, 2);
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.driveForward(midSpeed, 2.5);
            Bot.strafeRight(lowSpeed, 3);
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
            Bot.strafeRight(midSpeed, 3);
            Bot.driveBackward(midSpeed, 1.25);
            Bot.gyroCorrection(gyroSPD,-179);
            Bot.gyroCorrection(gyroSPD, 179);
            Bot.driveBackward(lowSpeed, 1.1);
            Bot.gyroCorrection(gyroSPD, -179);
            Bot.strafeRight(lowSpeed, .4);
        }
    }


    public void detectSkyStone (MetalBot Bot, String Alliance) {


        skyStoneTime.reset();
        timeThreshold = 4.5;
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Bot.gyroCorrection(lowSpeed, 0);
        while ((Bot.checkColor() > colorImage) && skyStoneTime.seconds() < timeThreshold && linearOp.opModeIsActive()){
            if (Alliance == "Red") {
                Bot.driveBackward(.1);



            }
            else if (Alliance == "Blue") {
                Bot.driveForward(.1);

            }
            linearOp.telemetry.addData("Color Sensor Red Value: ", Bot.checkColor());
            linearOp.telemetry.addLine("Finding SkyStone!");
            linearOp.telemetry.update();
        }
        Bot.stopMotors();

        if (Alliance == "Blue") {
            Bot.gyroCorrection(lowSpeed, 0);
        }

        Bot.strafeRight(lowSpeed, .2);
        sleep(sleepTime);
        detectStoneDistance(Bot);
        Bot.strafeRight(lowSpeed, .1);

        tracker = Math.abs(Bot.frontLeftMotor.getCurrentPosition());



        idle();
        //Bot.strafeLeft(lowSpeed,.25);
        idle();
    }



    public void goToFirstLocation (MetalBot Bot, String Alliance) {
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if (Alliance == "Red") {
            Bot.gyroCorrection(gyroSPD, 0);
        }
        else if (Alliance == "Blue") {
            Bot.gyroCorrection(gyroSPD, 0);
        }
        while (Math.abs(Bot.frontLeftMotor.getCurrentPosition()) < tracker && linearOp.opModeIsActive()) {
            linearOp.telemetry.addData("Current Left Motor Position: ", Math.abs(Bot.frontLeftMotor.getCurrentPosition()));
            linearOp.telemetry.addData("Target Left Motor Position: ", tracker);
            linearOp.telemetry.update();
            if (Alliance == "Red") {
                Bot.driveForward(.3);


            }
            else if (Alliance == "Blue") {
                Bot.driveBackward(.3 );
            }

        }
        Bot.stopMotors();
        linearOp.telemetry.addLine("DO I GET HERE");
        linearOp.telemetry.update();
        linearOp.sleep(2000);
        Bot.stopMotors();
//        Bot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


















    //********************************
    // Methods that have been used previously, but are not being used now and have been archieved
    // *******************************
    public void hardCodeVuforia (MetalBot Bot, String Alliance) {
        if (skystonePos == 1){

            if (Alliance == "Red") {
                Bot.driveBackward(midSpeed, .5);
            }
            else if (Alliance == "Blue") {
                Bot.driveForward(midSpeed, .5);
            }
            //  was 7 if servos are on left side... drive forward
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .6 );
            skystonePos = 1;

        }
        else if (skystonePos == 2) {

            if (Alliance == "Red") {
                Bot.driveForward(midSpeed, .2);
            }
            else if (Alliance == "Blue") {
                Bot.driveBackward(midSpeed, .2);
            }
            Bot.strafeLeft(midSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .6);
            sleep(sleepTime);
            skystonePos  = 2;

        }
        else {

            if (Alliance == "Red") {
                Bot.driveForward(midSpeed, 1);
            }
            else if (Alliance == "Blue") {
                Bot.driveBackward(midSpeed, 1);
            }
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 1.8);
            Bot.strafeLeft(lowSpeed, .5);
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

  /*  public void vuforiaStone(MetalBot Bot, VuforiaWebcam Cam) {

        Cam.trackObjects();
        sleep(1000);

        telemetry.addData("Target Y:", Cam.targetY);
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


*/
    public void detectStoneColor (MetalBot Bot, String Alliance) {
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
//        Bot.gyroCorrection(gyroSPD, 1);   // was 0 but turned too far left
//        idle();
//        sleep(100);
//        Bot.HookGrab();
        Bot.strafeLeft(lowSpeed, .2);       // testing
    }




}
