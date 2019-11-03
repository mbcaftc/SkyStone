package org.firstinspires.ftc.teamcode.MrAcker;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "Auto:Test Path:Test")
public class TestAutoRedPrimary extends TestAutoMainRed {

    public TestAckerBot Bot = new TestAckerBot();
    public TestVuforia Cam = new TestVuforia();
    public String Alliance = "Red";

    @Override
    public void runOpMode() throws InterruptedException {


        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        Cam.initCamera(hardwareMap);
        Cam.activateTracking();
        setLinearOp(this);

        waitForStart();

        while (opModeIsActive()) {

            //Find & Grab First Skystone
            camDrive(Bot, Cam, Alliance);
            grabSkyStone(Bot);

            // Deliver First Skystone
            //removeSkyStoneInnerPath(Bot,Alliance);
            //dropSkyStone(Bot, Alliance);

            // Return Back to Skystone Area
            //returnBacktoSkyStones(Bot, Alliance);

            //Find & Grab Second Skystone
            //camDrive(Bot, Cam, Alliance);
            //grabSkyStone(Bot);

            // Deliver Second Skystone
            //removeSkyStoneInnerPath(Bot, Alliance);
            //dropSkyStone(Bot,Alliance);

            // Move Build Plate

            //alignBuildPlate (Bot);
            //placeBuildPlate (Bot,Alliance);
            //releaseBuildPlate (Bot);

            idle();

            requestOpModeStop();

        }

        idle();

    }






}
