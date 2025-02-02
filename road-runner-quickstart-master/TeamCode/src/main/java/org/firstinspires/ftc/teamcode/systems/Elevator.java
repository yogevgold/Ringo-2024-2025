package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
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

@Config

public class Elevator {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private TouchSensor touchSensor;
    private ElapsedTime  timer;
    private ElevatorPIDF pid;

    public int currentHeight;
    public Elevator(HardwareMap map){
        leftMotor = map.get(DcMotorEx.class, DeviceNames.LEFT_ELEVATOR_NAME);
        rightMotor = map.get(DcMotorEx.class, DeviceNames.RIGHT_ELEVATOR_NAME);
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        pid = new ElevatorPIDF(Values.P_OF_ELEVATOR, Values.I_OF_ELEVATOR, Values.D_OF_ELEVATOR, Values.F_OF_ELEVATOR, Values.IZONE_OF_ELEVATOR);
        //קביעת ערכי משוואת הpid מהתיקייה של הערכים הקבועים PIDValues
        timer = new ElapsedTime();
        timer.startTime();

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        //קובע את אופן העובדה של המנוע - או הרצה לפי מהירות או לפי מיקום.
        //מעלית לדוגמא זה לפי מיקום מכיוון שרצים לפי גובה (סם) אבל איסוף זה מהירות מכיוון שרצים לפי המהירות של המערכת. ויישום ערכי הpid שקבענו מקודם.
        //קביעה של כמות הסבל שלהpid
        currentHeight = 0;
        pid.setTargetHeight(currentHeight);
    }

    public void powerMotors(double pow) {
        leftMotor.setPower(pow);
        rightMotor.setPower(pow);
    }

    public void setMotorsByLocation(int location) {
        leftMotor.setTargetPosition(location);
        rightMotor.setTargetPosition(location);
    }

    public Action moveToZero() {
        return new Action() {
            @Override
            public boolean run (@NonNull TelemetryPacket telemetryPacket) {
                powerMotors(-0.05);
                if(touchSensor.isPressed()) {
                    currentHeight = 0;
                }
                return touchSensor.isPressed();
            }
        };
    }

    public Action moveByPower(double pow) {
        return new Action() {
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                powerMotors(pow);
                return false;
            }
        };
    }
    public Action moveCM(int cm){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                pid.setTargetHeight(cm);
                rightMotor.setPower(-pid.calculate(((double) rightMotor.getCurrentPosition()) /  Values.TICKS_TO_CM_RATION));
                leftMotor.setPower(-pid.calculate(((double)  leftMotor.getCurrentPosition()) / Values.TICKS_TO_CM_RATION));

                telemetryPacket.put("LeftPower: ", -pid.calculate((double)  -leftMotor.getCurrentPosition() / Values.TICKS_TO_CM_RATION));
                telemetryPacket.put("RightPower: ", -pid.calculate((double) -rightMotor.getCurrentPosition() /  Values.TICKS_TO_CM_RATION));
                telemetryPacket.put("goalCm: ", cm);
                telemetryPacket.put("actual left cm: ", (-leftMotor.getCurrentPosition() /  Values.TICKS_TO_CM_RATION));
                telemetryPacket.put("actual right cm: ", (-rightMotor.getCurrentPosition() /  Values.TICKS_TO_CM_RATION));
                return false;
            }
        };
    }
}

