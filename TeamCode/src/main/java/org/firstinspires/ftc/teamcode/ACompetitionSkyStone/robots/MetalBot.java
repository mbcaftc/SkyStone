package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots;


import android.graphics.Color;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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
    public Servo stoneServo = null;

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

    // emma... added motors/ servos for intake and four bar from Boone and Dawsons code
    //*********************
    public DcMotor intakeLSpinner;
    public DcMotor intakeRSpinner;

    public Servo intakeLeftArm = null;
    public Servo intakeRightArm = null;

    public Servo stoneGrabberFourBar = null;
    public DcMotor fourBar;

    //********************



    //MetalBot Constructor

    public MetalBot() {

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

//        HookRelease(0.11, 0.0); Servos got swapped
        HookRelease();
        dropStone();
        raiseCapstone();



        // ******************
        // intake servos and motors
        intakeLeftArm = hwBot.get(Servo.class, "intake_left_arm");
        intakeLeftArm.setDirection(Servo.Direction.REVERSE);

        intakeRightArm = hwBot.get(Servo.class, "intake_right_arm");


        intakeRSpinner = hwBot.dcMotor.get("intake_right_spinner");
        intakeRSpinner.setDirection(DcMotor.Direction.FORWARD);
        intakeRSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeLSpinner = hwBot.dcMotor.get("intake_left_spinner");
        intakeLSpinner.setDirection(DcMotor.Direction.FORWARD);
        intakeLSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // virtual four bar linkage motor and servos
        stoneGrabberFourBar = hwBot.servo.get("four_bar_grabber");
        fourBar = hwBot.dcMotor.get("four_bar");

        fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //*******************

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

        HookLeft.setPosition(.12);
        HookRight.setPosition(0.0);
    }

    public void HookGrab () {

        HookLeft.setPosition(.75);
        HookRight.setPosition(.75);
    }

    //emma
    public void grabStone () {
        stoneServo.setPosition(.7);
    }
    public void dropStone() {
        stoneServo.setPosition(.10); // was .3
    }


    public void dropCapstone() {
        capstoneDropper.setPosition(.55);
    }

    public void raiseCapstone() {
        capstoneDropper.setPosition(0);
    }



    // intake - Boone and Dawson
    public void intakeSpinInward () {

        intakeLSpinner.setPower(1);
        intakeRSpinner.setPower(1);
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

        intakeLeftArm.setPosition(0);
        intakeRightArm.setPosition(0);

    }

    public void intakeArmRelease() {

        intakeLeftArm.setPosition(.6);
        intakeRightArm.setPosition(.6);

    }



    public void fourBarGrab() {

        stoneGrabberFourBar.setPosition(1); //for now, dont know it because i cant test it yet

    }

    public void fourBarRelease() {

        stoneGrabberFourBar.setPosition(0); //for now

    }

    public void fourBarUp() {

        fourBar.setPower(1);


    }

    public void fourBarDown() {

        fourBar.setPower(0);

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



}





