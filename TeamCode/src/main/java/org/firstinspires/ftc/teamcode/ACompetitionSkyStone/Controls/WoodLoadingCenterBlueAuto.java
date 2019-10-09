package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

public class WoodLoadingCenterBlueAuto extends LinearOpMode {


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

    public void vuforiaStone () {

    }
    public void grabSkyStone() {

    }
    public void alignBuildPlate () {

    }
    public void placeBuildPlate () {

    }
    public void releaseBuildPlate() {

    }
}
