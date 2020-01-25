package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Red:Loading:Inner:Full USE ME")
//@Disabled
public class AutoRedLoadingInnerFull extends AutoLoading {

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

            Bot.driveForward(lowSpeed, 1.8);

            Bot.detectSkyStone();
            sleep(500);     // added after testing on 1/23/20

            Bot.deActivateTracking();

            driveToSkyStone(Bot, "Red");


            manipulateIntake(Bot,"inward");


            Bot.driveForward(midSpeed, 1.4);

            sleep(1000);
            manipulateIntake(Bot, "stop");

            Bot.intakePushIn();
            sleep(100);


            removeSkyStoneInner(Bot);
            sleep(sleepTime);


            manipulateIntake(Bot, "flip_up");

            rotateToDriveDropStone(Bot, "Red");
            sleep(sleepTime);

            driveToPlate("Red", Bot);
            sleep(sleepTime);

            Bot.intakePushIn();
            sleep(100);

            Bot.HookRelease();

            dropSkyStone(Bot, "Red");
            sleep(sleepTime);

            alignGrabPlate(Bot, "Red");
            sleep(sleepTime);

            orientBuildPlate(Bot, "Red");
            sleep(sleepTime);

            parkInner(Bot, "Red");
            sleep(sleepTime);

//hello
            requestOpModeStop();
            idle();
        }
        idle();

    }
}
