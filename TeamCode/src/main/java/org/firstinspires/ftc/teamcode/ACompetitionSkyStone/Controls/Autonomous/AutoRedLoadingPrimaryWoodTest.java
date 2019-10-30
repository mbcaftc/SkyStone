package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Auto:Red Loading:Primary: Color Sensor: test")
public class AutoRedLoadingPrimaryWoodTest extends AutoMainLoadingWood {

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
            //Cam.trackObjects();
            sleep(sleepTime);



            Bot.strafeLeft(midSpeed, 1);

            detectSkystone(Bot, "Red");

            requestOpModeStop();
        }
        idle();
    }
}
