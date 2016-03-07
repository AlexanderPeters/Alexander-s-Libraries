package main.commands;

import main.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**@author Alexander Peters
 * PID
 *Example command implementing the Pidsetpointfollower library.
 */
public class PID extends Command {

    public PID() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pid.start(0, 100, "QuadEncoder", false, 0, 0.8, 0, 0);
    	/**parameters to be passed 
    	 * (int can port, double setpoint, string type of feedback device, 
    	 *boolean feedback device reversed, double Fpid, double Ppid, double IPid, double Dpid)
    	 */
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pid.run();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pid.stop();
    }
}
