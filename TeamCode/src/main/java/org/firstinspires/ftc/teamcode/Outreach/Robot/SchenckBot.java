package org.firstinspires.ftc.teamcode.Outreach.Robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Outreach.DriveTrains.OutreachDrive;

public class SchenckBot extends OutreachDrive {

    public HardwareMap hwBot = null;
//    public Servo windup = null;
//    public Servo arm1 = null;

    public CRServo heartSpinner = null;

    public DcMotor SchenckLauncher;

    public DcMotor launcherLSpinner;
    public DcMotor launcherRSpinner;

    public DcMotor heartLSpinner;
    public DcMotor heartRSpinner;

    public DcMotor convBelt;


    public SchenckBot() {

    }



    public void initRobot (HardwareMap hwMap) {

        hwBot = hwMap;



        frontLeftMotor =  hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");




        //direction set
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);


        //runmodes

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //brakes (init)
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        //launching mechanism
        //SchenckLauncher =  hwBot.dcMotor.get("schenck_launcher");

        launcherRSpinner = hwBot.dcMotor.get("launcher_right_spinner");
        launcherRSpinner.setDirection(DcMotor.Direction.REVERSE);
        launcherRSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        launcherLSpinner = hwBot.dcMotor.get("launcher_left_spinner");
        launcherLSpinner.setDirection(DcMotor.Direction.FORWARD);
        launcherLSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        heartLSpinner = hwBot.dcMotor.get("left_heart");
        heartLSpinner.setZeroPowerBehavior (DcMotor.ZeroPowerBehavior.BRAKE);
        heartLSpinner.setDirection(DcMotorSimple.Direction.FORWARD);

        heartRSpinner = hwBot.dcMotor.get("right_heart");
        heartRSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        heartRSpinner.setDirection(DcMotorSimple.Direction.FORWARD);

        convBelt = hwBot.dcMotor.get("conv_belt");
        convBelt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        convBelt.setDirection(DcMotorSimple.Direction.REVERSE);

        heartSpinner = hwBot.crservo.get("heart_servo");

//        windup = hwBot.servo.get("windup_servo");
//        windup.setPosition(0);

//        arm1 = hwBot.servo.get("arm1_servo");
        //arm1.setPosition(0);

    }

    public void launcherSpinInward () {

        launcherLSpinner.setPower(-1);
        launcherRSpinner.setPower(-1);
    }
    public void launcherSpinOutward () {

        launcherLSpinner.setPower(.5);
        launcherRSpinner.setPower(.5);
    }
    public void launcherSpinOff () {

        launcherLSpinner.setPower(0);
        launcherRSpinner.setPower(0);
    }

    public void heartSpinnersOn () {
        heartRSpinner.setPower(1);
        heartLSpinner.setPower(1);
    }

    public void heartSpinnerOff() {
        heartRSpinner.setPower(0);
        heartLSpinner.setPower(0);
    }

    public void convBeltForward () {
        convBelt.setPower(1);
    }

    public void convBeltReverse () {
        convBelt.setPower(-1);
    }

    public void convBeltOff () {
        convBelt.setPower(0);
    }


    //Servos



//    public void rightWindup () {
//        windup.setPosition(.2);// servo between 0 and 1
//    }
//    public void leftWindup () {
//        windup.setPosition(0.8);
//    }
//    public void WindupOff () {
//        windup.setPosition(0);
//    }

//    public void rightArm () {
//        arm1.setPosition(.2);
//    }
//    public void leftArm () {
//        arm1.setPosition(0.2);
//    }
//    public void armOff () {
//        arm1.setPosition(0);
//    }
}





