package org.firstinspires.ftc.teamcode.Dawson;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Emma.CompClassesGen1.AutoPathsHardCoded.AutoLoadingHardCodedHardCoded;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

@Autonomous(name = "Auto:Red Loading:Primary: TestD")
@Disabled
public class AutoRedLoadingPrimaryTestD extends AutoLoadingHardCodedHardCoded {

    public MetalBot Bot = new MetalBot();
    //public VuforiaWebcam Cam = new VuforiaWebcam();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap, "Auto");
        Bot.setLinearOp(this);

        //Cam.initCamera(hardwareMap);
        //Cam.activateTracking();
        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {
            //Cam.trackObjects();
            sleep(sleepTime);

            Bot.strafeLeft(midSpeed, 1);

            //vuforiaStone(Bot, Cam, "Red");
            hardCodeVuforia(Bot, "Red");

            removeSkyStoneInner(Bot,"Red");

            dropSkyStone(Bot, "Red");

            alignGrabBuildPlateInner(Bot, "Red");

            orientBuildPlate(Bot, "Red");

            pushBuildPlate(Bot, "Red");

            park(Bot, "Red");

            requestOpModeStop();
        }
        idle();
    }
}
