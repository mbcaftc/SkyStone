package org.firstinspires.ftc.teamcode.Boone;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.DriveTrains.MecanumDrive;


public class WoodBotTestB extends MecanumDrive {

    //Robot Hardware Constructors

    public HardwareMap hwBot  =  null;
    public Servo HookLeft = null;
    public Servo HookRight = null;
    public Servo stoneServo = null;
    public DcMotor intakeLSpinner;
    public DcMotor intakeRSpinner;
    public Servo intakeLeftArm = null;
    public Servo intakeRightArm = null;
    public Servo stoneGrabberFourBar = null;
    public DcMotor fourBar;


  //  public Servo intakePusher = null;
  //  public CRServo intakeLSpinner = null;
  //  public CRServo intakeRSpinner = null;

    //Gyro Objects and Variables
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public final double SPEED = .3;
    public final double TOLERANCE = .4;


    //WoodBot Constructor

    public WoodBotTestB() {

    }

    public void initRobot (HardwareMap hwMap) {

        hwBot = hwMap;

        // Define  Drive Train Motors for Robot
        frontLeftMotor =  hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");




        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);
        //intakeLeft.setDirection(DcMotor.Direction.);
        //intakeRight.setDirection(DcMotor.Direction.);

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

        HookRelease();
        grabStone();



        //---------------INTAKE


        intakeLSpinner = hwBot.dcMotor.get("intake_left");
        intakeRSpinner = hwBot.dcMotor.get("intake_right");


        intakeLSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeRSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //------Grabber

        stoneGrabberFourBar = hwBot.servo.get("four_bar_grabber");
        fourBar = hwBot.dcMotor.get("four_bar");

        fourBar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




        //---------------------------------------------------------------------------------------------

       // intakeRSpinner.setDirection(DcMotor.Direction.FORWARD);
       // intakeLSpinner.setDirection(DcMotor.Direction.REVERSE);

        //---------------------------------------------------------------------------------------------





        //intake Servos
     /*   intakeLeftArm = hwBot.get(Servo.class, "intakeLeftArm");
        intakeRightArm = hwBot.get(Servo.class, "intakeRightArm");
        intakePusher = hwBot.get(Servo.class, "intakePusher");
        intakeLSpinner = hwBot.get(CRServo.class, "intakeLSpinner");
        intakeRSpinner = hwBot.get(CRServo.class, "intakeRSpinner");*/



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

        HookLeft.setPosition(0);
        HookRight.setPosition(0);
    }


    public void HookGrab () {

        HookLeft.setPosition(1);
        HookRight.setPosition(1);
    }

    //emma
    public void grabStone () {
        stoneServo.setPosition(1);
    }
    public void dropStone() {
        stoneServo.setPosition(0);
    }

    public void intakeArmHold() {

          intakeLeftArm.setPosition(1);
          intakeRightArm.setPosition(1);

    }

    public void intakeArmRelease() {

      intakeLeftArm.setPosition(0);
      intakeRightArm.setPosition(0);

    }

    public void intakePushBlock() {

       // intakePusher.setPosition(-1);

    }

    public void intakePushReset() {

//        intakePusher.setPosition(0);

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

//-------------------------------------------------------------------------------------------------------------------------------------





}







