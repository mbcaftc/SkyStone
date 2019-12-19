package org.firstinspires.ftc.teamcode.Outreach.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Outreach.DriveTrains.OutreachDrive;

public class JesusBot extends OutreachDrive {

    public HardwareMap hwBot = null;

    public DcMotor JesusLift;
    public DcMotor JesusLauncher;
    public JesusBot() {

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
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        //lift mechanism
        JesusLift =  hwBot.dcMotor.get("jesus_lift");

        JesusLift.setDirection(DcMotor.Direction.FORWARD);

        JesusLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        JesusLauncher = hwBot.dcMotor.get("jesus_launcher");
        JesusLauncher.setDirection(DcMotorSimple.Direction.FORWARD);
        JesusLauncher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void runLift (double power) {

        JesusLift.setPower(power);

    }






}



