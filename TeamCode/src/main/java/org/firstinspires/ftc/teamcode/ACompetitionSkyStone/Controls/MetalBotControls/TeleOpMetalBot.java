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


    // Variables & Constants specific to TeleOp //
    double leftStickYVal;
    double leftStickXVal;
    double rightStickXVal;

    double frontLeftSpeed;
    double frontRightSpeed;
    double rearLeftSpeed;
    double rearRightSpeed;

    double powerThreshold = 0;
    double encoders;

    double stoneRotatePos;
    double stoneGrabberPos;
    double speedMultiply = 1;


    // Runs ONCE when driver presses INIT
    @Override
    public void init() {
        Bot.initRobot(hardwareMap, "TeleOp");

    }


    // Runs Repeatedly when driver presses INIT but before pressing PLAY
    @Override
    public void init_loop() {
    }


    // Runs ONCE when driver presses PLAY
    @Override
    public void start() {


    }


    // RUNS Repeatedly after driver presses PLAY
    @Override
    public void loop() {

        drive();

        controlHook();

        controlIntakeArms();

        controlIntakeSpinners();

        controlStackingArm();

        controlClawExtender();

        controlClawGrabber();

        controlIntakePusher();

        slowDrive();

        telemetryOutput();




    }

    // Code to run ONCE after the driver presses STOP
    @Override
    public void stop() {


    }


    // **********  Teleop Drive Control Method


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
            Bot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
        } else {
            Bot.frontLeftMotor.setPower(frontLeftSpeed * speedMultiply);
        }

        if (frontRightSpeed <= powerThreshold && frontRightSpeed >= -powerThreshold){
            frontRightSpeed = 0;
            Bot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
        } else {
            Bot.frontRightMotor.setPower(frontRightSpeed * speedMultiply);
        }

        if (rearLeftSpeed <= powerThreshold && rearLeftSpeed >= -powerThreshold) {
            rearLeftSpeed = 0;
            Bot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
        } else {
            Bot.rearLeftMotor.setPower(rearLeftSpeed * speedMultiply);
        }

        if (rearRightSpeed <= powerThreshold && rearRightSpeed >= -powerThreshold){
            rearRightSpeed = 0;
            Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
        } else {
            Bot.rearRightMotor.setPower(rearRightSpeed * speedMultiply);
        }

    }

    // **********   Teleop Intake Control Methods


    public void controlHook() {
        if (gamepad2.y) {
            Bot.HookRelease();
        }
        else if (gamepad2.a) {
            Bot.HookGrab();
        }
    }


    public void controlIntakeArms() {
        if (gamepad2.dpad_down) {
            //Bot.intakeArmHold();
            Bot.intakeDeployLower();
        }
        else if (gamepad2.dpad_up) {
            //Bot.intakeArmRelease();
            Bot.intakeDeployRaise();
        }
    }


    public void controlIntakeSpinners() {
        if (gamepad2.right_bumper) {
            Bot.intakeSpinOutward();

        } else if (gamepad2.left_bumper) {
            Bot.intakeSpinInward();
        }
        else if (gamepad2.dpad_left) {
            Bot.intakeSpinnerRunner();
        }
        else {
            Bot.intakeSpinOff();
        }

    }


    public void controlIntakePusher() {             //all of this is not final for button mapping
        if (gamepad1.y) {
            Bot.intakePushIn();
        }
        else {
            Bot.intakePushNeutral();
        }
    }


    //***********  TeleOp Claw Extension & Grabber Control Methods

    public void controlClawExtender () {
        if (gamepad2.right_trigger > .1) {
            Bot.clawExtenderExtend();
        }
        else if (gamepad2.left_trigger > .1) {
            Bot.clawExtenderRetract();
        }
        else {
            Bot.clawExtenderStop();
        }

    }

    public void controlClawGrabber () {
        if (gamepad2.x == true) {
            Bot.clawGrabberGrab();
            telemetry.addLine("Claw Grab!");
        }
        if (gamepad2.b == true) {
            Bot.clawGrabberRelease();
            telemetry.addLine("Claw Release!");
        }

    }


    // ***********  TeleOp Stacking Arm Control Methods

    public void controlStackingArm() {
        if (gamepad2.right_stick_y > .1) {
            Bot.stackingArmUp();

        }
        else if (gamepad2.right_stick_y < -.1) {
            Bot.stackingArmDown();
        }
        else {
            Bot.stackingArmOff();
        }
    }


    // ****** TeleOp Slow Drive Mode Methods

    public void slowDrive() {
        if (gamepad1.dpad_down) {
            speedMultiply = 0.45;
        }
        else if (gamepad1.dpad_up) {
            speedMultiply = 1;
        }
    }


    // ****  TeleOp Telemetry Methods

    public void telemetryOutput() {

        telemetry.addData("Motor ", "Front Left: " + frontLeftSpeed);
        telemetry.addData("Motor ", "Front Right: " + frontRightSpeed);
        telemetry.addData("Motor ", "Rear Left: " + rearLeftSpeed);
        telemetry.addData("Motor ", "Rear Right: " + rearRightSpeed);
        telemetry.addData("Left Hook Servo: ", Bot.HookLeft.getPosition());
        telemetry.addData("Right Hook Servo: ", Bot.HookRight.getPosition());

        /*
        telemetry.addData("Stone Grab Servo: ", Bot.clawGrabber.getPosition());
        telemetry.addData("Stacking arm encoders", Bot.stackingLiftLeft.getCurrentPosition());
        telemetry.addData("Camera Visible Target", Cam.targetName);
        telemetry.addData("Camera Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f", Cam.targetX, Cam.targetY, Cam.targetZ);
        telemetry.addData("Camera Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", Cam.targetRoll, Cam.targetPitch, Cam.targetHeading);
        telemetry.addData("Gyro Heading", Bot.angles.firstAngle);
        telemetry.addData("Gyro Roll", Bot.angles.secondAngle);
        telemetry.addData("Gyro Pitch", Bot.angles.thirdAngle);
        telemetry.addData("Encoders AUTO count: ", encoders);
        telemetry.addData("Encoder Counts ", Bot.frontLeftMotor.getCurrentPosition() / Bot.TICKS_PER_ROTATION);
        telemetry.addData("Stone Grabber Servo: ", Bot.stoneGrabber.getPosition());
        telemetry.addData("Stone Grabber Var: ", Bot.stoneGrabberPos);
        telemetry.addData("Stone Rotater Servo: ", Bot.stoneRotate.getPosition());
        telemetry.addData("Stone Rotater Var: ", Bot.stoneRotatePos);

        */

        telemetry.update();

    }

    //*****************************
    // Methods for testing purposes & older TeleOp Control Methods
    //*****************************


 /*
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




     public void controlStoneServoButton() {
        if (gamepad1.x  == true) {
            //telemetry.addLine("drop control stone servo!");
            //telemetry.addData("servo ", Bot.stoneServo.getPosition());
            telemetry.update();
            Bot.dropStone();      //was .5
        }
        else if (gamepad1.b == true) {
            //telemetry.addLine("grab the control stone servo");
            //telemetry.addData("servo ", Bot.stoneServo.getPosition());
            telemetry.update();
            Bot.grabStone();      // was .77 but too low
        }
    }*/



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

//    public void controlStackingArmGrabber () {
//        if (gamepad2.right_trigger > .1) {
//            Bot.stackingArmGrabberClose();
//        }
//        else {
//            Bot.stackingArmGrabberOpen();
//        }
//    }

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







}
