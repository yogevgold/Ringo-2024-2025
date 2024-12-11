package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.values.DeviceNames;

public class TeleopRunOneController extends LinearOpMode {


    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor intake;
    private DcMotor elevatorRight;
    private DcMotor elevatorLeft;
    private TouchSensor elevatorTouch;
    private DistanceSensor sempleCheck;


    public void hardwareMapNames() {
        frontLeft = hardwareMap.get(DcMotor.class, DeviceNames.FRONT_LEFT_NAME);
        frontRight = hardwareMap.get(DcMotor.class, DeviceNames.FRONT_RIGHT_NAME);
        backLeft = hardwareMap.get(DcMotor.class, DeviceNames.BACK_LEFT_NAME);
        backRight = hardwareMap.get(DcMotor.class, DeviceNames.BACK_RIGHT_NAME);
        elevatorRight = hardwareMap.get(DcMotor.class, DeviceNames.RIGHT_ELEVATOR_NAME);
        elevatorLeft = hardwareMap.get(DcMotor.class, DeviceNames.LEFT_ELEVATOR_NAME);
        elevatorTouch = hardwareMap.get(TouchSensor.class, DeviceNames.ELEVATOR_TOUCH_SENSOR_NAME);
        sempleCheck = hardwareMap.get(DistanceSensor.class, DeviceNames.ELEVATOR_TOUCH_SENSOR_NAME);
    }

    public void REVERSE() {
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        elevatorRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void Forward(double x) {
        frontRight.setPower(x);
        frontLeft.setPower(x);
        backRight.setPower(x);
        backLeft.setPower(x);
    }

    public void strafe(double x) {
        frontRight.setPower(-x);
        frontLeft.setPower(x);
        backRight.setPower(x);
        backLeft.setPower(-x);
    }

    public void turn(double x) {
        frontRight.setPower(-x);
        frontLeft.setPower(x);
        backRight.setPower(-x);
        backLeft.setPower(x);
    }

    public void IntakeForward(double x) {
        intake.setPower(x);
    }

    public void IntakeBackward(double x) {
        intake.setPower(-x);
    }

    public void Elevator(double x) {
        elevatorLeft.setPower(x);
        elevatorRight.setPower(x);
    }


    @Override
    public void runOpMode() throws InterruptedException {
        hardwareMapNames();
        REVERSE();
        waitForStart();
        while (opModeIsActive()) {
            Forward(-gamepad1.left_stick_y);
            strafe(gamepad1.left_stick_x);
            turn(gamepad1.right_stick_x);
            IntakeForward(gamepad1.right_trigger);
            IntakeBackward(gamepad1.left_trigger);

            if(gamepad1.a) {
                Elevator(-0.5);
                if(elevatorTouch.isPressed()) {
                    Elevator(0);
                }
            }

            if(gamepad1.b) {
                Elevator(0.5);
                sleep(2500);
                Elevator(0);
            }

            
        }
    }
}
