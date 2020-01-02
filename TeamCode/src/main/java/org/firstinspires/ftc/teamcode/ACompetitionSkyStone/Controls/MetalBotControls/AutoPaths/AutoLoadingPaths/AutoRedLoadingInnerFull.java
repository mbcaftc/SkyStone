package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Red:Loading:Inner:Full")
@Disabled
public class AutoRedLoadingInnerFull extends AutoLoading {

    public MetalBot Bot = new MetalBot();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
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

            driveToSkyStone(Bot, "Red");
            sleep(sleepTime);
            // intake down

            manipulateIntake(Bot,"flip down");
            sleep(sleepTime);

            Bot.driveForward(midSpeed, .3);
            sleep(sleepTime);

            manipulateIntake(Bot,"inward");
            sleep(sleepTime);

            removeSkyStoneInner(Bot);
            sleep(sleepTime);

            driveToPlate("Red", Bot);
            sleep(sleepTime);

            dropSkyStone(Bot, "Red");
            sleep(sleepTime);

            alignGrabPlate(Bot, "Red");
            sleep(sleepTime);

            orientBuildPlate(Bot, "Red");
            sleep(sleepTime);

            parkInner(Bot, "Red");
            sleep(sleepTime);



            requestOpModeStop();
        }
        idle();

    }
}
