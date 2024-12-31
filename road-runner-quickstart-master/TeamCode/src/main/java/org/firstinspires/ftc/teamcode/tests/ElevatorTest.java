package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.values.DeviceNames;


@TeleOp
public class ElevatorTest extends LinearOpMode {

    private DcMotorEx EL;
    private DcMotorEx ER;

    public void Elevator(double x) {
        EL.setPower(x);
        ER.setPower(x);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        EL = hardwareMap.get(DcMotorEx.class, DeviceNames.LEFT_ELEVATOR_NAME);
        ER = hardwareMap.get(DcMotorEx.class, DeviceNames.RIGHT_ELEVATOR_NAME);
        ER.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()) {
            Elevator(gamepad1.right_stick_y);
        }
    }
}
