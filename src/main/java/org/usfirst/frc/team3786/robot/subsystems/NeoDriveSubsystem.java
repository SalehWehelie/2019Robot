package org.usfirst.frc.team3786.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.usfirst.frc.team3786.robot.Mappings;
import org.usfirst.frc.team3786.robot.Dashboard;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class NeoDriveSubsystem extends Subsystem {

	private static NeoDriveSubsystem instance;

	private CANSparkMax left;
	private CANSparkMax right;

	private DifferentialDrive differentialDrive;

	private boolean boost = false;
	private boolean kBrake = false;

	public static NeoDriveSubsystem getInstance() {
		if (instance == null)
			instance = new NeoDriveSubsystem();
		return instance;
	}

	public NeoDriveSubsystem() {
		left = new CANSparkMax(Mappings.leftMotor, MotorType.kBrushless);
		right = new CANSparkMax(Mappings.rightMotor, MotorType.kBrushless);
		left.setSmartCurrentLimit(35);
		right.setSmartCurrentLimit(35);	
		left.setRampRate(0.1);
		right.setRampRate(0.1);

		differentialDrive = new DifferentialDrive(left, right);
	}

	public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
		left.set(leftSpeed);
		right.set(rightSpeed);
		Dashboard.getInstance().putNumber(false, "Left Motor Speed", leftSpeed);
		Dashboard.getInstance().putNumber(false, "Right Motor Speed", rightSpeed);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void arcadeDrive(double speed, double turnRate) {
		Dashboard.getInstance().putBoolean(true, "Boost", boost);
		Dashboard.getInstance().putBoolean(true, "Break", kBrake);
		Dashboard.getInstance().putNumber(false, "Driving Speed", speed);
		Dashboard.getInstance().putNumber(false, "TurnRate", turnRate);
		if(this.kBrake) {
			speed *= 0.0;
			turnRate *= 0.0;
		}

		if(this.boost) {
			speed *= 2.0;
			turnRate *= 2.0;
		}

		differentialDrive.arcadeDrive(speed, turnRate);
	}

	public void setkBrake(boolean kBrake) {
		this.kBrake = kBrake;
		if (this.kBrake) {
			left.setIdleMode(IdleMode.kBrake);
			right.setIdleMode(IdleMode.kBrake);
		} else {
			left.setIdleMode(IdleMode.kCoast);
			right.setIdleMode(IdleMode.kCoast);
		}
	}

	public void setBoost(boolean boost) {
		this.boost = boost;
	}

	public boolean getBoost() {
		return this.boost;
	}
}
