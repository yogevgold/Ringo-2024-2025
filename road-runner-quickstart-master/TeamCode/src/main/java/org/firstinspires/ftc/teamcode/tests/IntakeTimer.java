package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.Drive;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.Pincer;
import org.firstinspires.ftc.teamcode.values.Values;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class IntakeTimer extends LinearOpMode {
    Intake intake;
    Pincer pincer;

    private FtcDashboard dash;
    private List<Action> runningActions;
    private ElapsedTime IntakeTimer;



    @Override
    public void runOpMode() throws InterruptedException {
        int AutoIntake = 0;

        pincer = new Pincer(hardwareMap);
        intake = new Intake(hardwareMap);

        IntakeTimer = new ElapsedTime();
        IntakeTimer.startTime();

        Action contActions;
        waitForStart();
        while (opModeIsActive()) {
            List<Action> newActions = new ArrayList<>();


            if (gamepad2.x) {
                //IntakeTimer.reset();
                AutoIntake = 0;
                if (AutoIntake == 0) {
                    newActions.add(new SequentialAction(
                            new InstantAction(()->intake.horizontalslides(Values.HORIZONTAL_SLIDES_OPEN)),
                            new InstantAction(()->intake.setIntakeServo(Values.INTAKE_UP))
                    ));
                    AutoIntake = 1;
                }

                if (AutoIntake == 1) {
                        IntakeTimer.reset();
                        intake.IntakeMotor.setPower(0.2);
                        if (IntakeTimer.seconds() >= 2) {
                            intake.IntakeMotor.setPower(0);
                            AutoIntake = 2;
                        }
                }

                if (AutoIntake == 2) {
                    newActions.add(new SequentialAction(
                            new InstantAction(()->intake.horizontalslides(Values.HORIZONTAL_SLIDES_CLOSE)),
                            new InstantAction(()->intake.setIntakeServo(Values.INTAKE_CLOSE))
                    ));
                    AutoIntake = 0;
                }
            }




            telemetry.addData("RSV: ", gamepad1.right_stick_x);
            telemetry.update();
        }
    }
}
