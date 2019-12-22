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

            Bot.driveForward(.2, 2.3);

            Bot.detectSkyStone();

            Bot.deActivateTracking();

            Bot.driveToSkyStone("Blue");

            requestOpModeStop();
        }
        Bot.deActivateTracking(); // ????
        idle();


    }
}
