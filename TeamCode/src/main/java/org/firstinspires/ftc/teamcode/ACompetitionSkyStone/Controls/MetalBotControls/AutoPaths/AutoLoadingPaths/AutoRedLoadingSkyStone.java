package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Red:Loading:SkyStone")
public class AutoRedLoadingSkyStone extends AutoLoading {

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

            detectSkyStone (Bot, "Red"); //drive back until detects SKyStone
            sleep(sleepTime);

            encoderAdditionDetection(Bot, "Red");

            Bot.strafeLeft(lowSpeed, .2);

            manipulateStone(Bot, "grab"); //Grabs skystone
            sleep(500);

            removeSkyStoneInner(Bot, "Red");
            sleep(sleepTime);

            manipulateStone(Bot, "release rotator");
            sleep(sleepTime);

            goToFirstLocation(Bot, "Red");
            sleep(sleepTime);

            //parkSkyStone(Bot, "Red");

            postBuildPlateMove(Bot, "Red");
            sleep(sleepTime);

            manipulateStone(Bot,"lower rotator");
            sleep(sleepTime);

            manipulateStone(Bot, "release grabber");
            sleep(sleepTime);

            manipulateStone(Bot, "release rotator");

            postPlatePark(Bot, "Red");



            requestOpModeStop();
        }
        idle();

    }
}
