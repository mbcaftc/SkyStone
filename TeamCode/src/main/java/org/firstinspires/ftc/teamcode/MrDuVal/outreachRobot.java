package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.robot.Robot;
@TeleOp (name = "duval outreach")
@Disabled
public class outreachRobot extends OpMode {

    RobotDrive myRobotDrive;
//    enum State {Start, ArcadeMode, TankMode}
//    State DriveMode;

    @Override
    public void init() {
        myRobotDrive = new RobotDrive(hardwareMap.dcMotor.get("front_left_motor"),hardwareMap.dcMotor.get("front_right_motor"),hardwareMap.dcMotor.get("rear_left_motor"),hardwareMap.dcMotor.get("rear_right_motor"));

    }

    @Override
    public void loop() {
        myRobotDrive.arcadeDrive(gamepad1);
/*
        switch (DriveMode) {
            case Start:
                telemetry.addData("Choose Drive: ", "A - Arcade Mode;  b - Tank Mode");
                if (gamepad1.a)
                    DriveMode = State.ArcadeMode;
                else if (gamepad1.b)
                    DriveMode = State.TankMode;
                else
                    DriveMode = State.Start;
                break;
            case ArcadeMode:
                telemetry.addData("Current Drive Control: ", "Arcade Mode. Press B for TankMode");
                if (gamepad1.b) {
                    DriveMode = State.TankMode;
                } else {
                    myRobotDrive.arcadeDrive(gamepad1);
                }
                break;
            case TankMode:
                telemetry.addData("Current Drive Control: ", "Tank Mode. Press A for ArcadeMode");
                if (gamepad1.a) {
                    DriveMode = State.ArcadeMode;
                } else {
                    myRobotDrive.tankDrive(gamepad1);
                }
                break;

        } */
    }
}
