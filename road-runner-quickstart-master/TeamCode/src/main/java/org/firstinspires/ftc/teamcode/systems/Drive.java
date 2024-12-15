package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.values.DeviceNames;

public class Drive {

    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;
    private Limelight3A LimeLight;

    public Drive(HardwareMap map) {
        frontLeft = map.get(DcMotorEx.class, DeviceNames.FRONT_LEFT_NAME);
        frontRight = map.get(DcMotorEx.class, DeviceNames.FRONT_RIGHT_NAME);
        backLeft = map.get(DcMotorEx.class, DeviceNames.BACK_LEFT_NAME);
        backRight = map.get(DcMotorEx.class, DeviceNames.BACK_RIGHT_NAME);
        LimeLight = map.get(Limelight3A.class, DeviceNames.LIME_LIGHT);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public Action TeleDrive(double strafe, double move, double turn){
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
}
