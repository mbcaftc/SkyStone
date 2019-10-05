package org.firstinspires.ftc.teamcode.MrAcker;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;


public abstract class AutoNavigation extends LinearOpMode {

    public LinearOpMode linearOp = null;

    public final long  sleepTime = 1000;
    public final double maxSpeed = 1;
    public final double highSpeed = .6;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;


    public AutoNavigation() {

    }

    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }

    public void driveTest (AckerBot Bot) {

        Bot.driveForward(highSpeed, 3.4);
        sleep(sleepTime);

        Bot.driveBackward(highSpeed, 0.5);              // drive backward with stone
        sleep(sleepTime);
    }

    public void camTest (AckerBot Bot, Vuforia Cam) {

        Bot.driveForward(highSpeed, 3.4);
        sleep(sleepTime);

        Bot.driveBackward(highSpeed, 0.5);              // drive backward with stone
        sleep(sleepTime);
    }

    public void camDrive (AckerBot Bot, VuforiaWebcam Cam) {

        Cam.trackObjects();

        if (Cam.targetVisible){

            if (Cam.targetY < 0) {
                while (Cam.targetY < 0 && Cam.targetVisible && opModeIsActive()) {
                    Bot.strafeLeft(midSpeed);
                }
                Bot.stopMotors();
                Bot.gyroCorrection(lowSpeed, 0);
                Bot.stopMotors();

            }
            else if (Cam.targetY > 0) {
                while (Cam.targetY > 0 && Cam.targetVisible && opModeIsActive()) {
                    Bot.strafeRight(midSpeed);
                }
                Bot.stopMotors();
                Bot.gyroCorrection(lowSpeed, 0);
                Bot.stopMotors();
            }

        }
        Bot.driveForward(highSpeed, 3.4);
        Bot.stopMotors();
        Bot.gyroCorrection(lowSpeed, 0);
        Bot.stopMotors();


    }



}
