package org.firstinspires.ftc.teamcode.values;

import com.acmerobotics.dashboard.config.Config;

@Config
public class Values {
    public static  double P_OF_ELEVATOR = 0.3;
    public static  double I_OF_ELEVATOR = 0.18;
    public static  double D_OF_ELEVATOR = 0.002;
    public static  double IZONE_OF_ELEVATOR = 8.5;
    public static  double F_OF_ELEVATOR = 0;
    public static final int CM_TOLERANCE_ELEVATOR = 100; //ppr
    public static final int TICKS_TO_CM_RATION = 4250 / 70;//(ppr/cm)


    public static final int FIRST_BUCKET_HEIGHT_CM = 36;
    public static final int SECOUND_BUCKET_HEIGHT_CM = 82;//81
    public static final int SECOUND_BAR_HEIGHT_CM = 11;//81


    public static final double HOTIZONTAL_SLIDES_OPEN = 0;
    public static final double HOTIZONTAL_SLIDES_CLOSE = 0.13;


    public static final double INTAKE_DOWN = 0.2;
    public static final double INTAKE_CLOSE = 0.75;
    public static final double INTAKE_UP = 0.27;


    public static final double ARM_OUT = 0.015;
    public static final double ARM_IN = 0.06;
    public static final double ARM_WATING = 0.04;


    public static final double TURN_IN = 0;
    public static final double TURN_OUT = 0;
    public static final double TURN_WATING = 0.08;


    public static final double ROLL_OUT = 0.48;
    public static final double ROLL_IN = 0.82;


    public static final double GRAB_OPEN = 0.3;
    public static final double GRAB_CLOSE = 0;





}