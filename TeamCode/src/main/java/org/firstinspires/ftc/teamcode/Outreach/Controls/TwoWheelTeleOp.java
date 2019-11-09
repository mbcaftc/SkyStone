package org.firstinspires.ftc.teamcode.Outreach.Controls;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Outreach.DriveTrains.RoverDrive;
import org.firstinspires.ftc.teamcode.Outreach.DriveTrains.TwoWheelDrive;

@TeleOp(name = "Two Wheel Drive", group = "Outreach")
@Disabled

public class TwoWheelTeleOp extends OpMode {

    TwoWheelDrive myTwoWheelDrive;


    public void init(){
        myTwoWheelDrive = new TwoWheelDrive(hardwareMap.dcMotor.get("front_left_motor"), hardwareMap.dcMotor.get("front_right_motor"));
    }

    public void loop () {
        drive();
        telemetry();
    }


//methods

    public void drive() {
//            if(gamepad1.dpad_up) {      // code that works

            if (gamepad1.left_stick_y > .1) {
                myTwoWheelDrive.driveForward(gamepad1.left_stick_y);
            } else if (gamepad1.left_stick_y < -.1) {
                myTwoWheelDrive.driveBackward(gamepad1.left_stick_y);
            } else if (gamepad1.left_stick_x > .1) {
                myTwoWheelDrive.rotateLeft(gamepad1.left_stick_x);
            } else if (gamepad1.left_stick_x < -.1) {
                myTwoWheelDrive.rotateRight(gamepad1.left_stick_x);
            } else {
                myTwoWheelDrive.stopMotors();
            }
//            }
//            else if (gamepad1.dpad_down) {              // experimental code
            if (gamepad1.left_stick_y > .1) {
                myTwoWheelDrive.driveForward(gamepad1.left_stick_y);
            } else if (gamepad1.left_stick_y < -.1) {
                myTwoWheelDrive.driveBackward(gamepad1.left_stick_y);
            } else if (gamepad1.left_stick_x > .1) {
                myTwoWheelDrive.rotateLeft(gamepad1.left_stick_x);
            } else if (gamepad1.left_stick_x < -.1) {
                myTwoWheelDrive.rotateRight(gamepad1.left_stick_x);
            } else {
                myTwoWheelDrive.stopMotors();
            }
        }
//        }

    public void telemetry () {
        telemetry.addData("y coordinate", gamepad1.left_stick_y);
        telemetry.addData("x coordinate", gamepad1.left_stick_x);
//        telemetry.update();
    }



}

