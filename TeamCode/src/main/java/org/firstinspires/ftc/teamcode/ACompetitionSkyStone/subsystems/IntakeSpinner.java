package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSpinner {


    //instance variables
    public Servo intakeServoLeft;
    public Servo intakeServoRight;

    public double SPD_max = 1;
    public double SPD_mid = .5;
    public double SPD_low = .3;

    public LinearOpMode linearOp = null;




    // constructor
    public IntakeSpinner (Servo ISL, Servo ISR) {
        intakeServoLeft = ISL;
        intakeServoRight = ISR;

    }


    //methods
    public void stopMotors () {
        intakeServoLeft.setPosition(.5);
        intakeServoRight.setPosition(.5);
    }

    public void SpinForward () {
        intakeServoLeft.setPosition(1);
        intakeServoRight.setPosition(1);
    }

    public void SpinBackward () {
        intakeServoLeft.setPosition(-1);
        intakeServoRight.setPosition(-1);
    }




}
