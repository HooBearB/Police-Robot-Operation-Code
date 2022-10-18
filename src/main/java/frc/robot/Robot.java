/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
// don't change this lmao

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public final class Robot extends TimedRobot {
  RobotBase rb;
  Joystick joy; //joystick for 1 driver arcade drive
	DriverStation DS;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    //INSTANCE OF THE RB
		rb = new RobotBase();
		
		//INITIALIZING VARS
		joy = new Joystick(0);
		DS = DriverStation.getInstance();
		
		CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    teleopPeriodic();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public final void teleopPeriodic() {
    //run tele only while the ds is on
		if (DS.isEnabled()) {

			rb.drive(joy.getRawAxis(1) * -1, joy.getRawAxis(0)); //allows you to operate tank motors within arcade mode
      rb.doArm(joy.getRawButton(3), joy.getRawButton(5)); //raise arm with 3, lower with 5
			rb.doClaw(joy.getRawButton(1), joy.getRawButton(2)); //open with 2, close with 1
      rb.rotClaw(joy.getRawButton(6), joy.getRawButton(4)); //I forgot
      rb.rotCamera(joy.getRawButton(11), joy.getRawButton(12)); //also forgot
		} else {
			// rb.off(); // stop all motors
		}
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    if (DS.isEnabled()) {
			// rb.periodic(joy.getY(), joy.getX(), joy.getRawAxis(3), joy.getRawAxis(2), DS);
		}
		else {
			rb.off();
		}
  }
}
