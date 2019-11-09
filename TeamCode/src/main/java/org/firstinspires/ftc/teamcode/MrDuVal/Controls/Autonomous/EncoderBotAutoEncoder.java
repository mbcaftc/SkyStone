package org.firstinspires.ftc.teamcode.MrDuVal.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;
import org.firstinspires.ftc.teamcode.MrDuVal.EncoderBot.EncoderBot;

@Autonomous(name = "EncoderBot", group = "Lab")
public class EncoderBotAutoEncoder extends AutoMainEncoder {

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
            adjustDrive();
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

            telemetryData();
        }
        idle();
    }

    public void driveRobot () {
        if (gamepad1.dpad_up) {
            Bot.driveForwardPID (targetEncoders);
        }
        if (gamepad1.dpad_down) {
            Bot.driveBackwardPID (targetEncoders);
        }
        if (gamepad1.dpad_right) {
            Bot.gyroCorrectionPID (targetAngle);
        }
        if (gamepad1.b) {
            targetAngle = 0;
            targetEncoders = 0;
            Bot.gyroReset();
        }
    }

    public void telemetryData () {
        telemetry.addData("Current Angle runOpMode: ", Bot.angles.firstAngle);
        telemetry.update();
    }
}
