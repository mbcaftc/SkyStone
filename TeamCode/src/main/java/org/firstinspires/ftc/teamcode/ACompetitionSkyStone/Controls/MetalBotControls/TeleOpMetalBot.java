package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;



@TeleOp (name = "MetalBot: TeleOp")
public class TeleOpMetalBot extends OpMode {


    public ElapsedTime TeleOpTime = new ElapsedTime();
    public MetalBot Bot = new MetalBot();


    // Variables & Constants specific to TeleLabBot
    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double powerThreshold = 0;
    double encoders;




    // Runs ONCE when driver presses INIT
    @Override
    public void init() {
        Bot.initRobot(hardwareMap);

    }


    // Runs Repeatedly when driver presses INIT but before pressing PLAY
    @Override
    public void init_loop() {

    }


    // Runs ONCE when driver presses PLAY
    @Override
    public void start() {
        Bot.dropStone();
        Bot.stoneGrabberUp();

    }


    // RUNS Repeatedly after driver presses PLAY
    @Override
    public void loop() {

        drive();

        controlHook();

        //controlStoneServoButton();
        controlStoneServo();

        //controlCapstone ();

        controlIntakeArms();

        controlIntakeSpinners();

        controlStackingArm();

        controlStackingArmGrabber();

        //SimulateAuto();


    }

    // Code to run ONCE after the driver presses STOP
    @Override
    public void stop() {


    }


    // Teleop Drive Control Method


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

    // Teleop Mechanism Control Methods


    public void controlHook() {
        if (gamepad2.y) {
            Bot.HookRelease();
        }
        else if (gamepad2.a) {
            Bot.HookGrab();
        }

    }


    public void controlStoneServo() {
        if (gamepad2.left_stick_y > 0.1) {
            telemetry.addLine("drop control stone servo!");
            telemetry.addData("servo ", Bot.stoneServo.getPosition());
            telemetry.update();
            Bot.stoneGrabberUp();      //was .5
        }
        else if (gamepad2.left_stick_y <  -.1) {
            telemetry.addLine("grab the control stone servo");
            telemetry.addData("servo ", Bot.stoneServo.getPosition());
            telemetry.update();
            Bot.grabStone();      // was .77 but too low
        }
    }

    public void controlStoneServoButton() {
        if (gamepad1.x  == true) {
            telemetry.addLine("drop control stone servo!");
            telemetry.addData("servo ", Bot.stoneServo.getPosition());
            telemetry.update();
            Bot.dropStone();      //was .5
        }
        else if (gamepad1.b == true) {
            telemetry.addLine("grab the control stone servo");
            telemetry.addData("servo ", Bot.stoneServo.getPosition());
            telemetry.update();
            Bot.grabStone();      // was .77 but too low
        }
    }

    public void controlCapstone () {
        if (gamepad2.right_trigger > 0.1 ) {                //was gamepad2.left_bumper
            Bot.raiseCapstone();

        }

        else if (gamepad2.left_trigger > 0.1) {           //was gamepad2.right_bumper
            Bot.dropCapstone();
        }
    }

    public void controlIntakeArms() {
        if (gamepad2.dpad_down) {
            Bot.intakeArmHold();
        }
        else if (gamepad2.dpad_up) {
            Bot.intakeArmRelease();
        }
    }


    public void controlIntakeSpinners() {
        if (gamepad2.right_bumper) {                      //was gamepad2.left_trigger > 0.1
            Bot.intakeSpinInward();
        } else if (gamepad2.left_bumper) {              //was gamepad2.right_trigger > 0.1
            Bot.intakeSpinOutward();
        } else {
            Bot.intakeSpinOff();
        }

    }

//    public void controlStackingArmGrabber() {
//        if (gamepad2.x) {
//            Bot.stackingArmGrabberClose();
//            telemetry.addData("bot stack arm grabber open", Bot.stackingStoneGrabber.getPosition());
//            telemetry.update();
//
//        }
//        else if (gamepad2.b) {
//            Bot.stackingArmGrabberOpen();
//            telemetry.addData("bot stack arm grabber close", Bot.stackingStoneGrabber.getPosition());
//            telemetry.update();
//        }
//
//    }

    public void controlStackingArmGrabber () {
        if (gamepad2.right_trigger > .1) {
            Bot.stackingArmGrabberClose();
        }
        else {
            Bot.stackingArmGrabberOpen();
        }
    }

    public void controlStackingArm() {
        if (gamepad2.right_stick_y > .1) {
            Bot.stackingArmUp();

        }
        else if (gamepad2.right_stick_y < -.1) {
            Bot.stackingArmDown();
        }
        else {
            Bot.stackingArmOff();                           //added motor stop when not pushing a button
        }
    }












    //*****************************
    // Methods for testing purposes
    //*****************************
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


/*
    public void SimulateAuto () {

        if (gamepad1.dpad_left) {
            Bot.rotateLeft(.5, .5, "TeleOp");
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
            Bot.strafeLeft(.5,.5, "TeleOp");
            encoders += .5;
        }
        else if (gamepad1.right_bumper) {
            Bot.strafeRight(.5,.5, "TeleOp");
            encoders += .5;
        }
    }


*/







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

        telemetry.addData("Left Hook Servo: ", Bot.HookLeft);
        telemetry.addData("Right Hook Servo: ", Bot.HookRight);
        telemetry.addData("Stone Grab Servo: ", Bot.stoneServo);

//        telemetry.addData("Camera Visible Target", Cam.targetName);
//        telemetry.addData("Camera Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f", Cam.targetX, Cam.targetY, Cam.targetZ);
//        telemetry.addData("Camera Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", Cam.targetRoll, Cam.targetPitch, Cam.targetHeading);

        telemetry.update();

    }

























}
