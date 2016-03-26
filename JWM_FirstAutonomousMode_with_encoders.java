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


//@TeleOp is used by the driver station to select what program to run.  The 3 modes are TeleOp, Autonomous and Disabled.
@Autonomous(name="JWM_AutonomusMode_with_encoders")

/**********************************************
 * public class is the main section of your Java code this is fallowed by the Class name...in this case JWM_FirstOpMode
 * then some more info....bla bla bla
 **********************************************/

public class JWM_FirstAutonomousMode_with_encoders extends SynchronousOpMode
{
    /*************************************************************************************
     * This section is talking about the motors... We are going to intialize DcMotor first
     * we only have to declare the motors, because ours come with the encoders, our encoders provide 7 pulse per revolution (ppr)
     * considering our gearbox motor is a 40:1 reduction it has 280 ppr.  Now if the motor is being used for direct drive, we need to see the circumfrence of the wheel.
     * DcMotor is the blablabla then motorLeft is a blablabla and we are first setting its value to null(nothing) using the =
     * The circumfrince of the wheel will determine the distance the motor will produce with one revolution...
     * Useful link about the motors... http://www.andymark.com/Gearmotor-p/am-2964a.htm
     *************************************************************************************/
    DcMotor motorLeft = null;
    DcMotor motorRight = null;

    //Declare servos
    //Servo servoArm = null;

    //Declare some servo positions
    double ARM_MIN = 0.2; //notice here we are not using null, we are giving it a fix value
    double ARM_MAX = 0.8; //double is describing the type of number...


    @Override public void main() throws InterruptedException {
            /* Initialize our hardware variables. Note that the strings used here as parameters
            * to 'get' must correspond to the names you assigned during the robot configuration
            * step you did in the FTC Robot Controller app on the phone.
            */
        this.motorLeft = this.hardwareMap.dcMotor.get("motorLeft");
        this.motorRight = this.hardwareMap.dcMotor.get("motorRight");

        //Initalize servos here in the main method
        //servoArm = hardwareMap.servo.get("servoArm");

        //Set motor channel modes
        /**************************************************************************************
         * here is a bit about encorders in this program
         * fallow this link for a how-to video https://www.youtube.com/watch?v=d0liBxZCtrA
         * motorLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
         * allows you to set motor power without using encorders, but will still keep track of the encoder value
         *
         * motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
         * will allow you to set the speed of the motor, it will look at the encoders and run a loop to make sure that the speed is correct
         *
         * motorLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
         * allows you to set a target position that the motor will travel to, then stop
         *
         * motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
         * resets the encoder value back to zero
         */
        motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        //Here we are going to reverse one of the motors because they will both turn clockwise if the power's polarity is connected the same
        //This would cause the robot to spine around... :(
        motorLeft.setDirection(DcMotor.Direction.REVERSE);


        // Wait for the game to start
        waitForStart();

        //Go go robot!


        int ANDYMARK_TICKS_PER_REV = 1120;

        //double DRIVE_POWER = 1.0;

        //DriveForward(1);
        //Thread.sleep(4000);

        //DriveForwardDistance(0.5, 4880);
        DriveForwardDistance(0.2, 3200);
        //to make a right angle aka 90 deg, we will have to do 2240 steps
        TurnLeftDistance(0.1, 505);
        DriveForwardDistance(0.2, 2850);
       // TurnRightDistance(0.2, 2250);
       // DriveForwardDistance(0.5, 2240);
       // TurnRightDistance(0.2, 2240);
       // DriveForwardDistance(0.5, 6720);
       // ReverseDistance(0.5, 1120);

        //TurnLeft(1);
        //Thread.sleep(500);

        //TurnRight(1);
        //Thread.sleep(500);

        //check out how this is different from the rest, can you figure out how it is working?
        //TurnRightTime(1,500);


        //DriveForwardTime(DRIVE_POWER, 4400);
        //double DRIVE_POWER = 1.0;


        // StopDriving();




    }


        //this method will be used to drive the robot forward
        public void DriveForward(double power){
            motorLeft.setPower(power);
            motorRight.setPower(power);
        }

        public void StopDriving(){
            DriveForward(0);
         }

        public void TurnLeft(double power){
            motorLeft.setPower(-power);
            motorRight.setPower(power);
        }

        public void TurnRight(double power){
            motorLeft.setPower(power);
            motorRight.setPower(-power);
        }

        public void ReverseMode(double power){
            motorLeft.setPower(power);
            motorRight.setPower(-power);
        }



        //public void TurnRightTime(Double power, long time) throws InterruptedException
        //{
        //   TurnRight(power);
        //   Thread.sleep(time);
        //}

        //public void RaiseArm()
        //{
        //    armServo.setPosition(0.8);
        //}

        //public void LowerArm()
        //{
        //    armServo.setPosition(0.2);
        //}

        //is used for logical statements
        public boolean EncodersAreNegative(){
            return (motorLeft.getCurrentPosition() < 0) && (motorRight.getCurrentPosition() < 0);
        }

        //we might be able to get rid of power and change it to DRIVE_POWER
        public void DriveForwardDistance(double power, int distance) {
            //Reset both encoders
            motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);

            //Set target postion
            motorLeft.setTargetPosition(distance);
            motorRight.setTargetPosition(distance);

            //Set to RUN_TO_POSITION mode
            motorLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

            //Set drive power
            DriveForward(power);
            while (motorLeft.isBusy() && motorRight.isBusy()) {
                //wait until target position is reached
            }

            //Stop and chage modes back to normal
            StopDriving();
            motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }


        public void TurnLeftDistance(double power, int distance) {
         //Reset both encoders
            motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);

            //Set target postion
            motorLeft.setTargetPosition(-distance);
            motorRight.setTargetPosition(distance);

            //Set to RUN_TO_POSITION mode
            motorLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

            //Set drive power
            TurnLeft(power);
            while (motorLeft.isBusy() && motorRight.isBusy()) {
                //wait until target position is reached
            }

            //Stop and chage modes back to normal
            StopDriving();
            motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }


        public void TurnRightDistance(double power, int distance) {
            //Reset both encoders
            motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);

            //Set target postion
            motorLeft.setTargetPosition(distance);
            motorRight.setTargetPosition(-distance);

            //Set to RUN_TO_POSITION mode
            motorLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

            //Set drive power
            TurnRight(power);
            while (motorLeft.isBusy() && motorRight.isBusy()) {
                //wait until target position is reached
            }

            //Stop and chage modes back to normal
            StopDriving();
            motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }

        public void ReverseDistance(double power, int distance) {
            //Reset both encoders
            motorLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
            motorRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);

            //Set target postion
            motorLeft.setTargetPosition(-distance);
            motorRight.setTargetPosition(-distance);

            //Set to RUN_TO_POSITION mode
            motorLeft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

            //Set drive power
            ReverseMode(power);
            while (motorLeft.isBusy() && motorRight.isBusy()) {
                //wait until target position is reached
            }

            //Stop and chage modes back to normal
            StopDriving();
            motorLeft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
            motorRight.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        }
}
