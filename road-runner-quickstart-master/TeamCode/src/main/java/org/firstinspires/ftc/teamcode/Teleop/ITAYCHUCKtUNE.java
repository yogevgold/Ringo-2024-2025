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
import org.intellij.lang.annotations.JdkConstants;

import java.util.ArrayList;
import java.util.List;

@TeleOp
public class ITAYCHUCKtUNE extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Outtake outtake;


            outtake = new Outtake(hardwareMap);


            waitForStart();

            while (opModeIsActive()){

                outtake.Rightfunnel.setPosition(gamepad1.right_trigger);
                outtake.Rightfunnel.setPosition(-gamepad1.left_trigger);

                telemetry.addData("meow",outtake.Rightfunnel.getPosition());
                telemetry.update();
        }
    }


    }