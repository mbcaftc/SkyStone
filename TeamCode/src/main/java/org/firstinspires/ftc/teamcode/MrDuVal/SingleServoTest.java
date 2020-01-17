package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
@TeleOp (name = "Test Servo", group = "lab")
public class SingleServoTest extends OpMode {

    Servo testServo;

    double servoPos = .5;

    double incVal = 0.005;




    @Override
    public void init() {
        testServo = hardwareMap.servo.get("claw_grabber");
        testServo.setPosition(servoPos);
    }

    @Override
    public void loop() {
        if (gamepad1.right_bumper) {
            servoPos +=incVal;
            servoPos = Range.clip (servoPos,0,1);
//            StoneGrabberHinge.setPosition(hingePos);
            telemetry.addLine("Increase Servo!");
        }
        if (gamepad1.left_bumper) {
            servoPos -= incVal;
            servoPos = Range.clip (servoPos,0,1);
//            StoneGrabberHinge.setPosition(hingePos);
            telemetry.addLine("Decrease Servo!");
        }

        if (gamepad1.x) {
            servoPos = 1;
            telemetry.addLine("Set Servo to 1!");
        }
        if (gamepad1.y) {
            servoPos = 1;
            telemetry.addLine("Set Servo to 0!");

        }
        testServo.setPosition(servoPos);
        telemetry.addLine("RB: increase, LB: Decrease");
        telemetry.addLine("x = set to 1, y = set to 0");
        telemetry.addData("TestS ervo Positiom: ", testServo.getPosition());
        telemetry.addData("Servo Variable Position: ", servoPos);
        telemetry.update();
    }
}
