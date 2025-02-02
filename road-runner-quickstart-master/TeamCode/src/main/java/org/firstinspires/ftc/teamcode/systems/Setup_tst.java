package org.firstinspires.ftc.teamcode.systems;

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

public class Setup_tst extends LinearOpMode{
    public boolean x=false;
    Drive drive;
    Elevator elevator;
    Intake intake;
    public Pincer pincer;
    private FtcDashboard dash;
    private List<Action> runningActions;




    Action contActions;
    public List<Action> newActions;// = new ArrayList<>();
    TelemetryPacket packet = new TelemetryPacket();







    public void tst2(double a ,double b ,double c, double d) {
        newActions.add(new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(a)),
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
                new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),
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
