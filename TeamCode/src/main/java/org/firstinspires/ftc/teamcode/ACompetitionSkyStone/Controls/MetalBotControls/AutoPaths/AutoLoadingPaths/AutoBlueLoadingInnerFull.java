package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Blue:Loading:Inner:Full")
//@Disabled
public class AutoBlueLoadingInnerFull extends AutoLoading {

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


            removeSkyStoneInner(Bot);
            sleep(sleepTime);

            Bot.intakePushIn();
            sleep(100);

            manipulateIntake(Bot, "flip_up");


            rotateToDriveDropStone(Bot, "Blue");
            sleep(sleepTime);

            driveToPlate("Blue", Bot);
            sleep(sleepTime);

            Bot.intakePushIn();
            sleep(100);

            Bot.HookHalfGrab();

            dropSkyStone(Bot, "Blue");
            sleep(sleepTime);

            alignGrabPlate(Bot, "Blue");
            sleep(sleepTime);

            orientBuildPlate(Bot, "Blue");
            sleep(sleepTime);

            parkInner(Bot, "Blue");
            sleep(sleepTime);


            requestOpModeStop();
        }
        idle();

    }
}
