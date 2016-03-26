package org.usfirst.ftc.exampleteam.yourcodehere;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.swerverobotics.library.interfaces.TeleOp;

@TeleOp(name="JWM_TeleOpMode")
/**
 * Created by vhsrobotics on 12/3/15.
 */
public class JWM_TeleOp_Mode extends OpMode{

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor arm;
    DcMotor extender;
    //Servo hookRight;
    //Servo hookLeft;
    //Servo climber;
    //double out =0.8;
    //double in =-0.2;
    @Override
    public void init() {
        //get references to the motors from the hardware map
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        extender = hardwareMap.dcMotor.get("arm");
        arm = hardwareMap.dcMotor.get("extender");
        //hookRight = hardwareMap.servo.get("HookRight");
        //hookLeft = hardwareMap.servo.get("HookLeft");
        //climber = hardwareMap.servo.get("Climber");
        //reverse the right motor
        motorRight.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        float leftY = gamepad1.left_stick_y;
        float rightY = gamepad1.right_stick_y;
        float left2y = gamepad2.left_stick_y;
        float right2y = gamepad2.right_stick_y;
        //set the power of the motors with the gamepad values
        motorLeft.setPower(leftY);
        motorRight.setPower(rightY);
        extender.setPower(left2y);
        arm.setPower(right2y);
        //Set The Servo's to a certain level
        //if(gamepad2.a) {
          //  hookRight.setPosition(out);
          //  hookLeft.setPosition(out);
        //}
        //if(gamepad2.b) {
          //  hookRight.setPosition(in);
          //  hookLeft.setPosition(in);
        //}
        //if(gamepad2.x);{
           // climber.setPosition(out);
        //}
        //if(gamepad2.y) {
          //  climber.setPosition(in);
        //}
    }
}

