package org.firstinspires.ftc.teamcode.Outreach.Controls;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Outreach.DriveTrains.TwoWheelDrive;
import org.firstinspires.ftc.teamcode.Outreach.Robot.JesusBot;

@TeleOp(name = "Jesus Outreach Tele Op", group = "Outreach")
@Disabled





public class JesusTeleOp extends OpMode {

    public JesusBot Bot = new JesusBot();

    public void init(){
        Bot.initRobot(hardwareMap);
    }

    public void loop () {

    }

    public void drive() {
//            if(gamepad1.dpad_up) {      // code that works

        if (gamepad1.left_stick_y > .1) {
            Bot.driveForward(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y < -.1) {
            Bot.driveBackward(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_x > .1) {
            Bot.rotateLeft(gamepad1.left_stick_x);
        } else if (gamepad1.left_stick_x < -.1) {
            Bot.rotateRight(gamepad1.left_stick_x);
        } else {
            Bot.stopMotors();
        }
//            }
//            else if (gamepad1.dpad_down) {              // experimental code
        if (gamepad1.left_stick_y > .1) {
            Bot.driveForward(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y < -.1) {
            Bot.driveBackward(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_x > .1) {
            Bot.rotateLeft(gamepad1.left_stick_x);
        } else if (gamepad1.left_stick_x < -.1) {
            Bot.rotateRight(gamepad1.left_stick_x);
        } else {
            Bot.stopMotors();
        }
    }



}
