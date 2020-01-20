package org.firstinspires.ftc.teamcode.Emma.CompClassesGen1.AutoPathsHardCoded;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

@Autonomous(name = "Auto:Red Building:Primary")
@Disabled

public class AutoRedBuildingHardCodedPrimaryHardCoded extends AutoBuildingHardCodedHardCoded {

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

            alignBuildPlate(Bot, "Red");

            goToSkystones(Bot, "Red");

            hardCodeVuforia(Bot, "Red");
            //vuforiaStone(Bot, Cam);

            orientToDropStone(Bot, "Red");

            dropStone(Bot);

            park(Bot);

            requestOpModeStop();
        }
        idle();

    }
}
