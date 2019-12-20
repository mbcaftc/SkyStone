package org.firstinspires.ftc.teamcode.ACompetitionSkyStone.subsystems;


import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import java.util.ArrayList;
import java.util.List;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;



public class VuforiaWebcam {

    public static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    public static final boolean PHONE_IS_PORTRAIT = false  ;

    public static final String VUFORIA_KEY =
          "AZ7FRiT/////AAABmV8K//atfkrzl192hWMTHC5IF42DuOMXgcbKj3ykPItGlUrJghFCQLsg1Xv5u2CZ3m5bxMhQ4gT6Cf8nRDc7gLyJdYBU8y2sLtaC/aL3hXpGZdB6IQmiwOgdiYjZ5iK/jFS3QKnbk8QdOzLbifMssIY/3/8bMK0GAAUDTsZqivFJK7Kpa7ZXuuWlxZ36HzJv1UYBP+K/BxRQCHY7BarO/eCq2BExtHVyL6hu2sU8TZ8gKakUVEF0p13r/MzZaIsaCppZCfT9lLdlviprQgTn0TXqMSObvtSgjSJJbeXkS7hg0cY2OLbqrf8zJJnUspVmseOVm3h+7r0wtvugxSQWiE1mLgvBJ6Dsg9haM+nSonGi\"";

    public static final float mmPerInch        = 25.4f;
    public static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

    // Constant for Stone Target
    public static final float stoneZ = 2.00f * mmPerInch;

    // Constants for the center support targets
    public static final float bridgeZ = 6.42f * mmPerInch;
    public static final float bridgeY = 23 * mmPerInch;
    public static final float bridgeX = 5.18f * mmPerInch;
    public static final float bridgeRotY = 59;                                 // Units are degrees
    public static final float bridgeRotZ = 180;

    // Constants for perimeter targets
    public static final float halfField = 72 * mmPerInch;
    public static final float quadField  = 36 * mmPerInch;

    // Class Members
    public OpenGLMatrix lastLocation = null;
    public VuforiaLocalizer vuforia = null;


    public WebcamName webcamName = null;

    public boolean targetVisible = false;
    public float phoneXRotate    = 0;
    public float phoneYRotate    = 0;
    public float phoneZRotate    = 0;

    public HardwareMap hwCam   =  null;

    public VuforiaTrackables targetsSkyStone = null;
    public List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();

    public String targetName = null;
    public double targetX;
    public double targetY;
    public double targetZ;
    public double targetRoll;
    public double targetPitch;
    public double targetHeading;


    public void initCamera(HardwareMap hwMap) {

        hwCam = hwMap;

        webcamName = hwCam.get(WebcamName.class, "WebCam");

        int cameraMonitorViewId = hwCam.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwCam.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = VUFORIA_KEY;

        parameters.cameraName = webcamName;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Load the data sets for the trackable objects. These particular data

        targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");
//        VuforiaTrackable blueRearBridge = targetsSkyStone.get(1);
//        blueRearBridge.setName("Blue Rear Bridge");
//        VuforiaTrackable redRearBridge = targetsSkyStone.get(2);
//        redRearBridge.setName("Red Rear Bridge");
//        VuforiaTrackable redFrontBridge = targetsSkyStone.get(3);
//        redFrontBridge.setName("Red Front Bridge");
//        VuforiaTrackable blueFrontBridge = targetsSkyStone.get(4);
//        blueFrontBridge.setName("Blue Front Bridge");
//        VuforiaTrackable red1 = targetsSkyStone.get(5);


        // For convenience, gather together all the trackable objects in one easily-iterable collection */
        allTrackables.addAll(targetsSkyStone);


        stoneTarget.setLocation(OpenGLMatrix
                .translation(0, 0, stoneZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));

        //Set the position of the bridge support targets with relation to origin (center of field)
//        blueFrontBridge.setLocation(OpenGLMatrix
//                .translation(-bridgeX, bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, bridgeRotZ)));
//
//        blueRearBridge.setLocation(OpenGLMatrix
//                .translation(-bridgeX, bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, bridgeRotZ)));
//
//        redFrontBridge.setLocation(OpenGLMatrix
//                .translation(-bridgeX, -bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, -bridgeRotY, 0)));
//
//        redRearBridge.setLocation(OpenGLMatrix
//                .translation(bridgeX, -bridgeY, bridgeZ)
//                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 0, bridgeRotY, 0)));


        // We need to rotate the camera around it's long axis to bring the correct camera forward.
        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        // Rotate the phone vertical about the X axis if it's in portrait mode
        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90;
        }

        // Next, translate the camera lens to where it is on the robot.
        // In this example, it is centered (left to right), but forward of the middle of the robot, and above ground level.
        final float CAMERA_FORWARD_DISPLACEMENT = 4.0f * mmPerInch;   // eg: Camera is 4 Inches in front of robot-center
        final float CAMERA_VERTICAL_DISPLACEMENT = 8.0f * mmPerInch;   // eg: Camera is 8 Inches above ground
        final float CAMERA_LEFT_DISPLACEMENT = 0;     // eg: Camera is ON the robot's center line

        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

        /**  Let all the trackable listeners know where the phone is.  */
        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
        }

    }

    public void activateTracking() {

        targetsSkyStone.activate();
    }

    public void deActivateTracking() {

        targetsSkyStone.deactivate();
    }

    public void trackObjects (){

        // check all the trackable targets to see which one (if any) is visible.
        targetVisible = false;
        for (VuforiaTrackable trackable : allTrackables) {
            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                targetName = trackable.getName();
                targetVisible = true;

                // getUpdatedRobotLocation() will return null if no new information is available since the last time that call was made, or if the trackable is not currently visible.
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }

        // Provide feedback as to where the robot is located (if we know).
        if (targetVisible) {

            // express position (translation) of robot in inches.
            VectorF translation = lastLocation.getTranslation();

            targetX = translation.get(0) / mmPerInch;
            targetY = translation.get(1) / mmPerInch;
            targetZ = translation.get(2) / mmPerInch;

            // express the rotation of the robot in degrees.
            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);

            targetRoll = rotation.firstAngle;
            targetPitch = rotation.secondAngle;
            targetHeading = rotation.thirdAngle;

        }
        else {
            targetName = "none";
        }
    }


}
