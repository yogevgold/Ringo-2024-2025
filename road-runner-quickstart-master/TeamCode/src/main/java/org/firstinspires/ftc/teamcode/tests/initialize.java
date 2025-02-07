package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.values.DeviceNames;

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
    public Servo SERVO_CONTROL_00;
    public Servo SERVO_CONTROL_05;

    @Override
    public void runOpMode() throws InterruptedException {
        PincerGrab= hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_GRAB_NAME);
        PincerArmLeft = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ARM_LEFT_NAME);
        PincerArmRight = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ARM_RIGHT_NAME);
        PincerRoll = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ROLL_NAME);
        PincerTurn = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_TURN_NAME);
        RightIntake = hardwareMap.get(ServoImplEx.class, DeviceNames.RIGHT_INTAKE_SERVO_NAME);
        LeftIntake = hardwareMap.get(ServoImplEx.class, DeviceNames.LEFT_INTAKE_SERVO_NAME);
        LeftHorizontal = hardwareMap.get(Servo.class, DeviceNames.LEFT_HORIZONTAL_SLIDE_NAME);
        RightHorizontal = hardwareMap.get(Servo.class, DeviceNames.RIGHT_HORIZONTAL_SLIDE_NAME);
        PincerArmLeft.setDirection(Servo.Direction.REVERSE);
        LeftHorizontal.setDirection(Servo.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
//                PincerArmLeft.setPosition(0);
//                PincerArmRight.setPosition(0);
//                PincerTurn.setPosition(0);
                PincerRoll.setPosition(0.48);
            }


            else if (gamepad1.b) {
//                PincerArmLeft.setPosition(0.04);
//                PincerArmRight.setPosition(0.04);
//                PincerTurn.setPosition(0.18);
                PincerRoll.setPosition(0.82);


                
            } else if (gamepad1.x) {
//                PincerArmLeft.setPosition(0.08);
//                PincerArmRight.setPosition(0.08);
//                PincerTurn.setPosition(0.18);
//                PincerRoll.setPosition(0.48);
            }
        }
    }
}
