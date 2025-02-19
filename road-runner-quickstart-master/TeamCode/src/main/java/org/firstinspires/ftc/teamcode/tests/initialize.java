package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.values.DeviceNames;

@TeleOp
public class initialize extends LinearOpMode {


    public Servo PincerArmLeft;
    public Servo PincerArmRight;
    public Servo PincerTurn;
    public Servo PincerGrab;
    public Servo PincerRoll;
    public Servo RightIntake;
    public Servo LeftIntake;
    public Servo LeftHorizontal;
    public Servo RightHorizontal;
    public Servo LeftFunnel;
    public Servo RightFunnel;

    public DcMotor EL;
    public DcMotor ER;



    @Override
    public void runOpMode() throws InterruptedException {
        PincerGrab= hardwareMap.get(Servo.class, DeviceNames.PINCER_GRAB_NAME);
        PincerArmLeft = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ARM_LEFT_NAME);
        PincerArmRight = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ARM_RIGHT_NAME);
        PincerRoll = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ROLL_NAME);
        PincerTurn = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_TURN_NAME);
        RightIntake = hardwareMap.get(ServoImplEx.class, DeviceNames.RIGHT_INTAKE_SERVO_NAME);
        LeftIntake = hardwareMap.get(ServoImplEx.class, DeviceNames.LEFT_INTAKE_SERVO_NAME);
        LeftHorizontal = hardwareMap.get(Servo.class, DeviceNames.LEFT_HORIZONTAL_SLIDE_NAME);
        RightHorizontal = hardwareMap.get(Servo.class, DeviceNames.RIGHT_HORIZONTAL_SLIDE_NAME);
        ER = hardwareMap.get(DcMotor.class, DeviceNames.RIGHT_ELEVATOR_NAME);
        EL = hardwareMap.get(DcMotor.class, DeviceNames.LEFT_ELEVATOR_NAME);
        RightIntake.setDirection(Servo.Direction.REVERSE);
        PincerArmLeft.setDirection(Servo.Direction.REVERSE);
        LeftHorizontal.setDirection(Servo.Direction.REVERSE);
        PincerTurn.setDirection(Servo.Direction.REVERSE);


        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                PincerTurn.setPosition(0); //w
//                PincerArmLeft.setPosition(0); //w
//                PincerArmRight.setPosition(0); //w
//                PincerRoll.setPosition(0.81); //in
//                PincerGrab.setPosition(0.3); //open
//                LeftIntake.setPosition(0.6);
//                RightIntake.setPosition(0.6);
            }
            if (gamepad1.b) {
                PincerTurn.setPosition(0.16); //out
//                PincerArmLeft.setPosition(0.09); //out
//                PincerArmRight.setPosition(0.09); //out
//                PincerRoll.setPosition(0.46); //out
//                PincerGrab.setPosition(0); //close
//                LeftIntake.setPosition(0.5);
//                RightIntake.setPosition(0.5);
            }
            if (gamepad1.x) {
//                  PincerArmLeft.setPosition(0); //in
//                  PincerArmRight.setPosition(0); //in
                  PincerTurn.setPosition(0.18);
//                PincerRoll.setPosition(0.48);
//                LeftIntake.setPosition(0.13);
//                RightIntake.setPosition(0.13);
            }
            //EL.setPower(-gamepad1.left_stick_y);
            //ER.setPower(gamepad1.left_stick_y);
        }
    }
}
