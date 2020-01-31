package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "VEX 393 Motor Test", group = "Lab")
@Disabled
public class VexMotors extends OpMode {
//    CRServo VexLeft, VexRight;
    Servo VexLeft, VexRight;

    double leftTriggerValue, rightTriggerValue, leftJoystickValue;

    @Override
    public void init() {
//        VexLeft = hardwareMap.crservo.get("vex_left");
//        VexRight = hardwareMap.crservo.get("vex_right");

        VexLeft = hardwareMap.servo.get("vex_left");
        VexRight = hardwareMap.servo.get("vex_right");
//        VexRight.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger > .1) {
//            VexRight.setPower(1);
//            VexLeft.setPower(1);
            VexRight.setPosition(1);
            VexLeft.setPosition(1);
            telemetry.addLine("RIGHT TRIGGER > .1");
        }
        else if (gamepad1.left_trigger > .1) {
//            VexRight.setPower(-1);
//            VexLeft.setPower(-1);
            VexRight.setPosition(0);
            VexLeft.setPosition(0);
            telemetry.addLine("LEFT TRIGGER > .1");
        }
        else {
//            VexRight.setPower(.0);
//            VexLeft.setPower(0);
            VexRight.setPosition(.5);
            VexLeft.setPosition(.5);
            telemetry.addLine("ELSE NOTHING! STOP!!");
        }
        telemetryOutput();
    }

    private void telemetryOutput () {
        telemetry.addData("VEX RIGHT POSITION: ", VexRight.getPosition());
        telemetry.addData("VEX LEFT POSITION: ", VexLeft.getPosition());
        telemetry.addData("Left Trigger: ", gamepad1.left_trigger);
        telemetry.addData("Right Trigger: ", gamepad1.right_trigger);
        telemetry.addData("Left Joystick X: ", gamepad1.left_stick_x);
        telemetry.addData("Left Joystick Y: ", gamepad1.left_stick_y);

        telemetry.update();
    }
}
