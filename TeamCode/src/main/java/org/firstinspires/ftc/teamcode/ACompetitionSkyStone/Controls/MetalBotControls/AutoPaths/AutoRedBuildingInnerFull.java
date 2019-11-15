package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoBuilding;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoBuildingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Red Building:Inner:Full")
@Disabled
public class AutoRedBuildingInnerFull extends AutoBuilding {

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

            Bot.driveForward(midSpeed, .5);

            //detectStoneDistance(Bot);

            detectSkyStone (Bot, "Red");
            sleep(sleepTime);

            detectStoneDistance(Bot);

            manipulateStone(Bot, "grab");
            sleep(sleepTime);

            removeSkyStoneInner(Bot, "Red");

            adjustToDropSkyStone(Bot, "Red");

            dropStone(Bot);

            park (Bot, "Red");

            requestOpModeStop();
        }
        idle();

    }
}
