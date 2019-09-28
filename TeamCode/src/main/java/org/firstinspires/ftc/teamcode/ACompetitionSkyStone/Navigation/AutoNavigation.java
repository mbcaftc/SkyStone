package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Navigation;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;


public abstract class AutoNavigation extends LinearOpMode {


    public final long  sleepTime = 200;
    public final double maxSpeed = 1;
    public final double highSpeed = .6;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;

    public AckerBot Bot = new AckerBot();


    public AutoNavigation() {

    }


    public void sampleSkyStone () {

        telemetry.addLine("Sample Skystone");
        telemetry.update();
        Bot.driveForward(highSpeed, 3.4);
        sleep(sleepTime);

        //sample .a sky stone

        Bot.driveBackward(highSpeed, .5);              // drive backward with stone
        sleep(sleepTime);
    }

    public void dropOffStone () {

        telemetry.addLine("Drop off Stone");
        telemetry.update();
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
        telemetry.update();
        Bot.strafeRight(highSpeed, 3.6);
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 85);
        sleep(sleepTime);

        Bot.driveBackward(lowSpeed, 1);
        sleep(sleepTime);
    }

    public void reorientBuildPlate() {

        telemetry.addLine("Re-orient Build Plate");
        telemetry.update();
        //grab build plate
        Bot.rotateRight (midSpeed, 1.5);               // strafe between the wall and the build plate
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 40);
        sleep(sleepTime);

        Bot.driveBackward(midSpeed, 2);
        sleep(sleepTime);

        //release build plate

    }

    public void goToSkyStones() {

        telemetry.addLine("Go to Sky Stones");
        telemetry.update();
        Bot.driveForward(highSpeed, 1);
        sleep(sleepTime);

        Bot.rotateLeft(midSpeed,1);
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 82);
        sleep(sleepTime);

        Bot.driveForward(highSpeed, 8.5);             // strafe out (away from the wall and build site towards the skystones)
        sleep(sleepTime);

    }

    public void grab2Skystone () {
        Bot.rotateLeft(midSpeed, 2.5);
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 0);
        sleep(sleepTime);
        // intake skystone

    }

    public void dropOff2Skystone () {
        Bot.driveForward(highSpeed, 9.5);
        sleep(sleepTime);

        // drop off skystone with intake
    }

}
