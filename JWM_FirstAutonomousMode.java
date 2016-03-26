/****************************************************************************************************************************************
 * Name: Johnny McCown
 * Project: Helping Dylan (Mr. Programmer) with FTC Robot program....AutonomousMode
 * Date: 12 March 2016
 * Description:  This program will allow the operator to control the robot using a Logitec Dual Action Controller PN: 863247-0010
 * Resources:  found this help from https://www.youtube.com/watch?v=kOapppDNlSA&list=PLJIJCo7cYsE-ma0iYtbCf27s7zgLq-73i&index=3#t=1.397791
 * also https://www.youtube.com/watch?v=d0liBxZCtrA
 *****************************************************************************************************************************************/


package org.usfirst.ftc.exampleteam.yourcodehere;

//Here we are importing a few classes that will help us from having to type more code...
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.Autonomous;
import org.swerverobotics.library.interfaces.TeleOp;


//@TeleOp is used by the driver station to select what program to run.  The 3 modes are TeleOp, Autonomous and Disabled.
@Autonomous(name="JWM_AutonomusMode")

/*
 * public class is the main section of your Java code this is fallowed by the Class name...in this case JWM_FirstOpMode
 * then some more info....bla bla bla
 */

public class JWM_FirstAutonomousMode extends SynchronousOpMode {
    /* Declare here any fields you might find useful. */
    //We are going to intialize DcMotor first
    //DcMotor is the blablabla then motorLeft is a blablabla and we are first setting its value to null(nothing) using the =
    DcMotor motorLeft = null;
    DcMotor motorRight = null;

    //Declare servos
    Servo servoArm = null;

    //Declare some servo positions
    double ARM_MIN = 0.2; //notice here we are not using null, we are giving it a fix value
    double ARM_MAX = 0.8; //double is discribing the type of number...


    @Override public void main() throws InterruptedException {
            /* Initialize our hardware variables. Note that the strings used here as parameters
            * to 'get' must correspond to the names you assigned during the robot configuration
            * step you did in the FTC Robot Controller app on the phone.
            */
        this.motorLeft = this.hardwareMap.dcMotor.get("motorLeft");
        this.motorRight = this.hardwareMap.dcMotor.get("motorRight");

        //Initalize servos here in the main method
        servoArm = hardwareMap.servo.get("servoArm");

        //Set motor channel modes
        //we are going to first start with without_encoders, but I would like to use encoder in the autonomous mode...
        motorLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        motorRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        //Here we are going to reverse one of the motors because they will both spend clockwise if the power's polarity is connected the same
        //This would cause the robot to spine around... :(
        motorLeft.setDirection(DcMotor.Direction.REVERSE);


        // Wait for the game to start
        waitForStart();

        //Go go robot!


        //note to self, we are telling the robot to drive for x amout of time, but if our battery is low on power it will not go as far for that period of time
        double DRIVE_POWER = 1.0;

        DriveForward(1);
        Thread.sleep(4000);

        TurnLeft(1);
        Thread.sleep(500);

        TurnRight(1);
        Thread.sleep(500);

        //check out how this is different from the rest, can you figure out how it is working?
        TurnRightTime(1, 500);


        DriveForwardTime(DRIVE_POWER, 4400);

        StopDriving();


        //this method will be used to drive the robot forward
    }
    public void DriveForward(double power) {
        motorLeft.setPower(power);
        motorRight.setPower(power);
    }

    public void DriveForwardTime(double power, long time) throws InterruptedException {
        DriveForward(power);
        Thread.sleep(time);
    }

    public void StopDriving() {
        DriveForward(0);
    }

    public void TurnLeft(double power) {
        motorLeft.setPower(-power);
        motorRight.setPower(power);
    }

    public void TurnLeftTime(double power, long time) throws InterruptedException {
        TurnLeft(power);
        Thread.sleep(time);
    }


    public void TurnRight(double power) {
        motorLeft.setPower(power);
        motorRight.setPower(-power);
    }

    public void TurnRightTime(double power, long time) throws InterruptedException {
        TurnRight(power);
        Thread.sleep(time);
    }

    //public void RaiseArm()
    //{
    //     armServo.setPosition(0.8);
    //}

    //public void LowerArm()
    //{
    //    armServo.setPosition(0.2);
    //}

    //telemetry.update();
    //idle();

}


