package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@Autonomous (name = "AckerBot Auto Primary Path Test")
public class AckerBotAutoPath extends AutoNavigation {

    public AckerBot Bot = new AckerBot();
    public VuforiaWebcam Cam = new VuforiaWebcam();

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
