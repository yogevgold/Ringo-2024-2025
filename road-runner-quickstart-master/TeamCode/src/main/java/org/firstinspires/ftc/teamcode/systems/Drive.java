package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.values.DeviceNames;

public class Drive {

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
        //LimeLight = map.get(Limelight3A.class, DeviceNames.LIME_LIGHT);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public Action intakeCloseDrive(double strafe, double move, double turn){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                frontLeft.setPower(move + strafe + turn);
                backLeft.setPower(move - strafe + turn);
                frontRight.setPower(move - strafe - turn);
                backRight.setPower(move + strafe - turn);
                return false;
            }
        };
    }

    public Action intakeOpenDrive(double strafe, double move, double turn){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                frontLeft.setPower(move / 2 + strafe / 2 + turn / 2);
                backLeft.setPower(move / 2 - strafe / 2 + turn / 2);
                frontRight.setPower(move  / 2- strafe / 2 - turn / 2);
                backRight.setPower(move / 2 + strafe / 2 - turn / 2);
                return false;
            }
        };
    }
}
