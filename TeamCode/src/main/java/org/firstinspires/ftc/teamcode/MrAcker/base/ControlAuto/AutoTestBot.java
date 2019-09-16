package org.firstinspires.ftc.teamcode.MrAcker.base.ControlAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.MrAcker.base.Robots.BNIBot;
import org.firstinspires.ftc.teamcode.MrAcker.base.Robots.LabBotMec;


@Autonomous(name = "Auto - TestBot")
//@Disabled
public class AutoTestBot extends LinearOpMode {

    // Object Construction
   public ElapsedTime runtime = new ElapsedTime();
   public BNIBot TestBot = new BNIBot();

   // Variables & Constants Specific to Autonomous
   public final double SPD_DRIVE_LOW = 0.38;
   public final double SPD_DRIVE_MED = 0.50;
   public final double SPD_DRIVE_HIGH = 0.75;
   public final double SPD_DRIVE_MAX = 1.0;
   public final long sleepTime = 100;


    @Override
    public void runOpMode() throws InterruptedException {

        //Hardware Initialization from Robot Class
        TestBot.init(hardwareMap);


        waitForStart();

        idle();

        while (opModeIsActive()) {


            idle();

            TestBot.driveForward(SPD_DRIVE_MAX);
            sleep(1000);
            telemetry.addData("Status", "Drive Forward with Power");
            telemetry.update();

            TestBot.driveBackward(SPD_DRIVE_MAX);
            sleep(1000);
            telemetry.addData("Status", "Drive Backward with Power");
            telemetry.update();

            TestBot.rotateRight(SPD_DRIVE_LOW);
            sleep(5000);
            telemetry.addData("Status", "Rotate Right Power");
            telemetry.update();

            TestBot.rotateLeft(SPD_DRIVE_LOW);
            sleep(5000);
            telemetry.addData("Status", "Rotate Left with Powerr");
            telemetry.update();

            TestBot.stopMotors();
            sleep(5000);
            telemetry.addData("Status", "Stop Motors");
            telemetry.update();

            idle();

            requestOpModeStop();
        }

        idle();
    }
}