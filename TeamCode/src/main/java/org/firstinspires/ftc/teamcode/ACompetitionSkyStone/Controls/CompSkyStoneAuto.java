package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.DriveTrains.MecanumDrive;

@Autonomous (name = "Skystone Auto")
public class CompSkyStoneAuto extends LinearOpMode {

    @Override

    final long  sleepTime = 200;

    public void runOpMode() throws InterruptedException {

        MecanumDrive mechDrive = new MecanumDrive(hardwareMap.dcMotor.get("front_left_motor"), hardwareMap.dcMotor.get("front_right_motor"), hardwareMap.dcMotor.get("rear_left_motor"), hardwareMap.dcMotor.get("rear_right_motor"));
        mechDrive.setLinearOp(this);

        SkyStoneAuto myAuto = new SkyStoneAuto();

        waitForStart();
        idle();

        while (opModeIsActive()) {

            myAuto.sampleSkyStone();
            sleep(sleepTime);

            myAuto.dropOffMineral();
            sleep(sleepTime);

            myAuto.adjustBuildPlate();
            sleep(sleepTime);

            myAuto.goToSkyStones();
            sleep(sleepTime);

            myAuto.goToSkyStones();
            sleep(sleepTime);

            myAuto.sampleSkyStone();
            sleep(sleepTime);

            myAuto.dropOffMineral();
            sleep(sleepTime);

            myAuto.








        }

    }





}
