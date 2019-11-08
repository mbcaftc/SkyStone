package org.firstinspires.ftc.teamcode.Dawson;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Outreach.DriveTrains.MecanumDrive;

@Autonomous (name = "simple Auto Course")
@Disabled
public class SimpleAutoCourse extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive MechDrive = new MecanumDrive(hardwareMap.dcMotor.get("front_left_motor"), hardwareMap.dcMotor.get("front_right_motor"), hardwareMap.dcMotor.get("rear_left_motor"), hardwareMap.dcMotor.get("rear_right_motor"));


        MechDrive.setLinearOp(this);

        waitForStart();
        idle();

        while (opModeIsActive()) {

            MechDrive.stopMotors();



          //  MechDrive.driveForward(1, 7.5);
            sleep(1000);
            MechDrive.stopMotors();

            MechDrive.rotateLeft (1, 2.0);
            sleep(1000);
            MechDrive.stopMotors();


           /* MechDrive.strafeRight(1);
            sleep(1000);
            MechDrive.stopMotors();
            */



           // MechDrive.driveForward(1, 5);

           // MechDrive.rotateRight(1);

          //  MechDrive.driveForward(1, 1);

            //MechDrive.rotateRight(1);

           // MechDrive.driveForward(1, 6);


            MechDrive.stopMotors();


        }

        idle();


    }

}





