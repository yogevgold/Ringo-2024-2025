package org.firstinspires.ftc.teamcode.Autos;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.values.KeyPositions;
@Config
@Autonomous
public class AutonomousBlueLeftPark extends LinearOpMode {
    MecanumDrive drive;
    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, KeyPositions.BLUE_CLOSE_TO_BUCKET_SPAWN);
        waitForStart();
        sleep(0);
        Actions.runBlocking
                (
                        drive.actionBuilder(KeyPositions.BLUE_CLOSE_TO_BUCKET_SPAWN)
                                .setTangent(Math.toRadians(-90))
                                .splineToConstantHeading(new Vector2d(KeyPositions.BLUE_PARKING.position.x, KeyPositions.BLUE_PARKING.position.y), Math.toRadians(90))
                                .build()
        );
    }
}
