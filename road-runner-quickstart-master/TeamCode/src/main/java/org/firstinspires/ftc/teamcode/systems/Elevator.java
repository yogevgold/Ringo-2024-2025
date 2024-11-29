package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.systems.values.DeviceNames;
import org.firstinspires.ftc.teamcode.systems.values.PIDValues;

public class Elevator {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private TouchSensor touchSensor;
    private PIDFCoefficients pid;
    public double currentHeight;

    public Elevator(HardwareMap map){
        leftMotor = map.get(DcMotorEx.class, DeviceNames.LEFT_ELEVATOR_NAME);
        rightMotor = map.get(DcMotorEx.class, DeviceNames.RIGHT_ELEVATOR_NAME);
        touchSensor = map.get(TouchSensor.class, DeviceNames.ELEVATOR_TOUCH_SENSOR);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        pid = new PIDFCoefficients(PIDValues.P_OF_ELEVATOR, PIDValues.I_OF_ELEVATOR, PIDValues.D_OF_ELEVATOR, PIDValues.F_OF_ELEVATOR);
        //קביעת ערכי משוואת הpid מהתיקייה של הערכים הקבועים PIDValues
        leftMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pid);
        rightMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pid);
        //קובע את אופן העובדה של המנוע - או הרצה לפי מהירות או לפי מיקום.
        //מעלית לדוגמא זה לפי מיקום מכיוון שרצים לפי גובה (סם) אבל איסוף זה מהירות מכיוון שרצים לפי המהירות של המערכת. ויישום ערכי הpid שקבענו מקודם.
        leftMotor.setTargetPositionTolerance(PIDValues.CM_TOLERANCE_ELEVATOR);
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
}
