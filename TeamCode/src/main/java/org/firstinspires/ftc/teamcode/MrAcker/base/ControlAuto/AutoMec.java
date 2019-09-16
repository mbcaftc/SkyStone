package org.firstinspires.ftc.teamcode.MrAcker.base.ControlAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.MrAcker.base.Robots.LabBotMec;


@Autonomous(name = "Auto - LabBot Mecanum")
//@Disabled
public class AutoMec extends LinearOpMode {

    // Object Construction
   public ElapsedTime runtime = new ElapsedTime();
   public LabBotMec AckerBot = new LabBotMec();

   // Variables & Constants Specific to Autonomous
   public final double SPD_DRIVE_LOW = 0.38;
   public final double SPD_DRIVE_MED = 0.50;
   public final double SPD_DRIVE_HIGH = 0.75;
   public final double SPD_DRIVE_MAX = 1.0;
   public final long sleepTime = 100;


    @Override
    public void runOpMode() throws InterruptedException {

        //Hardware Initialization from Robot Class
        AckerBot.init(hardwareMap);
        AckerBot.gyroReset();

        waitForStart();

        idle();

        while (opModeIsActive()) {


            idle();

            AckerBot.HoodOpen();
            telemetry.addData("Status", "Hood Opened");
            telemetry.update();
            sleep(2000);

            AckerBot.HoodClose();
            telemetry.addData("Status", "Hood Closed");
            telemetry.update();
            sleep(2000);

            AckerBot.driveForward(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Drive Forward with Encoders");
            telemetry.update();

            AckerBot.HoodSmile();
            telemetry.addData("Status", "Hood Smiling");
            telemetry.update();
            sleep(2000);

            AckerBot.driveBackward(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Drive Backward with Encoders");
            telemetry.update();

            AckerBot.stopMotors();
            sleep(1000);

            AckerBot.rotateDirection(SPD_DRIVE_MAX, 90);
            sleep(1000);
            telemetry.addData("Status", "Rotate Left 90 degrees");
            telemetry.update();

            AckerBot.stopMotors();
            sleep(1000);

            AckerBot.rotateDirection(SPD_DRIVE_MAX, 2);
            sleep(1000);
            telemetry.addData("Status", "Rotate Right to zero");
            telemetry.update();

            AckerBot.stopMotors();
            sleep(1000);

            AckerBot.rotateDirection(SPD_DRIVE_MAX, -90);
            sleep(1000);
            telemetry.addData("Status", "Rotate Right to 90 Degrees");
            telemetry.update();

            AckerBot.stopMotors();
            sleep(1000);

            AckerBot.rotateDirection(SPD_DRIVE_MAX, 2);
            sleep(1000);
            telemetry.addData("Status", "Rotate Right to zero");
            telemetry.update();

            AckerBot.stopMotors();
            sleep(1000);

            AckerBot.strafeLeft(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Strafe Left with Encoders");
            telemetry.update();

            AckerBot.strafeRight(SPD_DRIVE_MAX,5.0);
            sleep(1000);
            telemetry.addData("Status", "Strafe Right with Encoders");
            telemetry.update();

            AckerBot.stopMotors();
            telemetry.addData("Status", "Stop All Motors");
            telemetry.update();

            idle();

            requestOpModeStop();
        }

        idle();
    }
}