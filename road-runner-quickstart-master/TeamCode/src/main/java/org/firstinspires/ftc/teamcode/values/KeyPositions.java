package org.firstinspires.ftc.teamcode.values;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
@Config
public class KeyPositions {
    // BLUE FAR
    public static Pose2d BLUE_FAR_FROM_BUCKET_SPAWN = new Pose2d(-12, 72,  Math.toRadians(-90));
    public static Pose2d BLUE_RIGHT_BAR_POS = new Pose2d(-2, 47.5, Math.toRadians(-90));
    public static Pose2d BLUE_RIGHT_BAR_POS_MID = new Pose2d(-2, 50 , Math.toRadians(-90));
    public static Pose2d BLUE_RIGHT_BAR_POS_BACK = new Pose2d(-2, 57 , Math.toRadians(-90));
    public static Pose2d BLUE_PARKING = new Pose2d(-47, 60, Math.toRadians(-0));

    // RED FAR
    public static Pose2d RED_FAR_FROM_BUCKET_SPAWN = new Pose2d(12, -72, Math.toRadians(90));
    public static Pose2d RED_RIGHT_BAR_POS = new Pose2d(2, -47.5, Math.toRadians(90));
    public static Pose2d RED_RIGHT_BAR_POS_MID = new Pose2d(2, -50 , Math.toRadians(90));
    public static Pose2d RED_RIGHT_BAR_POS_BACK = new Pose2d(2, -57, Math.toRadians(90));
    public static Pose2d RED_PARKING = new Pose2d(47, -60, Math.toRadians(180));









    public static Pose2d BLUE_CLOSE_TO_BUCKET_SPAWN = new Pose2d(12, 72, Math.toRadians(-90));
    public static Pose2d RED_CLOSE_TO_BUCKET_SPAWN = new Pose2d(12, -72, -180);
    public static Pose2d BLUE_LEFT_BAR_POS = new Pose2d(6, 40, 90);
    public static Pose2d BLUE_GRAB_POS = new Pose2d(-47, 70, 90 );


}
