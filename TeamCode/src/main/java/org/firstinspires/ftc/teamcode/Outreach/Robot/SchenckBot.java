package org.firstinspires.ftc.teamcode.Outreach.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Outreach.DriveTrains.OutreachDrive;

public class SchenckBot extends OutreachDrive {

    public HardwareMap hwBot = null;
    public Servo windup = null;
    public Servo head = null;

    public DcMotor SchenckLauncher;

    public DcMotor launcherLSpinner;
    public DcMotor launcherRSpinner;


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
        SchenckLauncher =  hwBot.dcMotor.get("schenck_launcher");

        launcherRSpinner = hwBot.dcMotor.get("launcher_right_spinner");
        launcherRSpinner.setDirection(DcMotor.Direction.REVERSE);
        launcherRSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        launcherLSpinner = hwBot.dcMotor.get("launcher_left_spinner");
        launcherLSpinner.setDirection(DcMotor.Direction.FORWARD);
        launcherLSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void launcherSpinInward () {

        launcherLSpinner.setPower(-0.6);
        launcherRSpinner.setPower(-0.6);
    }
    public void launcherSpinOutward () {

        launcherLSpinner.setPower(0.4);
        launcherRSpinner.setPower(0.4);
    }
    public void launcherSpinOff () {

        launcherLSpinner.setPower(0);
        launcherRSpinner.setPower(0);
    }

    //Servos


    public void rightWindup () {
        windup.setPosition(.2);// servo between 0 and 1
    }
    public void leftWindup () {
        windup.setPosition(0.8);
    }
    public void WindupOff () {
        windup.setPosition(0);
    }

    public void rightHead () {
        head.setPosition(.2);
    }
    public void leftHead () {
        head.setPosition(0.8);
    }
    public void headOff () {
        head.setPosition(0);
    }
}





