package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoPathsWood;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoBuildingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Bot:Wood Auto:Red Building:Outer: Full")
@Disabled
public class AutoRedBuildingOuterFullWood extends AutoBuildingWood {

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

            Bot.driveForward(midSpeed, .5);



            detectSkyStone (Bot, "Red");
            sleep(sleepTime);

            detectStoneDistance(Bot);

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
