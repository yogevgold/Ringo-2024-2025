package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.values.KeyPositions;

@Autonomous
public class AutonomousBlueRightParkTest extends LinearOpMode {
    private MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, KeyPositions.BLUE_FAR_FROM_BUCKET_SPAWN);
        waitForStart();

        sleep(0);

        Actions.runBlocking
                (
                        drive.actionBuilder(KeyPositions.BLUE_FAR_FROM_BUCKET_SPAWN)
                                .strafeToConstantHeading(KeyPositions.BLUE_RIGHT_BAR_POS.position)
                                .waitSeconds(1)
                                .strafeToConstantHeading(KeyPositions.BLUE_GRAB_POS.position)
                                .waitSeconds(1)
                                .strafeToConstantHeading(KeyPositions.BLUE_RIGHT_BAR_POS.position)
                                .build()
        );
    }
}