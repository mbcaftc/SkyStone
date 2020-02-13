package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

@Autonomous(name = "Blue:Loading:Inner:Full")
//@Disabled
public class AutoBlueLoadingInnerFullState extends AutoLoading {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap, "Auto");
        Bot.setLinearOp(this);

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {


            Bot.activateTracking();

            manipulateIntake(Bot,"flip down");
            sleep(200);
            Bot.intakePushNeutral();

            Bot.driveForward(lowSpeed, 1.7);

            Bot.detectSkyStone();
            sleep(1000);

            Bot.deActivateTracking();

            driveToSkyStone(Bot, "Blue");


            manipulateIntake(Bot,"inward");


            Bot.driveForward(midSpeed, 1.5);

            sleep(1000);
            manipulateIntake(Bot, "stop");


            Bot.intakePushIn();
            sleep(100);

            removeSkyStoneInner(Bot);
            sleep(sleepTime);

//            Bot.intakePushIn();
//            sleep(100);

            manipulateIntake(Bot, "flip_up");

            Bot.intakePushIn();
            sleep(100);


            rotateToDriveDropStone(Bot, "Blue");
            sleep(sleepTime);

            driveToPlate("Blue", Bot);
            sleep(sleepTime);

            Bot.intakePushIn();
            sleep(100);

            dropSkyStone(Bot, "Blue");
            sleep(sleepTime);

            // align grab plate and sleep(sleeptime)


            //hook grab here

//            dropSkyStone2(Bot, "Blue");
//            sleep(sleepTime);

//            dropSkyStone(Bot, "Blue");
 //           sleep(sleepTime);

            //align build plate was here

            orientBuildPlate(Bot, "Blue"); //updated for 2.5
            sleep(sleepTime);


            parkInner(Bot, "Blue"); //changed
            sleep(sleepTime);

            idle();
            requestOpModeStop();
        }
        idle();

    }
}
