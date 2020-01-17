package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
@TeleOp (name = "Stone Grabber Test", group = "lab")
@Disabled
public class DoubleServoStoneGrabber extends OpMode {

    Servo StoneGrabberHinge;
    Servo StoneGrabber;

    double hingePos = .5;
    double grabPos = .5;

    double incVal = 0.005;




    @Override
    public void init() {
        StoneGrabberHinge = hardwareMap.servo.get("stone_grabber_hinge");
        StoneGrabber = hardwareMap.servo.get("stone_grabber_intake");

        StoneGrabberHinge.setPosition(hingePos);
        StoneGrabber.setPosition(grabPos);
    }

    @Override
    public void loop() {
        if (gamepad1.right_bumper) {
            hingePos +=incVal;
            hingePos = Range.clip (hingePos,0,1);
//            StoneGrabberHinge.setPosition(hingePos);
            telemetry.addLine("Increase Hinge!");
        }
        if (gamepad1.left_bumper) {
            hingePos -= incVal;
            hingePos = Range.clip (hingePos,0,1);
//            StoneGrabberHinge.setPosition(hingePos);
            telemetry.addLine("Decrease Hinge!");
        }

        if (gamepad1.x) {
            grabPos += incVal;
            grabPos = Range.clip(grabPos,0,1);
//            StoneGrabberIntake.setPosition(grabPos);
            telemetry.addLine("Increase Grabber!");
        }
        if (gamepad1.y) {
            grabPos -= incVal;
            grabPos = Range.clip(grabPos,0,1);
//            StoneGrabberIntake.setPosition(grabPos);
            telemetry.addLine("Decrease Grabber!");

        }
        StoneGrabberHinge.setPosition(hingePos);
        StoneGrabber.setPosition(grabPos);
        telemetry.addData("Hinge Servo: ", hingePos + " " + StoneGrabberHinge.getPosition());
        telemetry.addData("Grabber Servo: ", grabPos + " " + StoneGrabber.getPosition());
        telemetry.update();
    }
}
