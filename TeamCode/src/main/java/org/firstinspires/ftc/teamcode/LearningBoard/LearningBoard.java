package org.firstinspires.ftc.teamcode.LearningBoard;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorDigitalTouch;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp (name = "learning board")
public class LearningBoard extends OpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor linearSlideMotor;

    Servo servo1;
    Servo servo2;

    //RevTouchSensor touchSensor;
    //TouchSensor touchSensor2;
    //SensorDigitalTouch touchSensor3;
    DigitalChannel touchSensor;

    Rev2mDistanceSensor disColSensor;



    public void init() {

        motor1 = hardwareMap.dcMotor.get("motor_1");
        motor2 = hardwareMap.dcMotor.get("motor_2");
//        servo1 = hardwareMap.servo.get("servo_1");
//        servo1.setDirection(Servo.Direction.FORWARD);
//        servo1.setDirection(Servo.Direction.REVERSE);
//        servo2 = hardwareMap.servo.get("servo_2");
//        servo2.setDirection(Servo.Direction.FORWARD);
//        servo2.setDirection(Servo.Direction.REVERSE);
        //linearSlideMotor = hardwareMap.dcMotor.get("linear_slide_motor");

        //touchSensor = hardwareMap.get(RevTouchSensor.class, "touch_sensor");
      //  touchSensor2 = hardwareMap.get(TouchSensor.class, "touch_sensor");
        touchSensor = hardwareMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);

        disColSensor = hardwareMap.get(Rev2mDistanceSensor.class, "rev_distance_color_sensor");


    }

    public void loop() {

        motor1Control();
        motorControl2();
        controlTouchSensor();
        control2mDistanceSensor ();
        //servo1Control();
        //servo2Control();

        telemetry.addData("touch", touchSensor.getState());

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




    public void control2mDistanceSensor () {
        if (disColSensor.getDistance(DistanceUnit.INCH)  < 5) {
            motor2.setPower(1);
        }
        else {
            motor2.setPower(0);
        }
    }

    public void controlTouchSensor () {
        if (touchSensor.getState() == false) {
            motor1.setPower(1);
        } else {
            motor1.setPower(0);
        }

    }

}






















