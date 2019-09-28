package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Navigation.AutoNavigation;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;

@Autonomous (name = "AckerBot Auto Path Red")
public class AckerBotAutoPathRed extends AutoNavigation {

    public AckerBot Bot = new AckerBot();
    final long  sleepTime = 200;

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            sampleSkyStone();
            sleep(sleepTime);

            dropOffStone();
            sleep(sleepTime);

            alignWithBuildPlate();
            sleep(sleepTime);

            reorientBuildPlate ();
            sleep(sleepTime);

            goToSkyStones();
            sleep(sleepTime);


            idle();

            requestOpModeStop();

        }

        idle();

    }






}
