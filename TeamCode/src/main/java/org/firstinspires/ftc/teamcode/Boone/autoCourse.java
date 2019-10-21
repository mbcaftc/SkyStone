package org.firstinspires.ftc.teamcode.Boone;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Boone.MecanumDrive;

@Autonomous (name = "auto Course")

public class autoCourse extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive MechDrive = new MecanumDrive(hardwareMap.dcMotor.get("front_left_motor"), hardwareMap.dcMotor.get("front_right_motor"), hardwareMap.dcMotor.get("rear_left_motor"), hardwareMap.dcMotor.get("rear_right_motor"));
        MechDrive.setLinearOp(this);

        waitForStart();
        idle();

        while (opModeIsActive()) {

            MechDrive.stopMotors();

            //-------------------------------------Power---------------------------------


            MechDrive.driveForward(1, 6);
            sleep(1000);
            MechDrive.stopMotors();

            MechDrive.rotateLeft(1,.5);
            MechDrive.stopMotors();


            //------------------------------------------Encoders--------------------------


           // MechDrive.driveForward(1, 5);

           // MechDrive.rotateRight(1);

           /* MechDrive.driveForward(1, 1);

            MechDrive.rotateRight(1);

            MechDrive.driveForward(1, 6);*/


            MechDrive.stopMotors();

            requestOpModeStop();


        }

        idle();

    }

}



