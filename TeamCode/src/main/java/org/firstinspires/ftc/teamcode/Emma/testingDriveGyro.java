package org.firstinspires.ftc.teamcode.Emma;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Testing Drive Gyro")
public class testingDriveGyro extends LinearOpMode {

    WoodBot Bot = new WoodBot();

    LinearOpMode linearOp;

    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        setLinearOp(this);

        waitForStart();


        while (opModeIsActive()) {
            Bot.driveGyro(4000,.2, "backward");
            sleep(1000);

            Bot.setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bot.setMotorRunModes(DcMotor.RunMode.RUN_USING_ENCODER);

            Bot.driveGyro(4000, .2, "forward");
            sleep(1000);

//            Bot.setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            Bot.setMotorRunModes(DcMotor.RunMode.RUN_USING_ENCODER);
//
//            Bot.driveGyro(4000,.2, "right");
//            sleep(1000);

            Bot.setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Bot.setMotorRunModes(DcMotor.RunMode.RUN_USING_ENCODER);



//            Bot.driveGyro(4000,.2, "left");
//            sleep(1000);
//
//            Bot.setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            Bot.setMotorRunModes(DcMotor.RunMode.RUN_USING_ENCODER);


            requestOpModeStop();
        }
        idle();
    }
}
