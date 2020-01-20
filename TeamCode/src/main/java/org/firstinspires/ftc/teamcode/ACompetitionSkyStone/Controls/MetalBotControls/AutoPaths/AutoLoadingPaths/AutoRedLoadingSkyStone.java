package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoPaths.AutoLoadingPaths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.MetalBotControls.AutoLoading;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.WoodBotControls.AutoLoadingWood;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Red:Loading:SkyStone:Inner")
public class AutoRedLoadingSkyStone extends AutoLoading {

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

            Bot.dropStone();
            Bot.releaseStone();
            //Bot.setServos();

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

            Bot.driveGyroStrafe(1000,.6,"right");

            dropSkyStonePostPlate(Bot, "Red");

            parkSkyStoneInner(Bot);


            requestOpModeStop();
        }
        idle();

    }
}
