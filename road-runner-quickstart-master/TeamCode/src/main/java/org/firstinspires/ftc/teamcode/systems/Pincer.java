package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.values.DeviceNames;

public class Pincer {
    public Servo PincerArmLeft;
    public Servo PincerArmRight;
    public Servo PincerGrab;
    public Servo PincerTurn;

    public Pincer(HardwareMap map){
        PincerArmLeft = map.get(Servo.class, DeviceNames.PINCER_ARM_LEFT_NAME);
        PincerArmRight = map.get(Servo.class, DeviceNames.PINCER_ARM_LEFT_NAME);
        PincerGrab = map.get(Servo.class, DeviceNames.PINCER_GRAB_NAME);
        PincerTurn = map.get(Servo.class, DeviceNames.PINCER_TURN_NAME);
    }


    public Action turnPincer(double pos){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                PincerArmLeft.setPosition(pos);


                return false;
            }
        };



    }
    public Action movePincer(double pos){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                PincerArmLeft.setPosition(pos);
                return false;
            }
        };



    }
    public Action grabsample(double pos){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                PincerGrab.setPosition(pos);
                return false;
            }
        };



    }
}
