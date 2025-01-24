package org.firstinspires.ftc.teamcode.values;

import com.acmerobotics.dashboard.config.Config;

@Config
public class Values {
    public static  double P_OF_ELEVATOR = 0.185;
    public static  double I_OF_ELEVATOR = 0.13;
    public static  double D_OF_ELEVATOR = 0.001;
    public static  double IZONE_OF_ELEVATOR = 7.5;
    public static  double F_OF_ELEVATOR = 0;
    public static final int CM_TOLERANCE_ELEVATOR = 100; //ppr
    public static final int TICKS_TO_CM_RATION = 4250 / 70;//(ppr/cm)

    public static final int FIRST_BUCKET_HEIGHT_CM = 36;
    public static final int SECOUND_BUCKET_HEIGHT_CM = 82;//81

    public static final double HOTIZONTAL_SLIDES_OPEN = 0.6;
    public static final double HOTIZONTAL_SLIDES_CLOSE = 0.9;
    public static final double INTAKE_CLOSE = 0.8;
    public static final double INTAKE_UP = 0.3;
    public static final double INTAKE_DOWN = 0.2;
    public static final double FUNNEL_OPEN = 0.6;
    public static final double FUNNEL_CLOSED = 0.9;



}