package org.firstinspires.ftc.teamcode.MrAcker.base.Robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MrAcker.base.Drivetrains.Tank2WDDrive;

public class LearningBoard extends Tank2WDDrive {

    // LearningBoard Instance Variables

    public HardwareMap hwBot;
    public Servo labServo1 = null;
    public Servo labServo2 = null;
    public DcMotor linearMotor = null;
    public DcMotor labMotor2 = null;



    //LearningBoard Constructor

    public LearningBoard() {

    }

    //FTC SDK Requirement
    public void setLinearOp(LinearOpMode Op) {
        linearOp = Op;
    }


    public void init(HardwareMap hwMap) {

        hwBot = hwMap;

        // Define & Initialize Motors for Robot

        rearLeftMotor = hwBot.dcMotor.get("motor1");
        rearRightMotor = hwBot.dcMotor.get("motor2");


        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);

        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Define & Initialize Servos

        labServo1 = hwBot.get(Servo.class, "left_servo1");
        labServo1.setDirection(Servo.Direction.FORWARD);

        labServo2 = hwBot.get(Servo.class, "right_servo2");
        labServo2.setDirection(Servo.Direction.FORWARD);

        ServoInit();

        // Define & Initialize Non-DriveTrain Motors

        linearMotor = hwBot.dcMotor.get("linear_motor");
        linearMotor.setDirection(DcMotor.Direction.FORWARD);
        linearMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void ServoInit () {

        labServo1.setPosition(.31);
        labServo2.setPosition(.31);
    }

    public void linearExtend(double power) {
        linearMotor.setPower(power);
    }

    public void linearRetract(double power) {
        linearMotor.setPower(-power);
    }

    public void ServoDance () {

        labServo1.setPosition(.1);
        labServo2.setPosition(.1);

        labServo1.setPosition(.9);
        labServo2.setPosition(.9);

        labServo1.setPosition(.5);
        labServo2.setPosition(.5);

    }
}
