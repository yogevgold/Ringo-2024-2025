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
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.systems.Drive;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.Outtake;
import org.firstinspires.ftc.teamcode.values.DeviceNames;
import org.firstinspires.ftc.teamcode.values.Values;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class actionMerge extends LinearOpMode {
    Drive drive;
    Elevator elevator;
    Intake intake;
    Outtake outtake;
    private ServoImplEx FL;
    private ServoImplEx FR;

    private FtcDashboard dash;
    private List<Action> runningActions;

    @Override
    public void runOpMode() throws InterruptedException {
        //FL = hardwareMap.get(ServoImplEx.class,DeviceNames.LEFT_FUNNEL_NAME);
        //FR = hardwareMap.get(ServoImplEx.class,DeviceNames.RIGHT_FUNNEL_NAME);

        dash = FtcDashboard.getInstance();
        runningActions = new ArrayList<>();
        int c = 0;

        int elevatorHeightCM = 0;
        double Angle = 0;
        double intakePower = 0;

        int bCount = 0;

        drive = new Drive(hardwareMap);
        elevator = new Elevator(hardwareMap);
        intake = new Intake(hardwareMap);
        outtake = new Outtake(hardwareMap);


        Action contActions;
        Actions.runBlocking(elevator.moveToZero());
        waitForStart();

        while (opModeIsActive()){
            c++;
            telemetry.addData("C: ", c);
            telemetry.update();
            List<Action> newActions = new ArrayList<>();

            if (gamepad2.dpad_up) {
                telemetry.addLine("dpadUp pressed");
                newActions.add(new SequentialAction(
                        new InstantAction(()->intake.horizontalslides(0.15)),
                        new SleepAction(0.5),
                        new InstantAction(()->intake.setIntakeServo(0.0))
                ));
            }else if (gamepad2.dpad_down) {
                telemetry.addLine("dpadDown pressed");
                newActions.add(new SequentialAction(
                        new InstantAction(()->intake.horizontalslides(0.0)),
                        new SleepAction(0.5),
                        new InstantAction(()->intake.setIntakeServo(0.5))
                ));
            } else if (gamepad2.dpad_right) {
                newActions.add(new SequentialAction(
                new InstantAction(()->intake.setIntakeServo(0.1))
                ));
///////////////////////////////
            } else telemetry.addLine("" );

            if(gamepad1.b) {
                newActions.add(new SequentialAction(
                new InstantAction(() -> outtake.MoveFunnel(0.8))
                ));
            }

            if(gamepad1.a) {
                newActions.add(new SequentialAction(
                        new InstantAction(() -> outtake.MoveFunnel(0.3))
                ));
            }
            //updated TODO: remove after first competition, for git testing purposes

/*
            if(gamepad1.a) elevatorHeightCM = 0;
            if(gamepad1.y) elevatorHeightCM = Values.SECOUND_BUCKET_HEIGHT_CM;
            if (gamepad1.x) elevatorHeightCM = Values.FIRST_BUCKET_HEIGHT_CM;
*/


            contActions = new ParallelAction(
                    drive.TeleDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x),
                    elevator.moveCM(gamepad1.right_trigger - gamepad1.left_trigger),
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
        }
    }
}
