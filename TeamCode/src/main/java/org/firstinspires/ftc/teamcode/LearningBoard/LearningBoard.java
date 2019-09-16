package org.firstinspires.ftc.teamcode.LearningBoard;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;

@TeleOp (name = "learning board")
public class LearningBoard extends OpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor linearSlideMotor;

    Servo servo1;
    Servo servo2;

    RevTouchSensor touchSensor;
    SensorREVColorDistance disColSensor;

    public void init() {

        motor1 = hardwareMap.dcMotor.get("motor_1");
        motor2 = hardwareMap.dcMotor.get("motor_2");
        servo1 = hardwareMap.servo.get("servo_1");
        servo1.setDirection(Servo.Direction.FORWARD);
        servo1.setDirection(Servo.Direction.REVERSE);
        servo2 = hardwareMap.servo.get("servo_2");
        servo2.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.REVERSE);
        linearSlideMotor = hardwareMap.dcMotor.get("linear_slide_motor");

        touchSensor = hardwareMap.get(RevTouchSensor.class, "touch_sensor");
        disColSensor = hardwareMap.get(SensorREVColorDistance.class, "rev_distance_color_sensor");


    }

    public void loop() {

        motor1Control();
        motorControl2();
        servo1Control();
        servo2Control();



    }


    public void motor1Control() {

        if (gamepad1.left_stick_y < -.1) {
            motor1.setPower(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y > .1) {
            motor1.setPower(gamepad1.left_stick_y);
        } else {
            motor1.setPower(0);
        }
    }

    public void motorControl2() {

        if (gamepad1.left_stick_y < -.1) {
            motor2.setPower(gamepad1.right_stick_y);
        } else if (gamepad1.right_stick_y > .1) {
            motor2.setPower(gamepad1.right_stick_y);
        } else {
            motor2.setPower(0);
        }
    }

    public void linearSlideControl () {
        if (gamepad1.dpad_up == true) {
            linearSlideMotor.setPower(1);
        } else if (gamepad1.dpad_down ==true) {
            linearSlideMotor.setPower(-1);
        } else {
            linearSlideMotor.setPower(0);
        }

    }
    public void servo1Control () {
        if (gamepad1.y ==true) {
            servo1.setPosition(0.5);
        } else if (gamepad1.x ==true) {
            servo1.setPosition(0.75);
        } else {

        }
    }
    public void servo2Control () {
        if (gamepad1.b ==true) {
            servo2.setPosition(0.25);
        } else if (gamepad1.a ==true) {
            servo2.setPosition(0.4);
        } else {

        }
    }
}






















