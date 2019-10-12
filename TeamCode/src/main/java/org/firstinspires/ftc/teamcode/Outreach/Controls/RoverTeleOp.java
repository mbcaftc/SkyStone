package org.firstinspires.ftc.teamcode.Outreach.Controls;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Outreach.DriveTrains.RoverDrive;


    @TeleOp(name = "Rover Drive", group = "Outreach")
    @Disabled
    public class RoverTeleOp extends OpMode {

        RoverDrive myRover;


        public void init(){

            myRover = new RoverDrive(hardwareMap.dcMotor.get("front_left_motor"), hardwareMap.dcMotor.get("front_right_motor"), hardwareMap.dcMotor.get("rear_left_motor"), hardwareMap.dcMotor.get("rear_right_motor"));


        }

        public void loop () {

            drive();
            telemetry();
        }


//methods

        public void drive() {
//            if(gamepad1.dpad_up) {      // code that works

                if (gamepad1.left_stick_y > .1) {
                    myRover.driveForward(gamepad1.left_stick_y);
                } else if (gamepad1.left_stick_y < -.1) {
                    myRover.driveBackward(gamepad1.left_stick_y);
                } else if (gamepad1.left_stick_x > .1) {
                    myRover.rotateLeft(gamepad1.left_stick_x);
                } else if (gamepad1.left_stick_x < -.1) {
                    myRover.rotateRight(gamepad1.left_stick_x);
                } else {
                    myRover.stopMotors();
                }
//            }
//            else if (gamepad1.dpad_down) {              // experimental code
                if (gamepad1.left_stick_y > .1) {
                    myRover.driveForward(gamepad1.left_stick_y);
                } else if (gamepad1.left_stick_y < -.1) {
                    myRover.driveBackward(gamepad1.left_stick_y);
                } else if (gamepad1.left_stick_x > .1) {
                    myRover.rotateLeft(gamepad1.left_stick_x);
                } else if (gamepad1.left_stick_x < -.1) {
                    myRover.rotateRight(gamepad1.left_stick_x);
                } else {
                    myRover.stopMotors();
                }
            }
//        }

        public void telemetry () {

            telemetry.addData("y coordinate", gamepad1.left_stick_y);
            telemetry.addData("x coordinate", gamepad1.left_stick_x);

            telemetry.update();
        }



    }

