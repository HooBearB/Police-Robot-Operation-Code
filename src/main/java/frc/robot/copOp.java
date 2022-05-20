package org.usfirst.frc.team5082.robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Solenoid;
import gradle.InstallAllTools;



public class copOp extends IterativeRobot {
	Joystick m_joyStick;

	Spark m_frontLeft = new Spark(9);
	Spark m_rearLeft = new Spark(6);
	SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
	
	Spark m_frontRight = new Spark(8);
	Spark m_rearRight = new Spark(7);
	SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
	DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
	
	//change from solenoid to motor correct ports, find out what elevator is
	Solenoid jawClose = new Solenoid(3); //port 3
	Solenoid jawOpen = new Solenoid(2); //port 2
	Spark armUp = new Motor(0); //port 0
	Spark armDown = new Motor(1); //port 1
	Spark ElevatorUp = new Motor(4); //port 4
	Spark ElevatorDown = new Motor(5); //port 5
	Talon ClawRotation = new Motor(5); //port 5
	
	@Override
	public void robotInit() {
		m_joyStick = new Joystick(0);
		
		button1 = new JoystickButton(m_joyStick, 1); //close jaw
		button2 = new JoystickButton(m_joyStick, 2); //open jaw
		button3 = new JoystickButton(m_joyStick, 3); //raise arm
		button4 = new JoystickButton(m_joyStick, 4); //lower arm
	}

	@Override
	public void teleopPeriodic() {
		m_drive.arcadeDrive(-m_joyStick.getY(), m_joyStick.getX());
		if(m_joyStick.getRawButton(button1)) { //close jaw
			jawClose.set(true);
			jawOpen.set(false);
		}
		else if(m_joyStick.getRawButton(button2)) { //open jaw
			jawClose.set(false);
			jawOpen.set(true);
		}
		else {
			jawClose.set(false);
			jawOpen.set(false);
		}
		
		if(m_joyStick.getRawButton(button3)) { //raise arm
			armUp.set(true);
			armDown.set(false);
		}
		else if(m_joyStick.getRawButton(button4)) { //lower arm
			armUp.set(false);
			armDown.set(true);
		}
		else {
			armUp.set(false);
			armDown.set(false);
		}
	}
}