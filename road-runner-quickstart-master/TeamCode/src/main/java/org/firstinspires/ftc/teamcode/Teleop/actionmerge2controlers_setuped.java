package org.firstinspires.ftc.teamcode.Teleop;
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
import org.firstinspires.ftc.teamcode.systems.Setup;
import org.firstinspires.ftc.teamcode.values.Values;


import java.util.ArrayList;
import java.util.List;

public class actionmerge2controlers_setuped extends LinearOpMode {
    Drive drive;
    Elevator elevator;
    Intake intake;
    Pincer pincer;
    Setup setup;

    private FtcDashboard dash;
    private List<Action> runningActions;
    private ElapsedTime IntakeTimer;



    @Override
    public void runOpMode() throws InterruptedException {

        dash = FtcDashboard.getInstance();
        runningActions = new ArrayList<>();
        int c = 0;
        int elevatorHeightCM = 0;
        boolean intakeOpen = false;
        int AutoIntake = 0;

        drive = new Drive(hardwareMap);
        elevator = new Elevator(hardwareMap);
        intake = new Intake(hardwareMap);
        pincer = new Pincer(hardwareMap);
        
        //setup connections
        setup = new Setup();
        setup.pincer=pincer;
        setup.intake=intake;
        setup.elevator=elevator;
        setup.drive=drive;
        List<Action> newActions = new ArrayList<>();
        setup.newActions = newActions;
        
        IntakeTimer = new ElapsedTime();
        IntakeTimer.startTime();

        Action contActions;
        waitForStart();
        while (opModeIsActive()) {
            c++;
            telemetry.addData("C: ", c);
            telemetry.update();


            if(gamepad1.y) {
                newActions.add(new SequentialAction(
                        setup.pincergrabclose,
                        setup.sleep03,
                        setup.pincerrollout,
                        setup.sleep03,
                        setup.pincerturnwaiting,
                        setup.sleep03,
                        setup.pincerarmwating
                ));
                elevatorHeightCM = Values.SECOUND_BUCKET_HEIGHT_CM;
            }

            if(gamepad1.b) {
                newActions.add(new SequentialAction(
                        setup.pincergrabclose,
                        setup.sleep03,
                        setup.pincerrollout,
                        setup.sleep03,
                        setup.pincerturnwaiting,
                        setup.sleep03,
                        setup.pincerarmwating
                ));
                elevatorHeightCM = Values.FIRST_BUCKET_HEIGHT_CM;
            }

            if(gamepad1.x) {
                newActions.add(new SequentialAction(
                        setup.pincergrabclose,
                        setup.sleep03,
                        setup.pincerrollout,
                        setup.sleep03,
                        setup.pincerturnwaiting,
                        setup.sleep03,
                        setup.pincerarmwating
                ));
                elevatorHeightCM = Values.SECOUND_BAR_HEIGHT_CM;
            }

            if(gamepad1.right_bumper) {
                newActions.add(new SequentialAction(
                        setup.pincergrabclose,
                        setup.sleep03,
                        setup.pincerrollout,
                        setup.sleep03,
                        setup.pincerturnwaiting,
                        setup.sleep03,
                        setup.pincerarmwating
                ));
                elevatorHeightCM = Values.SECOUND_BUCKET_HEIGHT_CM -10;
            }

            if(gamepad1.left_bumper) {
                newActions.add(new SequentialAction(
                        setup.pincergrabclose,
                        setup.sleep03,
                        setup.pincerrollout,
                        setup.sleep03,
                        setup.pincerturnwaiting,
                        setup.sleep03,
                        setup.pincerarmwating
                ));
                elevatorHeightCM = Values.SECOUND_BAR_HEIGHT_CM - 10;
            }

            if(gamepad1.a) {
                newActions.add(new SequentialAction(
                        setup.pincergrabclose,
                        setup.sleep03,
                        setup.pincerrollout,
                        setup.sleep03,
                        setup.pincerturnout,
                        setup.sleep03,
                        setup.pincerarmout
                ));
                elevatorHeightCM = 0;
            }



            if (gamepad2.dpad_left) {
                intakeOpen = true;
                newActions.add(new SequentialAction(
                        setup.pincergrabclose,
                        setup.sleep03,
                        setup.pincerrollwaiting,
                        setup.sleep03,
                        setup.pincerturnwaiting,
                        setup.sleep03,
                        setup.pincerarmwating,
                        setup.sleep03,


                        setup.intakeslidesopen,
                        setup.intakeup
                ));
            }

            if (gamepad2.x) {
                intakeOpen = false;
                newActions.add(new SequentialAction(
                        setup.pincergrabopen,
                        setup.sleep03,
                        setup.pincerrollwaiting,
                        setup.sleep03,
                        setup.pincerturnwaiting,
                        setup.sleep03,
                        setup.pincerarmwating,

                        setup.sleep05,

                        setup.intakeslidesclose,
                        setup.intakeclose,

                        setup.sleep05,

                        setup.pincergrabopen,
                        setup.sleep03,
                        setup.pincerrollin,
                        setup.sleep03,
                        setup.pincerturnin,
                        setup.sleep03,
                        setup.pincerarmin,

                        setup.sleep05,

                        setup.pincergrabclose,

                        setup.sleep03,

                        setup.pincergrabclose,
                        setup.sleep03,
                        setup.pincerrollwaiting,
                        setup.sleep03,
                        setup.pincergrabclose,
                        setup.sleep05
                ));


//                    newActions.add(new SequentialAction(
//                            new InstantAction(() -> intake.horizontalslides(Values.HORIZONTAL_SLIDES_OPEN)),
//                            setup.pincerarmwating,
//                            setup.pincergrabclose,
//
//                            setup.sleep08,
//
//                            setup.intakeslidesclose,
//                            setup.intakeclose
//                    ));

            }

            if (gamepad2.y) {
                IntakeTimer.reset();
                intake.IntakeMotor.setPower(-0.2);
                if (IntakeTimer.seconds() >= 2) {
                    intake.IntakeMotor.setPower(0);
                }

                newActions.add(new SequentialAction(
                        new InstantAction(() -> intake.horizontalslides(Values.HORIZONTAL_SLIDES_OPEN)),
                        setup.pincerturnwaiting,
                        setup.pincerarmwating,
                        setup.pincergrabclose,

                        setup.sleep08,

                        setup.intakeslidesclose,
                        setup.intakeclose
                ));
            }

            if (gamepad2.dpad_up) {
                intakeOpen = true;
                newActions.add(setup.intakeup);
            }

            if (gamepad2.dpad_down) {
                intakeOpen = true;
                newActions.add(setup.intakedown);
            }



            if (gamepad2.a) {
                newActions.add(setup.pincergrabopen);
            }

            if (gamepad2.b) {
                newActions.add(setup.pincergrabclose);
            }


            if (gamepad2.dpad_right) {
                intakeOpen = false;
                newActions.add(new SequentialAction(
                        setup.intakeslidesclose,
                        setup.intakeclose
                ));
            }

            contActions = new ParallelAction(
                    drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x, intakeOpen, true),
                    elevator.moveCM(elevatorHeightCM),
//                        elevator.moveByPower(gamepad1.right_stick_y),
//                        elevator.moveByPower(-gamepad1.right_stick_y),
                    intake.IntakePower(gamepad2.right_trigger - gamepad2.left_trigger)
            );
            telemetry.addData("RSV: ", gamepad1.right_stick_x);
            telemetry.update();

            TelemetryPacket packet = new TelemetryPacket();


            newActions.add(contActions);
            for (Action action : runningActions) {
                action.preview(packet.fieldOverlay());
                if (action.run(packet)) {
                    newActions.add(action);
                }
            }
            runningActions = newActions;
            dash.sendTelemetryPacket(packet);
        }
    }
}
