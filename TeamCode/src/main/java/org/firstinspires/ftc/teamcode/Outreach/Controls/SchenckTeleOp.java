package org.firstinspires.ftc.teamcode.Outreach.Controls;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Outreach.Robot.JesusBot;
import org.firstinspires.ftc.teamcode.Outreach.Robot.SchenckBot;

@TeleOp(name = "Schenck Outreach Tele Op", group = "Outreach")





public class SchenckTeleOp extends OpMode {

    public SchenckBot Bot = new SchenckBot();

    public void init()    {
        Bot.initRobot(hardwareMap);
    }

    public void loop () {

        drive();

        controlLauncherSpinners();

        controlWindup();

        controlArm();

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

    public void controlLauncherSpinners() {             //triggers are > 0 or < 0, bumpers are == true or ==false
        if (gamepad2.left_trigger > 0) {
            Bot.launcherSpinInward();
        } else if (gamepad2.right_trigger > 0) {
            Bot.launcherSpinOutward();
        } else {
            Bot.launcherSpinOff();
        }
    }

    public void controlWindup() {
        if (gamepad2.a) {
            Bot.leftWindup();
        } else if (gamepad2.b) {
            Bot.rightWindup();
        } else {
            Bot.WindupOff();
        }
    }

    public void controlArm() {
        if (gamepad2.x) {
            Bot.rightArm();
        } else if (gamepad2.y) {
            Bot.leftArm();
        } else {
            Bot.armOff();
        }
    }




}
