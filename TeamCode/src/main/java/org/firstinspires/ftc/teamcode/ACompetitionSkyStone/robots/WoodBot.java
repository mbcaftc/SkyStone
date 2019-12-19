package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots;


import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.lynx.commands.core.LynxGetAllDIOInputsCommand;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.DriveTrains.MecanumDrive;


public class WoodBot extends MecanumDrive {

    //Robot Hardware Constructors

    public HardwareMap hwBot  =  null;
    public Servo HookLeft = null;
    public Servo HookRight = null;
    public Servo stoneServo = null;
    public Servo capstoneDropper = null;

    //Gyro Objects and Variables
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public final double SPEED = .3;
    public final double TOLERANCE = .4;
    public ColorSensor sensorColor;
    public DistanceSensor sensorDistance;

    public float hsvValues[] = {0F, 0F, 0F};
    public int thresholdNothing = 350;       // Original was 180, adjusted for red hue
    public int threshholdColor= 150;          // Original was 270
    public final double SCALE_FACTOR = 1;



    //WoodBot Constructor

    public WoodBot() {

    }

    public void initRobot (HardwareMap hwMap) {

        hwBot = hwMap;

        // Define Motors for Robot
        frontLeftMotor =  hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");


        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);


        //Initialize Motor Run Mode for Robot
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Define & Initialize Servos
        HookLeft = hwBot.get(Servo.class, "hook_left");
        HookLeft.setDirection(Servo.Direction.FORWARD);

        HookRight = hwBot.get(Servo.class, "hook_right");
        HookRight.setDirection(Servo.Direction.FORWARD);

        //emma
        stoneServo = hwBot.get(Servo.class, "stone_servo");
        stoneServo.setDirection(Servo.Direction.FORWARD);

        capstoneDropper = hwBot.get(Servo.class, "capstone_dropper");
        capstoneDropper.setDirection(Servo.Direction.FORWARD);

        //sensorColor = hwBot.get(ColorSensor.class, "sensor_color_distance");
        //sensorDistance = hwBot.get(DistanceSensor.class, "sensor_color_distance");


        HookRelease();
        dropStone();
        raiseCapstone();

        //Define and Initialize Gyro

        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        parametersimu.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parametersimu.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parametersimu.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parametersimu.loggingEnabled = true;
        parametersimu.loggingTag = "IMU";
        parametersimu.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwBot.get(BNO055IMU.class, "imu");
        imu.initialize(parametersimu);


    }


    // Robot Servo Methods


    public void HookRelease () {

        HookLeft.setPosition(.11);
        HookRight.setPosition(0.0);
    }
//


    public void HookGrab () {
        HookLeft.setPosition(.87);
        HookRight.setPosition(.73);
    }

    //emma
    public void grabStone () {
        stoneServo.setPosition(.7);
    }
    public void dropStone() {
        stoneServo.setPosition(.3);
    }

    public void dropCapstone() {
        capstoneDropper.setPosition(.6);
    }

    public void raiseCapstone() {
        capstoneDropper.setPosition(0);
    }

    // Robot Gyro

    public void gyroCorrection (double speed, double angle) {

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        if (angles.firstAngle >= angle + TOLERANCE) {
            while (angles.firstAngle >=  angle + TOLERANCE && linearOp.opModeIsActive()) {
                rotateRight(speed);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }
        else if (angles.firstAngle <= angle - TOLERANCE) {
            while (angles.firstAngle <= angle - TOLERANCE && linearOp.opModeIsActive()) {
                rotateLeft(speed);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }
        stopMotors();

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }


    public void gyroReset () {
        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        imu.initialize(parametersimu);
    }

    public float checkColor() {
        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                (int) (sensorColor.green() * SCALE_FACTOR),
                (int) (sensorColor.blue() * SCALE_FACTOR),
                hsvValues);

//        return hsvValues[0];
        return sensorColor.red();
        /*
        if (hsvValues[0] >= thresholdNothing && hsvValues[0] <  threshholdColor) {

            return true;

        }
        else {

            return false;
        }

         */

    }

    public double checkDistance () {
        return sensorDistance.getDistance(DistanceUnit.INCH);
    }


    public void driveGyroStraight (int encoders, double power, String direction) throws InterruptedException {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentPos = 0;
        double leftSideSpeed;
        double rightSideSpeed;


        double target = angles.firstAngle;
        double startPosition = frontLeftMotor.getCurrentPosition();
        linearOp.telemetry.addData("Angle to start: ", angles.firstAngle);
        linearOp.telemetry.update();
        linearOp.sleep(2000);
            while (currentPos < encoders + startPosition && linearOp.opModeIsActive()) {

                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);


                currentPos = Math.abs(frontLeftMotor.getCurrentPosition());

                switch (direction) {
                    case "forward":
//                        currentPos = frontLeftMotor.getCurrentPosition();
                        leftSideSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
                        rightSideSpeed = power - (angles.firstAngle - target) / 100;

                        leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
                        rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);

                        frontLeftMotor.setPower(leftSideSpeed);
                        rearLeftMotor.setPower(leftSideSpeed);

                        frontRightMotor.setPower(rightSideSpeed);
                        rearRightMotor.setPower(rightSideSpeed);
                        break;
                    case "backward":
//                        currentPos = -frontLeftMotor.getCurrentPosition();
                        leftSideSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
                        rightSideSpeed = power + (angles.firstAngle - target) / 100;

                        leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
                        rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);

                        frontLeftMotor.setPower(-leftSideSpeed);
                        rearLeftMotor.setPower(-leftSideSpeed);

                        frontRightMotor.setPower(-rightSideSpeed);
                        rearRightMotor.setPower(-rightSideSpeed);
                        break;
                }



                linearOp.telemetry.addData("Left Speed", frontLeftMotor.getPower());
                linearOp.telemetry.addData("Right Speed", frontRightMotor.getPower());
                linearOp.telemetry.addData("Distance till destination ", encoders + startPosition - frontLeftMotor.getCurrentPosition());
                linearOp.telemetry.addData("Current Position", currentPos);
                linearOp.telemetry.addData("Target Position", target);
                linearOp.telemetry.addData("Angle: ", angles.firstAngle);

                linearOp.telemetry.update();
                // missing waiting
                linearOp.idle();
            }

            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            rearLeftMotor.setPower(0);
            rearRightMotor.setPower(0);

            linearOp.idle();

        }


    public void driveGyroStrafe (int encoders, double power, String direction) throws InterruptedException {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentPos = 0;
        double frontLeftSpeed;
        double frontRightSpeed;
        double rearLeftSpeed;
        double rearRightSpeed;


        double target = angles.firstAngle;
        double startPosition = frontLeftMotor.getCurrentPosition();
        linearOp.telemetry.addData("Angle to start: ", angles.firstAngle);
        linearOp.telemetry.update();
        linearOp.sleep(2000);
        while (currentPos < encoders + startPosition && linearOp.opModeIsActive()) {

            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);


            currentPos = Math.abs(frontLeftMotor.getCurrentPosition());

            switch (direction) {
                case "left":
                    frontLeftSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
                    frontRightSpeed = power - (angles.firstAngle - target) / 100;
                    rearLeftSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
                    rearRightSpeed = power + (angles.firstAngle - target) / 100;

                    frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);
                    rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

                    frontLeftMotor.setPower(-frontLeftSpeed);
                    frontRightMotor.setPower(frontRightSpeed);

                    rearLeftMotor.setPower(rearLeftSpeed);
                    rearRightMotor.setPower(-rearRightSpeed);
                    break;
                case "right":
                    frontLeftSpeed = power + (angles.firstAngle - target) / 100;            // they need to be different
                    frontRightSpeed = power + (angles.firstAngle - target) / 100;
                    rearLeftSpeed = power - (angles.firstAngle - target) / 100;            // they need to be different
                    rearRightSpeed = power - (angles.firstAngle - target) / 100;

                    frontLeftSpeed = Range.clip(frontLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    frontRightSpeed = Range.clip(frontRightSpeed, -1, 1);
                    rearLeftSpeed = Range.clip(rearLeftSpeed, -1, 1);        // helps prevent out of bounds error
                    rearRightSpeed = Range.clip(rearRightSpeed, -1, 1);

                    frontLeftMotor.setPower(frontLeftSpeed);
                    frontRightMotor.setPower(-frontRightSpeed);

                    rearLeftMotor.setPower(-rearLeftSpeed);
                    rearRightMotor.setPower(rearRightSpeed);
                    break;
            }



            linearOp.telemetry.addData("Left Speed", frontLeftMotor.getPower());
            linearOp.telemetry.addData("Right Speed", frontRightMotor.getPower());
            linearOp.telemetry.addData("Distance till destination ", encoders + startPosition - frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Current Position", currentPos);
            linearOp.telemetry.addData("Target Position", target);
            linearOp.telemetry.addData("Angle: ", angles.firstAngle);

            linearOp.telemetry.update();
            // missing waiting
            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();

    }
}






