package org.firstinspires.ftc.teamcode.MrAcker.rookie;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MechanumDrive {

    // Instance Variables

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearLeftMotor;
    public DcMotor rearRightMotor;
    public LinearOpMode LinearOp = null;

    // Constants
    public static final double TICKS_PER_ROTATION = 386.3;  //386.3 is defined from the specs of the motor

    //Constructor

    public MechanumDrive (DcMotor fl, DcMotor fr, DcMotor rl, DcMotor rr){

        frontLeftMotor = fl;
        frontRightMotor = fr;
        rearLeftMotor = rl;
        rearRightMotor = rr;

        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }


    public void setLinearOp (LinearOpMode Op) {LinearOp = Op; }

  //** common class methods below here **

    public void setMotorRunModes (DcMotor.RunMode mode) {

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

    public void driveForward (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);      //corrected issue
        rearRightMotor.setPower(speed);     //corrected issue
    }
    public void driveBackward (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(-speed);

    }
    public void strafeRight (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);

    }
    public void strafeLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);

    }
    public void rotateLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);

    }
    public void rotateRight (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);
    }

    public void driveForward( double speed, double rotations) {

        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks) {
            driveForward(speed);
        }
        stopMotors();
    }

    public void driveBackward ( double speed, double rotations){
        double ticks = rotations * (-1) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks) {
            driveBackward(speed);
        }
        stopMotors();

    }

    public void strafeLeft ( double speed, double rotations){
        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks) {
            strafeLeft(speed);
        }
        stopMotors();
    }
    public void strafeRight ( double speed, double rotations){
        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks) {
            strafeRight(speed);
        }
        stopMotors();
    }


}
