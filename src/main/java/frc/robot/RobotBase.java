/*
THE COP-OP ROBOTIC TELEOPERATIONS SYSTEM
Coded in 2015 by someone else, and adapted by a dumb 17 year old in 2022.
Designed for use in a specialized police robot, documentation for that will come later.

Someone's life is probably in my hands right now :) Good to know
*/

package frc.robot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;

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
    private Spark mMidLeft, mBackLeft, mMidRight, mBackRight, arm; //motors
    private SpeedControllerGroup leftCims, rightCims, winch;
	private DifferentialDrive driveTrain; //drive base with all drive motors included for only $4.99
	private DoubleSolenoid hatch, claw;
	private Talon armRot;

    public RobotBase () {
      //creates instances of motors 
      mMidLeft = new Spark(2);
      mBackLeft = new Spark(1);
      mMidRight = new Spark(3);
      mBackRight = new Spark(4);

      //defines the arm pneumatic motors
      arm = new Spark(6);
      arm.enableDeadbandElimination(true);
      armRot = new Talon(5);
      
      hatch = new DoubleSolenoid(0, 1);
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
      //driveTrain.tankDrive(joy1, joy2);
    }

    public void winch (double joy) {
      winch.set(joy);
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

	public void setHatch (boolean out, boolean in) {
		if(out) {
			System.out.println("Hatch out");
			hatch.set(DoubleSolenoid.Value.kForward);
		} else if(in) {
			System.out.println("Hatch in");
			hatch.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public void rotClaw (boolean left, boolean right) {
		if(left) {
			System.out.println("Going left right now");
			armRot.set(1.0);
		}
		if(right) {
			System.out.println("Goin RIGHT RN!!!!!!!!!!");
			armRot.set(-1.0);
		}
		if(left == right) {
			armRot.set(0);
		}
	}
}