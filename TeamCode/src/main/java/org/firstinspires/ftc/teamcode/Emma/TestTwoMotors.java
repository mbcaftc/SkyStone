package org.firstinspires.ftc.teamcode.Emma;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "Test Two Motors = 'test_motor1' , 'test_motor2'", group = "Lab")
//@Disabled
public class TestTwoMotors extends OpMode {
    DcMotor testMotor1;
    DcMotor testMotor2;
    @Override
    public void init() {
        testMotor1 = hardwareMap.dcMotor.get("stacking_lift_left");
        testMotor2 = hardwareMap.dcMotor.get("stacking_lift_right");

        testMotor1.setDirection(DcMotorSimple.Direction.FORWARD);
        testMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        testMotor2.setDirection(DcMotorSimple.Direction.FORWARD);
        testMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        if (gamepad2.left_stick_x > .1) {
            testMotor1.setPower(gamepad2.left_stick_x);
            testMotor2.setPower(gamepad2.left_stick_x);
        }
        else if (gamepad2.left_stick_x < .1) {
            testMotor1.setPower(gamepad2.left_stick_x);
            testMotor2.setPower(gamepad2.left_stick_x);
        }
        else {
            testMotor1.setPower(0);
            testMotor2.setPower(0);
        }

        displayTelemetry();
    }

    public void displayTelemetry () {
        telemetry.addData("gamepad 2 left stick x:", gamepad2.left_stick_x);
        telemetry.addData("testMotor1 Power: ", testMotor1.getPower());
        telemetry.addData("testMotor2 Power: ", testMotor2.getPower());
    }
}
