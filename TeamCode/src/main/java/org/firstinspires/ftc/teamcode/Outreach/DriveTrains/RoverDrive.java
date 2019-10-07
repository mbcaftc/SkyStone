package org.firstinspires.ftc.teamcode.Outreach.DriveTrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


public class RoverDrive {


    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;

    public LinearOpMode linearOp = null;                // this is done to make the TestMecanumDrive class aware of the linearOp
    //class which is used by Mecanum Drvie parents class


    public void setLinearOp(LinearOpMode linearOp) {
        this.linearOp = linearOp;
    }

    public final DcMotor.RunMode currentMotorRunMode = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public static final double TICKS_PER_ROTATION = 538;



    public RoverDrive(DcMotor FL, DcMotor FR, DcMotor RL, DcMotor RR) {
        frontLeftMotor = FL;
        frontRightMotor = FR;
        rearLeftMotor = RL;
        rearRightMotor = RR;

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);     //Forward and reverse depends on builder and manufacture
        rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.FORWARD);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);   //memorize
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // MUST HAVE RUN MODE

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }



    public void setMotorRunModes (DcMotor.RunMode mode) {               //sets the mode/behavior for the motor

//        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);

    }

    public void stopMotors () {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);
    }


    public void driveForward (double power) {
        double ABSpower = Math.abs(power);

        frontLeftMotor.setPower(ABSpower);
        frontRightMotor.setPower(ABSpower);
        rearLeftMotor.setPower(ABSpower);
        rearRightMotor.setPower(ABSpower);

    }

    public void driveBackward (double power) {
        double ABSpower = Math.abs(power);

        frontLeftMotor.setPower(-ABSpower);
        frontRightMotor.setPower(-ABSpower);
        rearLeftMotor.setPower(-ABSpower);
        rearRightMotor.setPower(-ABSpower);
    }

    public void rotateLeft (double power) {
        double ABSpower = Math.abs(power);

        frontLeftMotor.setPower(-ABSpower);
        rearLeftMotor.setPower(-ABSpower);

        frontRightMotor.setPower(ABSpower);
        rearRightMotor.setPower(ABSpower);
    }

    public void rotateRight (double power) {
        double ABSpower = Math.abs(power);

        frontLeftMotor.setPower(ABSpower);
        rearLeftMotor.setPower(ABSpower);

        frontRightMotor.setPower(-ABSpower);
        rearRightMotor.setPower(-ABSpower);

    }


    //******Drive with Encoder Methods********
    public void driveForward( double speed, double rotations) {

        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);
        while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
            driveForward(speed);
        }
        stopMotors();
    }

    public void driveBackward ( double speed, double rotations){

        double ticks = rotations * (-1) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

        while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
            driveBackward(-speed);
        }
        stopMotors();
    }

    public void rotateLeft (double speed, double rotations) {
        double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

        while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
            rotateLeft(speed);
        }
        stopMotors();
    }




    public void rotateRight (double speed, double rotations) {
        double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing right moves encoder towards -infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(currentMotorRunMode);

        while(frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
            rotateRight(speed);
        }
        stopMotors();
    }





}

