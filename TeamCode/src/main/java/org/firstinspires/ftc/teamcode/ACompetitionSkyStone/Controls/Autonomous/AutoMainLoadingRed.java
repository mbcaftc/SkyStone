package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

public abstract class AutoMainLoadingRed extends AutoMainLoading {



// Loading Center Red Auto
    public void vuforiaStone( AckerBot Bot, VuforiaWebcam Cam) {

        Cam.trackObjects();
        sleep(1000);

        telemetry.addData("Target Y:", Cam.targetY);
        telemetry.update();

        if (Cam.targetY > 1 && Cam.targetVisible) {             //position 3
            Bot.driveBackward(midSpeed, 1);                                 // if servos are on left side... drive forward
            Bot.strafeRight(highSpeed, 4);                                  // if servos are on left side... strafeLeft
            sleep(sleepTime);

            telemetry.addLine("targetY > 1... position 3");

        }
        else if (Cam.targetY < 1 && Cam.targetVisible) {        //position 2
            Bot.strafeRight(midSpeed, 4);                                   // if servos are on the left side... strafeLeft
            sleep(sleepTime);

            telemetry.addLine(" targetY < 1 ... position 2");
            telemetry.update();

        }
        else {                                                  // position 1
            Bot.driveForward(midSpeed, 1);                                  // if servos are on left side... driveBackwards
            Bot.strafeRight(highSpeed, 4);                                  // if servos are on the left side... strafeLeft

            telemetry.addLine(" target is on the far left... position 1");
            telemetry.update();

        }
    }



    public void dropSkyStone(AckerBot Bot) {
        Bot.strafeRight(midSpeed, .5);
        sleep(sleepTime);

        Bot.driveForward(highSpeed, 6.8);
        sleep(sleepTime);

        Bot.StoneRelease();
    }

    public void alignBuildPlate (AckerBot Bot) {
        Bot.rotateRight(highSpeed, 2.5);
        sleep(sleepTime);

        Bot.gyroCorrection(lowSpeed, 90);
        Bot.stopMotors();
        sleep(sleepTime);

        Bot.driveBackward(highSpeed,3.4);
        sleep(sleepTime);
    }

    public void placeBuildPlate ( AckerBot Bot) {
        Bot.HookRelease(.1, .1);
        sleep(sleepTime);

        Bot.strafeLeft(highSpeed, 1.2 );
        sleep(sleepTime);

        Bot.HookGrab(.9,.9);
        sleep(sleepTime);

        Bot.rotateRight(midSpeed, 2);
        // ********** Need GYRO Correction***********

        Bot.strafeLeft(highSpeed, 3.5);
        Bot.HookRelease(.1,.1);




    }

    public void releaseBuildPlate (AckerBot Bot) {
        Bot.strafeRight(highSpeed, 1);

        Bot.rotateLeft(highSpeed, 2);
    }

    public void park() {
        //rev color sensor
    }


}
