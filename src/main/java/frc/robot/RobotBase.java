/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Add your docs here.
 */
public class RobotBase {

	// private Solenoid hatchout, hatchin, outriggerout, outriggerin, liftout, liftin;
    private Spark  mMidLeft, mBackLeft, mMidRight, mBackRight, armUp, armDown;		//motors
    private SpeedControllerGroup leftCims, rightCims, winch;
	private DifferentialDrive driveTrain;								//drive base w all drive motors
	private DoubleSolenoid hatch, outrigger;

    public RobotBase () {
        //create instances of motors 
		mMidLeft = new Spark(9);
		mBackLeft = new Spark(6);
		mBackRight = new Spark(7);
		mMidRight = new Spark(8);

		armUp = new Spark(2);
		armDown = new Spark(3);
        
		hatch = new DoubleSolenoid(0, 1);
		outrigger = new DoubleSolenoid(2, 3);
        
        leftCims = new SpeedControllerGroup(mMidLeft, mBackLeft);
        rightCims = new SpeedControllerGroup(mMidRight, mBackRight);
        driveTrain = new DifferentialDrive(leftCims, rightCims);
    }   

    public void off () {
        driveTrain.stopMotor();
    }

    public void drive (double joy1, double joy2) {
        // arcade drive
         driveTrain.arcadeDrive(joy1, joy2);

        // tank drive
        //driveTrain.tankDrive(joy1, joy2);
    }

    public void winch (double joy) {
        winch.set(joy);
	}

	public void doArm (boolean up, boolean down) {
		if(up) {
			armUp.set(0.5);
			armDown.set(0);
		} else if(down) {
			armDown.set(0.5);
			armUp.set(0);
		}
		else {
			armUp.set(0);
			armDown.set(0);
		}
	}

	public void setOutrigger (boolean out, boolean in) {
		if(out) {
			outrigger.set(DoubleSolenoid.Value.kReverse);
		} else if(in) {
			outrigger.set(DoubleSolenoid.Value.kForward);
		}
	}

	public void setHatch (boolean out, boolean in) {
		if(out) {
			System.out.println("Hatch out");
			hatch.set(DoubleSolenoid.Value.kForward);
		} else if(in) {
			System.out.println("Hatch in");
			hatch.set(DoubleSolenoid.Value.kReverse);
		}
	}
}