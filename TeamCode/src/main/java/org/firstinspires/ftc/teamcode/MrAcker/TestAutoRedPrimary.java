package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@Autonomous (name = "Auto:Test Path:Test")
public class TestAutoRedPrimary extends TestAutoMainRed {

    public TestAckerBot Bot = new TestAckerBot();
    public TestVuforia Cam = new TestVuforia();

    @Override
    public void runOpMode() throws InterruptedException {


        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        Cam.initCamera(hardwareMap);
        Cam.activateTracking();
        setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            //Find & Deliver First Skystone
            camDrive(Bot, Cam);
            grabSkyStone(Bot);
            removeSkyStone(Bot, 0.5);
            dropSkyStone(Bot, 6.8);

            // Return
            returnBacktoSkyStones(Bot, 6.8);

            //Find & Deliver Second Skystone
            camDrive(Bot, Cam);
            grabSkyStone(Bot);
            removeSkyStone(Bot, 0.5);
            dropSkyStone(Bot, 6.8);

            // Move Build Plate
            //alignBuildPlate (Bot);
            //placeBuildPlate (Bot);
            //releaseBuildPlate (Bot);

            idle();

            requestOpModeStop();

        }

        idle();

    }






}
