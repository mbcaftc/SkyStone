package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.internal.android.dx.dex.file.MethodAnnotationStruct;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.DriveTrains.MecanumDrive;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.IntakeSpinner;

@Autonomous (name = "Comp Auto Gen 1")
public class CompetitionStoneAuto extends LinearOpMode {

    MecanumDrive mechDrive;
    IntakeSpinner intakeSpin;
    @Override





    public void runOpMode() throws InterruptedException {
      mechDrive = new MecanumDrive (hardwareMap.dcMotor.get("front_left_motor"), hardwareMap.dcMotor.get("front_right_motor"), hardwareMap.dcMotor.get("rear_left_motor"), hardwareMap.dcMotor.get("rear_right_motor"));
      mechDrive.setLinearOp(this);

      intakeSpin = new IntakeSpinner(hardwareMap.servo.get("intake_spinner_left"), hardwareMap.servo.get("intake_spinner_right"));

        waitForStart();
        idle();


        while (opModeIsActive()) {

            mechDrive.stopMotors();
            mechDrive.driveForward(1, 2.5);
            sleep(1000);
            mechDrive.stopMotors();








        }
    }











}
