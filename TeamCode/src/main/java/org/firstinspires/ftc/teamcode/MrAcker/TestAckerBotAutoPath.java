package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;

@Autonomous (name = "TestAckerBot Auto Test")
public class TestAckerBotAutoPath extends TestAutoNavigation {

    public TestAckerBot Bot = new TestAckerBot();
    public TestVuforia Cam = new TestVuforia();

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


            idle();

            requestOpModeStop();

        }

        idle();

    }






}
