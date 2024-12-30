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
        frontLeft = hardwareMap.get(DcMotor.class, DeviceNames.FRONT_LEFT_NAME);
        frontRight = hardwareMap.get(DcMotor.class, DeviceNames.FRONT_RIGHT_NAME);
        backLeft = hardwareMap.get(DcMotor.class, DeviceNames.BACK_LEFT_NAME);
        backRight = hardwareMap.get(DcMotor.class, DeviceNames.BACK_RIGHT_NAME);
        leftIntakeServo = hardwareMap.get(ServoImplEx.class, DeviceNames.LEFT_INTAKE_SERVO_NAME);
        rightIntakeServo = hardwareMap.get(ServoImplEx.class, DeviceNames.RIGHT_INTAKE_SERVO_NAME);
        leftHorizontalServo = hardwareMap.get(ServoImplEx.class, DeviceNames.LEFT_HORIZONTAL_SLIDE_NAME);
        rightHorizontalServo = hardwareMap.get(ServoImplEx.class, DeviceNames.RIGHT_HORIZONTAL_SLIDE_NAME);
        //leftFunnel = hardwareMap.get(Servo.class, DeviceNames.LEFT_FUNNEL_NAME);
        //rightFunnel = hardwareMap.get(Servo.class, DeviceNames.RIGHT_FUNNEL_NAME);
        elevatorRight = hardwareMap.get(DcMotor.class, DeviceNames.RIGHT_ELEVATOR_NAME);
        elevatorLeft = hardwareMap.get(DcMotor.class, DeviceNames.LEFT_ELEVATOR_NAME);
    }

    public void REVERSE() {
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
//      elevatorRight.setDirection(DcMotorSimple.Direction.REVERSE);
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
        //drive = new Drive(hardwareMap);
        betterGamepad1 = new BetterGamepad(gamepad1);
        betterGamepad2 = new BetterGamepad(gamepad1);
        hardwareMapNames();
        REVERSE();
        waitForStart();
        while (opModeIsActive()) {
            double strafe = -gamepad1.left_stick_x;
            double move = gamepad1.left_stick_y;
            double turn = -gamepad1.right_stick_x;
            frontLeft.setPower(move + strafe + turn);
            backLeft.setPower(move - strafe + turn);
            frontRight.setPower(move - strafe - turn);
            backRight.setPower(move + strafe - turn);
            Elevator(betterGamepad1.right_stick_y);
            Intake(betterGamepad1.right_trigger + -betterGamepad1.left_trigger);


            if (betterGamepad1.dpadUp()) {
                horizontalSlides(40);
            }

            if (betterGamepad1.dpadDown()) {
                horizontalSlides(0);
            }

            if (betterGamepad1.dpadLeft()) {
                intakeServo(0);
            }

            if (betterGamepad1.dpadRight()) {
                intakeServo(0.4);
            }

            /** if (betterGamepad1.B()) {
                funnle(0.3);
            }

            if (betterGamepad1.X()) {
                funnle(0);

            } **/
        }
    }
}
