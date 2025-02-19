package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.values.Values;
import java.util.ArrayList;
import java.util.List;

public class Setup extends LinearOpMode{
    public boolean x=false;
    public Drive drive;
    public Elevator elevator;
    public Intake intake;
    public Pincer pincer;
    private FtcDashboard dash;
    private List<Action> runningActions;



    //sleep action
    public SleepAction sleep02 = new SleepAction(0.2);
    public SleepAction sleep03 = new SleepAction(0.3);
    public SleepAction sleep05 = new SleepAction(0.5);
    public SleepAction sleep08 = new SleepAction(0.8);

    //pincer grab positions
    public Action pincergrabclose = new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE));
    public Action pincergrabopen = new InstantAction(()-> pincer.pincerGrab(Values.GRAB_OPEN));

    //pincer roll positions
    public Action pincerrollin = new InstantAction(()->pincer.pincerRoll(Values.ROLL_IN));
    public Action pincerrollout = new InstantAction(()->pincer.pincerRoll(Values.ROLL_OUT));
    public Action pincerrollwaiting = new InstantAction(()->pincer.pincerRoll(Values.ROLL_WATING));

    //pincer turn positions
    public Action pincerturnin = new InstantAction(()->pincer.pincerTurn(Values.TURN_IN));
    public Action pincerturnout = new InstantAction(()->pincer.pincerTurn(Values.TURN_OUT));
    public Action pincerturnwaiting = new InstantAction(()->pincer.pincerTurn(Values.TURN_WATING));

    // pincer arm positions
    public Action pincerarmin = new InstantAction(()->pincer.pincerArm(Values.ARM_IN));
    public Action pincerarmout = new InstantAction(()->pincer.pincerArm(Values.ARM_OUT));
    public Action pincerarmwating = new InstantAction(()->pincer.pincerArm(Values.ARM_WATING));

    //intake slide positions
    public Action intakeslidesopen = new InstantAction(()->intake.horizontalslides(Values.HORIZONTAL_SLIDES_OPEN));
    public Action intakeslidesclose = new InstantAction(()->intake.horizontalslides(Values.HORIZONTAL_SLIDES_CLOSE));

    //intake rotation positions
    public Action intakeup = new InstantAction(()->intake.setIntakeServo(Values.INTAKE_UP));
    public Action intakedown = new InstantAction(()->intake.setIntakeServo(Values.INTAKE_DOWN));
    public Action intakeclose = new InstantAction(()->intake.setIntakeServo(Values.INTAKE_CLOSE));

    Action contActions;
    public List<Action> newActions;// = new ArrayList<>();
    TelemetryPacket packet = new TelemetryPacket();








    public void tst2(double a ,double b ,double c, double d) {
        newActions.add(new SequentialAction(
                        pincergrabopen,
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(b)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(c)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerArm(d))
        ));
    }
    public void tst() {
        newActions.add(new SequentialAction(
                pincergrabopen,
                new SleepAction(0.3),
                new InstantAction(()-> pincer.pincerRoll(Values.ROLL_OUT)),
                new SleepAction(0.3),
                new InstantAction(()-> pincer.pincerTurn(Values.TURN_OUT)),
                new SleepAction(0.3),
                new InstantAction(()-> pincer.pincerArm(Values.ARM_IN))

        ));
        x = true;

    }

    @Override
    public void runOpMode() throws InterruptedException {
        dash = FtcDashboard.getInstance();
        runningActions = new ArrayList<>();

        contActions = new ParallelAction(
                drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x, false, true),
                elevator.moveByPower(-gamepad1.right_stick_y),
//                        elevator.moveCM(elevatorHeightCM),
                intake.IntakePower(gamepad1.right_trigger - gamepad1.left_trigger)
        );

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
