package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous.AutoMainRed;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@Autonomous(name = "Wood Auto Loading Red")
public class WoodLoadingCenterRedAutoRed extends AutoMainRed {


    public AckerBot Bot = new AckerBot();
    public VuforiaWebcam Cam = new VuforiaWebcam();



    final long sleepTime = 100;
    final double maxSpeed = 1;
    final double highSpeed = .6;
    final double midSpeed = .5;
    final double lowSpeed = .3;



    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        Cam.initCamera(hardwareMap);
        Cam.activateTracking();

        waitForStart();

        while (opModeIsActive()) {

            vuforiaStone(Bot, Cam);
            grabSkyStone(Bot);
            alignBuildPlate (Bot);
            placeBuildPlate (Bot);
            releaseBuildPlate (Bot);

        }

    }
}
