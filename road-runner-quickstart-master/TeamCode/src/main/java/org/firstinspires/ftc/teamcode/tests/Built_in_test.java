package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.systems.Drive;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.Pincer;
import org.firstinspires.ftc.teamcode.systems.Setup;

import java.util.ArrayList;
import java.util.List;

@Autonomous
public class Built_in_test extends LinearOpMode {
    Setup setup;
    Drive drive;
    Elevator elevator;
    Intake intake;
    Pincer pincer;
    @Override
    public void runOpMode() throws InterruptedException {
        List<Action> newActions = new ArrayList<>();
        setup.newActions = newActions;
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

        newActions.add(new SequentialAction(
                setup.pincergrabopen,
                setup.sleep03,
                setup.pincergrabclose
        ));

    }
}
