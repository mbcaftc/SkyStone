package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoPathsWood;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoBuildingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Bot:Wood Auto:Red Building:Outer")
public class AutoRedBuildingOuterWood extends AutoBuildingWood {

    public WoodBot Bot = new WoodBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            alignBuildPlate(Bot, "Red");
            sleep(sleepTime);

            goToSkystones(Bot, "Red");
            sleep(sleepTime);

            detectStoneDistance(Bot);

            detectSkyStone (Bot, "Red");
            sleep(sleepTime);

            manipulateStone(Bot, "grab");
            sleep(sleepTime);

            removeSkyStoneOuter(Bot, "Red");

            adjustToDropSkyStone(Bot, "Red");

            dropStone(Bot);

            park(Bot, "Red");

            requestOpModeStop();
        }
        idle();

    }
}
