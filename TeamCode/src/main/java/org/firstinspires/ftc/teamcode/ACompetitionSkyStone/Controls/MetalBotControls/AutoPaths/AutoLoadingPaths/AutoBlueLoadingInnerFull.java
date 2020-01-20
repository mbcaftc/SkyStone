package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Blue:Loading:Inner:Full")
@Disabled
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

            Bot.driveForward(.2, 1.8);
            sleep(sleepTime);

            Bot.detectSkyStone();
            sleep(sleepTime);

            Bot.deActivateTracking();
            sleep(sleepTime);

            driveToSkyStone(Bot, "Blue");
            sleep(sleepTime);
            // intake down

            manipulateIntake(Bot,"flip down");
            sleep(1000);

            Bot.driveForward(midSpeed, .3);
            sleep(sleepTime);

            manipulateIntake(Bot,"inward");
            sleep(sleepTime);

            removeSkyStoneInner(Bot);
            sleep(sleepTime);



            driveToPlate("Blue", Bot);
            sleep(sleepTime);

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
