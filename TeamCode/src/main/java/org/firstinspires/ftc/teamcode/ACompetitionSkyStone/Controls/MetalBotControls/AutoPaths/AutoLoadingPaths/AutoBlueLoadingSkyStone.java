package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;

@Autonomous(name = "Blue:Loading:SkyStone:Inner")
@Disabled
public class AutoBlueLoadingSkyStone extends AutoLoading {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap, "Auto");
        Bot.setLinearOp(this);
        Bot.HookRelease();
        Bot.autoRaiseStone();
        Bot.grabStone();
        //Bot.setServos();

        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {


            Bot.activateTracking();

            manipulateIntake(Bot,"flip down");
            sleep(200);
            Bot.intakePushNeutral();

            Bot.driveForward(lowSpeed, 1.8);

            Bot.detectSkyStone();

            Bot.deActivateTracking();

            driveToSkyStone(Bot, "Blue");

            manipulateIntake(Bot,"inward");

            Bot.driveForward(midSpeed, 1.4);

            sleep(1000);
            manipulateIntake(Bot, "stop");

            removeSkyStoneInner(Bot);
            sleep(sleepTime);

            Bot.intakePushIn();
            sleep(100);

            manipulateIntake(Bot, "flip_up");

            driveToPlate("Blue", Bot);
            sleep(sleepTime);

            idle();
            requestOpModeStop();
        }
        idle();

    }
}
