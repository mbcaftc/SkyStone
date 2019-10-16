package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

public abstract class AutoMain extends LinearOpMode {
    public final long  sleepTime = 1000;
    public final double maxSpeed = 1;
    public final double highSpeed = .6;
    public final double midSpeed = .5;
    public final double lowSpeed = .3;
    public LinearOpMode linearOp = null;


    public void setLinearOp(LinearOpMode Op) {

        linearOp = Op;
    }


    public void grabSkyStone( AckerBot Bot) {
        Bot.StoneGrab();
    }


    //  detecting the skystone
    public void vuforiaStone( WoodBot Bot, VuforiaWebcam Cam) {

        Cam.trackObjects();
        sleep(1000);

        telemetry.addData("Target Y:", Cam.targetY);
        telemetry.update();

        if (Cam.targetY > 1 && Cam.targetVisible) {             //position 3
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
            Bot.driveForward(midSpeed, 1);                                  // if servos are on left side... driveBackwards
            Bot.strafeLeft(highSpeed, 4);                                  // if servos are on the left side... strafeLeft

            telemetry.addLine(" target is on the far left... position 1");
            telemetry.update();

        }
    }


    //outer path - removing skystone
    public void removeSkyStoneOuterPath(WoodBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeRight(midSpeed, 3.5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeLeft(midSpeed, 3.5);
        }
        sleep(sleepTime);
    }

    // inner plath
    public void removeSkyStoneInnerPath (WoodBot Bot,String Alliance) {

        if (Alliance == "Red") {
            Bot.strafeLeft(midSpeed, .5);
        }
        else if (Alliance == "Blue") {
            Bot.strafeRight(midSpeed, .5);
        }
        sleep(sleepTime);


    }

    public void dropSkyStone(WoodBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.driveForward(highSpeed, 6.5);

        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(highSpeed, 6.5);
        }

        sleep(sleepTime);
        Bot.dropStone(.9);
    }

    public void RotateTowardPlate (WoodBot Bot, String Alliance) {

        if (Alliance == "Red") {
            Bot.rotateLeft(highSpeed, 2.5);
            Bot.gyroCorrection(lowSpeed, 90);       // not sure abt angles

        }
        else if (Alliance == "Blue") {
            Bot.rotateRight(highSpeed, 2.5);
            Bot.gyroCorrection(lowSpeed, 90);       // not sure abt angles
        }

    }

    public void alignBuildPlateOuter (WoodBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.driveForward(highSpeed, 1.2);
        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(highSpeed, 1.2);
        }

    }
    public void alignBuildPlateInner (WoodBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.driveForward(highSpeed, 2.5);
        }
        else if (Alliance == "Blue") {
            Bot.driveBackward(highSpeed, 2.5);
        }
    }

    public void orientBuildPlate (WoodBot Bot, String Alliance) {
        if (Alliance == "Red") {
            Bot.rotateRight(highSpeed, 2);
        }
        else if (Alliance == "Blue") {
            Bot.rotateLeft(highSpeed, 2);
            Bot.gyroCorrection(lowSpeed, 138);
        }
    }

    public void pushBuildPlate (WoodBot Bot, String Alliance) {
        Bot.driveForward(midSpeed, 2.5);

        Bot.HookRelease(0,0 );
    }













}
