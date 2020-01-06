package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp (name = "Test One Motor = 'test_motor'", group = "Lab")
//@Disabled
public class TestOneMotor extends OpMode {
    DcMotor testMotor;
    @Override
    public void init() {
        testMotor = hardwareMap.dcMotor.get("stacking_arm");
        testMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        testMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        if (gamepad2.left_stick_x > .1) {
            testMotor.setPower(gamepad2.left_stick_x);
        }
        else if (gamepad2.left_stick_x < .1) {
            testMotor.setPower(-gamepad2.left_stick_x);
        }
        else {
            testMotor.setPower(0);
        }

        displayTelemetry();
    }

    public void displayTelemetry () {
        telemetry.addData("gamepad 2 left stick x:", gamepad2.left_stick_x);
        telemetry.addData("testMotor Power: ", testMotor.getPower());
    }
}
