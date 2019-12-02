package org.firstinspires.ftc.teamcode.Emma;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
            Bot.driveGyro(1000, .3);
            sleep(100);

        }
        idle();
    }
}
