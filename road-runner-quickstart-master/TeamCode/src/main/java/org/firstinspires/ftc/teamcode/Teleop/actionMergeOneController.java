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
import org.firstinspires.ftc.teamcode.values.Values;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class actionMergeOneController extends LinearOpMode {
    Drive drive;
    Elevator elevator;
    Intake intake;
    Pincer pincer;

    private FtcDashboard dash;
    private List<Action> runningActions;

    @Override
    public void runOpMode() throws InterruptedException {

        dash = FtcDashboard.getInstance();
        runningActions = new ArrayList<>();
        int c = 0;
        int elevatorHeightCM = 0;
        boolean intakeOpen = false;


        drive = new Drive(hardwareMap);
        elevator = new Elevator(hardwareMap);
        intake = new Intake(hardwareMap);
        pincer = new Pincer(hardwareMap);

        Action contActions;
        waitForStart();
        while (opModeIsActive()) {
            c++;
            telemetry.addData("C: ", c);
            telemetry.update();
            List<Action> newActions = new ArrayList<>();


            if(gamepad1.y) {
                newActions.add(new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(Values.ROLL_OUT)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(Values.TURN_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerArm(Values.ARM_WATING))
                ));
                elevatorHeightCM = Values.SECOUND_BUCKET_HEIGHT_CM;
            }

            if(gamepad1.b) {
                newActions.add(new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(Values.ROLL_OUT)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(Values.TURN_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerArm(Values.ARM_WATING))
                ));
                elevatorHeightCM = Values.FIRST_BUCKET_HEIGHT_CM;
            }

            if(gamepad1.x) {
                newActions.add(new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(Values.ROLL_OUT)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(Values.TURN_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerArm(Values.ARM_WATING))
                ));
                elevatorHeightCM = Values.SECOUND_BAR_HEIGHT_CM;
            }

            if(gamepad1.a) {
                newActions.add(new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(Values.ROLL_OUT)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(Values.TURN_OUT)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerArm(Values.ARM_OUT))
                ));
                elevatorHeightCM = 0;
            }



            if (gamepad1.dpad_left) {
                intakeOpen = true;
                newActions.add(new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(Values.ROLL_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(Values.TURN_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerArm(Values.ARM_WATING)),
                        new SleepAction(0.3),

                        new InstantAction(()->intake.horizontalslides(Values.HORIZONTAL_SLIDES_OPEN)),
                        new InstantAction(()->intake.setIntakeServo(Values.INTAKE_UP))
                ));
            }

            if (gamepad1.dpad_right) {
                intakeOpen = false;
                newActions.add(new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_OPEN)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(Values.ROLL_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(Values.TURN_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerArm(Values.ARM_WATING)),

                        new SleepAction(0.5),

                        new InstantAction(()->intake.horizontalslides(Values.HORIZONTAL_SLIDES_CLOSE)),
                        new InstantAction(()->intake.setIntakeServo(Values.INTAKE_CLOSE)),

                        new SleepAction(0.5),

                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_OPEN)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(Values.ROLL_IN)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(Values.TURN_IN)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerArm(Values.ARM_IN)),

                        new SleepAction(0.5),

                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),

                        new SleepAction(0.3),

                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(Values.ROLL_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(Values.TURN_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),
                        new InstantAction(()->intake.horizontalslides(Values.HORIZONTAL_SLIDES_OPEN)),
                        new InstantAction(()-> pincer.pincerArm(Values.ARM_WATING)),
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),

                        new SleepAction(0.8),

                        new InstantAction(()->intake.horizontalslides(Values.HORIZONTAL_SLIDES_CLOSE)),
                        new InstantAction(()->intake.setIntakeServo(Values.INTAKE_CLOSE))


                ));
            }

            if (gamepad1.dpad_up) {
                intakeOpen = true;
                newActions.add(new InstantAction(()->intake.setIntakeServo(Values.INTAKE_UP)));
            }

            if (gamepad1.dpad_down) {
                intakeOpen = true;
                newActions.add(new InstantAction(()->intake.setIntakeServo(Values.INTAKE_DOWN)));
            }



            if (gamepad1.right_bumper) {
                newActions.add(new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_OPEN))
                ));
            }

            if (gamepad1.left_bumper) {
                newActions.add(new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE))
                ));
            }


            if (gamepad1.right_stick_button) {
                intakeOpen = false;
                newActions.add(new SequentialAction(
                        new InstantAction(()->intake.horizontalslides(Values.HORIZONTAL_SLIDES_CLOSE)),
                        new InstantAction(()->intake.setIntakeServo(Values.INTAKE_CLOSE))
                ));
            }

            if (gamepad1.left_stick_button) {
                newActions.add(new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(Values.ROLL_OUT)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(Values.TURN_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerArm(Values.ARM_WATING))
                ));
                elevatorHeightCM = Values.SECOUND_BAR_HEIGHT_CM - 10;
            }





            contActions = new ParallelAction(
                    drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x, intakeOpen, true),
                    elevator.moveCM(elevatorHeightCM),
//                        elevator.moveByPower(gamepad1.right_stick_y),
//                        elevator.moveByPower(-gamepad1.right_stick_y),
                    intake.IntakePower(gamepad1.right_trigger - gamepad1.left_trigger)
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
