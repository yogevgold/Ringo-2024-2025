package org.firstinspires.ftc.teamcode.Teleop;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.systems.Drive;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.Outtake;
import org.firstinspires.ftc.teamcode.systems.Pincer;
import org.firstinspires.ftc.teamcode.values.Values;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class teletest extends LinearOpMode {
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
        int bCount = 0;
        double Angle = 0;
        double intakePower = 0;
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
            //if(gamepad1.y){elevatorHeightCM = Values.SECOUND_BUCKET_HEIGHT_CM;}
            //if(gamepad1.b){elevatorHeightCM = Values.FIRST_BUCKET_HEIGHT_CM;}
            //if(gamepad1.x){elevatorHeightCM = Values.SECOUND_BAR_HEIGHT_CM;}
            if(gamepad1.a){elevatorHeightCM = 0;}
            /* if (gamepad2.a){
                telemetry.addLine("boop");
                newActions.add(new SequentialAction(
//                       pincer.PincerPosition(Values.ARM_WATING,Values.TURN_WATING,Values.ROLL_IN,Values.GRAB_OPEN))
                        new InstantAction(()->pincer.pincerTurn(Values.TURN_OUT)
                ))
            } */


            if (gamepad2.dpad_left) {
                intakeOpen = true;
                newActions.add(new SequentialAction(
                        new InstantAction(()->intake.horizontalslides(Values.HOTIZONTAL_SLIDES_OPEN)),
                        new SleepAction(0.5),
                        new InstantAction(()->intake.setIntakeServo(Values.INTAKE_UP))
                ));
            }else if (gamepad2.dpad_right) {
                intakeOpen = false;
                newActions.add(new SequentialAction(
                        new InstantAction(()->intake.horizontalslides(Values.HOTIZONTAL_SLIDES_CLOSE)),
                        new SleepAction(0.5),
                        new InstantAction(()->intake.setIntakeServo(Values.INTAKE_CLOSE))
                ));
            } else if (gamepad2.dpad_up) {
                intakeOpen = true;
                newActions.add(new InstantAction(()->intake.setIntakeServo(Values.INTAKE_UP)));
            } else if (gamepad2.dpad_down) {
                intakeOpen = true;
                newActions.add(new InstantAction(()->intake.setIntakeServo(Values.INTAKE_DOWN)));
            }
            else telemetry.addLine("");


//                newActions.add(new InstantAction(()-> outtake.MoveFunnel(Values.FUNNEL_CLOSED)));
//            }
//            else if(gamepad1.a){
//                newActions.add(new InstantAction(()-> outtake.MoveFunnel(Values.FUNNEL_OPEN)));
//            }


            if (intakeOpen) {
                contActions = new ParallelAction(
                        drive.intakeOpenDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x),
                        elevator.moveCM(elevatorHeightCM),
                        intake.IntakePower(gamepad2.right_stick_y)


                        //new InstantAction(()-> intake.horizontalslides(gamepad2.left_stick_x))
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

            } else if (!intakeOpen) {
                contActions = new ParallelAction(
                        drive.intakeCloseDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x),
                        elevator.moveCM(elevatorHeightCM),
                        intake.IntakePower(gamepad2.right_stick_y),
                        intake.IntakePower(-gamepad2.right_trigger / 7));
                TelemetryPacket packet = new TelemetryPacket();




                newActions.add(contActions);
                for (Action action : runningActions) {
                    action.preview(packet.fieldOverlay());
                    if (action.run(packet)) {
                        newActions.add(action);
                    }
                }
                runningActions = newActions;
                dash.sendTelemetryPacket(packet);            }
        }
    }
}
