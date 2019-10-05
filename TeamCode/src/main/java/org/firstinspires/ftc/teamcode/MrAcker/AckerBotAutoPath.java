package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.MrAcker.Vuforia;

@Autonomous (name = "AckerBot Auto Test")
public class AckerBotAutoPath extends AutoNavigation {

    public AckerBot Bot = new AckerBot();
    public Vuforia Cam = new Vuforia();

    @Override
    public void runOpMode() throws InterruptedException {


        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        Cam.initCamera(hardwareMap);
        setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            Cam.activateTracking();

            telemetry.addLine("Drive Test");
            telemetry.update();
            driveTest(Bot);

            telemetry.addLine("Cam Test");
            telemetry.update();
            camTest(Bot, Cam);

            telemetry.addLine("Cam Drive");
            telemetry.update();
            camTest(Bot, Cam);

            idle();

            requestOpModeStop();

        }

        idle();

    }






}
