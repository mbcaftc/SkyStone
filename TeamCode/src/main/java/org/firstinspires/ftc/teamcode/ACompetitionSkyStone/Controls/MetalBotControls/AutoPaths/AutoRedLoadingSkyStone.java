package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths;

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
        Bot.dropStone();

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {


            detectStoneDistance(Bot); //drives forward to find any stone
            sleep(sleepTime);

            detectSkyStone (Bot, "Red"); //drive back until detects SKyStone
            sleep(sleepTime);

            encoderAdditionDetection(Bot, "Red");

            manipulateStone(Bot, "grab"); //Grabs skystone
            sleep(sleepTime);

            removeSkyStoneInner(Bot, "Red");
            sleep(sleepTime);

            goToFirstLocation(Bot, "Red");
            sleep(sleepTime);

            //parkSkyStone(Bot, "Red");

            postBuildPlateMove(Bot, "Red");
            sleep(sleepTime);

            manipulateStone(Bot,"release");
            sleep(sleepTime);


            requestOpModeStop();
        }
        idle();

    }
}
