package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoBuilding;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoBuildingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Blue:Building:Inner:Full")
@Disabled
public class AutoBlueBuildingInnerFull extends AutoBuilding {

    public MetalBot Bot = new MetalBot();

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

            Bot.driveBackward(midSpeed, .5);

            //detectStoneDistance(Bot);

            detectSkyStone (Bot, "Blue");
            sleep(sleepTime);

            detectStoneDistance(Bot);

            manipulateStone(Bot, "grab");
            sleep(sleepTime);

            removeSkyStoneInner(Bot, "Blue");

            adjustToDropSkyStone(Bot, "Blue");

            dropStone(Bot);

            park (Bot, "Blue");

            requestOpModeStop();
        }
        idle();

    }
}
