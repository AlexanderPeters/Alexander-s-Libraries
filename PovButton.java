package lib.joystick;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
/**
 * PovButton
 * @author Alexander Peters
 * This is a wrapper class which extends the functionality of the button library by 
 * allowing the use of POV buttons/Hat switch on an Xbox controller.
 *
 */

public class PovButton extends Button {
	GenericHID m_joystick;
	int m_povNumber;
	String m_selector;
	public boolean selected;
	
	public PovButton(GenericHID joystick, int povNumber, String selector) {
	    m_joystick = joystick;
	    m_povNumber = povNumber;
	    m_selector = selector;
	    int degree = m_joystick.getPOV(m_povNumber);
	    switch (m_selector){
	    case("up"):selected = (degree==315 || degree==0 || degree==45);
	    	break;
	    case("down"):selected = (degree<=225 && degree>=135);
    		break;
	    case("left"):selected = (degree<=315 && degree>=225);
    		break;
	    case("right"):selected = (degree<=135 && degree>=45);
    		break;
	    case("north"):selected = (degree == 0);
    		break;
	    case("south"):selected = (degree == 180);
    		break;
	    case("east"):selected = (degree == 90);
    		break;
	    case("west"):selected = (degree == 270);
	    	break;
	    case("northEast"):selected = (degree == 315);
    		break;
	    case("northWest"):selected = (degree == 45);
    		break;
	    case("southEast"):selected = (degree == 135);
	    	break;
	    case("southWest"):selected = (degree == 225);
    		break;
	    case("notPressed"):selected = (degree == -1);
    		break;
    	}
	}

	
	public boolean get() {
		return selected;
	}

}

