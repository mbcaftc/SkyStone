package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;

@Autonomous (name = "AckerBot Auto Primary Path Test")
public class AckerBotAutoPath extends AutoNavigation {

    public AckerBot Bot = new AckerBot();


    @Override
    public void runOpMode() throws InterruptedException {


        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addLine("Sample Skystone");
            telemetry.update();
            sampleSkyStone(Bot);


            idle();

            requestOpModeStop();

        }

        idle();

    }






}
