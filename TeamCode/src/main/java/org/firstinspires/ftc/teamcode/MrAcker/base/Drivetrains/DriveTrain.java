package org.firstinspires.ftc.teamcode.MrAcker.base.Drivetrains;

import com.qualcomm.robotcore.hardware.DcMotor;


public interface DriveTrain {

    void setMotorSpeeds (double speed);

    void setMotorRunModes (DcMotor.RunMode mode);

    void stopMotors();

    void driveForward( double speed);

    void driveBackward ( double speed);

    void rotateLeft (double speed);

    void rotateRight (double speed);

    void driveForward( double speed, double rotations);

    void driveBackward ( double speed, double rotations);

    void rotateLeft (double speed, double rotations);

    void rotateRight (double speed, double rotations);




}
