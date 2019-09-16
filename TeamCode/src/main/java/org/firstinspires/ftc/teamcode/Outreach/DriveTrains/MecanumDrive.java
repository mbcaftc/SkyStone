package org.firstinspires.ftc.teamcode.Outreach.DriveTrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumDrive {

        public DcMotor frontLeftMotor;
        public DcMotor frontRightMotor;
        public DcMotor rearLeftMotor;
        public DcMotor rearRightMotor;

        public LinearOpMode LinearOp = null;    //memorize

        public static final double TICKS_PER_ROTATION = 386.3;  //386.3 is defined from the specs of the motor


        public MecanumDrive (DcMotor fl, DcMotor fr, DcMotor rl, DcMotor rr){

            frontLeftMotor = fl;
            frontRightMotor = fr;
            rearLeftMotor = rl;
            rearRightMotor = rr;

            frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);     //Forward and reverse depends on builder and manufacture
            rearLeftMotor.setDirection(DcMotor.Direction.REVERSE);
            frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
            rearRightMotor.setDirection(DcMotor.Direction.FORWARD);

            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);   //memorize
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // MUST HAVE RUN MODE

            frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        }
//** common class methods below here **


        public void setLinearOp (LinearOpMode LinearOp) {
            this.LinearOp = LinearOp;
        }

        public void setMotorRunModes (DcMotor.RunMode mode) {

            frontLeftMotor.setMode(mode);
            frontRightMotor.setMode(mode);
            rearLeftMotor.setMode(mode);
            rearRightMotor.setMode(mode);


        }
        public void stopMotors () {
            frontLeftMotor.setPower(0);
            frontRightMotor.setPower(0);
            rearLeftMotor.setPower(0);
            rearRightMotor.setPower(0);
        }
        public void driveForward (double speed) {
            frontLeftMotor.setPower(speed);
            frontRightMotor.setPower(speed);
            rearLeftMotor.setPower(speed);
            rearRightMotor.setPower(speed);
        }
        public void driveBackward (double speed) {
            frontLeftMotor.setPower(-speed);
            frontRightMotor.setPower(-speed);
            rearLeftMotor.setPower(-speed);
            rearRightMotor.setPower(-speed);

        }
        public void strafeRight (double speed) {
            frontLeftMotor.setPower(speed);
            frontRightMotor.setPower(-speed);
            rearLeftMotor.setPower(speed);
            rearRightMotor.setPower(-speed);

        }
        public void strafeLeft (double speed) {
            frontLeftMotor.setPower(-speed);
            frontRightMotor.setPower(speed);
            rearLeftMotor.setPower(-speed);
            rearRightMotor.setPower(speed);

        }
        public void rotateLeft (double speed) {
            frontLeftMotor.setPower(-speed);
            frontRightMotor.setPower(speed);
            rearLeftMotor.setPower(speed);
            rearRightMotor.setPower(-speed);

        }
        public void rotateRight (double speed) {
            frontLeftMotor.setPower(speed);
            frontRightMotor.setPower(-speed);
            rearLeftMotor.setPower(-speed);
            rearRightMotor.setPower(speed);
        }

        public void driveForward( double speed, double rotations) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks) {
                driveForward(speed);
            }
            stopMotors();
        }

        public void driveBackward ( double speed, double rotations){
            double ticks = rotations * (-1) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks) {
                driveBackward(speed);
            }
            stopMotors();

        }

        public void strafeLeft ( double speed, double rotations){
            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks) {
                strafeLeft(speed);
            }
            stopMotors();
        }
        public void strafeRight ( double speed, double rotations){
            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks) {
                strafeRight(speed);
            }
            stopMotors();
        }



    }

