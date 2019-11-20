package org.firstinspires.ftc.teamcode.MrDuVal.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;
import org.firstinspires.ftc.teamcode.MrDuVal.EncoderBot.EncoderBot;

@Autonomous(name = "EncoderBot", group = "Lab")
@Disabled
public class EncoderBotAutoEncoder extends AutoMainEncoder {

    public EncoderBot Bot = new EncoderBot();
    private boolean PIDdrive = true;
    private boolean PIDtoggleAllow = true;
    private boolean speedToggleAllow = true;
    private double speedAdjust = .1;
//    public VuforiaWebcam Cam = new VuforiaWebcam();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

//        Cam.initCamera(hardwareMap);
//        Cam.activateTracking();
        setLinearOp(this);

        telemetry.addLine("WAIT FOR START: ");
        telemetry.addLine("Y to toggle PID mode");
        telemetry.addLine("B to reset encoders & gyro");
        telemetry.addLine ("left stick UP and DOWN : Change encoder target");
        telemetry.addLine ("left stick LEFT & dpad RIGHT : Change gyro target angle");
        telemetry.addLine("dpad UP & dpad DOWN : Move forward or back");
        telemetry.addLine("dpad LEFT & dpad RIGHT : Strafe left or right ");
        telemetry.addLine("LB: Rotate gyro ccw; RB: Rotate gyro cw");
        telemetry.addLine("A: Gyro straight @ 45 degrees");

        telemetry.update();

        waitForStart();
        telemetry.clearAll();
        while (opModeIsActive()) {
            //Cam.trackObjects();
            //sleep(sleepTime);
            adjustDrive();  //AutoMainEncoder.java
            driveRobot();   //function inside this class.
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
            telemetry.addLine("drive forward with dpad up");  //leaving for debugging.
//            Bot.drivePID (targetEncoders, "forward");
            if (PIDdrive) Bot.drive(targetEncoders, "forward", "PID");
            else Bot.drive(targetEncoders, "forward", "no");
                //useing the "old" functions with no Strings being passed.
//            Bot.drivePID(targetEncoders, "forward");
        }
//        if (gamepad1.y) {
//            Bot.drivePID(targetEncoders, "forward");
//            telemetry.addLine("Drivin forward with Y");
//        }
        if (gamepad1.dpad_down) {
//            Bot.drivePID (targetEncoders, "backward");
            if (PIDdrive) Bot.drive(targetEncoders, "backward", "PID");
            else Bot.drive(targetEncoders, "backward", "no");
        }

        if (gamepad1.dpad_left) {
//            Bot.drivePID(targetEncoders, "left");
            if (PIDdrive) Bot.drive(targetEncoders, "left", "PID");
            else Bot.drive(targetEncoders, "left", "no");
        }

        if (gamepad1.dpad_right) {
//            Bot.drivePID(targetEncoders, "right");
            if (PIDdrive) Bot.drive(targetEncoders, "right", "PID");
            else Bot.drive(targetEncoders, "right", "no");
        }

        if (gamepad1.y && PIDtoggleAllow == true) {
            if (PIDdrive == true) {
                PIDdrive = false;
            }
            else {
                PIDdrive = true;
            }
            PIDtoggleAllow = false;
        }
        else if (!gamepad1.y) {
            PIDtoggleAllow = true;
        }


        if (gamepad1.right_trigger > 0.5 && speedToggleAllow && Bot.moveSpeed < 1 - speedAdjust) {
            Bot.moveSpeed += speedAdjust;
            speedToggleAllow = false;
        }
        else if (gamepad1.left_trigger > 0.5 && speedToggleAllow && Bot.moveSpeed > 0 + speedAdjust ) {
            Bot.moveSpeed -= speedAdjust;
            speedToggleAllow = false;
        }
        else if (gamepad1.left_trigger < .5 && gamepad1.right_trigger < .5) {
            speedToggleAllow = true;
        }



        if (gamepad1.right_bumper && PIDdrive) {
            Bot.gyroCorrectionPID (targetAngle);
        }
        if (gamepad1.left_bumper && PIDdrive) {
            Bot.gyroCorrectionPID(targetAngle);
        }
        if (gamepad1.b) {
            targetAngle = 0;
            targetEncoders = 0;
            Bot.gyroReset();
        }
        if (gamepad1.a) {
            Bot.driveGyro(45, 1500);
        }
    }

    public void telemetryData () {
//        telemetry.addData("Current Angle runOpMode: ", Bot.angles.firstAngle);
        telemetry.addData("PID Mode: ", PIDdrive);
        if (!PIDdrive) {
            telemetry.addData("Drive Speed: ", Bot.moveSpeed);
        }
        telemetry.update();
    }
}
