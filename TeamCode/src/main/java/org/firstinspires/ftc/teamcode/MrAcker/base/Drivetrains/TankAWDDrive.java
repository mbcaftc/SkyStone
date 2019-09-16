package org.firstinspires.ftc.teamcode.MrAcker.base.Drivetrains;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class TankAWDDrive implements DriveTrain {

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor rearRightMotor;
    public DcMotor rearLeftMotor;
    public LinearOpMode linearOp = null;

    public static final double TICKS_PER_ROTATION = 386.3;   // GoBilda Motor TICKS


    //FTC SDK Requirement
    public void setLinearOp (LinearOpMode Op) {
        linearOp = Op;
    }

    // Rotating with GyroSensor
    public Orientation angles;
    public Acceleration gravity;
    public BNO055IMU imu;
    final public double SPEED = .3;
    public final double TOLERANCE = .4;


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


    public void rotateLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);
        frontRightMotor.setPower(speed);


    }

    public void rotateRight (double speed) {
        frontLeftMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);
        frontRightMotor.setPower(-speed);
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

        while (frontLeftMotor.getCurrentPosition() > ticks) {
            driveBackward(speed);
        }
        stopMotors();
    }


    public void rotateLeft (double speed, double rotations) {

        double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() > ticks) {
                rotateLeft(speed);
        }
        stopMotors();
    }

    public void rotateRight (double speed, double rotations) {

        double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing right moves encoder towards -infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while(frontLeftMotor.getCurrentPosition() < ticks) {
            rotateRight(speed);
        }
        stopMotors();
    }



}
