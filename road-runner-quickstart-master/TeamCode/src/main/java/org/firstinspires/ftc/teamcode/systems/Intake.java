package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.values.DeviceNames;
import org.firstinspires.ftc.teamcode.values.Values;


public class Intake {
    private final DcMotorEx IntakeMotor;
    public ServoImplEx leftSlide;
    public ServoImplEx rightSlide;
    public ServoImplEx LeftIntake;
    public ServoImplEx RightIntake;

    public void setter(double set) {
        leftSlide.setPosition(set);
        rightSlide.setPosition(set);
        LeftIntake.setPosition(set);
        RightIntake.setPosition(set);
    }

    public Intake(HardwareMap map) {
        IntakeMotor = map.get(DcMotorEx.class, DeviceNames.INTAKE_MOTOR_NAME);
        leftSlide = map.get(ServoImplEx.class, DeviceNames.LEFT_HORIZONTAL_SLIDE_NAME);
        rightSlide = map.get(ServoImplEx.class, DeviceNames.RIGHT_HORIZONTAL_SLIDE_NAME);
        LeftIntake = map.get(ServoImplEx.class, DeviceNames.LEFT_INTAKE_SERVO_NAME);
        RightIntake = map.get(ServoImplEx.class, DeviceNames.RIGHT_INTAKE_SERVO_NAME);

        leftSlide.setPwmRange(new PwmControl.PwmRange(500, 2500));
        rightSlide.setPwmRange(new PwmControl.PwmRange(500, 2500));
        LeftIntake.setPwmRange(new PwmControl.PwmRange(500, 2500));
        RightIntake.setPwmRange(new PwmControl.PwmRange(500, 2500));
        //leftSlide.setPwmEnable();
        //rightSlide.setPwmEnable();
        //LeftIntake.setPwmEnable();
        //RightIntake.setPwmEnable();

        leftSlide.setDirection(Servo.Direction.REVERSE);
        RightIntake.setDirection(Servo.Direction.REVERSE);
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

//        pid = new PIDFCoefficients(Values.P_OF_INTAKE, Values.I_OF_INTAKE, Values.D_OF_INTAKE, Values.F_OF_INTAKE);
    }

    public void setIntakeServo(double position) {
        LeftIntake.setPosition(position);
        RightIntake.setPosition(position);
    }


    public void intakeSpin(double pow) {
        IntakeMotor.setPower(pow);
    }

    public void horizontalslides(double val) {
        leftSlide.setPosition(val);
        rightSlide.setPosition(val);
    }

    public Action ServoMove(double move){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                setIntakeServo(move);
                return true;
            }
        };
    }
    public Action IntakePower(double pow){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    intakeSpin(pow);
                return false;
            }
        };
    }

    public Action HorizontalAngleSlide(double Angle) {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                horizontalslides(Angle);
                return true;
            }
        };
    }
}
