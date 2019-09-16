package org.firstinspires.ftc.teamcode.MrAcker.base.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;

public class Lift {

    // instance variables

    public Servo servoLift;

    public double liftExtend = 0.9;
    public double liftMid = 0.5;
    public double liftRetract = 0.10;


    // constructors

    public Lift(Servo servoLift) {
        this.servoLift = servoLift;
    }

    public Lift(Servo servoLift, double extend, double retract) {

        liftExtend = extend;
        liftRetract = retract;
        this.servoLift = servoLift;
    }


    public void LiftExtend () {

        servoLift.setPosition(liftExtend);
    }

    public void LiftMidPoint () {

        servoLift.setPosition(liftMid);
    }

    public void LiftRetract () {

        servoLift.setPosition(liftRetract);
    }



}



