package org.firstinspires.ftc.teamcode.MrDuVal.MecanumDrive;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.MrDuVal.Controls.TeleOp.EncoderBotTeleOp;


public class MecanumDriveEncoder {


    // Instance Variables & Constants

    public DcMotor frontLeftMotor = null;
    public DcMotor frontRightMotor = null;
    public DcMotor rearRightMotor = null;
    public DcMotor rearLeftMotor = null;
    public static final double TICKS_PER_ROTATION = 386.3;   // GoBilda Motor TICKS

    public final double minStraightSpeed = .2 , minStrafeSpeed = .2, minTurnSpeed = .1;
    public final double maxStraightSpeed = .6, maxStrafeSpeed = .6, maxTurnSpeed = .6;
    public double moveSpeed = 0.5;
//    public double PIDcoefficient = 0;

    public LinearOpMode linearOp = null;


    public void setLinearOp(LinearOpMode linearOp) {

        this.linearOp = linearOp;
    }


    public MecanumDriveEncoder() {

    }


    public void stopMotors() {
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

    }


    public void setMotorRunModes (DcMotor.RunMode mode) {

        frontLeftMotor.setMode(mode);
        frontRightMotor.setMode(mode);
        rearLeftMotor.setMode(mode);
        rearRightMotor.setMode(mode);

    }

    // Sets speed for all motors with one method
    public void setMotorSpeeds (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
    }


    // Powers Motors with no encoder counts

    public void rotateRight (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);
    }

    public void rotateLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);
    }

    public void strafeLeft (double speed) {
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(-speed);
    }

    public void strafeRight (double speed) {
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(speed);
    }

    public void driveForward (double speed){
        frontLeftMotor.setPower(speed);
        frontRightMotor.setPower(speed);
        rearLeftMotor.setPower(speed);
        rearRightMotor.setPower(speed);
    }

    public void driveBackward (double speed){
        frontLeftMotor.setPower(-speed);
        frontRightMotor.setPower(-speed);
        rearLeftMotor.setPower(-speed);
        rearRightMotor.setPower(-speed);
    }



    // Powers Motors with Encoder Counts

    public void driveForward( double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = rotations * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
                driveForward(speed);
            }
            stopMotors();
        }

    }


    public void driveBackward ( double speed, double rotations){

        if (linearOp.opModeIsActive()) {
            double ticks = rotations * (-1) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                driveBackward(speed);
            }
            stopMotors();
        }
    }


    public void rotateLeft (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                rotateLeft(speed);
            }
            stopMotors();
        }
    }

    public void rotateRight (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing right moves encoder towards -infinity
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive() ) {
                rotateRight(speed);
            }
            stopMotors();
        }
    }


    public void strafeRight (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() < ticks && linearOp.opModeIsActive()) {
                strafeRight(speed);
            }
            stopMotors();
        }
    }

    public void strafeLeft (double speed, double rotations) {

        if (linearOp.opModeIsActive()) {

            double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION;
            setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            while (frontLeftMotor.getCurrentPosition() > ticks && linearOp.opModeIsActive()) {
                strafeLeft(speed);
            }
            stopMotors();
        }
    }


    //****
    //
    // Overloaded Methods for Powering Motors with Encoder Counts for TeleOp OpMode... TeleOp
    //
    // ***


    public void driveForward( double speed, double rotations, String Mode) {


        double ticks = rotations * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks) {
            driveForward(speed);
        }
        stopMotors();

    }


    public void driveBackward ( double speed, double rotations, String Mode){

        double ticks = rotations * (-1) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() > ticks) {
            driveBackward(speed);
        }
        stopMotors();

    }


    public void rotateLeft (double speed, double rotations, String Mode) {

        double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION; //strafing left moves encoder towards positive infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() > ticks) {
            rotateLeft(speed);
        }
        stopMotors();

    }

    public void rotateRight (double speed, double rotations, String Mode) {


        double ticks = Math.abs(rotations) * TICKS_PER_ROTATION; //strafing right moves encoder towards -infinity
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks ) {
            rotateRight(speed);
        }
        stopMotors();
    }



    public void strafeRight (double speed, double rotations, String Mode) {

        double ticks = Math.abs(rotations) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() < ticks ) {
            strafeRight(speed);
        }
        stopMotors();

    }

    public void strafeLeft (double speed, double rotations, String Mode) {

        double ticks = Math.abs(rotations) * (-1) * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (frontLeftMotor.getCurrentPosition() > ticks ) {
            strafeLeft(speed);
        }
        stopMotors();
    }





    public void drive (double targetEncoders, String direction, String PIDmode) {
        int i = 0;
        double PIDcoefficient = 0;
        double currentEncoders = 0;
        double ticks = targetEncoders * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Updating to using Variable currentEncoders so we don't care about - / + of encoder counts.
//        while (frontLeftMotor.getCurrentPosition() < targetEncoders && linearOp.opModeIsActive()) {
        while (currentEncoders < targetEncoders && linearOp.opModeIsActive()) {
            //Not sure if this PID equation works - DuVal
            //            PIDcoefficient = (((ticks/2)-(ticks/2) - (frontLeftMotor.getCurrentPosition()/2)));
            // Commented out the ticks for now - multiplying by "TICKS_PER_ROTATION" made testing not work. -DuVal
            //            PIDcoefficient = (frontLeftMotor.getCurrentPosition() / (ticks / 2))*maxStraightSpeed;
            currentEncoders = Math.abs(frontLeftMotor.getCurrentPosition());
            if (PIDmode.equals("PID")) {
//                linearOp.telemetry.addData("confirming PID calc : ", i+=1);
                PIDcoefficient = PIDcalculator(direction, targetEncoders, currentEncoders);
            }
//            DRIVE!
            // For if PIDCalculator was a 'void' function, but it's now a 'return' function.  Kept for potential troubleshooting.
            //            PIDcalculator("straightEncoder", targetEncoders);
            if (PIDmode.equals("PID")) {
                linearOp.telemetry.addData("PID Coefficient: ", PIDcoefficient);
            }
            else {
                linearOp.telemetry.addData("RAW Speed: ", moveSpeed);
            }
            linearOp.telemetry.addData("Current Position (raw): ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Current Encoders (variable)", currentEncoders);
            linearOp.telemetry.addData("Target Position:", targetEncoders);
            linearOp.telemetry.addData("Counter for debugging: ", i+=1);
            linearOp.telemetry.update();
            switch (direction){
                case "forward":
                    linearOp.telemetry.addLine("we're in FORWARD the switch!");
                    if (PIDmode.equals("PID")) {
                        linearOp.telemetry.addLine("do PID things! ");
                        driveForward(PIDcoefficient);
                    }
                    else {
                        driveForward(moveSpeed);
                    }
                    break;
                case "backward":
                    linearOp.telemetry.addLine("we're in BACKWARD the switch!");
                    if (PIDmode.equals("PID")) {
                        driveBackward(PIDcoefficient);
                    }
                    else {
                        driveBackward(moveSpeed);
                    }
                    break;
                case "left":
                    linearOp.telemetry.addLine("we're in LEFT the switch!");
                    if (PIDmode.equals("PID")) {
                        strafeLeft(PIDcoefficient);
                    }
                    else {
                        strafeLeft(moveSpeed);
                    }
                    break;
                case "right":
                    linearOp.telemetry.addLine("we're in RIGHT the switch!");
                    if (PIDmode.equals("PID")) {
                        strafeRight(PIDcoefficient);
                    }
                    else {
                        strafeRight(moveSpeed);
                    }
                    break;
            }
        }
        stopMotors();
        linearOp.telemetry.addLine("done with drivePID! ");
        linearOp.telemetry.update();
//        linearOp.sleep(2000);
    }



    public double PIDcalculator (String moveType, Double value, Double curEncoders) {
        double PIDcoefficient = 0;
//        if (PIDcoefficient == 0) {
//            return 1;
//
//        }
        //Not sure if this PID equation works - DuVal
        //            PIDcoefficient = (((ticks/2)-(ticks/2) - (frontLeftMotor.getCurrentPosition()/2)));
        // Commented out the ticks for now - multiplying by "TICKS_PER_ROTATION" made testing not work. -DuVal
        //            PIDcoefficient = (frontLeftMotor.getCurrentPosition() / (ticks / 2))*maxStraightSpeed;

        // Updated to use Encoders Variable that adds absolute value of encoders - allows us to not care about + / -
//        PIDcoefficient = (frontLeftMotor.getCurrentPosition() / (value / 2));
        PIDcoefficient = (curEncoders / (value / 2));

//            If get past half of distance, need to get "distance to"
        if (PIDcoefficient > 1) {
            PIDcoefficient = 2-PIDcoefficient;
        }
        // will reduce max speed from 1.0 to a factor of maxStraightSpeed
        PIDcoefficient *= maxStraightSpeed;
//            makes sure motors don't stall out by going below minStraightSpeed
        if ((moveType.equals("forward") || moveType.equals("backward")) && PIDcoefficient <= minStraightSpeed) {
            linearOp.telemetry.addLine("A??");
            PIDcoefficient = minStraightSpeed;
        }
        if ((moveType.equals("left") || moveType.equals("right")) && PIDcoefficient <= minStrafeSpeed) {
            linearOp.telemetry.addLine("B??....");
            PIDcoefficient = minStrafeSpeed;
        }
        linearOp.telemetry.addData("PID inside calc function: ", PIDcoefficient);
//        linearOp.telemetry.update();
//        linearOp.sleep(200);
        return PIDcoefficient;
    }



    /*

    LEGACY CODE

     */

    public void driveNoPID (double targetEncoders, String direction) {
        double currentEncoders = 0;
        double ticks = targetEncoders * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Updating to using Variable currentEncoders so we don't care about - / + of encoder counts.
//        while (frontLeftMotor.getCurrentPosition() < targetEncoders && linearOp.opModeIsActive()) {
        while (currentEncoders < targetEncoders && linearOp.opModeIsActive()) {
            //Not sure if this PID equation works - DuVal
            //            PIDcoefficient = (((ticks/2)-(ticks/2) - (frontLeftMotor.getCurrentPosition()/2)));
            // Commented out the ticks for now - multiplying by "TICKS_PER_ROTATION" made testing not work. -DuVal
            //            PIDcoefficient = (frontLeftMotor.getCurrentPosition() / (ticks / 2))*maxStraightSpeed;
            currentEncoders = Math.abs(frontLeftMotor.getCurrentPosition());
//            DRIVE!
            // For if PIDCalculator was a 'void' function, but it's now a 'return' function.  Kept for potential troubleshooting.
            //            PIDcalculator("straightEncoder", targetEncoders);
            linearOp.telemetry.addData("Current Position (raw): ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Current Encoders (variable)", currentEncoders);
            linearOp.telemetry.addData("Target Position:", targetEncoders);
            linearOp.telemetry.update();
            switch (direction){
                case "forward":
                    linearOp.telemetry.addLine("we're in FORWARD the switch!");
                    driveForward(moveSpeed);
                    break;
                case "backward":
                    linearOp.telemetry.addLine("we're in BACKWARD the switch!");
                    driveBackward(moveSpeed);
                    break;
                case "left":
                    linearOp.telemetry.addLine("we're in LEFT the switch!");
                    strafeLeft(moveSpeed);
                    break;
                case "right":
                    linearOp.telemetry.addLine("we're in RIGHT the switch!");
                    strafeRight(moveSpeed);
                    break;
            }
        }
        stopMotors();
        linearOp.telemetry.addLine("done with drivePID! ");
        linearOp.telemetry.update();
//        linearOp.sleep(2000);
    }

    public void drivePID (double targetEncoders, String direction) {
        double PIDcoefficient = 0;
        double currentEncoders = 0;
        double ticks = targetEncoders * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Updating to using Variable currentEncoders so we don't care about - / + of encoder counts.
//        while (frontLeftMotor.getCurrentPosition() < targetEncoders && linearOp.opModeIsActive()) {
        while (currentEncoders < targetEncoders && linearOp.opModeIsActive()) {
            //Not sure if this PID equation works - DuVal
            //            PIDcoefficient = (((ticks/2)-(ticks/2) - (frontLeftMotor.getCurrentPosition()/2)));
            // Commented out the ticks for now - multiplying by "TICKS_PER_ROTATION" made testing not work. -DuVal
            //            PIDcoefficient = (frontLeftMotor.getCurrentPosition() / (ticks / 2))*maxStraightSpeed;
            currentEncoders = Math.abs(frontLeftMotor.getCurrentPosition());
            PIDcoefficient = PIDcalculator("straightEncoder", targetEncoders, currentEncoders);
//            DRIVE!
            // For if PIDCalculator was a 'void' function, but it's now a 'return' function.  Kept for potential troubleshooting.
            //            PIDcalculator("straightEncoder", targetEncoders);
            linearOp.telemetry.addData("PID Coefficient: ", PIDcoefficient);
            linearOp.telemetry.addData("Current Position (raw): ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Current Encoders (variable)", currentEncoders);
            linearOp.telemetry.addData("Target Position:", targetEncoders);
            linearOp.telemetry.update();
            switch (direction){
                case "forward":
                    linearOp.telemetry.addLine("we're in FORWARD the switch!");
                    driveForward(PIDcoefficient);
                    break;
                case "backward":
                    linearOp.telemetry.addLine("we're in BACKWARD the switch!");
                    driveBackward(PIDcoefficient);
                    break;
                case "left":
                    linearOp.telemetry.addLine("we're in LEFT the switch!");
                    strafeLeft(PIDcoefficient);
                    break;
                case "right":
                    linearOp.telemetry.addLine("we're in RIGHT the switch!");
                    strafeRight(PIDcoefficient);
                    break;
            }
        }
        stopMotors();
        linearOp.telemetry.addLine("done with drivePID! ");
        linearOp.telemetry.update();
//        linearOp.sleep(2000);
    }

    public void driveForwardPID (double targetEncoders) {
        double PIDcoefficient = 0;
        double ticks = targetEncoders * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (frontLeftMotor.getCurrentPosition() < targetEncoders && linearOp.opModeIsActive()) {
            //Not sure if this PID equation works - DuVal
            //            PIDcoefficient = (((ticks/2)-(ticks/2) - (frontLeftMotor.getCurrentPosition()/2)));
            // Commented out the ticks for now - multiplying by "TICKS_PER_ROTATION" made testing not work. -DuVal
            //            PIDcoefficient = (frontLeftMotor.getCurrentPosition() / (ticks / 2))*maxStraightSpeed;
//            PIDcoefficient = PIDcalculator("straightEncoder", targetEncoders);
//            DRIVE!
            linearOp.telemetry.addData("PID Coefficient: ", PIDcoefficient);
            linearOp.telemetry.addData("Current Position: ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Target Position:", targetEncoders);
            linearOp.telemetry.update();
//            driveForward(PIDcoefficient);
            driveForward(1);
        }

        stopMotors();
        linearOp.telemetry.addLine("done driving forward!");
        linearOp.telemetry.update();
        linearOp.sleep(2000);
    }

    public void driveBackwardPID (double targetEncoders) {
        double PIDcoefficient = 0;
        double ticks = targetEncoders * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (frontLeftMotor.getCurrentPosition() < targetEncoders && linearOp.opModeIsActive()) {
            //Not sure if this PID equation works - DuVal
            //            PIDcoefficient = (((ticks/2)-(ticks/2) - (frontLeftMotor.getCurrentPosition()/2)));
            // Commented out the ticks for now - multiplying by "TICKS_PER_ROTATION" made testing not work. -DuVal
            //            PIDcoefficient = (frontLeftMotor.getCurrentPosition() / (ticks / 2))*maxStraightSpeed;
//            PIDcoefficient = PIDcalculator("straightEncoder", targetEncoders);
//            DRIVE!
            linearOp.telemetry.addData("PID Coefficient: ", PIDcoefficient);
            linearOp.telemetry.addData("Current Position: ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Target Position:", targetEncoders);
            linearOp.telemetry.update();
            driveBackward(PIDcoefficient);
        }
        stopMotors();
    }

    public void strafeLeftPID (double targetEncoders) {
        double PIDcoefficient = 0;
        double ticks = targetEncoders * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (frontLeftMotor.getCurrentPosition() < targetEncoders && linearOp.opModeIsActive()) {
            //Not sure if this PID equation works - DuVal
            //            PIDcoefficient = (((ticks/2)-(ticks/2) - (frontLeftMotor.getCurrentPosition()/2)));
            // Commented out the ticks for now - multiplying by "TICKS_PER_ROTATION" made testing not work. -DuVal
            //            PIDcoefficient = (frontLeftMotor.getCurrentPosition() / (ticks / 2))*maxStraightSpeed;
//            PIDcoefficient = PIDcalculator("straightEncoder", targetEncoders);
//            DRIVE!
            linearOp.telemetry.addData("PID Coefficient: ", PIDcoefficient);
            linearOp.telemetry.addData("Current Position: ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Target Position:", targetEncoders);
            linearOp.telemetry.update();
            strafeLeft(PIDcoefficient);
        }
        stopMotors();
    }

    public void strafeRightPID (double targetEncoders) {
        double PIDcoefficient = 0;
        double ticks = targetEncoders * TICKS_PER_ROTATION;
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        while (frontLeftMotor.getCurrentPosition() < targetEncoders && linearOp.opModeIsActive()) {
            //Not sure if this PID equation works - DuVal
            //            PIDcoefficient = (((ticks/2)-(ticks/2) - (frontLeftMotor.getCurrentPosition()/2)));
            // Commented out the ticks for now - multiplying by "TICKS_PER_ROTATION" made testing not work. -DuVal
            //            PIDcoefficient = (frontLeftMotor.getCurrentPosition() / (ticks / 2))*maxStraightSpeed;
//            PIDcoefficient = PIDcalculator("straightEncoder", targetEncoders);
//            DRIVE!
            linearOp.telemetry.addData("PID Coefficient: ", PIDcoefficient);
            linearOp.telemetry.addData("Current Position: ", frontLeftMotor.getCurrentPosition());
            linearOp.telemetry.addData("Target Position:", targetEncoders);
            linearOp.telemetry.update();
            strafeRight(PIDcoefficient);
        }
        stopMotors();
    }


}
