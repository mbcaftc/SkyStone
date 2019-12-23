package org.firstinspires.ftc.teamcode.Emma.WoodBotCode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.Emma.AutoPathsHardCoded.AutoBuildingHardCodedHardCoded;


@Autonomous(name = "Testing Auto: Vuforia: Emma")

public class TestAutoWoodEmma extends LinearOpMode {

    public WoodBotEmma Bot = new WoodBotEmma();

    public LinearOpMode linearOp = null;
    public long sleepTime = 50;

    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.initWebCam();
        Bot.setLinearOp(this);

        setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            Bot.activateTracking();

            Bot.driveForward(.2, 1.8);
            sleep(sleepTime);

            Bot.detectSkyStone();
            sleep(sleepTime);

            Bot.deActivateTracking();
            sleep(sleepTime);

            Bot.driveToSkyStone("Red");
            sleep(sleepTime);

            //Intake Skystone
            Bot.driveBackward(.2, .5);
            sleep(sleepTime);

            Bot.driveToPlate("Red");
            sleep(sleepTime);

            Bot.dropOffSkyStone("Red");
            sleep(sleepTime);

            Bot.alignWithPlate("Red");
            sleep(sleepTime);

            Bot.movePlate("Red");
            sleep(sleepTime);

            Bot.parkInner("Red");
            sleep(sleepTime);

            requestOpModeStop();
        }
        idle();


    }
}
