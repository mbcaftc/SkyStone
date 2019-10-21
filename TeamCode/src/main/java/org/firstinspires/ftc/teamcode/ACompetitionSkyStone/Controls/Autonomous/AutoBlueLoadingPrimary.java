package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@Autonomous(name = "Auto:Blue Loading:Primary")
public class AutoBlueLoadingPrimary extends AutoMain {

    public WoodBot Bot = new WoodBot();
    public VuforiaWebcam Cam = new VuforiaWebcam();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        Cam.initCamera(hardwareMap);
        Cam.activateTracking();
        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {
            //Cam.trackObjects();
            //sleep(sleepTime);

            Bot.strafeLeft(midSpeed, 1);

            //vuforiaStone(Bot, Cam);
            hardCodeVuforia(Bot);
            removeSkyStoneInnerPath(Bot,"Blue");
            dropSkyStone(Bot, "Blue");
            alignGrabBuildPlateInner(Bot, "Blue");

            orientBuildPlate(Bot, "Blue");

            pushBuildPlate(Bot, "Blue");

            park(Bot, "Blue");

            idle();
            requestOpModeStop();
        }
        idle();

    }
}
