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

import org.firstinspires.ftc.teamcode.systems.Drive;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.Pincer;
import org.firstinspires.ftc.teamcode.systems.Setup;
import org.firstinspires.ftc.teamcode.values.Values;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class actionmerge_setupd extends LinearOpMode {
    Drive drive;
    Elevator elevator;
    Intake intake;
    Pincer pincer;
    Setup setup;
    private FtcDashboard dash;
    private List<Action> runningActions;




    @Override
    public void runOpMode() throws InterruptedException {

        dash = FtcDashboard.getInstance();
        runningActions = new ArrayList<>();
        int c = 0;
        int elevatorHeightCM = 0;
        boolean intakeOpen = false;

        //new class instances
        drive = new Drive(hardwareMap);
        elevator = new Elevator(hardwareMap);
        intake = new Intake(hardwareMap);
        pincer = new Pincer(hardwareMap);
        
        //setup class instances
        setup = new Setup();
        setup.pincer=pincer;
        setup.intake=intake;
        setup.elevator=elevator;
        setup.drive=drive;
        Action contActions;
        waitForStart();
        while (opModeIsActive()) {
            c++;
            telemetry.addData("C: ", c);
            telemetry.update();
            List<Action> newActions = new ArrayList<>();
            setup.newActions = newActions;

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



            if (gamepad1.dpad_left) {
                intakeOpen = true;
                newActions.add(new SequentialAction(
                        setup.pincergrabclose,
                        setup.sleep03,
                        setup.pincerrollwaiting,
                        setup.sleep03,
                        setup.pincerturnwaiting,
                        setup.sleep03,
                        setup.pincerarmwating,


                        setup.intakeslidesopen,
                        setup.intakeup
                ));
            }

            if (gamepad1.dpad_right) {
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
                        setup.pincerturnwaiting,
                        setup.sleep03,
                        setup.intakeslidesopen,
                        setup.pincerarmwating,

                        setup.sleep05,

                        setup.intakeslidesclose
                ));
            }

            if (gamepad1.dpad_up) {
                intakeOpen = true;
                newActions.add(setup.intakeup);
            }

            if (gamepad1.dpad_down) {
                intakeOpen = true;
                newActions.add(setup.intakedown);
            }



            if (gamepad1.right_bumper) {
                newActions.add(new SequentialAction(
                        setup.pincergrabopen
                ));
            }

            if (gamepad1.left_bumper) {
                newActions.add(new SequentialAction(
                        setup.pincergrabclose
                ));
            }


            if (gamepad1.right_stick_button) {
                intakeOpen = false;
                newActions.add(new SequentialAction(
                        setup.intakeslidesclose,
                        setup.intakeclose
                ));
            }




            contActions = new ParallelAction(
                    drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x, intakeOpen, true),
                    elevator.moveByPower(-gamepad1.right_stick_y),
//                      elevator.moveCM(elevatorHeightCM),
                    intake.IntakePower(gamepad1.right_trigger - gamepad1.left_trigger)
            );
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

