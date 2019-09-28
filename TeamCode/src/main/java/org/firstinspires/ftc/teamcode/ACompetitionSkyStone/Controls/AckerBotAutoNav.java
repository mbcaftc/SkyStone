package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Navigation.AutoNavigation;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;

@Autonomous (name = "AckerBot Auto Navigation")
public class AckerBotAutoNav extends LinearOpMode {

    public AckerBot Bot = new AckerBot();
    //public AutoNavigation Nav = new AutoNavigation();

    final long  sleepTime = 200;
    final double maxSpeed = 1;
    final double highSpeed = .6;
    final double midSpeed = .5;
    final double lowSpeed = .3;


    @Override
    public void runOpMode() throws InterruptedException {


        Bot.initRobot(hardwareMap);

        Bot.setLinearOp(this);
        //Nav.setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addLine("Sample Skystone");
            telemetry.update();
            //Nav.sampleSkyStone();
            sleep(sleepTime);

            telemetry.addLine("Drop off Stone");
            //Nav.dropOffStone();
            sleep(sleepTime);

            telemetry.addLine("Align with Build Plate");
            telemetry.update();
            //Nav.alignWithBuildPlate();
            sleep(sleepTime);

            telemetry.addLine("Re-orient Build Plate");
            telemetry.update();
           // Nav.reorientBuildPlate ();
            sleep(sleepTime);

            telemetry.addLine("Go to Sky Stones");
            telemetry.update();
            //Nav.goToSkyStones();
            sleep(sleepTime);


            idle();

            requestOpModeStop();

        }

        idle();

    }






}
