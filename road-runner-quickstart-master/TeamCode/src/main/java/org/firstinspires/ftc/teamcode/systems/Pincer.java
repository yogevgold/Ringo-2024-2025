package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.values.DeviceNames;

public class Pincer {
    public Servo PincerArmLeft;
    public Servo PincerArmRight;
    public Servo PincerGrab;
    public Servo PincerTurn;
    public Servo PincerRoll;

    public Pincer(HardwareMap map) {
        PincerArmLeft = map.get(Servo.class, DeviceNames.PINCER_ARM_LEFT_NAME);
        PincerArmRight = map.get(Servo.class, DeviceNames.PINCER_ARM_RIGHT_NAME);
        PincerGrab = map.get(Servo.class, DeviceNames.PINCER_GRAB_NAME);
        PincerTurn = map.get(Servo.class, DeviceNames.PINCER_TURN_NAME);
        PincerRoll = map.get(Servo.class, DeviceNames.PINCER_ROLL_NAME);

        PincerArmLeft.setDirection(Servo.Direction.REVERSE);
        //PincerTurn.setDirection(Servo.Direction.REVERSE);
    }

    public void pincerArm(double pos) {
        PincerArmLeft.setPosition(pos);
        PincerArmRight.setPosition(pos);
    }

    public void pincerTurn(double pos) {
        PincerTurn.setPosition(pos);
    }

    public void pincerRoll(double pos) {
        PincerRoll.setPosition(pos);
    }

    public void pincerGrab(double pos) {
        PincerGrab.setPosition(pos);
    }
}
