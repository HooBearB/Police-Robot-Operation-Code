/*
THE COP-OP ROBOTIC TELEOPERATIONS SYSTEM
Coded in 2015 by someone else, and adapted by a dumb 17 year old in 2022.
Designed for use in a specialized police robot, documentation for that will come later.
*/

package frc.robot;
import edu.wpi.first.wpilibj.Spark;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;

// I built this software on somebody else's base using popsicle sticks and duct tape, damn it.

/**
 * Hey random tech! If you're servicing this for whatever reason, just know that I'm long gone, and am no longer around to tell
 * you what the hell any of this means! Good luck!! :)
 * -KMS
 * 
 * P.S.
 * If you blow this robot up with a bomb or something I swear to god
 */
public class RobotBase {
	// private Solenoid hatchout, hatchin, outriggerout, outriggerin, liftout, liftin;
  private Spark mMidLeft, mBackLeft, mMidRight, mBackRight, arm, clawRot; //motors
  private SpeedControllerGroup leftCims, rightCims;
	private DifferentialDrive driveTrain; //drive base with all drive motors included for only $4.99
  private DoubleSolenoid claw;
  private Servo camMotor;
  // if the talon module doesn't work properly just gently hit the central controller a couple times. it might work after that

  public RobotBase () {
    //creates instances of motors 
    mMidLeft = new Spark(2);
    mBackLeft = new Spark(1);
    mMidRight = new Spark(3);
    mBackRight = new Spark(4);
    camMotor = new Servo(7);

    //defines the arm pneumatic motors
    arm = new Spark(6);
    clawRot = new Spark(5);
    
    claw = new DoubleSolenoid(2, 3);
    
    //defines drivetrain as an object
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

    // tank drive if you want it idk
    // driveTrain.tankDrive(joy1, joy2);
  }

	public void doArm (boolean up, boolean down) {
		if(up) {
			arm.set(2.0);
			System.out.println("Arm up");
		} else if(down) {
			arm.set(-2.0);
			System.out.println("Arm down");
		}
		else {
			arm.set(0);
		}
	}

	public void doClaw (boolean open, boolean close) {
		if(open) {
			claw.set(DoubleSolenoid.Value.kReverse);
		} else if(close) {
			claw.set(DoubleSolenoid.Value.kForward);
		}
	}

	public void rotClaw (boolean left, boolean right) {
		if(left) {
			System.out.println("Going left right now");
			clawRot.set(1.0);
		}
		if(right) {
			System.out.println("Goin RIGHT RN!!!!!!!!!!");
			clawRot.set(-1.0);
		}
		if(left == right) {
			clawRot.set(0);
		}
  }
  
  public void rotCamera (boolean up, boolean down) {
    if (up) {
      camMotor.set(1);
    }
    else if (down) {
      camMotor.set(-1);
    }
    else {
      camMotor.set(0);
    }
  }
}