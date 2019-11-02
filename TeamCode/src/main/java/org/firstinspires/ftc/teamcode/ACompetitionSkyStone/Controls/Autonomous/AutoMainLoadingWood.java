package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

public abstract class AutoMainLoadingWood extends LinearOpMode {
    public final long  sleepTime = 20;
    public final double maxSpeed = 1;
    public final double highSpeed = .5;
    public final double midSpeed = .4;
    public final double lowSpeed = .25;
    public LinearOpMode linearOp = null;
    public final double gyroSPD = .2;
    public final int colorImage = 22;       // was 15 ... color of image is 18
    public final int colorYellow = 40;
    public final int colorNoBackground = 60;
    public double tracker = 0;

    public double timeThreshold = 0;
    public ElapsedTime skyStoneTime = new ElapsedTime();


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

    public void detectStoneColor (WoodBot Bot, String Alliance) {
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

    public void detectStoneDistance (WoodBot Bot) {

        while (!(Bot.checkDistance() < 5) && linearOp.opModeIsActive() )  {
            Bot.strafeLeft(lowSpeed);

        }
        linearOp.telemetry.addData("Distance Sensor (inches)", Bot.checkDistance());
        Bot.stopMotors();




    }



    public void detectSkyStone (WoodBot Bot, String Alliance) {
        skyStoneTime.reset();
        timeThreshold = 4.5;
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while ((Bot.checkColor() > colorImage) && skyStoneTime.seconds() < timeThreshold && linearOp.opModeIsActive()){
            if (Alliance == "Red") {
                Bot.driveBackward(.15);

            }
            else if (Alliance == "Blue") {
                Bot.driveBackward(lowSpeed);
            }
            linearOp.telemetry.addData("Color Sensor Red Value: ", Bot.checkColor());
            linearOp.telemetry.addLine("Finding SkyStone!");
            linearOp.telemetry.update();
        }
        Bot.stopMotors();


        tracker = Math.abs(Bot.frontLeftMotor.getCurrentPosition());

        idle();
        Bot.strafeLeft(lowSpeed,.25);
        idle();
//        Bot.gyroCorrection(gyroSPD,  1) ;     // was 0 turn left to much
//        idle();
//        sleep(100);
//        Bot.HookGrab();  //moved to own function
    }

    public void manipulateStone (WoodBot Bot, String manipulate) {
        if (manipulate == "grab") {


            Bot.grabStone();
            sleep(1000);
        }
        if (manipulate == "release") {
            Bot.dropStone();
        }
        idle();
    }

    public void removeSkyStoneOuter(WoodBot Bot, String Alliance) {


        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, 2.5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, 2.5);
        }
        sleep(sleepTime);
    }

    public void removeSkyStoneInner (WoodBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, .5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, .5);
        }
        sleep(sleepTime);
    }

    public void adjustToDropSkyStone (WoodBot Bot,String Alliance) {

        if (Alliance == "Red") {
            Bot.rotateRight(lowSpeed, 2);
            sleep(sleepTime);
            Bot.gyroCorrection(gyroSPD, -90);
        }
        else if (Alliance == "Blue") {
            Bot.rotateLeft(midSpeed, 2);
            sleep(sleepTime);
            Bot.gyroCorrection(gyroSPD, 90);
        }
        sleep(sleepTime);


    }



    public void goToFirstLocation (WoodBot Bot, String Alliance) {
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (Math.abs(Bot.frontLeftMotor.getCurrentPosition()) < tracker && linearOp.opModeIsActive()) {
            linearOp.telemetry.addData("Current Left Motor Position: ", Math.abs(Bot.frontLeftMotor.getCurrentPosition()));
            linearOp.telemetry.addData("Target Left Motor Position: ", tracker);
            linearOp.telemetry.update();
            if (Alliance == "Red") {
                Bot.strafeLeft(.3);
            }
            else if (Alliance == "Blue") {
                Bot.strafeLeft(.3 );
            }

        }
        Bot.stopMotors();
        linearOp.telemetry.addLine("DO I GET HERE");
        linearOp.telemetry.update();
        linearOp.sleep(2000);
        Bot.stopMotors();
//        Bot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }




    //outer path - removing skystone


    // inner plath


    public void dropSkyStone(WoodBot Bot, String Alliance) {

        if (Alliance == "Red") {
           // switch (skystonePos) {
//                case 1:
//                    Bot.strafeLeft(highSpeed, 7.4);
//                    Bot.gyroCorrection(gyroSPD, -92);
////                    Bot.gyroCorrection(lowSpeed, -92);
//                    break;
//                case 2:
                    Bot.strafeLeft(highSpeed, 5.5);
                    Bot.gyroCorrection(gyroSPD, -91);
                    //Bot.gyroCorrection(lowSpeed, -92);
//                    break;
//                case 3:
//                    Bot.strafeLeft(highSpeed, 5.1);
//                    Bot.gyroCorrection(gyroSPD, -91);
//                    //Bot.gyroCorrection(lowSpeed, -92);
//                    break;

//            }
//
        } else if (Alliance == "Blue") {
//            switch (skystonePos) {
//                case 1: // left
//                    Bot.strafeLeft(highSpeed, 5.6);
//                    Bot.gyroCorrection(gyroSPD, 92);
//                    break;
//                case 2: // right
                    Bot.strafeLeft(highSpeed, 6.6);
                    Bot.gyroCorrection(gyroSPD, 91);
//                    break;
//                case 3: //middle
//                    Bot.strafeLeft(highSpeed, 7.8);
//                    Bot.gyroCorrection(gyroSPD, 91);
//                    break;
//            }

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
