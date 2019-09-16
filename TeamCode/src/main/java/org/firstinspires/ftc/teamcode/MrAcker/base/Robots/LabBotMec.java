package org.firstinspires.ftc.teamcode.MrAcker.base.Robots;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.MrAcker.base.Drivetrains.MecanumDrive;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;



public class LabBotMec extends MecanumDrive {

    //Robot Hardware Constructors

    public Servo HoodLeft = null;
    public Servo HoodRight = null;
    public Servo RearLift = null;
    public HardwareMap hwBot  =  null;
    public RevBlinkinLedDriver blinkinLedDriver;
    public RevBlinkinLedDriver.BlinkinPattern pattern;



   //FTC SDK Requirement
   public LinearOpMode linearOp = null;
   public void setLinearOp (LinearOpMode Op) {
       linearOp = Op;
   }


    //LabBotMec Constructor

    public LabBotMec() {

    }

    public void init(HardwareMap hwMap) {

        hwBot = hwMap;

        // Define Motors for Robot
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
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
        HoodLeft = hwBot.get(Servo.class, "hood_left");
        HoodLeft.setDirection(Servo.Direction.FORWARD);

        HoodRight = hwBot.get(Servo.class, "hood_right");
        HoodLeft.setDirection(Servo.Direction.REVERSE);
        HoodInit();

        RearLift = hwBot.get(Servo.class, "rear_lift");
        RearLiftInit();

        //Define & Initialize LED Lights
        blinkinLedDriver = hwBot.get(RevBlinkinLedDriver.class, "led_strip");
        pattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_LAVA_PALETTE;
        blinkinLedDriver.setPattern(pattern);

        //Define and Initialize GyroSensor

        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        parametersimu.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parametersimu.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parametersimu.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parametersimu.loggingEnabled = true;
        parametersimu.loggingTag = "IMU";
        parametersimu.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwBot.get(BNO055IMU.class, "imu");
        imu.initialize(parametersimu);

        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);





    }



    public void HoodClose () {

        HoodLeft.setPosition(.31);
        HoodRight.setPosition(.31);
    }


    public void HoodInit () {

        HoodLeft.setPosition(.31);
        HoodRight.setPosition(.31);
    }

    public void HoodSmile () {

        HoodLeft.setPosition(.18);
        HoodRight.setPosition(.18);

    }


    public void HoodOpen () {

        HoodLeft.setPosition(.10);
        HoodRight.setPosition(.10);

    }



    public void RearLiftUp () {

        RearLift.setPosition(.20);
    }


    public void RearLiftInit () {

        RearLift.setPosition(.20);

    }

    public void RearLiftDown () {

        RearLift.setPosition(.50);


    }

    public void RearLiftMid () {

        RearLift.setPosition(.40);


    }


}
