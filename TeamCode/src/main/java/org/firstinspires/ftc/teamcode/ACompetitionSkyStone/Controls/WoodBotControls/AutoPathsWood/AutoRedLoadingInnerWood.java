package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoPathsWood;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Bot:Wood Auto:Red Loading:Inner")
public class AutoRedLoadingInnerWood extends AutoLoadingWood {

    public WoodBot Bot = new WoodBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        setLinearOp(this);


        waitForStart();

        while (opModeIsActive()) {

            idle();

            detectStoneDistance(Bot); //drives forward to find any stone
            sleep(sleepTime);

            detectSkyStone (Bot, "Red"); //drive back until detects SKyStone
            sleep(sleepTime);

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
