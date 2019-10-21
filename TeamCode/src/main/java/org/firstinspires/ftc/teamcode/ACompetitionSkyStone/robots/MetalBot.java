package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.DriveTrains.MecanumDrive;


public class MetalBot extends MecanumDrive {

    //Robot Hardware Constructors

    public HardwareMap hwBot  =  null;
    public Servo HookLeft = null;
    public Servo HookRight = null;
    public Servo stoneServo = null;
//    public Servo intakeLeftArm = null;
//    public Servo intakeRightArm = null;
//    public Servo intakePusher = null;
//    public CRServo intakeLSpinner = null;
//    public CRServo intakeRSpinner = null;

    //Gyro Objects and Variables
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public final double SPEED = .3;
    public final double TOLERANCE = .4;


    //WoodBot Constructor

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

        HookRelease(0.11, 0.0);
        grabStone(.35);

//
//        intakeLeftArm = hwBot.get(Servo.class, "intake_left_arm");
//        intakeRightArm = hwBot.get(Servo.class, "intake_right_arm");
//        intakePusher = hwBot.get(Servo.class, "intake_pusher");
//        intakeLSpinner = hwBot.get(CRServo.class, "intake_l_spinner");
//        intakeRSpinner = hwBot.get(CRServo.class, "intake_r_spinner");
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

    public void HookRelease (double leftPosition, double rightPosition) {

        HookLeft.setPosition(leftPosition);
        HookRight.setPosition(rightPosition);
    }


    public void HookGrab (double leftPosition, double rightPosition) {

        HookLeft.setPosition(leftPosition);
        HookRight.setPosition(rightPosition);
    }

    //emma
    public void grabStone (double position) {
        stoneServo.setPosition(position);
    }
    public void dropStone(double position) {
        stoneServo.setPosition(position);
    }

//    public void intakeSpinInward () {
//
//        intakeLSpinner.setPower(1);
//        intakeRSpinner.setPower(1);
//    }
//    public void intakeSpinOutward () {
//
//        intakeLSpinner.setPower(-1);
//        intakeRSpinner.setPower(-1);
//    }
//    public void intakeSpinOff () {
//
//        intakeLSpinner.setPower(0);
//        intakeRSpinner.setPower(0);
//    }
//
//    public void intakeArmHold() {
//
//        intakeLeftArm.setPosition(1);
//        intakeRightArm.setPosition(1);
//
//    }
//
//    public void intakeArmRelease() {
//
//        intakeLeftArm.setPosition(0);
//        intakeRightArm.setPosition(0);
//
//    }
//
//    public void intakePushBlock() {
//
//        intakePusher.setPosition(-1);
//
//    }
//
//    public void intakePushReset() {
//
//        intakePusher.setPosition(0);
//
//    }




    // Robot Gyro

    public void gyroCorrection (double speed, double angle) {

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        if (angles.firstAngle >= angle + TOLERANCE) {
            while (angles.firstAngle >=  angle + TOLERANCE) {
                rotateRight(speed);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }
        else if (angles.firstAngle <= angle - TOLERANCE) {
            while (angles.firstAngle <= angle - TOLERANCE) {
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



}





