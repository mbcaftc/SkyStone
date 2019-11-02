package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Auto WoodBot:Red Loading:Primary: Color Sensor: test")
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
//            sleep(sleepTime);
            idle();


//            Bot.strafeLeft(midSpeed, 1);

            detectStone(Bot, "Red"); //drives forward to find any stone
            sleep(sleepTime);
            detectSkyStone (Bot, "Red"); //drive back until detects SKyStone
            sleep(sleepTime);

            manipulateStone(Bot, "grab"); //Grabs skystone
            sleep(sleepTime);
            idle();
            requestOpModeStop();
        }
        idle();
    }
}
