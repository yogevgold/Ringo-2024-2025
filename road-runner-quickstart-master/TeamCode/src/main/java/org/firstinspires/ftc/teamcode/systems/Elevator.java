package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.values.DeviceNames;
import org.firstinspires.ftc.teamcode.values.Values;

import java.util.concurrent.TimeUnit;

public class Elevator {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private Servo rightHook;
    private Servo leftHook;
    private TouchSensor touchSensor;
    private ElapsedTime  timer;
    private PIDFCoefficients pid;
    public double currentHeight;

    public Elevator(HardwareMap map){
        leftMotor = map.get(DcMotorEx.class, DeviceNames.LEFT_ELEVATOR_NAME);
        rightMotor = map.get(DcMotorEx.class, DeviceNames.RIGHT_ELEVATOR_NAME);
        touchSensor = map.get(TouchSensor.class, DeviceNames.ELEVATOR_TOUCH_SENSOR_NAME);
        leftHook = map.get(Servo.class, DeviceNames.LEFT_HOOK_NAME);
        rightHook = map.get(Servo.class, DeviceNames.RIGHT_HOOK_NAME);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        pid = new PIDFCoefficients(Values.P_OF_ELEVATOR, Values.I_OF_ELEVATOR, Values.D_OF_ELEVATOR, Values.F_OF_ELEVATOR);
        //קביעת ערכי משוואת הpid מהתיקייה של הערכים הקבועים PIDValues
        timer = new ElapsedTime();
        timer.startTime();
        leftMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pid);
        rightMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pid);
        //קובע את אופן העובדה של המנוע - או הרצה לפי מהירות או לפי מיקום.
        //מעלית לדוגמא זה לפי מיקום מכיוון שרצים לפי גובה (סם) אבל איסוף זה מהירות מכיוון שרצים לפי המהירות של המערכת. ויישום ערכי הpid שקבענו מקודם.
        leftMotor.setTargetPositionTolerance(Values.CM_TOLERANCE_ELEVATOR);
        //קביעה של כמות הסבל שלהpid

        currentHeight = 0;
    }

    public void powerMotors(double pow){
        leftMotor.setPower(pow);
        rightMotor.setPower(pow);
    }
    public void setMotorsByLocation(int location){
        leftMotor.setTargetPosition(location);
        rightMotor.setTargetPosition(location);
    }

    public Action moveToZero(boolean opModeIsActive) {
        return new Action() {

            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {

                if(touchSensor.isPressed()){
                    currentHeight = 0;
                }
                else powerMotors(-0.15);
                return !opModeIsActive;
            }
        };
    }
    public Action moveCM(int cm, boolean opModeisActive) {
        return new Action() {

            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                setMotorsByLocation(cm);
                telemetryPacket.put("Current left Location: ", leftMotor.getCurrentPosition());
                telemetryPacket.put("Current right Location", rightMotor.getCurrentPosition());
                return opModeisActive;
            }
        };
    }

    public Action firstBarPullUp() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                setMotorsByLocation(25);
                //נסיעה אחורה
                setMotorsByLocation(0);
                rightHook.setPosition(rightHook.getPosition() + 0.6);
                leftHook.setPosition(Values.CLIMB_LOCKED);
                return leftHook.getPosition() == Values.CLIMB_LOCKED;
            }
        };
    }

    public Action secoundBarPullUp() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                timer.reset();
                setMotorsByLocation(25);
                if(timer.time(TimeUnit.SECONDS) >= 1.5 && timer.time(TimeUnit.SECONDS) <= 2) {
                    setMotorsByLocation(20);
                    rightHook.setPosition(rightHook.getPosition() - 0.6);
                    leftHook.setPosition(leftHook.getPosition() - 0.6);
                    setMotorsByLocation(0);
                }
                return leftMotor.getCurrentPosition() == 0;
            }
        };
    }
}
