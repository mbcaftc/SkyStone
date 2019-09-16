package org.firstinspires.ftc.teamcode.MrAcker.base.Subsystems;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.base.Drivetrains.MecanumDrive;


public class GyroSensor {

    public Orientation angles;
    public Acceleration gravity;
    public BNO055IMU imu;
    final public double SPEED = .3;
    public final double TOLERANCE = .7;


    public LinearOpMode linearOp = null;
    public void setLinearOp (LinearOpMode Op) {
        linearOp = Op;
    }

    public MecanumDrive myDriveMotors = new MecanumDrive();


    public GyroSensor(BNO055IMU I) {

        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        parametersimu.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parametersimu.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parametersimu.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parametersimu.loggingEnabled = true;
        parametersimu.loggingTag = "IMU";
        parametersimu.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu = I;
        imu.initialize(parametersimu);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }


        public void gyroOrientMecanum (double angle, MecanumDrive myMechDrive) {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        linearOp.telemetry.update();
        linearOp.sleep(50); //intentionally long sleep for feedback
        if (angles.firstAngle >= angle + TOLERANCE) {
            while (angles.firstAngle >=  angle + TOLERANCE && linearOp.opModeIsActive()) {
                myMechDrive.rotateRight(SPEED);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }
        else if (angles.firstAngle <= angle - TOLERANCE) {
            while (angles.firstAngle <= angle - TOLERANCE && linearOp.opModeIsActive()) {
                myMechDrive.rotateLeft(SPEED);
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            }
        }
        myMechDrive.stopMotors();
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }



    public void gyroReset () {
        BNO055IMU.Parameters parametersimu = new BNO055IMU.Parameters();
        imu.initialize(parametersimu);
    }
}
