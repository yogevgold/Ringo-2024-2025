package org.firstinspires.ftc.teamcode.systems;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.values.DeviceNames;
import org.firstinspires.ftc.teamcode.values.Values;

public class Outtake {
    public Servo Rightfunnel;
    private Servo Leftfunnel;


    public Outtake(HardwareMap map) {
        Rightfunnel = map.get(Servo.class, DeviceNames.RIGHT_FUNNEL_NAME);
        Leftfunnel = map.get(Servo.class, DeviceNames.LEFT_FUNNEL_NAME);
        Leftfunnel.setDirection(Servo.Direction.REVERSE);

    }

    public void MoveFunnel(double FunnelPos){
        Leftfunnel.setPosition(FunnelPos);
        Rightfunnel.setPosition(FunnelPos);
    }
    public Action Funnels(double val) {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                MoveFunnel(val);
                return true;
            }
        };
    }
}
