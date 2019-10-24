package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

public abstract class AutoMainBuilding extends LinearOpMode {
    public final long  sleepTime = 20;
    public final double maxSpeed = 1;
    public final double highSpeed = .5;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;
    public LinearOpMode linearOp = null;
    public final double gyroSPD = .15;


    public int skystonePos = 2;
    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }


    public void grabSkyStone( AckerBot Bot) {
        Bot.StoneGrab();
    }


    //  detecting the skystone
    public void vuforiaStone(MetalBot Bot, VuforiaWebcam Cam) {

        Cam.trackObjects();
        sleep(1000);

        telemetry.addData("Target Y:", Cam.targetY);
        telemetry.update();

        if (Cam.targetY > 1 && Cam.targetVisible) {             //position 3
            //Bot.rotateRight(highSpeed, 1);
            Bot.driveBackward(midSpeed, 1);                                 // if servos are on left side... drive forward
            Bot.strafeLeft(highSpeed, 4);                                  // if servos are on left side... strafeLeft
            sleep(sleepTime);

            telemetry.addLine("targetY > 1... position 3");

        }
        else if (Cam.targetY < 1 && Cam.targetVisible) {        //position 2

            Bot.strafeLeft(midSpeed, 4);                                   // if servos are on the left side... strafeLeft
            sleep(sleepTime);

            telemetry.addLine(" targetY < 1 ... position 2");
            telemetry.update();

        }
        else {                                                  // position 1
            //Bot.rotateLeft(highSpeed, 1);
            Bot.driveForward(midSpeed, 1);                                  // if servos are on left side... driveBackwards
            Bot.strafeLeft(highSpeed, 4);                                  // if servos are on the left side... strafeLeft

            telemetry.addLine(" target is on the far left... position 1");
            telemetry.update();

        }

        Bot.grabStone();
    }



    public void alignBuildPlate (MetalBot Bot, String Alliance) {
        Bot.strafeLeft(highSpeed, 1.5);

        Bot.HookGrab();
        sleep(1000);

        Bot.strafeRight(1.5);

    }

    //outer path - removing skystone
    public void goToSkystones(MetalBot Bot, String Alliance) {
            

    }

    public void hardCodeVuforia ( MetalBot Bot, String Alliance) {
        if (skystonePos == 1){
            Bot.driveBackward(midSpeed, .6);                                 //  was 7 if servos are on left side... drive forward
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .6 );
            skystonePos = 1;

        }
        else if (skystonePos == 2) {
            Bot.driveForward(lowSpeed, .35);
            Bot.strafeLeft(midSpeed, 1.8);
            sleep(sleepTime);
            Bot.strafeLeft(lowSpeed, .4);
            sleep(sleepTime);
            skystonePos  = 2;

        }
        else {
            Bot.driveForward(midSpeed, .7);
            sleep(sleepTime);
            Bot.strafeLeft(midSpeed, 1.8);
            Bot.strafeLeft(lowSpeed, .4);
            sleep(sleepTime);
            skystonePos = 3;

        }
        Bot.grabStone();
        sleep(1000);
        Bot.stopMotors();
        if (Alliance == "Red") {
            Bot.driveForward(lowSpeed, .4);
        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(lowSpeed, .4);
        }

    }

    // inner plath
    public void grabSkystone (MetalBot Bot,String Alliance) {


    }

    public void dropSkyStone(MetalBot Bot, String Alliance) {


    }






    public void orientBuildPlate (MetalBot Bot, String Alliance) {

    }

    public void pushBuildPlate (MetalBot Bot, String Alliance) {
    }

    public void park (MetalBot Bot, String Alliance) {


    }

}
