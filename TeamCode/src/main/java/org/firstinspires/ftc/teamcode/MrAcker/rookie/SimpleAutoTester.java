package org.firstinspires.ftc.teamcode.MrAcker.rookie;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.base.Drivetrains.MecanumDrive;

//@Disabled
@Autonomous (name = "Simple Auto Tester")

public class SimpleAutoTester  extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        MechanumDrive MechDrive = new MechanumDrive(hardwareMap.dcMotor.get("front_left_motor"), hardwareMap.dcMotor.get("front_right_motor"), hardwareMap.dcMotor.get("rear_left_motor"), hardwareMap.dcMotor.get("rear_right_motor"));
        MechDrive.setLinearOp(this);    //memorize (often the cause of null pointer exceptions)


        waitForStart();
        idle();

        while (opModeIsActive()) {

            //------------------------------------Power---------------------------------

            MechDrive.driveForward(1);
            sleep(2000);
            MechDrive.stopMotors();


            MechDrive.driveBackward(1);
            sleep(2000);
            MechDrive.stopMotors();


            requestOpModeStop();

        }


        idle();


    }




}
