package org.firstinspires.ftc.teamcode.Outreach.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Outreach.DriveTrains.OutreachDrive;

public class JesusBot extends OutreachDrive {

    public HardwareMap hwBot = null;


    public JesusBot() {

    }



    public void initRobot (HardwareMap hwMap) {

        hwBot = hwMap;

        //direction set
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        //runmodes

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);   //memorize
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // MUST HAVE RUN MODE

        //brakes (init)
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }






}



