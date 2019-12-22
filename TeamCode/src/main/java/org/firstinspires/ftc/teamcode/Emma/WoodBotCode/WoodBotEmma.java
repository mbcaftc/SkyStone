package org.firstinspires.ftc.teamcode.Emma.WoodBotCode;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.DriveTrains.MecanumDrive;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;


public class WoodBotEmma extends MecanumDrive {

    //Robot Hardware Constructors

    public HardwareMap hwBot  =  null;
    public Servo HookLeft = null;
    public Servo HookRight = null;
    public Servo stoneServo = null;
    public Servo capstoneDropper = null;
    public OpenGLMatrix lastLocation = null;
    public VuforiaLocalizer vuforia = null;
    public WebcamName webcamName = null;
    public VuforiaTrackables targetsSkyStone = null;
    public List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
    public BNO055IMU imu;
    public Orientation angles;
    public Acceleration gravity;


    //Gyro Variables
    public final double SPEED = .3;
    public final double TOLERANCE = .4;

    // Vuforia Web Camera Variables

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


    //WoodBotEmma Constructor

    public WoodBotEmma() {

    }

    // Initialize Robot Primary Method

    public void initRobot (HardwareMap hwMap) {

        hwBot = hwMap;

        // Define & Initialize Hardware

        initMotorsDriveTrain();
        initServoHooks();
        initServoStoneGrabber();
        initServoCaptsone();
        initGyro();
        //initWebCam();    DO NOT INCLUDE IN INIT ROBOT, CALLED in AUTONOMOUS

        //Initialize Mechanism Positions and Camera
        HookRelease();
        dropStone();
        raiseCapstone();


    }

   // Hardware Initialization Sub Methods

    public void initMotorsDriveTrain(){

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

    }


    public void initServoHooks() {

        HookLeft = hwBot.get(Servo.class, "hook_left");
        HookLeft.setDirection(Servo.Direction.FORWARD);

        HookRight = hwBot.get(Servo.class, "hook_right");
        HookRight.setDirection(Servo.Direction.FORWARD);

    }

    public void initServoStoneGrabber() {

        stoneServo = hwBot.get(Servo.class, "stone_servo");
        stoneServo.setDirection(Servo.Direction.FORWARD);

    }

    public void initServoCaptsone() {

        capstoneDropper = hwBot.get(Servo.class, "capstone_dropper");
        capstoneDropper.setDirection(Servo.Direction.FORWARD);

    }

    public void initGyro() {
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

    public void initWebCam() {

        webcamName = hwBot.get(WebcamName.class, "WebCam");

        int cameraMonitorViewId = hwBot.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwBot.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;

        parameters.cameraName = webcamName;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);


        // Only one data set in the trackable objects arraylist .... Sky Stone

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


    // Robot Mechanism - Hook Methods

    public void HookRelease () {

        HookLeft.setPosition(.11);
        HookRight.setPosition(0.0);
    }


    public void HookGrab () {
        HookLeft.setPosition(.87);
        HookRight.setPosition(.73);
    }


    // Robot Mechanisms - Stone Grabber Methods

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


    // Robot Mechanisms - Gyro Methods

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


    // Autonomous Methods

    public void detectSkyStone () {

        while (!targetVisible && linearOp.opModeIsActive()) {
            trackObjects();

        }

        linearOp.telemetry.addData("targetY: ", targetY);
        linearOp.telemetry.addData("targetX: ", targetX);
        linearOp.telemetry.addData("targetVisible: ", targetVisible);
        linearOp.telemetry.addData("targetName: ", targetName);
        linearOp.telemetry.update();

    }


    public void driveToSkyStone (String Alliance) {

     // Positive target value is from the center (0) to the right
     // Negative target value is from the center (0) to the left

        if (Alliance == "Red") {

            if (targetY > 1 && targetVisible && targetName == "Stone Target") {                 //Position 1

                strafeRight(.3, 4);
                linearOp.sleep(100);
                linearOp.telemetry.addLine("Position 1");
                linearOp.telemetry.update();


            } else if (targetY < 1 && targetVisible && targetName == "Stone Target" ) {       // Position 2

                // No Need to Move
                linearOp.telemetry.addLine("Position 2");
                linearOp.telemetry.update();

            } else {                                                                            // position 3

                strafeLeft(.3, 4);
                linearOp.sleep(100);
                linearOp.telemetry.addLine("targetY > 1... position 3");
                linearOp.telemetry.update();
            }
        }
        else if (Alliance == "Blue") {

            if (targetY < -1 && targetVisible && targetName == "Stone Target") {           //position 1

                strafeLeft(.3, 2);
                linearOp.telemetry.addLine(" Position 1");
                linearOp.telemetry.update();



            } else if (targetY > -1 && targetVisible && targetName == "Stone Target") {      //position 2

                // No Need to Move
                linearOp.telemetry.addLine("Position 2");
                linearOp.telemetry.update();

            } else {                                                                        // position 3

                strafeRight(.3, 2);
                linearOp.sleep(100);
                linearOp.telemetry.addLine("Position 3");
                linearOp.telemetry.update();

            }
            driveForward(.3, 1);
        }
    }


}






