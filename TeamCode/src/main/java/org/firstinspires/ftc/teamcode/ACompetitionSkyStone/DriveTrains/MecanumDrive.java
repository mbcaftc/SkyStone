package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.DriveTrains;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;



public class MecanumDrive  {


    // Instance Variables & Constants

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearRightMotor;
    public DcMotor rearLeftMotor;
    public static final double TICKS_PER_ROTATION = 386.3;   // GoBilda Motor TICKS

    public LinearOpMode linearOp = null;


    public void setLinearOp(LinearOpMode linearOp) {

        this.linearOp = linearOp;
    }


    public MecanumDrive() {

    }


    public void stopMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

    }


    public void setMotorRunModes (DcMotor.RunMode mode) {

        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);

    }

    // Sets speed for all motors with one method
    public void setMotorSpeeds (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
    }


    // Powers Motors with no encoder counts

    public void rotateRight (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);
    }

    public void rotateLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);
    }

    public void strafeLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);
    }

    public void strafeRight (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);
    }

    public void driveForward (double speed){
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(speed);
    }

    public void driveBackward (double speed){
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(-speed);
    }



    // Powers Motors with Encoder Counts

    public void driveForward( double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
                driveForward(speed);
            }
            stopMotors();
        }

    }


    public void driveBackward ( double speed, double rotations){

        if (linearOp.opModeIsActive()) {
            double ticks = rotations * (-1) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                driveBackward(speed);
            }
            stopMotors();
        }
    }


    public void rotateLeft (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                rotateLeft(speed);
            }
            stopMotors();
        }
    }

    public void rotateRight (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing right moves encoder towards -infinity
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive() ) {
                rotateRight(speed);
            }
            stopMotors();
        }
    }


    public void strafeRight (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
                strafeRight(speed);
            }
            stopMotors();
        }
    }

    public void strafeLeft (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                strafeLeft(speed);
            }
            stopMotors();
        }
    }


    //****
    //
    // Overloaded Methods for Powering Motors with Encoder Counts for TeleOp OpMode... TeleOp
    //
    // ***


    public void driveForward( double speed, double rotations, String Mode) {


        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks) {
            driveForward(speed);
        }
        stopMotors();

    }


    public void driveBackward ( double speed, double rotations, String Mode){

        double ticks = rotations * (-1) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() > ticks) {
            driveBackward(speed);
        }
        stopMotors();

    }


    public void rotateLeft (double speed, double rotations, String Mode) {

        double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() > ticks) {
            rotateLeft(speed);
        }
        stopMotors();

    }

    public void rotateRight (double speed, double rotations, String Mode) {


        double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing right moves encoder towards -infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks ) {
            rotateRight(speed);
        }
        stopMotors();
    }


    public void strafeRight (double speed, double rotations, String Mode) {

        double ticks = Math.abs(rotations) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks ) {
            strafeRight(speed);
        }
        stopMotors();

    }

    public void strafeLeft (double speed, double rotations, String Mode) {

        double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() > ticks ) {
            strafeLeft(speed);
        }
        stopMotors();
    }




}
