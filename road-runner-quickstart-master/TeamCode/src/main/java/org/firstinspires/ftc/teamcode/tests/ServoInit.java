package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.values.DeviceNames;

@TeleOp
public class ServoInit extends LinearOpMode {

    private ServoImplEx leftIntake;
    private ServoImplEx rightIntake;
    private ServoImplEx leftHorizontal;
    private ServoImplEx rightHorizontal;


    @Override
    public void runOpMode() throws InterruptedException {
        leftIntake = hardwareMap.get(ServoImplEx.class, DeviceNames.LEFT_INTAKE_SERVO_NAME);
        rightIntake = hardwareMap.get(ServoImplEx.class, DeviceNames.RIGHT_INTAKE_SERVO_NAME);
        leftHorizontal = hardwareMap.get(ServoImplEx.class, DeviceNames.LEFT_HORIZONTAL_SLIDE_NAME);
        rightHorizontal = hardwareMap.get(ServoImplEx.class, DeviceNames.RIGHT_HORIZONTAL_SLIDE_NAME);
        //leftHorizontal.setDirection(ServoImplEx.Direction.REVERSE);
        rightIntake.setDirection(ServoImplEx.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                leftIntake.setPosition(0.0);
                rightIntake.setPosition(0.0);
            }

            if (gamepad1.b) {
                leftIntake.setPosition(0.5);
                rightIntake.setPosition(0.5);
            }

            if (gamepad1.y) {
                leftHorizontal.setPosition(0);
                rightHorizontal.setPosition(0);
            }

            if (gamepad1.x) {
                leftHorizontal.setPosition(0.2);
                rightHorizontal.setPosition(0.2);
            }
        }

    }
}
