package org.firstinspires.ftc.teamcode.Emma;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.DriveTrains.MecanumDrive;

public class TestBotTeleOp extends OpMode {

    @Override
    public void init() {
        MecanumDrive mechDrive = new MecanumDrive(hardwareMap.dcMotor.get("front_left_motor"), hardwareMap.dcMotor.get("front_right_motor"), hardwareMap.dcMotor.get("rear_left_motor"), hardwareMap.dcMotor.get("rear_right_motor"));

    }

    @Override
    public void loop() {

        if (gamepad1.left_stick_y > .1 && gamepad1.left_stick_y < -.1) {

        }

    }
}
