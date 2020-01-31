package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoBuilding;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

@Autonomous(name = "Park: Outer")
public class AutoParkOuter extends AutoBuilding {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap, "Build");
        Bot.setLinearOp(this);
        //Bot.HookRelease();

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            sleep(6000);

            Bot.driveForward(midSpeed, 2);

            idle();
            requestOpModeStop();
        }
        idle();

    }
}
