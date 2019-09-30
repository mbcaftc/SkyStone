package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Navigation.AutoNavigation;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;

@Autonomous (name = "AckerBot Auto Primary Path Test")
public class AckerBotAutoPathPrimary extends AutoNavigation {

    public AckerBot Bot = new AckerBot();


    @Override
    public void runOpMode() throws InterruptedException {


        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);
        setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addLine("Sample Skystone");
            telemetry.update();
            sampleSkyStone();
            //sleep(1000);

            //telemetry.addLine("Drop off Stone");
            //Nav.dropOffStone();
            //sleep(1000);

            //telemetry.addLine("Align with Build Plate");
            //telemetry.update();
            //alignWithBuildPlate();
            //sleep(1000);

            //telemetry.addLine("Re-orient Build Plate");
            //telemetry.update();
            //reorientBuildPlate ();
            //sleep(1000);

            //telemetry.addLine("Go to Sky Stones");
            //telemetry.update();
            //goToSkyStones();
            //sleep(1000);

            idle();

            requestOpModeStop();

        }

        idle();

    }






}
