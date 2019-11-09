package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoPathsWood;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Bot:Wood Auto:Blue Loading:Outer: Full")
@Disabled
public class AutoBlueLoadingOuterFullWood extends AutoLoadingWood {

    public WoodBot Bot = new WoodBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {


            detectStoneDistance(Bot); //drives forward to find any stone
            sleep(sleepTime);

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
