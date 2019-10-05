package org.firstinspires.ftc.teamcode.MrAcker;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;


public abstract class AutoNavigation extends LinearOpMode {


    final long  sleepTime = 1000;
    final double maxSpeed = 1;
    final double highSpeed = .6;
    final double midSpeed = .5;
    final double lowSpeed = .3;



    public AutoNavigation() {

    }



    public void sampleSkyStone (AckerBot Bot) {

        Bot.driveForward(highSpeed, 3.4);
        sleep(sleepTime);

        Bot.driveBackward(highSpeed, 0.5);              // drive backward with stone
        sleep(sleepTime);
    }


}
