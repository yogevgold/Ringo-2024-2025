package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.values.DeviceNames;
import org.firstinspires.ftc.teamcode.values.Values;

@TeleOp
public class PIDElevatorInit extends LinearOpMode {
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private PIDFCoefficients pid;
    public int currentHeight = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        double p=3,i=0.0,d=0.0,f=0.0;
        pid = new PIDFCoefficients(p, i, d, f);
        leftMotor = hardwareMap.get(DcMotorEx.class, DeviceNames.LEFT_ELEVATOR_NAME);
        rightMotor = hardwareMap.get(DcMotorEx.class, DeviceNames.RIGHT_ELEVATOR_NAME);
        leftMotor.setTargetPositionTolerance(1);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pid);
        rightMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, pid);

        waitForStart();

        while (opModeIsActive()){
            if(gamepad1.a) currentHeight=20;
            if(gamepad1.b)currentHeight=35;
            if(gamepad1.x) currentHeight = 0;
            leftMotor.setTargetPosition(currentHeight);
            rightMotor.setTargetPosition(currentHeight);

            telemetry.addData("Current Height left", leftMotor.getCurrentPosition()/ 751.8); //751.8 = ppr
            telemetry.speak("Hello");
            telemetry.addData("P: ", p);
            telemetry.addData("I: ", i);
            telemetry.addData("D: ", d);
            telemetry.addData("FF: ", f);



        }
    }
}
