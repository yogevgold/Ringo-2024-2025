package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.values.DeviceNames;


@TeleOp
public class TeleopYarden1 extends LinearOpMode {

    private DcMotor FrontRight; //הגדרת מנוע DC קידמי ימני
    private DcMotor FrontLeft; //
    private DcMotor BackRight;
    private DcMotor BackLeft;
    private Servo LeftHook;
    private Servo RightHook;
    private DcMotor REHook;
    private DcMotor LEHook;
    private DcMotor Intake;
    private Servo LElevator;
    private Servo RElevator;
    private Servo LSlide;
    private Servo RSlide;
    private Servo LIntake;
    private Servo RIntake;
    private Servo LeftLocker;
    private Servo RightLocker;

    private ElapsedTime Climbtimer;
    private ElapsedTime Intaketimer;
    private ElapsedTime Outtaketimer;


    public void forwardBackward(double x) {
        FrontRight.setPower(x);
        FrontLeft.setPower(x);
        BackLeft.setPower(x);
        BackRight.setPower(x);
    }

    public void strafe(double x) {
        FrontRight.setPower(-x);
        FrontLeft.setPower(x);
        BackRight.setPower(x);
        BackLeft.setPower(-x);
    }

    public void turn(double x) {
        FrontRight.setPower(x);
        FrontLeft.setPower(-x);
        BackLeft.setPower(-x);
        BackRight.setPower(x);
    }



    public void grab(){

        telemetry.addLine("a pressed");
        LElevator.setPosition(0.4);
        RElevator.setPosition(0.4);


    }
    public void take(){

        telemetry.addLine("b pressed");
        LElevator.setPosition(0);
        RElevator.setPosition(0);
    }
    public void MoveIntake(){
        telemetry.addLine("x pressed");
        LIntake.setPosition(0.4);
        RIntake.setPosition(0.4);
    }
    public void ReturnIntake(){
        telemetry.addLine("y pressed");
        LIntake.setPosition(-0.4);
        RIntake.setPosition(-0.4);
    }
    public void MoveSlide(){
        telemetry.addLine("dpad_up pressed");
        LSlide.setPosition(0.4);
        RSlide.setPosition(0.4);
    }
    public void ReturnSlide(){
        telemetry.addLine("dpad_down pressed");
        LSlide.setPosition(0);
        RSlide.setPosition(0);
    }
    public void Elevators(double x){
        REHook.setPower(x);
        LEHook.setPower(x);
    }

    public void CloseE(){
        telemetry.addLine("dpad_left pressed");
        RightHook.setPosition(0.4);
        LeftHook.setPosition(0.4);
    }
    public void OpenE(){
        telemetry.addLine("dpad_right pressed");
        RightHook.setPosition(0);
        LeftHook.setPosition(0);
    }
    public void CloseME(){
        telemetry.addLine("left bumper pressed");
        RightLocker.setPosition(0.4);
        LeftLocker.setPosition(0.4);
    }
    public void OpenME(){
        telemetry.addLine("right bumper pressed");
        RightLocker.setPosition(0);
        LeftLocker.setPosition(0);
    }









    @Override
    public void runOpMode() throws InterruptedException {
        FrontRight = hardwareMap.get(DcMotor.class, DeviceNames.FRONT_RIGHT_NAME);
        FrontLeft = hardwareMap.get(DcMotor.class, DeviceNames.FRONT_LEFT_NAME);
        BackRight = hardwareMap.get(DcMotor.class, DeviceNames.BACK_RIGHT_NAME);
        BackLeft = hardwareMap.get(DcMotor.class, DeviceNames.BACK_LEFT_NAME);
        REHook = hardwareMap.get(DcMotor.class, DeviceNames.RIGHT_ELEVATOR_NAME);
        LEHook = hardwareMap.get(DcMotor.class, DeviceNames.LEFT_ELEVATOR_NAME);
        Intake = hardwareMap.get(DcMotor.class,DeviceNames.INTAKE_MOTOR_NAME);
        LElevator = hardwareMap.get(Servo.class,DeviceNames.LEFT_HORIZONTAL_SLIDE_NAME);
        RElevator = hardwareMap.get(Servo.class, DeviceNames.RIGHT_HORIZONTAL_SLIDE_NAME);
        LIntake = hardwareMap.get(Servo.class, DeviceNames.LEFT_INTAKE_SERVO_NAME);
        RIntake = hardwareMap.get(Servo.class, DeviceNames.RIGHT_INTAKE_SERVO_NAME);
         LeftHook = hardwareMap.get(Servo.class,DeviceNames.LEFT_HOOK_NAME);
         RightHook = hardwareMap.get(Servo.class,DeviceNames.RIGHT_HOOK_NAME);
         // LSlide = hardwareMap.get(Servo.class, DeviceNames.LEFT_FUNNEL_NAME);
        //  RSlide = hardwareMap.get(Servo.class,DeviceNames.RIGHT_FUNNEL_NAME);
        ///  LeftLocker = hardwareMap.get(Servo.class, "ll");
        // RightLocker = hardwareMap.get(Servo.class, "rl");


        BackLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        FrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        LEHook.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();
        telemetry.addLine("started ");
        telemetry.update();

        while (opModeIsActive()) {

            forwardBackward(-gamepad1.left_stick_y);
            strafe(gamepad1.left_stick_x);
            turn(-gamepad1.right_stick_x);
            Elevators(gamepad2.left_stick_y);
            Intake.setPower(gamepad2.right_trigger);
            Intake.setPower(-gamepad2.left_trigger);

            if (gamepad2.right_trigger > 0){
                telemetry.addLine("right trigger  pressed");

            }
            if (gamepad2.left_trigger > 0){
                telemetry.addLine("left trigger  pressed");

            }



            if (gamepad2.a) {
                grab();
            }


            if (gamepad2.b) {
                take();
            }


            if (gamepad2.x) {
                MoveIntake();

            }

            if (gamepad2.y) {
                ReturnIntake();
            }
/**
 if (gamepad2.dpad_up) {
 MoveSlide();
 }

 if (gamepad2.dpad_down) {
 ReturnSlide();
 }

 if (gamepad2.dpad_left) {
 CloseE();
 }

 if (gamepad2.dpad_right) {
 OpenE();
 }

 if (gamepad2.left_bumper) {
 CloseME();
 }

 if (gamepad2.right_bumper) {
 OpenME();
 }
 **/
            telemetry.update();


        }
    }
}


