package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@Autonomous(name = "Auto:Blue Loading:Primary")
public class AutoBlueLoadingPrimary extends AutoMainLoading {

    public MetalBot Bot = new MetalBot();
    //public VuforiaWebcam Cam = new VuforiaWebcam();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        //Cam.initCamera(hardwareMap);
        //Cam.activateTracking();
        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {
            //Cam.trackObjects();
           // telemetry.addData("Camera Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f", Cam.targetX, Cam.targetY, Cam.targetZ);
            //telemetry.update();
            //sleep(sleepTime);

            Bot.strafeLeft(midSpeed, 1);
            //sleep(1000);

            //vuforiaStone(Bot, Cam, "Blue");
            hardCodeVuforia(Bot, "Blue");

            removeSkyStoneInnerPath(Bot,"Blue");
            dropSkyStone(Bot, "Blue");
            alignGrabBuildPlateInner(Bot, "Blue");

            orientBuildPlate(Bot, "Blue");

            pushBuildPlate(Bot, "Blue");

            park(Bot, "Blue");

            requestOpModeStop();
        }
        idle();

    }
}
