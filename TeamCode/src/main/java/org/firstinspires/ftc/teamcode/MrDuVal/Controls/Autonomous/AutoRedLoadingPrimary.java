package org.firstinspires.ftc.teamcode.MrDuVal.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;
import org.firstinspires.ftc.teamcode.MrDuVal.EncoderBot.EncoderBot;

@Autonomous(name = "EncoderBot", group = "Lab")
//@Disabled
public class AutoRedLoadingPrimary extends AutoMain {

    public EncoderBot Bot = new EncoderBot();
    public VuforiaWebcam Cam = new VuforiaWebcam();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

//        Cam.initCamera(hardwareMap);
//        Cam.activateTracking();
        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {
            //Cam.trackObjects();
            //sleep(sleepTime);
            adjustEncoders();
            driveRobot();
//            Bot.strafeLeft(midSpeed, 1);

            //vuforiaStone(Bot, Cam);
//            hardCodeVuforia(Bot, "Red");

//            removeSkyStoneInnerPath(Bot,"Red");

//            dropSkyStone(Bot, "Red");

//            alignGrabBuildPlateInner(Bot, "Red");

//            orientBuildPlate(Bot, "Red");

//            pushBuildPlate(Bot, "Red");

//            park(Bot, "Red");
        }
        idle();
    }

    public void driveRobot () {
        if (gamepad1.dpad_up) {
            Bot.driveForwardPID (targetEncoders);
//            encoders += .5;
        }
    }
}
