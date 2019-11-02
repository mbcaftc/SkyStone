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

            detectStoneDistance(Bot); //drives forward to find any stone
            sleep(sleepTime);


//            telemetry.addData("Current encoder - 1 ", Bot.frontLeftMotor.getCurrentPosition());
//            telemetry.update();
//            sleep(3000);


            detectSkyStone (Bot, "Red"); //drive back until detects SKyStone
            sleep(sleepTime);


//            telemetry.addData("Current encoder - 2 ", Bot.frontLeftMotor.getCurrentPosition());
//            telemetry.update();
//            sleep (3000);


            manipulateStone(Bot, "grab"); //Grabs skystone
            sleep(sleepTime);

            removeSkyStoneInner(Bot, "Red");
            sleep(sleepTime);

            adjustToDropSkyStone(Bot, "Red");
            sleep(sleepTime);

            goToFirstLocation(Bot, "Red");
            sleep(sleepTime);

            dropSkyStone(Bot, "Red");

            requestOpModeStop();
        }
        idle();
    }
}
