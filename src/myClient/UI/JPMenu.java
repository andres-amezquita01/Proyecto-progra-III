package myClient.UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import myClient.Commands;
import myClient.items.RoundedJButton;

public class JPMenu extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoundedJButton createPerson,option1,option2,option3;
	private JPCreatePerson jpcreatePerson;

	public JPMenu(ActionListener listener,JPanel cont) {
		jpcreatePerson = new JPCreatePerson(listener);
		new JPOption1();
		this.setLayout(new FlowLayout());
		initComponenets(listener,cont);
	}

	private void initComponenets(ActionListener listener,JPanel cont) {
		createPerson = new RoundedJButton(15, 15, "Crear Persona", ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.PANEL_ONE.name(), listener );
		option1 = new RoundedJButton(15, 15, "Opcion 2", ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.PANEL_TWO.name(), listener );



		this.add(createPerson);
		this.add(option1);

	}

	public JButton getPerson() {
		return option3;
	}

	public RoundedJButton getOption1() {
		return option1;
	}

	public void setOption1(RoundedJButton option1) {
		this.option1 = option1;
	}

	public RoundedJButton getOption2() {
		return option2;
	}

	public void setOption2(RoundedJButton option2) {
		this.option2 = option2;
	}

	public RoundedJButton getOption3() {
		return option3;
	}

	public void setOption3(RoundedJButton option3) {
		this.option3 = option3;
	}

	public JPCreatePerson getJpcreatePerson() {
		return jpcreatePerson;
	}

	public void setJpcreatePerson(JPCreatePerson jpcreatePerson) {
		this.jpcreatePerson = jpcreatePerson;
	}
}