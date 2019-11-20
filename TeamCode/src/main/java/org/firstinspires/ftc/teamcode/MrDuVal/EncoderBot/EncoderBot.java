package org.firstinspires.ftc.teamcode.MrDuVal.EncoderBot;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.MrDuVal.MecanumDrive.MecanumDriveEncoder;


public class EncoderBot extends MecanumDriveEncoder {

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
    public BNO055IMU imu = null;
    public Orientation angles = null;
    public Acceleration gravity = null;
    public final double SPEED = .3;
    public final double TOLERANCE = .4;

    public double minStraightSpeed = .2 , minStrafeSpeed = .1, minTurnSpeed = .2;
    public double maxStraightSpeed = .6, maxStrafeSpeed = .6, maxTurnSpeed = .6;
    public double medStraightSpeed = .4, medtrafeSpeed = .4, medTurnSpeed = .4;

//    public double PIDcoefficient = 0;

    //Bot Constructor

    public EncoderBot() {

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

//        HookRelease(0.11, 0.0); Servos got swapped
        HookRelease();
        dropStone();

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
        stoneServo.setPosition(.74);
    }
    public void dropStone() {
        stoneServo.setPosition(.30);
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

    public void gyroCorrectionPID (double angle) {
        double PIDcoefficient = 0;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        if (angles.firstAngle >= angle + TOLERANCE) {
            while (angles.firstAngle >=  angle + TOLERANCE && linearOp.opModeIsActive()) {
                PIDcoefficient = PIDcalculatorGyro("gyro", angle);
                rotateRight(PIDcoefficient);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                linearOp.telemetry.addData("PID Coefficient: ", PIDcoefficient);
                linearOp.telemetry.addData("Current Position: ", angles.firstAngle);
                linearOp.telemetry.addData("Target Position:", angle);
                linearOp.telemetry.update();
            }
        }
        else if (angles.firstAngle <= angle - TOLERANCE) {
            while (angles.firstAngle <= angle - TOLERANCE && linearOp.opModeIsActive()) {
                PIDcoefficient = PIDcalculatorGyro("gyro", angle);
                rotateLeft(PIDcoefficient);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                linearOp.telemetry.addData("PID Coefficient: ", PIDcoefficient);
                linearOp.telemetry.addData("Current Position: ", angles.firstAngle);
                linearOp.telemetry.addData("Target Position:", angle);
                linearOp.telemetry.update();
            }
        }
        stopMotors();
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }


    public void gyroReset () {
        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        imu.initialize(parametersimu);
    }


    public double PIDcalculatorGyro (String moveType, Double value) {
        double PIDcoefficient = 0;
        if (moveType == "gyro") {
            PIDcoefficient = (angles.firstAngle / (value / 2));
//            If get past half of distance, need to get "distance to"
            if (PIDcoefficient > 1) {
                PIDcoefficient = 2-PIDcoefficient;
            }
            // will reduce max speed from 1.0 to a factor of maxStraightSpeed
            PIDcoefficient *= maxTurnSpeed;
//            makes sure motors don't stall out by going below minStraightSpeed
            if (PIDcoefficient <= minTurnSpeed) {
                PIDcoefficient = minTurnSpeed;
            }
        }
        return PIDcoefficient;
    }

    public void driveGyro (double targetAngle, double distance) {
        //target = target angle
        //distance = target distance
        double fLspeed, fRspeed, rLspeed, rRspeed, leftSpeed, rightSpeed;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double startPos = frontLeftMotor.getCurrentPosition();  //where is our encoder counts starting?  Could also reset encoders...
        while (frontLeftMotor.getCurrentPosition() < distance + startPos) {
            //update our current angle
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            leftSpeed = medStraightSpeed + ((angles.firstAngle - targetAngle) / 100);
            rightSpeed = medStraightSpeed - ((angles.firstAngle - targetAngle) / 100);
            leftSpeed = Range.clip(leftSpeed, -1, 1);
            rightSpeed = Range.clip(rightSpeed, -1, 1);
            frontLeftMotor.setPower(leftSpeed);
            rearLeftMotor.setPower(leftSpeed);
            frontRightMotor.setPower(rightSpeed);
            rearLeftMotor.setPower(rightSpeed);
            linearOp.telemetry.addData("LEFT: ", frontLeftMotor.getPower());
            linearOp.telemetry.addData("RIGHT: ", frontRightMotor.getPower());
            linearOp.telemetry.addData("Distance to Go:", distance +startPos - frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Current Angle:", angles.firstAngle);
            linearOp.telemetry.update();
            linearOp.idle();
        }
        stopMotors();
        linearOp.idle();
    }

}





