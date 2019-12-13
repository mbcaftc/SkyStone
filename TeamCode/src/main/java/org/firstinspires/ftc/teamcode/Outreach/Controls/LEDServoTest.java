package org.firstinspires.ftc.teamcode.Outreach.Controls;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "LED Servo Test", group = "Lab")
//@Disabled
public class LEDServoTest extends OpMode {
    Servo led;

    double ledValue = .5;
    double ledInc = .001;
    @Override
    public void init() {
        led = hardwareMap.servo.get("led");
        led.setPosition(ledValue);
    }

    @Override
    public void loop() {
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
            ledLoop();
        }

        led.setPosition(ledValue);
        showTelemetry();
    }


    public void showTelemetry (){
        telemetry.addData("LED Servo Pos: ", led.getPosition());
        telemetry.addData("ledValue: ", ledValue);
        telemetry.addData("ledInc", ledInc);
    }

    public void ledLoop () {
        if (ledValue >= .7475) {
            ledInc *= -1;
        }
        if (ledValue <= .2525) {
            ledInc *= -1;
        }
        ledValue += ledInc;
    }

}
