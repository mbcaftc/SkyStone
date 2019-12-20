package org.firstinspires.ftc.teamcode.MrDuVal;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems.VuforiaWebcam;

@TeleOp (name = "Test WebCam Only!!!", group = "Lab")
//@Disabled
public class WebCamOnly extends OpMode {
    public VuforiaWebcam Cam = new VuforiaWebcam();

    @Override
    public void init() {
        Cam.initCamera(hardwareMap);
        Cam.activateTracking();
    }

    @Override
    public void loop() {
        Cam.trackObjects();
        telemetryOutput();
    }

    private void telemetryOutput () {
        telemetry.addData("Camera Visible Target", Cam.targetName);
        telemetry.addData("Camera Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f", Cam.targetX, Cam.targetY, Cam.targetZ);
        telemetry.addData("Camera Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", Cam.targetRoll, Cam.targetPitch, Cam.targetHeading);
    }
}
