package org.firstinspires.ftc.teamcode.tests;




import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
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

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class Built_in_test extends LinearOpMode {
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
                        //pincer grab
                        setup.pincergrabopen,
                        new SleepAction(1),
                        setup.pincergrabclose,
                        new SleepAction(1),
                        //pincer roll
                        setup.pincerrollin,
                        new SleepAction(1),
                        setup.pincerrollout,
                        new SleepAction(1),
                        setup.pincerrollwaiting,
                        new SleepAction(1),
                        //pincer turn
                        setup.pincerturnin,
                        new SleepAction(1),
                        setup.pincerturnout,
                        new SleepAction(1),
                        setup.pincerturnwaiting,
                        new SleepAction(1),
                        //pincer arm positions
                        setup.pincerarmin,
                        new SleepAction(1),
                        setup.pincerarmout,
                        new SleepAction(1),
                        setup.pincerarmwating,
                        new SleepAction(1),
                        //intake slides
                        setup.intakeslidesopen,
                        new SleepAction(1),
                        setup.intakeslidesclose,
                        new SleepAction(1),
                        //intake hight pos
                        setup.intakeup,
                        new SleepAction(1),
                        setup.intakedown,
                        new SleepAction(1),
                        setup.intakeclose


                ));

                //elevatorHeightCM = Values.SECOUND_BUCKET_HEIGHT_CM;
            }


            contActions = new ParallelAction(
                    drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x, intakeOpen, true),
                    elevator.moveByPower(-gamepad1.right_stick_y),
//                  elevator.moveCM(elevatorHeightCM),
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

