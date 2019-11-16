package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "Spinner Motor Intake Test- both motors", group = "Lab")
@Disabled
public class OneMotorTest extends OpMode {
    DcMotor spinnerIntakeL;
    DcMotor spinnerIntakeR;
    @Override
    public void init() {
        spinnerIntakeL = hardwareMap.dcMotor.get("spinner_intake_l");
        spinnerIntakeR = hardwareMap.dcMotor.get("spinner_intake_r");
        spinnerIntakeL.setDirection(DcMotorSimple.Direction.FORWARD);
        spinnerIntakeR.setDirection(DcMotorSimple.Direction.REVERSE);
        spinnerIntakeL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        spinnerIntakeR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger > .1) {
            spinnerIntakeL.setPower(gamepad1.right_trigger);
            spinnerIntakeR.setPower(gamepad1.right_trigger);
        }
        else if (gamepad1.left_trigger > .1) {
            spinnerIntakeL.setPower(-gamepad1.left_trigger);
            spinnerIntakeR.setPower(-gamepad1.left_trigger);
        }
        else {
            spinnerIntakeL.setPower(0);
            spinnerIntakeR.setPower(0);
        }
    }
}
