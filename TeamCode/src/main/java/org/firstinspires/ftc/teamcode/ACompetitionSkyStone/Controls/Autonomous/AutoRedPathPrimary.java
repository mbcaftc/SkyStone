package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.AckerBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@Autonomous(name = "Auto:Red Path:Primary")
public class AutoRedPathPrimary extends AutoMainRed {

    public AckerBot Bot = new AckerBot();
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

            vuforiaStone(Bot, Cam);
            grabSkyStone(Bot);
            //alignBuildPlate (Bot);
            //placeBuildPlate (Bot);
            //releaseBuildPlate (Bot);

            requestOpModeStop();

        }

    }
}
