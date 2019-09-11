package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


/**
 * Created by john on 11/18/15.
 * 15. Creating Reusable Classes
 */
public class RobotDrive {

    public double leftMotorValue;
    public double rightMotorValue;

    private DcMotor leftFrontMotor;
    private DcMotor rightFrontMotor;
    private DcMotor leftRearMotor;
    private DcMotor rightRearMotor;

    public RobotDrive (DcMotor lfm, DcMotor rfm, DcMotor lrm, DcMotor rrm) {
        leftFrontMotor = lfm;
        rightFrontMotor = rfm;
        leftRearMotor = lrm;
        rightRearMotor = rrm;

        rightRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void arcadeDrive (Gamepad gamepad) {
        arcadeDrive(-gamepad.left_stick_y, gamepad.left_stick_x);
    }


    public void arcadeDrive (double forwardSpeed, double turnRate) {
        leftMotorValue = forwardSpeed + turnRate;
        rightMotorValue = forwardSpeed - turnRate;
        leftMotorValue = Range.clip(leftMotorValue, -1, 1);
        rightMotorValue = Range.clip(rightMotorValue, -1, 1);
        leftFrontMotor.setPower(leftMotorValue);
        leftRearMotor.setPower(leftMotorValue);
        rightFrontMotor.setPower(rightMotorValue);
        rightRearMotor.setPower(rightMotorValue);
    }

    public void tankDrive (Gamepad gamepad) {
        tankDrive(-gamepad.left_stick_y, -gamepad.right_stick_y, true);
    }

    public void tankDrive (double leftValue, double rightValue) {
        //added code to make sure setPower values do not exceed [-1,1]
        leftMotorValue = Range.clip(leftValue, -1, 1);
        rightMotorValue = Range.clip(rightValue, -1, 1);

        leftFrontMotor.setPower(leftMotorValue);
        leftRearMotor.setPower(leftMotorValue);
        rightFrontMotor.setPower(rightMotorValue);
        rightRearMotor.setPower(rightMotorValue);
    }

    public void tankDrive (double leftValue, double rightValue, boolean squareInputs) {
        //if squareInputs is true, square each of the inputs, preserving the sign
        double leftSquaredValue = leftValue * leftValue;
        double rightSquaredValue = rightValue * rightValue;

        if (squareInputs) {
            if (leftValue >= 0.0) {
                leftSquaredValue = (leftValue*leftValue);
            } else {
                leftSquaredValue = -(leftValue*leftValue);
            }
            if (rightValue >= 0.0) {
                rightSquaredValue = (rightValue*rightValue);
            } else {
                rightSquaredValue = -(rightValue*rightValue);
            }
        }
        //apply the values using the other tankDrive method
        tankDrive(leftSquaredValue, rightSquaredValue);
    }
}
