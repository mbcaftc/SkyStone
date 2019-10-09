package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@Autonomous(name = "Wood Auto Loading Red")
public class WoodLoadingCenterRedAuto extends LinearOpMode {


    public WoodBot Bot = new WoodBot();
    public VuforiaWebcam Cam = new VuforiaWebcam();


    final long sleepTime = 100;
    final double maxSpeed = 1;
    final double highSpeed = .6;
    final double midSpeed = .5;
    final double lowSpeed = .3;


    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        Cam.initCamera(hardwareMap);
        Cam.activateTracking();

        waitForStart();

        while (opModeIsActive()) {
            vuforiaStone();
            grabSkyStone();
            alignBuildPlate ();
            placeBuildPlate ();
            releaseBuildPlate ();

        }

    }


    // methods

    public void vuforiaStone() {

        Cam.trackObjects();
        sleep(1000);

        if (Cam.targetY > 1 && Cam.targetVisible) {             //position 3
            Bot.driveForward(midSpeed, 1);
            Bot.strafeLeft(highSpeed, 4);
            sleep(sleepTime);

            telemetry.addLine("position 3");
            telemetry.update();
        }
        else if (Cam.targetY < 1 && Cam.targetVisible) {        //position 2
            Bot.strafeLeft(midSpeed, 4);
            sleep(sleepTime);

            telemetry.addLine(" drive forward ... position 2");
            telemetry.update();
        }
        else {                                                  // position 1
            Bot.driveBackward(midSpeed, 1);
            Bot.strafeLeft(highSpeed, 4);

            telemetry.addLine(" strafe left... position 1");
            telemetry.update();
        }
    }

    public void grabSkyStone() {
        Bot.grabStone(.5);
    }

    public void dropSkyStone() {
        Bot.strafeRight(midSpeed, .5);
        sleep(sleepTime);

        Bot.driveForward(highSpeed, 6.8);
        sleep(sleepTime);

        Bot.dropStone(0);
    }

    public void alignBuildPlate () {
        Bot.rotateRight(highSpeed, 2.5);
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 90);
        Bot.stopMotors();
        sleep(sleepTime);

        Bot.driveBackward(highSpeed,3.4);
        sleep(sleepTime);
    }

    public void placeBuildPlate () {
        Bot.HookRelease(.1, .1);
        sleep(sleepTime);

        Bot.strafeLeft(highSpeed, 1.2 );
        sleep(sleepTime);

        Bot.HookGrab(.9,.9);
        sleep(sleepTime);

        Bot.rotateRight(midSpeed, 2);
        // ********** Need GYRO Correction***********

        Bot.strafeLeft(highSpeed, 3.5);
        Bot.HookRelease(.1,.1);




    }

    public void releaseBuildPlate () {
        Bot.strafeRight(highSpeed, 1);

        Bot.rotateLeft(highSpeed, 2);
    }

    public void park() {
        //rev color sensor
    }




}
