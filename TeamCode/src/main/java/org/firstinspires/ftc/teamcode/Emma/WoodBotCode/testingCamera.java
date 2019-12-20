package org.firstinspires.ftc.teamcode.Emma.WoodBotCode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;


@TeleOp (name = "testing Camera: Emma")
public class testingCamera extends OpMode {


    public WoodBotEmma Bot = new WoodBotEmma();


    // Variables & Constants specific to TeleLabBot
    public double leftStickYVal;
    public double leftStickXVal;
    public double rightStickXVal;

    public double frontLeftSpeed;
    public double frontRightSpeed;
    public double rearLeftSpeed;
    public double rearRightSpeed;

    public double powerThreshold = 0;
    public double encoders;




    // Runs ONCE when driver presses INIT
    @Override
    public void init() {

        //Hardware Initialization from Robot and Camera Classes

        Bot.initRobot(hardwareMap);
        Bot.activateTracking();

    }


    // Runs ONCE when driver presses PLAY
    @Override
    public void start() {

        Bot.gyroReset();

    }


    // RUNS Repeatedly after driver presses PLAY
    @Override
    public void loop() {

        Bot.angles   = Bot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        drive();
        Bot.trackObjects();
        telemetryOutput();
        controlResetEncoders ();
        controlResetGyro();
        SimulateAuto ();

    }

    // Code to run ONCE after the driver presses STOP
    @Override
    public void stop() {

       Bot.deActivateTracking();

    }



    public void drive () {

        leftStickYVal = -gamepad1.left_stick_y;
        leftStickYVal = Range.clip(leftStickYVal, -1, 1);
        leftStickXVal = gamepad1.left_stick_x;
        leftStickXVal = Range.clip(leftStickXVal, -1, 1);
        rightStickXVal = gamepad1.right_stick_x;
        rightStickXVal = Range.clip(rightStickXVal, -1, 1);

        frontLeftSpeed = leftStickYVal + leftStickXVal + rightStickXVal;
        frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);

        frontRightSpeed = leftStickYVal - leftStickXVal - rightStickXVal;
        frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);

        rearLeftSpeed = leftStickYVal - leftStickXVal + rightStickXVal;
        rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);

        rearRightSpeed = leftStickYVal + leftStickXVal - rightStickXVal;
        rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);


        if (frontLeftSpeed <= powerThreshold && frontLeftSpeed >= -powerThreshold) {
            frontLeftSpeed = 0;
            Bot.frontLeftMotor.setPower(frontLeftSpeed);
        } else {
            Bot.frontLeftMotor.setPower(frontLeftSpeed);
        }

        if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
            frontRightSpeed = 0;
            Bot.frontRightMotor.setPower(frontRightSpeed);
        } else {
            Bot.frontRightMotor.setPower(frontRightSpeed);
        }

        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
            rearLeftSpeed = 0;
            Bot.rearLeftMotor.setPower(rearLeftSpeed);
        } else {
            Bot.rearLeftMotor.setPower(rearLeftSpeed);
        }

        if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
            rearRightSpeed = 0;
            Bot.rearRightMotor.setPower(rearRightSpeed);
        } else {
            Bot.rearRightMotor.setPower(rearRightSpeed);
        }

    }


    public void controlResetEncoders () {
        if (gamepad1.b) {
            Bot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            encoders = 0;

        }
    }

    public void controlResetGyro () {
        if (gamepad1.x) {
            Bot.gyroReset();
        }
    }

    public void SimulateAuto () {

        if (gamepad1.dpad_left) {
            Bot.rotateLeft(.5, .5,"TeleOp");
            encoders += .5;
        }
        else if (gamepad1.dpad_right) {
            Bot.rotateRight(.5, .5,"TeleOp");
            encoders += .5;
        }
        else if (gamepad1.dpad_up) {
            Bot.driveForward(.5, .5,"TeleOp");
            encoders += .5;
        }
        else if (gamepad1.dpad_down) {
            Bot.driveBackward(.5, .5,"TeleOp");
            encoders += .5;
        }
        else if (gamepad1.left_bumper) {
            Bot.strafeLeft(.5,.5,"TeleOp");
            encoders += .5;
        }
        else if (gamepad1.right_bumper) {
            Bot.strafeRight(.5,.5,"TeleOp");
            encoders += .5;
        }
    }


    public void telemetryOutput() {

        telemetry.addData("Gyro Heading", Bot.angles.firstAngle);
        telemetry.addData("Gyro Roll", Bot.angles.secondAngle);
        telemetry.addData("Gyro Pitch", Bot.angles.thirdAngle);

        telemetry.addData("Encoders AUTO count: ", encoders);

        telemetry.addData("Encoder Counts ", Bot.frontLeftMotor.getCurrentPosition() / Bot.TICKS_PER_ROTATION);
        telemetry.addData("Motor ", "Front Left: " + frontLeftSpeed);
        telemetry.addData("Motor ", "Front Right: " + frontRightSpeed);
        telemetry.addData("Motor ", "Rear Left: " + rearLeftSpeed);
        telemetry.addData("Motor ", "Rear Right: " + rearRightSpeed);


        telemetry.addData("Camera Visible Target", Bot.targetName);
        telemetry.addData("Camera Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f", Bot.targetX, Bot.targetY, Bot.targetZ);
        telemetry.addData("Camera Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", Bot.targetRoll, Bot.targetPitch, Bot.targetHeading);


        telemetry.update();

    }


}
