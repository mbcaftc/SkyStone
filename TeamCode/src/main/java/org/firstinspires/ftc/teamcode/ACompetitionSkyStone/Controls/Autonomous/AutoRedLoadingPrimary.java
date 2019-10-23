package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@Autonomous(name = "Auto:Red Loading:Primary")
public class AutoRedLoadingPrimary extends AutoMain {

    public MetalBot Bot = new MetalBot();
    public VuforiaWebcam Cam = new VuforiaWebcam();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        Cam.initCamera(hardwareMap);
        Cam.activateTracking();
        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {
            //Cam.trackObjects();
            //sleep(sleepTime);

            Bot.strafeLeft(midSpeed, 1);

            //vuforiaStone(Bot, Cam);
            hardCodeVuforia(Bot, "Red");

            removeSkyStoneInnerPath(Bot,"Red");

            dropSkyStone(Bot, "Red");

            alignGrabBuildPlateInner(Bot, "Red");

            orientBuildPlate(Bot, "Red");

            pushBuildPlate(Bot, "Red");

            park(Bot, "Red");

            idle();
            requestOpModeStop();
        }
        idle();
    }
}
