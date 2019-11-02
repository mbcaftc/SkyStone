package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.Controls.Autonomous.MetalBotAuto.AutoMainBuilding;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.MetalBot;
import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.robots.WoodBot;

@Autonomous(name = "Auto:Red Building:Primary: Wood: Color sensor :Test ")
public class AutoRedPrimaryBuildingWood extends AutoMainBuildingWood {

    public WoodBot Bot = new WoodBot();
    //public VuforiaWebcam Cam = new VuforiaWebcam();

    @Override
    public void runOpMode() throws InterruptedException {

        Bot.initRobot(hardwareMap);
        Bot.setLinearOp(this);

        //Cam.initCamera(hardwareMap);
        //Cam.activateTracking();
        setLinearOp(this);



        waitForStart();

        while (opModeIsActive()) {

            alignBuildPlate(Bot, "Red");
            sleep(sleepTime);

            goToSkystones(Bot, "Red");
            sleep(sleepTime);

            detectSkyStone (Bot, "Red");
            sleep(sleepTime);

            manipulateSkyStone(Bot, "grab");
            sleep(sleepTime);

            removeSkyStoneInner(Bot, "Red");



            requestOpModeStop();
        }
        idle();

    }
}
