import java.awt.EventQueue;

import javax.swing.JFrame;

public class Calculator {

	public Calculator() {
	}

	static GUI gui=new GUI();
	
	public void run() throws InterruptedException{
		initialize(gui);
	}

	private void initialize(GUI gui2) {
		gui2.setVisible(true);
	}

}
