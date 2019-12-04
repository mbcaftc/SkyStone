package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots;


import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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


public class MetalBot extends MecanumDrive {

    //Robot Hardware Constructors

    public HardwareMap hwBot  =  null;
    public Servo HookLeft = null;
    public Servo HookRight = null;


    //public Servo stoneServo = null;

    public Servo stoneRotate;
    public Servo stoneGrabber;



    public double armMultiplier = .35;
    //public Servo intakePusher = null;

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
    public final double SCALE_FACTOR = 1;

    //*********************

    /*
    public DcMotor intakeLSpinner;
    public DcMotor intakeRSpinner;

    public Servo intakeLeftArm = null;
    public Servo intakeRightArm = null;

    */

    public Servo stackingStoneGrabber = null;
    public DcMotor stackingArm;




    //********************



    //MetalBot Constructor

    public MetalBot() {

    }

    public void initRobot (HardwareMap hwMap) {

        hwBot = hwMap;

        // Define Drive Train Motors for Robot
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


        // Define & Initialize Servos for build plate hooks

        HookLeft = hwBot.get(Servo.class, "hook_left");
        HookLeft.setDirection(Servo.Direction.FORWARD);

        HookRight = hwBot.get(Servo.class, "hook_right");
        HookRight.setDirection(Servo.Direction.FORWARD);

        //HookRelease();



        //Define and Intialize Color and Distance Sensor
        sensorColor = hwBot.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hwBot.get(DistanceSensor.class, "sensor_color_distance");



        // Define and Intialize Servo for skyStone grabber


        //stoneServo = hwBot.get(Servo.class, "stone_grabber");
        //stoneServo.setDirection(Servo.Direction.FORWARD);

        stoneRotate = hwBot.get(Servo.class, "stone_rotate");
        stoneRotate.setDirection(Servo.Direction.FORWARD);

        stoneGrabber = hwBot.get(Servo.class, "stone_grabber");
        stoneGrabber.setDirection(Servo.Direction.FORWARD);
        //dropStone();




        // Define and Intialize Servo for capstone arm
//        capstoneDropper = hwBot.get(Servo.class, "capstone_dropper");
//        capstoneDropper.setDirection(Servo.Direction.FORWARD);
//        raiseCapstone();


        // Define and Initialize Servos and Motors for intake

        /*
        intakeLeftArm = hwBot.get(Servo.class, "intake_left_arm");
        intakeLeftArm.setDirection(Servo.Direction.REVERSE);

        intakeRightArm = hwBot.get(Servo.class, "intake_right_arm");





        intakeRSpinner = hwBot.dcMotor.get("intake_right_spinner");
        intakeRSpinner.setDirection(DcMotor.Direction.REVERSE);
        intakeRSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeLSpinner = hwBot.dcMotor.get("intake_left_spinner");
        intakeLSpinner.setDirection(DcMotor.Direction.FORWARD);
        intakeLSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        */
        // Define and Initialize Servo and Motor for stacking arm
        stackingStoneGrabber = hwBot.servo.get("stacking_grabber");
        stackingArm = hwBot.dcMotor.get("stacking_arm");
        stackingArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        // Define and Initialize Gyro
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


    // Mechanism Methods


    public void HookRelease () {

        HookLeft.setPosition(.05);
        HookRight.setPosition(.75);
    }


    public void HookGrab () {

        HookLeft.setPosition(.8);
        HookRight.setPosition(0.0);
    }



    public void dropStone () {
        stoneRotate.setPosition(.55);
    }
    public void raiseStone() {
        // stoneRotate.setPosition(.20); // was .3
        stoneRotate.setPosition(.15);

    }
    public void grabStone () {
        stoneGrabber.setPosition(0);
    }
    public void releaseStone() {
        stoneGrabber.setPosition(.7);
    }
    public void neutralStone () {
        stoneRotate.setPosition(.15);
        stoneGrabber.setPosition(0);
    }


    public void dropCapstone() {
        capstoneDropper.setPosition(.55);
    }

    public void raiseCapstone() {
        capstoneDropper.setPosition(0);
    }


    /*
    public void intakeSpinInward () {

        intakeLSpinner.setPower(.4);
        intakeRSpinner.setPower(.4);
    }
    public void intakeSpinOutward () {

        intakeLSpinner.setPower(-1);
        intakeRSpinner.setPower(-1);
    }
    public void intakeSpinOff () {

        intakeLSpinner.setPower(0);
        intakeRSpinner.setPower(0);
    }

    public void intakeArmHold() {

        intakeLeftArm.setPosition(.4);
        intakeRightArm.setPosition(.4);

    }

    public void intakeArmRelease() {

        intakeLeftArm.setPosition(0);
        intakeRightArm.setPosition(0);

    }


     */


    public void stackingArmGrabberClose() {

        stackingStoneGrabber.setPosition(0.95);
    }

    public void stackingArmGrabberOpen() {

        stackingStoneGrabber.setPosition(0.88);
    }

    public void stackingArmUp() {

        stackingArm.setPower(1 * armMultiplier);
    }

    public void stackingArmDown() {

        stackingArm.setPower(-1 * armMultiplier);
    }

    public void stackingArmOff () { stackingArm.setPower(0); }




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

        return sensorColor.red();


    }

    public double checkDistance () {
        return sensorDistance.getDistance(DistanceUnit.INCH);
    }

    public void driveGyro (int encoders, double power) throws InterruptedException {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double leftSideSpeed;
        double rightSideSpeed;


        double  target = angles.firstAngle;
        double startPosition =  frontLeftMotor.getCurrentPosition();

        while (frontLeftMotor.getCurrentPosition() < encoders + startPosition) {
            double targetAngle = angles.firstAngle;

            leftSideSpeed = power + (angles.firstAngle  - target) / 100;
            rightSideSpeed = power + (angles.firstAngle - target) / 100;


            leftSideSpeed = Range.clip(leftSideSpeed, -1, 1);        // helps prevent out of bounds error
            rightSideSpeed = Range.clip(rightSideSpeed, -1, 1);

            frontLeftMotor.setPower(leftSideSpeed);
            rearLeftMotor.setPower(rightSideSpeed);

            frontRightMotor.setPower(rightSideSpeed);
            frontRightMotor.setPower(rightSideSpeed);

            linearOp.telemetry.addData("Left Speed",frontLeftMotor.getPower());
            linearOp.telemetry.addData("Right Speed", frontRightMotor.getPower());
            linearOp.telemetry.addData("Distance till destination ", encoders + startPosition - frontLeftMotor.getCurrentPosition());

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





