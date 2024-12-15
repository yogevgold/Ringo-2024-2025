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
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.values.DeviceNames;
import org.firstinspires.ftc.teamcode.values.Values;


public class Intake {
    private DcMotorEx IntakeMotor;
    private Servo LeftIntakeServo;
    private Servo RightIntakeServo;
    private Servo RightHorizontalSlide;
    private Servo LeftHorizontalSlide;
    private DistanceSensor sempleCheck;
    private TouchSensor turnIntakeServoInitializer;
    private PIDFCoefficients pid;
    private ElapsedTime timer;

    private double realDifLocation;
    private double startLocation;

    private boolean IntakeLocked = false;

    public Intake(HardwareMap map){
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

        pid = new PIDFCoefficients(Values.P_OF_INTAKE, Values.I_OF_INTAKE, Values.D_OF_INTAKE, Values.F_OF_INTAKE);
        timer = new ElapsedTime();
        timer.startTime();

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

    public void horizontalslides(double angle) {
        LeftHorizontalSlide.setPosition(LeftHorizontalSlide.getPosition() + angle / 180);
        RightHorizontalSlide.setPosition(RightHorizontalSlide.getPosition() + angle / 180);
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
    public Action IntakePower(double pow){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if(IntakeLocked){
                    intakeSpin(0.3);
                }
                else {
                    intakeSpin(pow);
                }
                return false;
            }
        };
    }

    public Action HorizontalAngleSlide(double Angle) {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                horizontalslides(Angle);
                return false;
            }
        };
    }
    public Action Intaken(){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if(sempleCheck.getDistance(DistanceUnit.CM) < Values.DISTANCE_SAMPLE_CHECK){
                    IntakeLocked = true;
                    horizontalslides(0.0);
                }
            }
        };
    }
}
