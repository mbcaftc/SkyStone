package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoBuilding;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoBuildingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Auto:Red Building:Inner")
public class AutoRedBuildingInner extends AutoBuilding {

    public MetalBot Bot = new MetalBot();

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

            removeSkyStoneInner(Bot, "Red");

            requestOpModeStop();
        }
        idle();

    }
}
