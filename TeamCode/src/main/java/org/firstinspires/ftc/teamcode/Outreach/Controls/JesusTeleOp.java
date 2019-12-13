package org.firstinspires.ftc.teamcode.Outreach.Controls;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Outreach.DriveTrains.TwoWheelDrive;
import org.firstinspires.ftc.teamcode.Outreach.Robot.JesusBot;

@TeleOp(name = "Jesus Outreach Tele Op", group = "Outreach")





public class JesusTeleOp extends OpMode {

    public JesusBot Bot = new JesusBot();

    public void init(){
        Bot.initRobot(hardwareMap);
    }

    public void loop () {

        drive();
        lift();


    }

    public void lift() {
        if (gamepad1.y) {

            Bot.runLift(1);

        } else if (gamepad1.a) {

            Bot.runLift(-1);

        } else {

            Bot.runLift(0);

        }


    }

    public void drive() {

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
