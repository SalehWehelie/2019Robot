package org.usfirst.frc.team3786.robot;

import org.usfirst.frc.team3786.robot.commands.grabber.GrabberCloseCommand;
import org.usfirst.frc.team3786.robot.commands.grabber.GrabberInCommand;
import org.usfirst.frc.team3786.robot.commands.grabber.GrabberOpenCommand;
import org.usfirst.frc.team3786.robot.commands.grabber.GrabberOutCommand;
import org.usfirst.frc.team3786.robot.commands.grabber.GrabberStopCommand;
import org.usfirst.frc.team3786.robot.subsystems.ElevatorSubsystem.Levels;
import org.usfirst.frc.team3786.robot.subsystems.ElevatorSubsystem.VerticalDirection;
import org.usfirst.frc.team3786.robot.commands.debug.DebugMotorControllerDecrement;
import org.usfirst.frc.team3786.robot.commands.debug.DebugMotorControllerIncrement;
import org.usfirst.frc.team3786.robot.commands.drive.BoostOffCommand;
import org.usfirst.frc.team3786.robot.commands.drive.BoostOnCommand;
import org.usfirst.frc.team3786.robot.commands.drive.BrakeOnCommand;
import org.usfirst.frc.team3786.robot.commands.drive.BrakeOffCommand;
import org.usfirst.frc.team3786.robot.commands.elevator.ElevatorChangeCommand;
import org.usfirst.frc.team3786.robot.commands.elevator.ElevatorDownCommand;
import org.usfirst.frc.team3786.robot.commands.elevator.ElevatorStopCommand;
import org.usfirst.frc.team3786.robot.commands.elevator.ElevatorUpCommand;
import org.usfirst.frc.team3786.robot.commands.elevator.ElevatorSendCommand;
import org.usfirst.frc.team3786.robot.commands.climber.ClimbWhileLevelCommand;
import org.usfirst.frc.team3786.robot.utils.XboxController;

public class Mappings {

	public final static int primaryControllerId = 0;
	public final static int secondaryControllerId = 1;

	public final static int leftMotor = 8; // Drive left 1, SPARK Max controller
	public final static int rightMotor = 9; // Drive right 1, SPARK MAX controller

	public final static int grabberMotor = 2; // Open/Close gripper, Talon SRX
	public final static int flingerMotor = 4;

	public final static int tiltMotor = 5; // Drive, Talon SRX
	public final static int elevatorMotor = 12; // Lift, SPARK Max

	public static void setupDefaultMappings() {
		
		XboxController primary = OI.getPrimaryController();
		primary.buttonA.whenPressed(new BrakeOnCommand());
		primary.buttonA.whenReleased(new BrakeOffCommand());
		primary.buttonB.whenPressed(new BoostOnCommand());
		primary.buttonB.whenReleased(new BoostOffCommand());
		primary.buttonX.whenPressed(new ElevatorDownCommand());
		primary.buttonX.whenReleased(new ElevatorStopCommand());
		primary.buttonY.whenPressed(new ElevatorUpCommand());
		primary.buttonY.whenReleased(new ElevatorStopCommand());
		primary.buttonBumperLeft.whenPressed(new ElevatorChangeCommand(VerticalDirection.UP));
		primary.buttonBumperLeft.whenReleased(new ElevatorChangeCommand(VerticalDirection.STOP));
		primary.buttonBumperRight.whenPressed(new ElevatorChangeCommand(VerticalDirection.DOWN));
		primary.buttonBumperRight.whenReleased(new ElevatorChangeCommand(VerticalDirection.STOP));
		
		XboxController secondary = OI.getSecondaryController();
		GrabberStopCommand grabberStopCommand = new GrabberStopCommand();
		secondary.buttonA.whenPressed(new GrabberInCommand());
		secondary.buttonA.whenReleased(grabberStopCommand);
		secondary.buttonB.whenPressed(new GrabberOutCommand());
		secondary.buttonB.whenReleased(grabberStopCommand);
		secondary.buttonY.whenPressed(new ClimbWhileLevelCommand());
		secondary.buttonBumperLeft.whenPressed(new ElevatorChangeCommand(VerticalDirection.UP));
		secondary.buttonBumperLeft.whenReleased(new ElevatorChangeCommand(VerticalDirection.STOP));
		secondary.buttonBumperRight.whenPressed(new ElevatorChangeCommand(VerticalDirection.DOWN));
		secondary.buttonBumperRight.whenReleased(new ElevatorChangeCommand(VerticalDirection.STOP));
		secondary.buttonTriggerLeft.whenPressed(new GrabberOpenCommand());
		secondary.buttonTriggerLeft.whenReleased(grabberStopCommand);
		secondary.buttonTriggerRight.whenPressed(new GrabberOpenCommand());
		secondary.buttonTriggerRight.whenReleased(grabberStopCommand);
	}

	public static void setupTestMappings() {
		XboxController primary = OI.getPrimaryController();
		primary.buttonBumperRight.whenPressed(new DebugMotorControllerIncrement());
		primary.buttonBumperLeft.whenPressed(new DebugMotorControllerDecrement());
	}

}