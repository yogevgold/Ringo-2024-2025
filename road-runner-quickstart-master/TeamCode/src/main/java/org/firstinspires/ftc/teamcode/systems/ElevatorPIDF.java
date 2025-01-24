package org.firstinspires.ftc.teamcode.systems;

// Modified ElevatorPIDF Class with IZone
public class ElevatorPIDF {
    private double kP, kI, kD, kF;
    private double integral, previousError;
    private double targetHeight;
    private long lastTime;
    private double IZone;  // New field to store the IZone
    private double lastError;  // To track the last error for derivative calculations

    public ElevatorPIDF(double kP, double kI, double kD, double kF, double IZone) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;
        this.IZone = IZone;
        this.integral = 0;
        this.previousError = 0;
        this.lastError = 0;
        this.lastTime = System.currentTimeMillis();
    }

    // Set the target height
    public void setTargetHeight(double targetHeight) {
        this.targetHeight = targetHeight;
        this.integral = 0;  // Reset the integral term when setting a new target
        this.previousError = 0;  // Reset the previous error
    }

    // Calculate the output from PIDF control
    public double calculate(double currentHeight) {
        long currentTime = System.currentTimeMillis();
        double deltaTime = (currentTime - lastTime) / 1000.0;  // Convert ms to seconds
        lastTime = currentTime;

        // Calculate error terms
        double error = targetHeight - currentHeight;
        double derivative = 0;

        // Apply IZone logic: only accumulate the integral when within the IZone
        if (Math.abs(error) < IZone) {
            integral += error * deltaTime;
        } else {
            integral = 0;  // Optionally reset the integral term when out of the IZone
        }

        // Calculate derivative (rate of change of error)
        derivative = (error - lastError) / deltaTime;

        // Calculate PIDF output
        double output = kP * error + kI * integral + kD * derivative + kF * targetHeight;

        // Store the current error for the next iteration
        previousError = error;
        lastError = error;

        return output;
    }
}
