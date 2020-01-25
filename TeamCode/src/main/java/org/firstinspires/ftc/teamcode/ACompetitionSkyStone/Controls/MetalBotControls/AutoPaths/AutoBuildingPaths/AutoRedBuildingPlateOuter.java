package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoBuildingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoBuilding;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

@Autonomous(name = "Red:Building Plate: Outer")
public class AutoRedBuildingPlateOuter extends AutoBuilding {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap, "Build");
        Bot.setLinearOp(this);
        Bot.HookRelease();

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            goToBuildPlate(Bot, "Red");

            orientBuildPlateBuild(Bot, "Red");

            pushBuildPlate(Bot, "Red");

            parkBuildingPlateOuter(Bot, "Red");

            requestOpModeStop();
        }
        idle();

    }
}
