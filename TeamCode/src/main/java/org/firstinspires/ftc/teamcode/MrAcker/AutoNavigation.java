package org.firstinspires.ftc.teamcode.MrAcker;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;


public abstract class AutoNavigation extends LinearOpMode {

    public LinearOpMode linearOp = null;

    public final long  sleepTime = 1000;
    public final double maxSpeed = 1;
    public final double highSpeed = .6;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;


    public AutoNavigation() {

    }

    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }

    public void driveTest (AckerBot Bot) {

        Bot.driveForward(highSpeed, 3.4);
        sleep(sleepTime);

        Bot.driveBackward(highSpeed, 0.5);              // drive backward with stone
        sleep(sleepTime);
    }

    public void sampleSkyStone (AckerBot Bot, VuforiaWebcam Cam) {

        Bot.driveForward(highSpeed, 3.4);
        sleep(sleepTime);

        Bot.driveBackward(highSpeed, 0.5);              // drive backward with stone
        sleep(sleepTime);
    }

}
