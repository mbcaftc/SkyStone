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
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.DriveTrains.MecanumDrive;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;


public class MetalBot extends MecanumDrive {

    //Robot Hardware Constructors
    public HardwareMap hwBot  =  null;


    // Build Plate Hook Hardware & Variables
    public Servo HookLeft = null;
    public Servo HookRight = null;

    // Capstone Drop Servo Hardware & Variables
    public Servo capstoneDropper = null;

    //Gyro Objects, Hardware & Variables
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;
    public final double SPEED = .3;
    public final double TOLERANCE = .4;

    // Color and Distance Hardware & Variables
    public ColorSensor sensorColor;
    public DistanceSensor sensorDistance;
    public float hsvValues[] = {0F, 0F, 0F};
    public final double SCALE_FACTOR = 1;

    //Intake Hardware
    public DcMotor intakeLSpinner;
    public DcMotor intakeRSpinner;
    public Servo intakeDeploy;
    public Servo intakePusher = null;

    //Stacking Arms Hardware & Variables
    public DcMotor stackingLiftLeft;
    public DcMotor stackingLiftRight;
    public int stackingArmTargetPos = 100;
    public double getMaxStackingArmTime = 1;
    public ElapsedTime stackingArmTimer;


    // Claw Hardware & Variables
    public CRServo clawExtender;
    public Servo clawGrabber;

    // Global Variables for Skystone Position
    public double skyStoneValue;


    //Vuforia Constructors, Variables and Constants
    public OpenGLMatrix lastLocation = null;
    public VuforiaLocalizer vuforia = null;
    public WebcamName webcamName = null;
    public VuforiaTrackables targetsSkyStone = null;
    public List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
    public ElapsedTime vuforiaTimer;

    public static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    public static final boolean PHONE_IS_PORTRAIT = false  ;

    public static final String VUFORIA_KEY =
            "AZ7FRiT/////AAABmV8K//atfkrzl192hWMTHC5IF42DuOMXgcbKj3ykPItGlUrJghFCQLsg1Xv5u2CZ3m5bxMhQ4gT6Cf8nRDc7gLyJdYBU8y2sLtaC/aL3hXpGZdB6IQmiwOgdiYjZ5iK/jFS3QKnbk8QdOzLbifMssIY/3/8bMK0GAAUDTsZqivFJK7Kpa7ZXuuWlxZ36HzJv1UYBP+K/BxRQCHY7BarO/eCq2BExtHVyL6hu2sU8TZ8gKakUVEF0p13r/MzZaIsaCppZCfT9lLdlviprQgTn0TXqMSObvtSgjSJJbeXkS7hg0cY2OLbqrf8zJJnUspVmseOVm3h+7r0wtvugxSQWiE1mLgvBJ6Dsg9haM+nSonGi\"";

    public static final float mmPerInch        = 25.4f;
    public static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

    public static final float stoneZ = 2.00f * mmPerInch;

    public boolean targetVisible = false;
    public float phoneXRotate    = 0;
    public float phoneYRotate    = 0;
    public float phoneZRotate    = 0;

    public String targetName = null;
    public double targetX;
    public double targetY;
    public double targetZ;
    public double targetRoll;
    public double targetPitch;
    public double targetHeading;


    //MetalBot Hardware Constructor

    public MetalBot() {

    }

    // Hardware Initialization for Metal Bot

    public void initRobot (HardwareMap hwMap, String Mode) {


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


        //Define and Intialize Color and Distance Sensor
        /*
        sensorColor = hwBot.get(ColorSensor.class, "sensor_color_distance");
        sensorDistance = hwBot.get(DistanceSensor.class, "sensor_color_distance");
        */


        // Define and Intialize Servo for skyStone grabber
        /*
        stoneServo = hwBot.get(Servo.class, "stone_grabber");
        stoneServo.setDirection(Servo.Direction.FORWARD);
        stoneRotate = hwBot.get(Servo.class, "stone_rotate");
        stoneRotate.setDirection(Servo.Direction.FORWARD);
        stoneGrabber = hwBot.get(Servo.class, "stone_grabber");
        stoneGrabber.setDirection(Servo.Direction.FORWARD);
        */


        // Define and Intialize Servo for capstone servo arm
        /*
        capstoneDropper = hwBot.get(Servo.class, "capstone_dropper");
        capstoneDropper.setDirection(Servo.Direction.FORWARD);
        raiseCapstone();
        */

        // Define and Initialize Servos and Motors for intake

        intakeRSpinner = hwBot.dcMotor.get("intake_right_spinner");
        intakeRSpinner.setDirection(DcMotor.Direction.REVERSE);
        intakeRSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeLSpinner = hwBot.dcMotor.get("intake_left_spinner");
        intakeLSpinner.setDirection(DcMotor.Direction.FORWARD);
        intakeLSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeDeploy = hwBot.get(Servo.class, "intake_deploy");
        intakeDeploy.setDirection(Servo.Direction.FORWARD);

        intakePusher = hwBot.get(Servo.class, "intake_pusher");
        intakePusher.setDirection(Servo.Direction.FORWARD);


        // Define and Initialize Servo and Motor for stacking arm

        stackingLiftLeft = hwBot.dcMotor.get("stacking_lift_left");
        stackingLiftLeft.setDirection(DcMotor.Direction.REVERSE);
        stackingLiftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        stackingLiftRight = hwBot.dcMotor.get("stacking_lift_right");
        stackingLiftRight.setDirection(DcMotor.Direction.FORWARD);
        stackingLiftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Define & Initialize Servos for Claw Extender and Grabber

        clawExtender = hwBot.get(CRServo.class, "claw_extender");
        clawExtender.setPower(0);

        clawGrabber = hwBot.get(Servo.class, "claw_grabber");
        clawGrabber.setDirection(Servo.Direction.FORWARD);


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


        // Init webcamera only for autonomous
        if (Mode == "Auto") {
            initWebCam();
        }

        //init timer
        initTimers ();


    }


    // ***********  Initialize WebCam Method used by Autonomous

    public void initWebCam() {

        webcamName = hwBot.get(WebcamName.class, "WebCam");

        int cameraMonitorViewId = hwBot.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwBot.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;

        parameters.cameraName = webcamName;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);


        // Only one datda set in the trackable objects arraylist .... Sky Stone

        targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");
        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");
        allTrackables.addAll(targetsSkyStone);
        stoneTarget.setLocation(OpenGLMatrix
                .translation(0, 0, stoneZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));


        // We need to rotate the camera around it's long axis to bring the correct camera forward.

        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        // Rotate the phone vertical about the X axis if it's in portrait mode
        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90;
        }

        // Placement of the WebCam in the robot

        final float CAMERA_FORWARD_DISPLACEMENT = 10.0f * mmPerInch;   // eg: Camera is 10 Inches in front of robot-center
        final float CAMERA_VERTICAL_DISPLACEMENT = 9.0f * mmPerInch;   // eg: Camera is 9 Inches above ground
        final float CAMERA_LEFT_DISPLACEMENT = 0;     // eg: Camera is ON the robot's center line
        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
        }


    }

    // ******  Initialize Timers used by Autonomous and TeleOp

    public void initTimers () {
        vuforiaTimer = new ElapsedTime();
        vuforiaTimer.reset();

        stackingArmTimer = new ElapsedTime();
        stackingArmTimer.reset();
    }


    // *********  Build Plate Hook Mechanism Methods


    public void HookGrab () {

        HookLeft.setPosition(.19);       // was .5
        HookRight.setPosition(.871);
    }

    public void HookHalfGrab () {
        HookLeft.setPosition(.35);
        HookRight.setPosition(.67);
    }


    public void HookRelease () {

        HookLeft.setPosition(.5539);
        HookRight.setPosition(.3359);
    }




// ******   Intake Control Mechanisms

    public void intakeSpinInward () {           // Used in TeleOp

        intakeLSpinner.setPower(-0.85);
        intakeRSpinner.setPower(-0.85);
    }

    public void intakeSpinInwardAuto () {       // Used in Auto
        intakeLSpinner.setPower(-.75);      // was .7
        intakeRSpinner.setPower(-.75);      // was .7 ~  worked  sometimes but did not spin... maybe not enough torque?
    }
    public void intakeSpinOutward () {          // Reverse Normal

        intakeLSpinner.setPower(0.4);
        intakeRSpinner.setPower(0.4);
    }

    public void intakeSpinnerRunner () {        // Reverse Slowly for TeleOp (Runner Bot Button Only)

        intakeLSpinner.setPower(0.4);
        intakeRSpinner.setPower(0.2);

    }


    public void intakeSpinOff () {

        intakeLSpinner.setPower(0);
        intakeRSpinner.setPower(0);
    }

    public void intakePushIn () {
        intakePusher.setPosition(0.85);               // values came from servo testing
    }

    public void intakePushNeutral () {
        intakePusher.setPosition(0.29);                // values came from servo testing
    }

    public void intakePushHalf () {
        intakePusher.setPosition(.449);
    }

    public void intakePushMiddle () {
        intakePusher.setPosition(.7);
    }



    //*********** Intake Deployment Controls

    public void intakeDeployLower () {

        intakeDeploy.setPosition(.84);

    }
    public void intakeDeployRaise () {

        intakeDeploy.setPosition(.421);
    }


    // ***** Stacking Arm Lift Mechanisms without Encoders

    public void stackingArmUp() {                    // This is physically down

        stackingLiftLeft.setPower(0.25);            // 25% power to minimize unspooling
        stackingLiftRight.setPower(0.25);
    }

    public void stackingArmDown() {                 //This is physically up

        stackingLiftLeft.setPower(-0.5);            //50% power to mininmize uspooling
        stackingLiftRight.setPower(-0.5);
    }

    public void stackingArmOff () {
        stackingLiftLeft.setPower(0);
        stackingLiftRight.setPower(0);
    }

    // ***** Stacking Arm Lift Mechanisms Using Encoders

 /*   public void stackingArmUpEncoders () {
        stackingArmTimer.reset();
        while (stackingLiftLeft.getCurrentPosition() < stackingArmTargetPos && linearOp.opModeIsActive()) {
            stackingArmUp();
            if (stackingArmTimer.time() >= getMaxStackingArmTime) {
                break;
            }
            linearOp.sleep(300);
            linearOp.idle();
        }
        stackingArmOff();
    }

    public void stackingArmDownEncoders () {
        stackingArmTimer.reset();
        while (stackingLiftLeft.getCurrentPosition() >= 5 && linearOp.opModeIsActive()) {
            stackingArmDown();
            if (stackingArmTimer.time() >= getMaxStackingArmTime) {
                break;
            }
            linearOp.sleep(300);
            linearOp.idle();
        }
        stackingArmOff();
    }*/



    // ****** Claw Extension and Grabber Mechanisms

    public void clawExtenderExtend () {
        clawExtender.setPower(1);
    }
    public void clawExtenderRetract () {
        clawExtender.setPower(-1);
    }
    public void clawExtenderStop () {
        clawExtender.setPower(0);
    }

    public void clawGrabberGrab () {
        clawGrabber.setPosition(0.55);
    }

    public void clawGrabberGrabCapStone () {
        clawGrabber.setPosition(.4);
    }

    public void clawGrabberRelease () {

        clawGrabber.setPosition(0.95);
    }

    public void clawGrabberManualControl (double increment) {
        double servoPos = clawGrabber.getPosition();
        servoPos += increment;
        servoPos = Range.clip (servoPos,0,1);
        clawGrabber.setPosition(servoPos);
    }


    // ***********  Robot Gyro Controls and Gyro Correction Methods

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


    //************  Check Color and Distance Methods

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


    // *********** Gyro Drive and Gyro Stafing Methods

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

    public void driveGyroStraight (int encoders, double power, String direction) throws InterruptedException {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentPos = 0;
        double leftSideSpeed;
        double rightSideSpeed;


        double target = angles.firstAngle;
        double startPosition = frontLeftMotor.getCurrentPosition();
      //  linearOp.telemetry.addData("Angle to start: ", angles.firstAngle);
      //  linearOp.telemetry.update();
        linearOp.sleep(100);
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


/*
            linearOp.telemetry.addData("Left Speed", frontLeftMotor.getPower());
            linearOp.telemetry.addData("Right Speed", frontRightMotor.getPower());
            linearOp.telemetry.addData("Distance till destination ", encoders + startPosition - frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Current Position", currentPos);
            linearOp.telemetry.addData("Target Position", target);
            linearOp.telemetry.addData("Angle: ", angles.firstAngle);
            linearOp.telemetry.update();
            // missing waiting
*/
            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();
//
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
    //    linearOp.telemetry.addData("Angle to start: ", angles.firstAngle);
    //    linearOp.telemetry.update();
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


/*
            linearOp.telemetry.addData("Left Speed", frontLeftMotor.getPower());
            linearOp.telemetry.addData("Right Speed", frontRightMotor.getPower());
            linearOp.telemetry.addData("Distance till destination ", encoders + startPosition - frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Current Position", currentPos);
            linearOp.telemetry.addData("Target Position", target);
            linearOp.telemetry.addData("Angle: ", angles.firstAngle);

            linearOp.telemetry.update();
*/
            // missing waiting
            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();

    }



    public void driveGyroStrafeAngle (int encoders, double power, String direction, double angle) throws InterruptedException {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double currentPos = 0;
        double frontLeftSpeed;
        double frontRightSpeed;
        double rearLeftSpeed;
        double rearRightSpeed;


        double target = angle;
        double startPosition = frontLeftMotor.getCurrentPosition();
    //    linearOp.telemetry.addData("Angle to start: ", angles.firstAngle);
    //    linearOp.telemetry.update();
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


/*
           linearOp.telemetry.addData("Left Speed", frontLeftMotor.getPower());
           linearOp.telemetry.addData("Right Speed", frontRightMotor.getPower());
           linearOp.telemetry.addData("Distance till destination ", encoders + startPosition - frontLeftMotor.getCurrentPosition());
           linearOp.telemetry.addData("Current Position", currentPos);
           linearOp.telemetry.addData("Target Position", target);
           linearOp.telemetry.addData("Angle: ", angles.firstAngle);

           linearOp.telemetry.update();
*/
            // missing waiting
            linearOp.idle();
        }

        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        linearOp.idle();

    }


    // Robot Mechanisms - Vuforia WebCam Methods

    public void activateTracking() {

        targetsSkyStone.activate();
    }

    public void deActivateTracking() {

        targetsSkyStone.deactivate();
    }

    public void trackObjects (){

        for (VuforiaTrackable trackable : allTrackables) {
            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                targetName = trackable.getName();
                targetVisible = true;

                // getUpdatedRobotLocation() will return null if no new information is available since the last time that call was made, or if the trackable is not currently visible.
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }

        if (targetVisible) {

            // express position (translation) of robot in inches.
            VectorF translation = lastLocation.getTranslation();
            targetX = translation.get(0) / mmPerInch;
            targetY = translation.get(1) / mmPerInch;
            targetZ = translation.get(2) / mmPerInch;

            //BNI global variable
            skyStoneValue = targetY;

            // express the rotation of the robot in degrees.
            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
            targetRoll = rotation.firstAngle;
            targetPitch = rotation.secondAngle;
            targetHeading = rotation.thirdAngle;

        }
        else {
            targetName = "none";
        }
    }
    public void detectSkyStone () {
        vuforiaTimer.reset();

        while (!targetVisible && linearOp.opModeIsActive() && vuforiaTimer.time() <= 1) {
            trackObjects();
        }

        skyStoneValue = targetY;

//        linearOp.telemetry.addData("targetY: ", targetY);
//        linearOp.telemetry.addData("targetX: ", targetX);
//        linearOp.telemetry.addData("targetVisible: ", targetVisible);
//        linearOp.telemetry.addData("targetName: ", targetName);
//        linearOp.telemetry.update();

    }


// *****   Original Stone Grabber Auto Mechanism... deprecated (not used in latest generation)

    public void dropStone () {
        //stoneRotate.setPosition(.55);
        //stoneRotatePos = .55;
    }

    public void raiseStone() {
        //stoneRotatePos = .22;        //.2

    }

    public void autoRaiseStone() {
        //stoneRotatePos =.15;
    }

    public void grabStone () {
        //stoneGrabberPos = 0.4;
        //stoneGrabber.setPosition(0);
    }

    public void releaseStone() {

        //stoneGrabberPos = .9;
        //stoneGrabber.setPosition(.7);
    }

    public void neutralStone () {

        //stoneRotate.setPosition(.15);
        //stoneRotatePos = .15;
        //stoneGrabber.setPosition(0);
        //stoneGrabberPos = 0.4;
    }

    //  ******  Original Capstone Servo Mechanism  deprecated (not used in latest generation)

    public void dropCapstone() {
        capstoneDropper.setPosition(.55);
    }

    public void raiseCapstone() {
        capstoneDropper.setPosition(0);
    }




}





