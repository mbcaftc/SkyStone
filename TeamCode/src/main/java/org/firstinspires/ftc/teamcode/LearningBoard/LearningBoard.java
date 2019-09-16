package org.firstinspires.ftc.teamcode.LearningBoard;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "learning board")
public class LearningBoard extends OpMode {
    public Servo servo_1, servo_2;
    public DcMotor motor_1, motor_2, linear_slide_motor;

    @Override
    public void init() {
        servo_1 = hardwareMap.servo.get("servo_1");
        servo_2 = hardwareMap.servo.get("servo_2");
        motor_1 = hardwareMap.dcMotor.get("motor_1");
        motor_2 = hardwareMap.dcMotor.get("motor_2");
        linear_slide_motor = hardwareMap.dcMotor.get("linear_slide_motor");
        motor_1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linear_slide_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor_2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor_1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linear_slide_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            servo_1.setPosition(1);
        }
        else if (gamepad1.b){
            servo_1.setPosition(0);
        }
        else {
            servo_1.setPosition(.5);
        }
        if (gamepad1.x) {
            servo_2.setPosition(1);
        }
        else if (gamepad1.y){
            servo_2.setPosition(0);
        }
        else {
            servo_2.setPosition(.5);
        }

        motor_1.setPower(gamepad1.left_stick_y);
        motor_2.setPower(gamepad1.right_stick_y);
        linear_slide_motor.setPower(gamepad1.right_trigger);
        linear_slide_motor.setPower(-gamepad1.left_trigger);
    }
}
