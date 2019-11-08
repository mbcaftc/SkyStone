package org.firstinspires.ftc.teamcode.Dawson;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Auto:Red Building:PrimaryTestD")
@Disabled
public class AutoRedPrimaryBuildingTestD extends AutoMainBuildingTestD {

    public WoodBot Bot = new WoodBot();
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

            alignBuildPlate(Bot, "Red");

            goToSkystonesInner(Bot, "Red");

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
