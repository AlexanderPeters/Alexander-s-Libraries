package lib;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import java.text.DecimalFormat;
/**
 * Pidsetpointfollower
 * @author Alexander Peters
 * This is a class which allows for the creation of set points and
 * PID controls for a CAN Talon to move to that set point.
 * Designed to allow the use of PID set points with sensor feedback on the fly elsewhere in code.
 *
 */


public class Pidsetpointfollower {
	boolean firstRun = true;
	public double m_setF;
	public double m_setP;
	public double m_setI;
	public double m_setD;
	public int m_canPort;
	public boolean m_reverseSensor = false;
	String m_feedbackDevice;
	
	public String m_setPoint;
	public double setPoint = Double.parseDouble(m_setPoint);
	public double targetPos = (double) (setPoint/360.00000000);
	
	public CANTalon talon = new CANTalon(m_canPort);
	
	
	public void start(int canPort, double setPoint, String feedbackDevice, boolean reverseSensor, double setF, double setP,double setI,double setD) {
		m_canPort = canPort;
		m_feedbackDevice = feedbackDevice;
		m_reverseSensor = reverseSensor;
		m_setF = setF;
		m_setP = setP;
		m_setI = setI;
		m_setD = setD;
		/* choose the sensor and sensor direction
		 *ensure correct number of decimal places and proper formatting of set point
		 * tell it to read the position fir a baseline value to be modified on the first run
		 * set the talon's encoder position to the current position
		 */
		
		if (m_feedbackDevice == "QuadEncoder"){
			talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		}
		else if (m_feedbackDevice == "AnalogEncoder"){ //TO DO set point modifier for different sensor values
			talon.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		}
		else if (m_feedbackDevice == "AnalogPot"){ //To DO set point modifier for different sensor values
			talon.setFeedbackDevice(FeedbackDevice.AnalogPot);
		}
				
		talon.reverseSensor(this.m_reverseSensor);
		
		DecimalFormat encoderPattern = new DecimalFormat("0.00000000");
		
		if (setPoint > 360){
			m_setPoint = "360.00000000";
		}
		else if (setPoint < 0){
			m_setPoint = "0.00000000";
		}
		else m_setPoint = encoderPattern.format(setPoint);
		
		if(firstRun)
		{
			targetPos += talon.getPosition();
			
			firstRun = !firstRun;
		}
		
		double absolutePosition = talon.getPosition();
		
		//use the low level API to set the quad encoder signal 
		talon.setPosition(absolutePosition);

		
/*
 * not sure if it is important but, the libraries no longer exist 
 * for nominal && peak output voltage and allowable closed loop error
 */
		
		talon.setProfile(0);
		talon.setF(this.m_setF);
		talon.setPID(this.m_setP, this.m_setI, this.m_setD);
		
	
	}
	
	public void run() {
		
		talon.changeControlMode(ControlMode.Position);
		talon.set(targetPos); 
		talon.changeControlMode(ControlMode.PercentVbus);
	}
	
	public void stop(){
		talon.changeControlMode(ControlMode.PercentVbus);
		talon.set(0);
	}

	
}



