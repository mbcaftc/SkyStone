package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

@Autonomous(name = "Blue:Loading:SkyStone: Outer")
public class AutoBlueLoadingSkyStoneOuter extends AutoLoading {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);
        Bot.HookRelease();
        Bot.dropStone();

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {


            detectStoneDistance(Bot); //drives forward to find any stone
            sleep(sleepTime);

            Bot.strafeRight(lowSpeed, .1);

            encoderAdditionDetection(Bot, "Blue");

            detectSkyStone (Bot, "Blue"); //drive back until detects SKyStone
            sleep(sleepTime);

            manipulateStone(Bot, "grab"); //Grabs skystone
            sleep(sleepTime);

            removeSkyStoneInner(Bot, "Blue");
            sleep(sleepTime);

            adjustToDropSkyStone(Bot, "Blue");
            sleep(sleepTime);

            goToFirstLocation(Bot, "Blue");
            sleep(sleepTime);

            postBuildPlateMove(Bot, "Blue");
            sleep(sleepTime);

            manipulateStone(Bot,"release");
            sleep(sleepTime);

            postPlateParkOuter(Bot, "Blue");



            requestOpModeStop();
        }
        idle();

    }
}