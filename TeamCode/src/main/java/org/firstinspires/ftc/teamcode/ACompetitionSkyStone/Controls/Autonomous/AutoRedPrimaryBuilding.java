package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@Autonomous(name = "Auto:Red Building:Primary")
public class AutoRedPrimaryBuilding extends AutoMainBuilding {

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
