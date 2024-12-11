package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.values.DeviceNames;
import org.firstinspires.ftc.teamcode.values.PIDValues;


public class IntakeAction {
    private DcMotorEx IntakeMotor;
    private Servo LeftIntakeServo;
    private Servo RightIntakeServo;
    private Servo RightHorizontalSlide;
    private Servo LeftHorizontalSlide;
    private DistanceSensor sempleCheck;
    private TouchSensor turnIntakeServoInitializer;
    private PIDFCoefficients pid;

    private double realDifLocation;
    private double startLocation;


    public IntakeAction(HardwareMap map){
        IntakeMotor = map.get(DcMotorEx.class, DeviceNames.INTAKE_MOTOR_NAME);
        LeftIntakeServo = map.get(Servo.class, DeviceNames.LEFT_INTAKE_SERVO_NAME);
        RightIntakeServo = map.get(Servo.class, DeviceNames.RIGHT_INTAKE_SERVO_NAME);
        RightHorizontalSlide = map.get(Servo.class, DeviceNames.RIGHT_HORIZONTAL_SLIDE_NAME);
        LeftHorizontalSlide = map.get(Servo.class, DeviceNames.LEFT_HORIZONTAL_SLIDE_NAME);
        sempleCheck = map.get(DistanceSensor.class, DeviceNames.SEMPLE_CHECK_NAME);
        turnIntakeServoInitializer =map.get(TouchSensor.class, DeviceNames.TURN_INIT_INTAKE_TOUCH_NAME);

        LeftIntakeServo.setDirection(Servo.Direction.REVERSE);
        LeftHorizontalSlide.setDirection(Servo.Direction.REVERSE);
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pid = new PIDFCoefficients(PIDValues.P_OF_INTAKE, PIDValues.I_OF_INTAKE, PIDValues.D_OF_INTAKE, PIDValues.F_OF_INTAKE);
        IntakeMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pid);

        startLocation = RightIntakeServo.getPosition();
        realDifLocation = 0;//to prevent null errors
    }
    public void setAbsIntakeServosPosition(double position) {
        LeftIntakeServo.setPosition(position);
        RightIntakeServo.setPosition(position);
    }

    public void setRelativeIntakeServosPosition(double position) {
        setAbsIntakeServosPosition(position + realDifLocation);
    }

    public void intakeSpin(double pow) {
        IntakeMotor.setPower(pow);
    }

    public Action IntakeServoInit(){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                setAbsIntakeServosPosition(RightIntakeServo.getPosition() + 0.01);
                if(turnIntakeServoInitializer.isPressed()) {
                    realDifLocation = startLocation - RightIntakeServo.getPosition();
                }
                return turnIntakeServoInitializer.isPressed();
            }
        };
    }

    public Action AutonomouSempleDeliver() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                intakeSpin(1);
                if(sempleCheck.getDistance(DistanceUnit.CM) < 5) { // השערה של מרחק
                    intakeSpin(0.3);
                    LeftIntakeServo.setPosition(LeftIntakeServo.getPosition() + 0.01);
                    RightIntakeServo.setPosition(RightIntakeServo.getPosition() + 0.01);
                    if (turnIntakeServoInitializer.isPressed()) {
                        LeftIntakeServo.setPosition(LeftIntakeServo.getPosition());
                        RightIntakeServo.setPosition(RightIntakeServo.getPosition());
                    }
                    intakeSpin(-0.7);
                    LeftIntakeServo.setPosition(LeftIntakeServo.getPosition() - 0.4);
                    RightIntakeServo.setPosition(RightIntakeServo.getPosition() - 0.4);
                    intakeSpin(0);
                }
                return sempleCheck.getDistance(DistanceUnit.CM) < 5;
            }
        };
    }
}
