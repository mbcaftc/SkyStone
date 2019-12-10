package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Blue:Loading:Outer:Full")
@Disabled
public class AutoBlueLoadingOuterFull extends AutoLoading {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        setLinearOp(this);


//
        waitForStart();

        while (opModeIsActive()) {


            detectStoneDistance(Bot); //drives forward to find any stone
            sleep(sleepTime);

            Bot.strafeRight(lowSpeed, .15);


            detectSkyStone (Bot, "Blue"); //drive back until detects SKyStone
            sleep(sleepTime);

            manipulateStone(Bot, "grab"); //Grabs skystone
            sleep(sleepTime);

            removeSkyStoneOuter(Bot, "Blue");
            sleep(sleepTime);

            adjustToDropSkyStone(Bot, "Blue");
            sleep(sleepTime);

            goToFirstLocation(Bot, "Blue");
            sleep(sleepTime);

            dropSkyStone(Bot, "Blue");
            sleep(sleepTime);

            Bot.strafeLeft(midSpeed, .5);

            alignBuildPlateOuter(Bot, "Blue");
            sleep(sleepTime);

            orientBuildPlate(Bot, "Blue");
            sleep(sleepTime);

            pushBuildPlate(Bot, "Blue");
            sleep(sleepTime);

            parkOuter(Bot, "Blue");
            sleep(sleepTime);


            requestOpModeStop();
        }
        idle();

    }
}
