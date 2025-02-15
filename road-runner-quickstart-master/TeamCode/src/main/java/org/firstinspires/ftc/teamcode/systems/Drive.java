package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.values.DeviceNames;

public class Drive {
    private final IMU imu;

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Limelight3A LimeLight;

    public Drive(HardwareMap map) {
        frontLeft = map.get(DcMotor.class, DeviceNames.FRONT_LEFT_NAME);
        frontRight = map.get(DcMotor.class, DeviceNames.FRONT_RIGHT_NAME);
        backLeft = map.get(DcMotor.class, DeviceNames.BACK_LEFT_NAME);
        backRight = map.get(DcMotor.class, DeviceNames.BACK_RIGHT_NAME);

        imu = map.get(IMU.class, "imu");

        //LimeLight = map.get(Limelight3A.class, DeviceNames.LIME_LIGHT);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        imu.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                                RevHubOrientationOnRobot.UsbFacingDirection.RIGHT
                        )
                )
        );
    }

    public double getRobotYawRAD() {
        return imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }

    public Action drive(double x_val, double y_val, double turn_val, boolean intakeIsOpen, boolean fieldCentric) {
        double ADPower;
        double BCPower;
        double turningVal;
        double turningScale;
        double angle_t = 0;

        if (intakeIsOpen) {
            x_val = x_val / 2;
            y_val = y_val / 2;
            turn_val = turn_val / 2;
        }

        if (fieldCentric) {
            double angle = Math.atan2(y_val, x_val);
            double magnitude = Math.hypot(y_val, x_val);

            angle -= getRobotYawRAD();

            ADPower = magnitude * Math.sqrt(2) * 0.5 * (Math.sin(angle) + Math.cos(angle));
            BCPower = magnitude * Math.sqrt(2) * 0.5 * (Math.sin(angle) - Math.cos(angle));
            turningScale = Math.max(Math.abs(ADPower + turn_val), Math.abs(ADPower - turn_val));
            turningScale = Math.max(turningScale, Math.max(Math.abs(BCPower + turn_val), Math.abs(BCPower - turn_val)));

            if (Math.abs(turningScale) < 1.0) {
                turningScale = 1.0;
            }

            // turningVal = -turningScale;
            angle_t = angle;
        } else {
            ADPower = y_val + x_val;
            BCPower = y_val - x_val;
            turningVal = turn_val;
            turningScale = 1.0;
        }

        double finalTurningScale = turningScale;
        double finalAngle_t = angle_t;
        turningVal = turn_val;
        double finalTurningVal = turningVal;

        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                frontLeft.setPower((ADPower + finalTurningVal) / finalTurningScale);
                backLeft.setPower((BCPower + finalTurningVal) / finalTurningScale);
                frontRight.setPower((BCPower - finalTurningVal) / finalTurningScale);
                backRight.setPower((ADPower - finalTurningVal) / finalTurningScale);

                /*
                    telemetryPacket.addLine("FL: " + (ADPower + finalTurningVal) / finalTurningScale);
                    telemetryPacket.addLine("BL: " + (BCPower + finalTurningVal) / finalTurningScale);
                    telemetryPacket.addLine("FR: " + (BCPower - finalTurningVal) / finalTurningScale);
                    telemetryPacket.addLine("BR: " + (ADPower - finalTurningVal) / finalTurningScale);
                    telemetryPacket.addLine("Angle: " + finalAngle_t);

                    frontLeft.setPower(move + strafe + turn);
                    backLeft.setPower(move - strafe + turn);
                    frontRight.setPower(move - strafe - turn);
                    backRight.setPower(move + strafe - turn);
                */
                return false;
            }
        };
    }

    public Action drive2(double x_val, double y_val, double turn_val, boolean intakeIsOpen, boolean fieldCentric) {
        double ADPower;
        double BCPower;
        double turningVal;
        double turningScale;
        double angle_t = 0;

        if (intakeIsOpen) {
            x_val = x_val / 2;
            y_val = y_val / 2;
            turn_val = turn_val / 2;
        }

        if (fieldCentric) {
            double angle = Math.atan2(y_val, x_val);
            double magnitude = Math.hypot(y_val, x_val);

            angle -= getRobotYawRAD();

            ADPower = magnitude * Math.sqrt(2) * 0.5 * (Math.sin(angle) + Math.cos(angle));
            BCPower = magnitude * Math.sqrt(2) * 0.5 * (Math.sin(angle) - Math.cos(angle));
            turningScale = Math.max(Math.abs(ADPower + turn_val), Math.abs(ADPower - turn_val));
            turningScale = Math.max(turningScale, Math.max(Math.abs(BCPower + turn_val), Math.abs(BCPower - turn_val)));

            if (Math.abs(turningScale) < 1.0) {
                turningScale = 1.0;
            }

            // turningVal = -turningScale;
            angle_t = angle;
        } else {
            ADPower = y_val + x_val;
            BCPower = y_val - x_val;
            turningVal = turn_val;
            turningScale = 1.0;
        }

        double finalTurningScale = turningScale;
        double finalAngle_t = angle_t;
        turningVal = turn_val;
        double finalTurningVal = turningVal;

        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                frontLeft.setPower((ADPower + finalTurningVal) / finalTurningScale);
                backLeft.setPower((BCPower + finalTurningVal ) / finalTurningScale);
                frontRight.setPower((BCPower - finalTurningVal) / finalTurningScale);
                backRight.setPower((ADPower - finalTurningVal ) / finalTurningScale);

                telemetryPacket.addLine("FL: " + (ADPower + finalTurningVal) / finalTurningScale);
                telemetryPacket.addLine("BL: " + (BCPower + finalTurningVal) / finalTurningScale);
                telemetryPacket.addLine("FR: " + (BCPower - finalTurningVal) / finalTurningScale);
                telemetryPacket.addLine("BR: " + (ADPower - finalTurningVal) / finalTurningScale);
                telemetryPacket.addLine("Angle: " + finalAngle_t);






                /*
                    frontLeft.setPower(move + strafe + turn);
                    backLeft.setPower(move - strafe + turn);
                    frontRight.setPower(move - strafe - turn);
                    backRight.setPower(move + strafe - turn);
                */
                return false;
            }
        };
    }
}