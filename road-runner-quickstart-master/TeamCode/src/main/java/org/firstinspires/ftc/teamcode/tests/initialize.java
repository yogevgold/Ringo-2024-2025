package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.values.DeviceNames;

@TeleOp
public class initialize extends LinearOpMode {


    public ServoImplEx leftSlide;
    public ServoImplEx rightSlide;
    public ServoImplEx LeftIntake;
    public ServoImplEx RightIntake;
    public ServoImplEx LeftFunnel;

    public ServoImplEx RightFunnel;

    public void setter(double set){
        leftSlide.setPosition(set);
        rightSlide.setPosition(set);
        LeftIntake.setPosition(set);
        RightIntake.setPosition(set);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        leftSlide= hardwareMap.get(ServoImplEx.class, "LHS");
        rightSlide= hardwareMap.get(ServoImplEx.class, "RHS");
        LeftFunnel = hardwareMap.get(ServoImplEx.class, DeviceNames.LEFT_FUNNEL_NAME);
        RightFunnel = hardwareMap.get(ServoImplEx.class, DeviceNames.RIGHT_FUNNEL_NAME);
        LeftFunnel.setDirection(Servo.Direction.REVERSE);


//        LeftIntake= hardwareMap.get(ServoImplEx.class, "LIS");
//        RightIntake= hardwareMap.get(ServoImplEx.class, "RIS");

        leftSlide.setPwmRange(new PwmControl.PwmRange(500, 2500));
        rightSlide.setPwmRange(new PwmControl.PwmRange(500, 2500));
//        LeftIntake.setPwmRange(new PwmControl.PwmRange(500, 2500));
//        RightIntake.setPwmRange(new PwmControl.PwmRange(500, 2500));

        //leftSlide.setPwmEnable();
        //rightSlide.setPwmEnable();
        //LeftIntake.setPwmEnable();
        //RightIntake.setPwmEnable();

        rightSlide.setDirection(Servo.Direction.REVERSE);
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
//                rightSlide.setPosition(20.0 / 300.0);
//            }
            if (gamepad1.a) {
                LeftFunnel.setPosition(0);
                RightFunnel.setPosition(0);
            }

            telemetry.addData("type left slide: ", leftSlide.getPwmRange());
            telemetry.addData("type right slide: ", rightSlide.getPwmRange());
            //telemetry.addData("type left servo: ", LeftIntake.getPwmRange());
            //telemetry.addData("type right servo: ", RightIntake.getPwmRange());
            telemetry.update();
        }
    }
}