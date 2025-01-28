package org.firstinspires.ftc.teamcode.Autos;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.values.KeyPositions;

@Autonomous
public class AutonomousRedRightPark extends LinearOpMode {
    MecanumDrive drive;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, KeyPositions.RED_FAR_FROM_BUCKET_SPAWN);
        waitForStart();

        sleep(0);

        Actions.runBlocking
                (
                        drive.actionBuilder(KeyPositions.RED_FAR_FROM_BUCKET_SPAWN)
                                .lineToX(KeyPositions.RED_PARKING.position.x)
                                .build()
        );
    }
}
