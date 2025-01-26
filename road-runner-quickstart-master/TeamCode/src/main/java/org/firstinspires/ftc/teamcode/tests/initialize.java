package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PwmControl;
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
    public ServoImplEx RightIntake;
    public ServoImplEx LeftFunnel;
    public ServoImplEx RightFunnel;



    @Override
    public void runOpMode() throws InterruptedException {
        PincerGrab= hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_GRAB_NAME);
        PincerArmLeft = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ARM_LEFT_NAME);
        PincerArmRight = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ARM_RIGHT_NAME);
        PincerRoll = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_ROLL_NAME);
        PincerTurn = hardwareMap.get(ServoImplEx.class, DeviceNames.PINCER_TURN_NAME);
        PincerArmLeft.setDirection(Servo.Direction.REVERSE);
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
//            if (gamepad1.a) {
//                leftSlide.setPosition(20.0 / 300.0);
//                rightSlide.setPosition(20.0 / 300.0);
//            }
//
//            if (gamepad1.b) {
//                leftSlide.setPosition(0.25);
//                rightSlide.setPosition(0.25);
//            }
//
//            if (gamepad1.y) {
//                leftSlide.setPosition(20.0 / 300.0);
//                rightSlide.setPosition(20.0 / 300.0)
//            }
            if (gamepad1.a) {
//                PincerArmLeft.setPosition(0.093);
//                PincerArmRight.setPosition(0.093);
//                PincerRoll.setPosition(0.48);
//                PincerGrab.setPosition(0.03);
                PincerTurn.setPosition(0.1);
            }else if (gamepad1.b) {
                PincerArmLeft.setPosition(0.08);
                PincerArmRight.setPosition(0.08);
//                PincerRoll.setPosition(0.82);
//                PincerGrab.setPosition(0.3);
                PincerTurn.setPosition(0.3);
            } else if (gamepad1.y) {
                PincerTurn.setPosition(0.3);
            } else if (gamepad1.right_trigger > 0) {
                PincerRoll.setPosition(gamepad1.right_trigger);
                //PincerArmRight.setPosition(gamepad1.right_trigger);
                telemetry.addData("pos", PincerRoll.getPosition());
            }
        }
    }
}