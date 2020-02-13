package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@TeleOp (name = "Test WebCam Only + LED Servo ('led')!!!", group = "Lab")
//@Disabled
public class WebCamOnly extends OpMode {
    public VuforiaWebcam Cam = new VuforiaWebcam();

    Servo led;
    double ledInc = .001;
    double ledMin = .2525;
    double ledMax = .7475;
    double ledValue = ledMin;


    @Override
    public void init() {
        Cam.initCamera(hardwareMap);
        Cam.activateTracking();

        led = hardwareMap.servo.get("led");
        led.setPosition(ledValue);
    }

    @Override
    public void loop() {
        ledLoop();
        Cam.trackObjects();
        telemetryOutput();
    }

    private void telemetryOutput () {
        telemetry.addData("Camera Visible Target", Cam.targetName);
        telemetry.addData("Camera Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f", Cam.targetX, Cam.targetY, Cam.targetZ);
        telemetry.addData("Camera Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", Cam.targetRoll, Cam.targetPitch, Cam.targetHeading);
        telemetry.addData("ledValue: ", ledValue);
        telemetry.addData("LED Servo Pos: ", led.getPosition());
//        telemetry.addData("ledInc", ledInc);
    }
    public void ledLoop () {

        if (gamepad1.a){
            ledValue = .3;
        }
        if (gamepad1.x) {
            ledValue = .4;
        }
        if (gamepad1.b) {
            ledValue = .6;
        }
        if (gamepad1.y) {
            ledValue = .7;
        }
        if (gamepad1.right_bumper) {
            ledValue += ledInc;
        }
        if (gamepad1.left_bumper) {
            ledValue -= ledInc;
        }


        /*
        if (ledValue >= .7475) {
            ledInc *= -1;
        }
        if (ledValue <= .2525) {
            ledInc *= -1;
        }*/



//        ledValue += ledInc;
        ledValue = Range.clip(ledValue, ledMin, ledMax);
        led.setPosition(ledValue);
    }

}
