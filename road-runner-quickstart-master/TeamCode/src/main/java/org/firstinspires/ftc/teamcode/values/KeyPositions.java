package org.firstinspires.ftc.teamcode.values;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
@Config
public class KeyPositions {
    public static Pose2d BLUE_FAR_FROM_BUCKET_SPAWN = new Pose2d(-12, 72, -180);
    public static Pose2d BLUE_CLOSE_TO_BUCKET_SPAWN = new Pose2d(12, 72, -180);
    public static Pose2d RED_FAR_FROM_BUCKET_SPAWN = new Pose2d(-12, -72, -180);
    public static Pose2d RED_CLOSE_TO_BUCKET_SPAWN = new Pose2d(12, -72, -180);

    public static Pose2d BLUE_PARKING = new Pose2d(-38, 72, 90 );
    public static Pose2d RED_PARKING = new Pose2d(38, -72, 90 );

}
