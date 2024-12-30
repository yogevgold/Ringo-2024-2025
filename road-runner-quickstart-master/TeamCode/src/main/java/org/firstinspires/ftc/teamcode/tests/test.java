package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.BetterGamepad;

@TeleOp
public class test extends LinearOpMode {

    private ServoImplEx Lservo;
    private ServoImplEx Rservo;


    @Override
    public void runOpMode() throws InterruptedException {
        Rservo = hardwareMap.get(ServoImplEx.class, "Rs");
        Lservo = hardwareMap.get(ServoImplEx.class, "Ls");
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                Rservo.setPosition(0);
                Lservo.setPosition(0);
            }

            if (gamepad1.b) {
                Rservo.setPosition(0.5);
                Lservo.setPosition(0.5);
            }
        }
    }
}
