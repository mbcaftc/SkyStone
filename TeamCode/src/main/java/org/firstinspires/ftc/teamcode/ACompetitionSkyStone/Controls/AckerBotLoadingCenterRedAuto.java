package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@Autonomous (name = "AckerBot Auto Loading Red")
public class AckerBotLoadingCenterRedAuto extends LinearOpMode {

    public AckerBot Bot = new AckerBot();
    public VuforiaWebcam Cam = new VuforiaWebcam();
    final long sleepTime = 100;
    final double maxSpeed = 1;
    final double highSpeed = .6;
    final double midSpeed = .5;
    final double lowSpeed = .3;
    int skyStonePosition;


    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        Cam.initCamera(hardwareMap);
        Cam.activateTracking();

        waitForStart();

        while (opModeIsActive()) {


            telemetry.addLine("before vuforia SkyStone");
//
            vuforiaDetecting();
            sleep(sleepTime);

            telemetry.addLine("after vuforia SkyStone");

            sampleSkyStone();
            sleep(sleepTime);

            dropOffStone();
            sleep(sleepTime);
//
            alignWithBuildPlate();
            sleep(sleepTime);

            reorientBuildPlate();
            sleep(sleepTime);
//
            goToSkyStones();
            sleep(sleepTime);
//

            grab2Skystone();
            sleep(sleepTime);

            dropOff2Skystone();
            sleep(sleepTime);


            idle();

            requestOpModeStop();

        }

        idle();

    }


    // **** methods for autonomous *****


    public void vuforiaDetecting () {


        Cam.trackObjects();
        sleep(1000);

        if (Cam.targetY > 1 && Cam.targetVisible) {
            Bot.strafeRight(midSpeed, 1);
            Bot.driveForward(highSpeed, 4);
            sleep(sleepTime);
            Bot.pattern =  RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_OCEAN_PALETTE;
            Bot.blinkinLedDriver.setPattern(Bot.pattern);
            telemetry.addLine("strafe right so... position 3");
            telemetry.update();
        }
        else if (Cam.targetY < 1 && Cam.targetVisible) {
            Bot.driveForward(midSpeed, 4);
            Bot.pattern =  RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_OCEAN_PALETTE;
            Bot.blinkinLedDriver.setPattern(Bot.pattern);
            telemetry.addLine(" drive forward... position 2");
            telemetry.update();
        }
        else {
            Bot.strafeLeft(midSpeed, 1);
            Bot.driveForward(highSpeed, 4);
            Bot.pattern =  RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_OCEAN_PALETTE;
            Bot.blinkinLedDriver.setPattern(Bot.pattern);
            telemetry.addLine(" strafe left... position 1");
            telemetry.update();
        }

    }




    public void sampleSkyStone () {

        telemetry.addLine("Sample Skystone");
        //sample a sky stone

        Bot.driveBackward(highSpeed, .5);              // drive backward with stone
        sleep(sleepTime);
    }

    public void dropOffStone () {

        telemetry.addLine("Drop off Stone");
        Bot.rotateLeft(highSpeed, 2.5);               // rotate toward build site with stone
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 85);
        sleep(sleepTime);

        Bot.driveBackward(highSpeed, 6.8);                // drive toward build site with the skystone
        sleep(sleepTime);

        // drop off the sky stone
    }

    public void alignWithBuildPlate () {

        telemetry.addLine("Align with Build Plate");

        Bot.strafeRight(highSpeed, 3.4);
        sleep(sleepTime);

//        Bot.gyroCorrection(lowSpeed, 85);
//        sleep(sleepTime);

        Bot.driveBackward(lowSpeed, 1);
        sleep(sleepTime);
    }

    public void reorientBuildPlate() {

        //grab build plate
        Bot.HookGrab(.9,.9);
        sleep(1000);

        telemetry.addLine("Re-orient Build Plate");

        Bot.driveForward(lowSpeed, .8);
        Bot.rotateRight (midSpeed, 2);               // strafe between the wall and the build plate
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 25);
        sleep(sleepTime);

        Bot.driveBackward(midSpeed, 3.5);
        sleep(sleepTime);

        //release build plate
        Bot.HookRelease(.1,.1);
        sleep(1000);

        // spit out stone

    }

    public void goToSkyStones() {

        telemetry.addLine("Go back to Sky Stones");


        Bot.driveForward(highSpeed, 2.3);
        sleep(sleepTime);

        Bot.rotateLeft(midSpeed,1);
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 82);
        sleep(sleepTime);

        Bot.strafeLeft(midSpeed, 5);

        Bot.rotateRight(midSpeed, 2.5);
        Bot.gyroCorrection(lowSpeed, -85);

        Bot.driveBackward(highSpeed, 9.5);             // strafe out (away from the wall and build site towards the skystones)
        sleep(sleepTime);

    }

    public void grab2Skystone () {
        Bot.rotateLeft(midSpeed, 2.5);
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 0);
        sleep(sleepTime);
        // intake skystone

        Bot.driveForward(lowSpeed, .5);
        Bot.driveBackward(lowSpeed, 1);

    }

    public void dropOff2Skystone () {

        Bot.rotateRight(midSpeed, 2.5);
        Bot.gyroCorrection(lowSpeed, -85);
        Bot.driveForward(highSpeed, 9.5);
        sleep(sleepTime);

        // drop off skystone with intake
    }

    // 358
    //152

    //need to verify this method
    public void parkSensor () {

        if (Bot.checkColor(150,350) == false) {
            Bot.stopMotors();
        }

    }

}
