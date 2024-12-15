package org.firstinspires.ftc.teamcode.systems;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.values.DeviceNames;
import org.firstinspires.ftc.teamcode.values.Values;

public class Outtake {
    private Servo funnel;

    public Outtake(HardwareMap map) {
        funnel = map.get(Servo.class, DeviceNames.FUNNEL_NAME);
    }
    public Action sampleIntoBucket() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                funnel.setPosition(Values.FUNNEL_OPEN);
                return funnel.getPosition() == Values.FUNNEL_OPEN;
            }
        };
    }

    public Action funnleInPlace() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                funnel.setPosition(Values.FUNNEL_CLOSED);
                return funnel.getPosition() == Values.FUNNEL_CLOSED;
            }
        };
    }
}
