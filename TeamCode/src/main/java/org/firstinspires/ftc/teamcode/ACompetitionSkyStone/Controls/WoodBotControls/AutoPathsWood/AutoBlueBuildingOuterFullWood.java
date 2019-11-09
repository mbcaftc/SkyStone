package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoPathsWood;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoBuildingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Bot:Wood Auto:Blue Building:Outer: Full")
@Disabled
public class AutoBlueBuildingOuterFullWood extends AutoBuildingWood {

    public WoodBot Bot = new WoodBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            alignBuildPlate(Bot, "Blue");
            sleep(sleepTime);

            goToSkystones(Bot, "Blue");
            sleep(sleepTime);

            Bot.driveForward(midSpeed, .5);



            detectSkyStone (Bot, "Blue");
            sleep(sleepTime);

            detectStoneDistance(Bot);

            manipulateStone(Bot, "grab");
            sleep(sleepTime);

            removeSkyStoneOuter(Bot, "Blue");

            adjustToDropSkyStone(Bot, "Blue");

            dropStone(Bot);

            park(Bot, "Blue");

            requestOpModeStop();
        }
        idle();

    }
}
