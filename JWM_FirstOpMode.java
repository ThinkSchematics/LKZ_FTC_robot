/****************************************************************************************************************************************
 * Name: Johnny McCown
 * Project: Helping Dylan (Mr. Programmer) with FTC Robot program....OpMode
 * Date: 12 March 2016
 * Description:  This program will allow the operator to control the robot using a Logitec Dual Action Controller PN: 863247-0010
 * Resources:  found this help from https://www.youtube.com/watch?v=sAZ2yJfJj4Q
 *****************************************************************************************************************************************/


package org.usfirst.ftc.exampleteam.yourcodehere;
//Here we are importing a few classes that will help us from having to type more code...

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.TeleOp;



//@TeleOp is used by the driver station to select what program to run.  The 3 modes are TeleOp, Autonomous and Disabled.
@TeleOp(name="JWM_FirstOpMode")

/*********************************************************************************
 * Public class is the main section of your Java code this is fallowed by the Class name...in this case JWM_FirstOpMode.
 * Public makes the class accessible from any other class.  Private would make it only accessible from within its own class.
 * If neither public or private is used the the block is accessible by any class in the same package.
 * Within the brackets {} is the class block...everything that is part of this class.
 *********************************************************************************/


public class JWM_FirstOpMode extends SynchronousOpMode
{
    /**************************************************
     * Declare here any fields you might find useful.
     * When we declare variable it tells the compiler to allocate memory space base on the date type
     * for example double is the datatype and ARM_MIN is the variable's name.
     * Check out this link for Primitive Data Types https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
     * The = sign you might consider to be an equal sign but that would be wrong, it is an assignment operator.
     * Look at this link for operators such as =, == , !=, &&, ||, and much more http://www.tutorialspoint.com/java/java_basic_operators.htm
     * null is starting off the memory space as being empty.  Though for the servos we are starting off with a set value.
     *************************************************/


    DcMotor motorLeft = null;
    DcMotor motorRight = null;

    //Declare servos
    //Servo servoArm = null;

    //Declare some servo positions
    double ARM_MIN = 0.2; //notice here we are not using null, we are giving it a fix value
    double ARM_MAX = 0.8; //double is discribing the type of number...


    @Override public void main() throws InterruptedException
        {
            /* Initialize our hardware variables. Note that the strings used here as parameters
            * to 'get' must correspond to the names you assigned during the robot configuration
            * step you did in the FTC Robot Controller app on the phone.
            */
            motorLeft = hardwareMap.dcMotor.get("motorLeft");
            motorRight = hardwareMap.dcMotor.get("motorRight");

            //Initalize servos here in the main method
            //servoArm = hardwareMap.servo.get("servoArm");

            //Set motor channel modes
            //we are going to first start with without_encoders, but I would like to use encoder in the autonomous mode...
            motorLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
            motorRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

            //Here we are going to reverse one of the motors because they will both spend clockwise if the power's polarity is connected the same
            //This would cause the robot to spine around... :(
            motorLeft.setDirection(DcMotor.Direction.REVERSE);


            // Wait for the game to start
            waitForStart();

            // Go go gadget robot!
            while (opModeIsActive())
            {
                if (updateGamepads())
                {
                    // we are going to use Tank drive mode
                    motorLeft.setPower(gamepad1.left_stick_y);
                    motorRight.setPower(gamepad1.right_stick_y);

                }

            telemetry.update();
            idle();
            }
        }
}
