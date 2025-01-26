package org.firstinspires.ftc.teamcode.Autos;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.values.KeyPositions;

@Autonomous
public class AutonomousBlueRightPark extends LinearOpMode {
    MecanumDrive drive;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, KeyPositions.BLUE_FAR_FROM_BUCKET_SPAWN);
        waitForStart();

        sleep(0);

        Actions.runBlocking
                (
                        drive.actionBuilder(KeyPositions.BLUE_FAR_FROM_BUCKET_SPAWN)
                                .lineToX(KeyPositions.BLUE_PARKING.position.x)
                                .build()
        );
    }
}
