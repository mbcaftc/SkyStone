package org.firstinspires.ftc.teamcode.MrAcker;


import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;


public abstract class TestAutoMain extends LinearOpMode {

    public final long  sleepTime = 1000;
    public final double maxSpeed = 1;
    public final double highSpeed = .6;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;
    public LinearOpMode linearOp = null;



    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }


    public void grabSkyStone( TestAckerBot Bot) {

        Bot.StoneGrab();
    }




    public void camDrive (TestAckerBot Bot, TestVuforia Cam) {

        Cam.trackObjects();
        sleep(1000);

        telemetry.addData("Target Y:", Cam.targetY);
        telemetry.update();

        if (Cam.targetY > 1 && Cam.targetVisible) {    //position 3

            while (Cam.targetY > 1 && Cam.targetVisible && opModeIsActive()) {

                Bot.driveBackward(midSpeed);
            }
            Bot.stopMotors();                                                        // if servos are on left side... drive forward
            sleep(sleepTime);
            telemetry.addLine("targetY > 1... position 3");

        }
        else if (Cam.targetY < 1 && Cam.targetVisible) {        //position 2

            while (Cam.targetY > 1 && Cam.targetVisible && opModeIsActive()) {

                Bot.driveForward(midSpeed);
            }
            Bot.stopMotors();
            sleep(sleepTime);
            telemetry.addLine(" targetY < 1 ... position 2");
            telemetry.update();
        }
        else {                                                  // position 1
            Bot.driveForward(midSpeed, 1);              // if servos are on left side... driveBackwards
            Bot.stopMotors();
            telemetry.addLine(" target is on the far left... position 1");
            telemetry.update();
        }

        while (Cam.targetX < 0 && Cam.targetVisible && opModeIsActive()) {

            Bot.strafeRight(midSpeed);
        }
    }


}
