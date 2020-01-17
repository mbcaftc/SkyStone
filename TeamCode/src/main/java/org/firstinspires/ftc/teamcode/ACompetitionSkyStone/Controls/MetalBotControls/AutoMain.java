package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;

public abstract class AutoMain extends LinearOpMode {

    // Variables & Constants used for MetalBot across both Building and Loading Locations on the field

    public final long sleepTime = 15;
    public final double maxSpeed = 1;
    public final double highSpeed = .6;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;
    public LinearOpMode linearOp = null;
    public final double gyroSPD = .25;

    public final int colorImage = 20;       // was 15 ... color of image is 18, 27 in MBCA BNI room  15 comp = 20
    public final int colorYellow = 40; //40 in MBCA BNI room b  30
    public final int colorNoBackground = 35; //is 35 ... was 60 in MBCA BNI room
    public double tracker = 0;

    public double timeThreshold = 0;
    public ElapsedTime skyStoneTime = new ElapsedTime();

    public int skyStonePosition = 1;

    public static final float mmPerInch = 25.4f;
    public static final float mmTargetHeight = (6) * mmPerInch;          // the height of the center of the target image above the floor

    public static final float stoneZ = 2.00f * mmPerInch;

    public boolean targetVisible = false;
    public float phoneXRotate = 0;
    public float phoneYRotate = 0;
    public float phoneZRotate = 0;

    public String targetName = null;
    public double targetX;
    public double targetY;
    public double targetZ;
    public double targetRoll;
    public double targetPitch;
    public double targetHeading;


    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }

    // Methods used for both Building and Loading on the Field

    public void manipulateIntake (MetalBot Bot, String position) {
        if (position == "flip down") {
            Bot.intakeDeployLower();
        }
        else if (position == "inward") {
            Bot.intakeSpinInward();
        }
        else if ( position == "outward") {
            Bot.intakeSpinOutward();
        }
        else if (position == "stop") {
            Bot.intakeSpinOff();
        }
    }


    public void removeSkyStoneOuter(MetalBot Bot, String Alliance) {
        if (Alliance == "Red") {


        } else if (Alliance == "Blue") {


        }
    }

    public void removeSkyStoneInner(MetalBot Bot) {

            Bot.driveBackward(.2, 1.8);
            sleep(sleepTime);
    }


    public void adjustToDropSkyStone(MetalBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.gyroCorrection(.2, 0);

        } else if (Alliance == "Blue") {
            Bot.gyroCorrection(.2, 0);
        }

    }

    public void driveToPlate (String Alliance, MetalBot Bot) throws InterruptedException {
        if (Alliance == "Red") {
            Bot.driveGyroStrafe(2800,.6,"right");
        }
        else if (Alliance == "Blue") {
            Bot.driveGyroStrafe(2800,.6,"left");
        }
    }



    public void goToFirstLocation(MetalBot Bot, String Alliance) {
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Bot.frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        if (Alliance == "Red") {
            // gyro correction
        } else if (Alliance == "Blue") {
            // gyro correction
        }
        while (Math.abs(Bot.frontLeftMotor.getCurrentPosition()) < tracker && linearOp.opModeIsActive()) {
            linearOp.telemetry.addData("Current Left Motor Position: ", Math.abs(Bot.frontLeftMotor.getCurrentPosition()));
            linearOp.telemetry.addData("Target Left Motor Position: ", tracker);
            linearOp.telemetry.update();
            if (Alliance == "Red") {
                // drive at a low speed


            } else if (Alliance == "Blue") {
                // drive at a low speed
            }

        }
        Bot.stopMotors();
    }

    public void driveToSkyStone (MetalBot Bot, String Alliance) {

        // Positive target value is from the center (0) to the right
        // Negative target value is from the center (0) to the left
        if (Alliance == "Red") {
            telemetry.addData("target Y:", targetY);
            telemetry.update();
            sleep(500);
            if (Bot.skyStoneValue < -1 ) {                 ////position 1 (LEFT)

                Bot.strafeLeft(.3, 1.2);
                linearOp.telemetry.addLine("Position 1");
                telemetry.addData("target Y 2:", targetY);
                linearOp.telemetry.update();
                sleep(500);
                skyStonePosition = 1;


            }  else if (targetY == 0.0) {                                                                         // position 3

                Bot.strafeRight(.3, .8);
                linearOp.telemetry.addLine("Position 3");
                linearOp.telemetry.update();
                skyStonePosition = 3;
            }

            else if (Bot.skyStoneValue > -1 ) {       // position 2 (MIDDLE)

                Bot.strafeLeft(.3, .3);     // was .1
                linearOp.telemetry.addLine("Position 2");
                telemetry.addData("target Y:", targetY);
                linearOp.telemetry.update();
                sleep(500);

                skyStonePosition = 2;

            }

        }

        // Positive target value is from the center (0) to the right
        // Negative target value is from the center (0) to the left
        else if (Alliance == "Blue") {

            if (targetY < -1 && targetVisible) {           //position 1 (LEFT) with Camera on Left Sideskirt ranges from -5.498 to -6.059

                Bot.strafeLeft(.3, 1.2);
                linearOp.telemetry.addLine(" Position 1");
                linearOp.telemetry.update();
                skyStonePosition = 1;



            } else if (targetY == 0.0) {                                                                        // position 3

                Bot.strafeRight(.3, .8);
                linearOp.telemetry.addLine("Position 3");
                linearOp.telemetry.update();
                skyStonePosition = 3;
            }

            else if (targetY > -1 && targetVisible) {      //position 2 (MIDDLE) with Camera on on Left Sideskirt ranges from 2.65 to 2.757

                Bot.strafeLeft(.3, .1);
                // No Need to Move
                linearOp.telemetry.addLine("Position 2");
                linearOp.telemetry.update();
                skyStonePosition = 2;

            }
        }
    }


}
