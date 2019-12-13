package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

@Autonomous(name = "Blue:Loading:SkyStone")
public class AutoBlueLoadingSkyStone extends AutoLoading {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);
        Bot.HookRelease();
        Bot.autoRaiseStone();
        Bot.grabStone();
        Bot.setServos();

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {


            Bot.dropStone();
            Bot.releaseStone();
            Bot.setServos();

            detectStoneDistance(Bot); //drives forward to find any stone
            sleep(sleepTime);

            detectSkyStone (Bot, "Blue"); //drive back until detects SKyStone
            sleep(sleepTime);

            encoderAdditionDetection(Bot, "Blue");

            Bot.strafeLeft(lowSpeed, .2);

            manipulateStone(Bot, "grab"); //Grabs skystone
            sleep(500);

            removeSkyStoneInner(Bot, "Blue");
            sleep(sleepTime);

            manipulateStone(Bot, "release rotator");
            sleep(sleepTime);

            goToFirstLocation(Bot, "Blue");
            sleep(sleepTime);

            //parkSkyStone(Bot, "Blue");

            postBuildPlateMove(Bot, "Blue");
            sleep(sleepTime);

            manipulateStone(Bot,"lower rotator");
            sleep(sleepTime);

            manipulateStone(Bot, "release grabber");
            sleep(sleepTime);

            manipulateStone(Bot, "release rotator");

            postPlatePark(Bot, "Blue");




            requestOpModeStop();
        }
        idle();

    }
}
