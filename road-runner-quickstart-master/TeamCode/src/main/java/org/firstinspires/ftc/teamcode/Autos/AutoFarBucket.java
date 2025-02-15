package org.firstinspires.ftc.teamcode.Autos;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.Pincer;
import org.firstinspires.ftc.teamcode.values.KeyPositions;
import org.firstinspires.ftc.teamcode.values.Values;
@Autonomous
public class AutoFarBucket extends LinearOpMode {
    MecanumDrive drive;
    Elevator elevator;
    Pincer pincer;
    Intake intake;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, KeyPositions.BLUE_FAR_FROM_BUCKET_SPAWN);// נקודת התחלה
        elevator = new Elevator(hardwareMap);
        pincer = new Pincer(hardwareMap);
//        intake = new Intake(hardwareMap);
        SequentialAction armToBarAction = new SequentialAction(
                        new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerRoll(Values.ROLL_OUT)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerTurn(Values.TURN_WATING)),
                        new SleepAction(0.3),
                        new InstantAction(()-> pincer.pincerArm(Values.ARM_WATING))
        );
        InstantAction releaseSpecimen = new InstantAction(()-> pincer.pincerGrab(Values.GRAB_OPEN));
        InstantAction holdSpecimen = new InstantAction(()-> pincer.pincerGrab(Values.GRAB_CLOSE));


        Action trajecory1 =//
                drive.actionBuilder(KeyPositions.BLUE_FAR_FROM_BUCKET_SPAWN) //נקודת התחלה כללית
                .splineToLinearHeading(KeyPositions.BLUE_RIGHT_BAR_POS,Math.toRadians(30))
                .build();

        Action trajectory2 =
                drive.actionBuilder(KeyPositions.BLUE_RIGHT_BAR_POS) //end of former trajectory (1)
                .splineToLinearHeading(KeyPositions.BLUE_RIGHT_BAR_POS_BACK,Math.toRadians(30))
                .build();

        Action trajectory3 =
                drive.actionBuilder(KeyPositions.BLUE_RIGHT_BAR_POS_BACK) //end of former trajectory (2)
                        .splineToLinearHeading(KeyPositions.BLUE_PARKING,Math.toRadians(30))
                        .build();

        waitForStart();
        sleep(0);
        Actions.runBlocking
                (
                        new SequentialAction(
                                holdSpecimen
                                ,armToBarAction
                                ,new SleepAction(0.8)

                                ,new ParallelAction(
                                        trajecory1
                                        ,elevator.moveCMAuto(Values.SECOUND_BAR_HEIGHT_CM)
                                        ,armToBarAction
                                )

                                ,new SleepAction(0.5)
                                ,elevator.moveCMAuto(Values.SECOUND_BAR_HEIGHT_CM - 9)
                                ,new SleepAction(0.5)


                                ,trajectory2
                                ,elevator.moveCMAuto(0)
                                ,releaseSpecimen

                                ,new SleepAction(1)

                                ,trajectory3
                                , new SleepAction(10)
                                )
                );
    }
}
