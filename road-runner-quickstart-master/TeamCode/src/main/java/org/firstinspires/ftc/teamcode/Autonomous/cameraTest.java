package org.firstinspires.ftc.teamcode.Autonomous;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;

@Autonomous
public class cameraTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        PredominantColorProcessor colorProcessor = new PredominantColorProcessor.Builder()
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.5, 1, 0.1, -0.5))
                .setSwatches(
                        PredominantColorProcessor.Swatch.RED,
                        PredominantColorProcessor.Swatch.BLUE,
                        PredominantColorProcessor.Swatch.YELLOW)
                .build();

        VisionPortal visionPortal = new VisionPortal.Builder()
                .addProcessor(colorProcessor)
                .setCameraResolution(new Size(1280, 720))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam"))
                .build();

        waitForStart();
        while (opModeIsActive()) {
            PredominantColorProcessor.Result result = colorProcessor.getAnalysis();
            telemetry.addData("Color Detected:", result.closestSwatch);
            telemetry.update();
        }
    }
}