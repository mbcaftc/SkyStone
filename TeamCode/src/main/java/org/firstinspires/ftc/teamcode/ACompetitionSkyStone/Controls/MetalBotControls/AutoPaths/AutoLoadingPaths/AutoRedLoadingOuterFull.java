package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Red:Loading:Outer:Full")
@Disabled
public class AutoRedLoadingOuterFull extends AutoLoading {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {


            detectStoneDistance(Bot); //drives forward to find any stone
            sleep(sleepTime);

            detectSkyStone (Bot, "Red"); //drive back until detects SKyStone
            sleep(sleepTime);

            manipulateStone(Bot, "grab"); //Grabs skystone
            sleep(sleepTime);

            removeSkyStoneOuter(Bot, "Red");
            sleep(sleepTime);

            adjustToDropSkyStone(Bot, "Red");
            sleep(sleepTime);

            goToFirstLocation(Bot, "Red");
            sleep(sleepTime);

            dropSkyStone(Bot, "Red");
            sleep(sleepTime);

            Bot.strafeLeft(midSpeed, .5);

            alignBuildPlateOuter(Bot, "Red");
            sleep(sleepTime);

            orientBuildPlate(Bot, "Red");
            sleep(sleepTime);

            pushBuildPlate(Bot, "Red");
            sleep(sleepTime);

            parkOuter(Bot, "Red");
            sleep(sleepTime);


            requestOpModeStop();
        }
        idle();

    }
}
