package org.firstinspires.ftc.teamcode.systems;


import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.values.DeviceNames;

public class outtake {
    private Servo funnel;
    private PIDFCoefficients pid;

    public outtake(HardwareMap map) {
        funnel = map.get(Servo.class, DeviceNames.FUNNEL_NAME);
    }

    public Action sampleIntoBucket() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                funnel.setPosition(funnel.getPosition() + 0.4);
                return false;
            }
        };
    }

    public Action funnleInPlace() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                funnel.setPosition(funnel.getPosition() - 0.4);
                return false;
            }
        };
    }
}
