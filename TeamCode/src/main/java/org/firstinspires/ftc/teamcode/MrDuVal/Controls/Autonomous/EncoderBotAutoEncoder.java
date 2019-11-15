package org.firstinspires.ftc.teamcode.MrDuVal.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;
import org.firstinspires.ftc.teamcode.MrDuVal.EncoderBot.EncoderBot;

@Autonomous(name = "EncoderBot", group = "Lab")
public class EncoderBotAutoEncoder extends AutoMainEncoder {

    public EncoderBot Bot = new EncoderBot();
//    public VuforiaWebcam Cam = new VuforiaWebcam();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

//        Cam.initCamera(hardwareMap);
//        Cam.activateTracking();
        setLinearOp(this);

        telemetry.addLine("WAIT FOR START: ");
        telemetry.addLine ("left stick UP and DOWN : Change encoder target");
        telemetry.addLine ("left stick LEFT & dpad RIGHT : Change gyro target angle");
        telemetry.addLine("dpad UP & dpad DOWN : Move forward or back");
        telemetry.addLine("dpad LEFT & dpad RIGHT : Strafe left or right ");
        telemetry.addLine("LB: Rotate gyro ccw; RB: Rotate gyro cw");
        telemetry.update();

        waitForStart();
        telemetry.clearAll();
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
//            Bot.driveForwardPID (targetEncoders);
            Bot.drivePID(targetEncoders, "forward");
        }
        if (gamepad1.dpad_down) {
            Bot.driveBackwardPID (targetEncoders);
        }

        if (gamepad1.dpad_right) {
            Bot.strafeRightPID(targetEncoders);
        }

        if (gamepad1.dpad_left) {
            Bot.strafeLeftPID(targetEncoders);
        }

        if (gamepad1.right_bumper) {
            Bot.gyroCorrectionPID (targetAngle);
        }
        if (gamepad1.left_bumper) {
            Bot.gyroCorrectionPID(targetAngle);
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
