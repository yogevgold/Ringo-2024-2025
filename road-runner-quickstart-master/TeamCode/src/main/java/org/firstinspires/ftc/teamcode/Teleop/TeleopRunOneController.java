package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.BetterGamepad;
import org.firstinspires.ftc.teamcode.systems.Drive;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.values.DeviceNames;

@TeleOp
public class TeleopRunOneController extends LinearOpMode {


    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;
    private DcMotor intake;
    private ServoImplEx leftIntakeServo;
    private ServoImplEx rightIntakeServo;
    private ServoImplEx leftHorizontalServo;
    private ServoImplEx rightFunnel;
    private ServoImplEx leftFunnel;
    private ServoImplEx rightHorizontalServo;


    private DcMotor elevatorRight;
    private DcMotor elevatorLeft;
    private TouchSensor elevatorTouch;
    private DistanceSensor sampleCheck;

    Drive drive;
    BetterGamepad betterGamepad1;
    BetterGamepad betterGamepad2;


    public void hardwareMapNames() {
        elevatorRight = hardwareMap.get(DcMotor.class, DeviceNames.RIGHT_ELEVATOR_NAME);
        elevatorLeft = hardwareMap.get(DcMotor.class, DeviceNames.LEFT_ELEVATOR_NAME);
        elevatorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        elevatorRight    .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    }

    public void REVERSE() {

      elevatorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void Intake(double x) {
        intake.setPower(x);
    }

    public void Elevator(double x) {
        elevatorLeft.setPower(x);
        elevatorRight.setPower(x);
    }

    public void horizontalSlides(double x) {
        leftHorizontalServo.setPosition(x / 180);
        rightHorizontalServo.setPosition(x / 180);
    }

    public void intakeServo(double x) {
        leftIntakeServo.setPosition(x);
        rightIntakeServo.setPosition(x);
    }

    public void funnle(double x) {
        leftFunnel.setPosition(x);
        rightFunnel.setPosition(x);
    }


    @Override
    public void runOpMode() throws InterruptedException {
        hardwareMapNames();
        REVERSE();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Spins: ", elevatorLeft.getCurrentPosition());
            telemetry.update();
            Elevator(gamepad1.left_stick_x);
        }
    }
    }

