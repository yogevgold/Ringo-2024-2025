package org.firstinspires.ftc.teamcode.tests;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.BetterGamepad;
import org.firstinspires.ftc.teamcode.systems.Drive;
import org.firstinspires.ftc.teamcode.systems.Elevator;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.Outtake;
import org.firstinspires.ftc.teamcode.values.Values;

@TeleOp
public class actionMerge extends LinearOpMode {
    Drive drive;
    Elevator elevator;
    Intake intake;
    Outtake outtake;
    BetterGamepad betterGamepad1;
    BetterGamepad betterGamepad2;
    @Override
    public void runOpMode() throws InterruptedException {
        int elevatorHeightCM = 0;
        drive = new Drive(hardwareMap);
        elevator = new Elevator(hardwareMap);
        intake = new Intake(hardwareMap);
        outtake = new Outtake(hardwareMap);
        betterGamepad1 = new BetterGamepad(gamepad1);
        betterGamepad2 = new BetterGamepad(gamepad1);

        while (opModeInInit()){
            Actions.runBlocking(intake.IntakeServoInit());
        }

        while (opModeIsActive()){
            if(betterGamepad1.X()) elevatorHeightCM = 0;
            else if(betterGamepad1.Y()) elevatorHeightCM = Values.SECOUND_BUCKET_HEIGHT_CM;
            else if (betterGamepad1.A()) elevatorHeightCM = Values.FIRST_BUCKET_HEIGHT_CM;

            Actions.runBlocking(
//                    new ParallelAction(
//                            drive.TeleDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x),
//                            elevator.moveCM(elevatorHeightCM, opModeIsActive()),
//
//                    )
                    //עשיתי קומנט להסרת ארורים בשביל פוש, להסרה פשוט תסיר תקומנט מכל השורות
                    // תשלח לי הודעה של "אני קראתי את ההודעה שלך בקוד" שאראה שראית את זה וניסית לפתור
            );
        }
    }
}
