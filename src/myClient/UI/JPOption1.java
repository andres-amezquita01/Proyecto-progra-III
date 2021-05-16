package myClient.UI;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPOption1 extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel message;

	public JPOption1() {
		setMessage(new JLabel("panel Opcion 1"));
		this.add(message);
	}

	public JLabel getMessage() {
		return message;
	}

	public void setMessage(JLabel message) {
		this.message = message;
	}

}