package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

@TeleOp
public class initialize extends LinearOpMode {


    public Servo PincerArmLeft;
    public Servo PincerArmRight;
    public ServoImplEx PincerTurn;
    public ServoImplEx PincerGrab;
    public ServoImplEx PincerRoll;
    public Servo RightIntake;
    public Servo LeftIntake;
    public Servo LeftHorizontal;
    public Servo RightHorizontal;
    public ServoImplEx LeftFunnel;
    public ServoImplEx RightFunnel;

    public Servo SERVO_EXPANSION_00;
    public Servo SERVO_EXPANSION_01;
    public Servo SERVO_EXPANSION_02;
    public Servo SERVO_EXPANSION_03;
    public Servo SERVO_EXPANSION_04;
    public Servo SERVO_EXPANSION_05;

    public Servo SERVO_CONTROL_03;
    public Servo SERVO_CONTROL_04;
    public Servo SERVO_CONTROL_05;

    @Override
    public void runOpMode() throws InterruptedException {
//        PincerGrab= hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_GRAB_NAME);
//        PincerArmLeft = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ARM_LEFT_NAME);
//        PincerArmRight = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ARM_RIGHT_NAME);
//        PincerRoll = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ROLL_NAME);
//        PincerTurn = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_TURN_NAME);
//        RightIntake = hardwareMap.get(ServoImplEx.class, DeviceNames.RIGHT_INTAKE_SERVO_NAME);
//        LeftIntake = hardwareMap.get(ServoImplEx.class, DeviceNames.LEFT_INTAKE_SERVO_NAME);
//        LeftHorizontal = hardwareMap.get(Servo.class, DeviceNames.LEFT_HORIZONTAL_SLIDE_NAME);
//        RightHorizontal = hardwareMap.get(Servo.class, DeviceNames.RIGHT_HORIZONTAL_SLIDE_NAME);
//        PincerArmLeft.setDirection(Servo.Direction.REVERSE);
//        LeftHorizontal.setDirection(Servo.Direction.REVERSE);

        SERVO_EXPANSION_00 = hardwareMap.get(Servo.class, "00E"); // intake right
        SERVO_EXPANSION_01 = hardwareMap.get(Servo.class, "01E"); // intake left
        SERVO_EXPANSION_02 = hardwareMap.get(Servo.class, "02E"); // horizontal right
        SERVO_EXPANSION_03 = hardwareMap.get(Servo.class, "03E"); // horizontal left
        SERVO_EXPANSION_04 = hardwareMap.get(Servo.class, "04E"); // grab
        SERVO_EXPANSION_05 = hardwareMap.get(Servo.class, "05E"); // Roll

        SERVO_CONTROL_03 = hardwareMap.get(Servo.class, "03C"); // Arm Right
        SERVO_CONTROL_04 = hardwareMap.get(Servo.class, "04C"); // turn
        SERVO_CONTROL_05 = hardwareMap.get(Servo.class, "05C"); // Arm Left

//        RightFunnel = hardwareMap.get(ServoImplEx.class, DeviceNames.RIGHT_FUNNEL_NAME);
//        LeftFunnel.setDirection(Servo.Direction.REVERSE);

//        LeftIntake= hardwareMap.get(ServoImplEx.class, "LIS");
//        RightIntake= hardwareMap.get(ServoImplEx.class, "RIS");

//        LeftIntake.setPwmRange(new PwmControl.PwmRange(500, 2500));
//        RightIntake.setPwmRange(new PwmControl.PwmRange(500, 2500));

        //leftSlide.setPwmEnable();
        //rightSlide.setPwmEnable();
        //LeftIntake.setPwmEnable();
        //RightIntake.setPwmEnable();

        //rightSlide.setDirection(Servo.Direction.REVERSE);
        // LeftIntake.setDirection(Servo.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                SERVO_CONTROL_04.setPosition(0);
                sleep(200);
                SERVO_CONTROL_04.setPosition(0.2);
            } else if (gamepad1.b) {
                SERVO_CONTROL_05.setPosition(0);
                sleep(200);
                SERVO_CONTROL_05.setPosition(0.2);
            } else if (gamepad1.y) {
                SERVO_CONTROL_03.setPosition(0);
                sleep(200);
                SERVO_CONTROL_03.setPosition(0.2);
            } else if (gamepad1.x) {
                SERVO_EXPANSION_04.setPosition(0);
                sleep(200);
                SERVO_EXPANSION_04.setPosition(0.2);
            }
        }
    }
}