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
	private RoundedJButton createPerson,jButtonOption1,jButtonOption2,jButtoOption3;
	private JPCreatePerson jpcreatePerson;

	public JPMenu(ActionListener listener,JPanel cont) {
		jpcreatePerson = new JPCreatePerson(listener);
		new JPOption1();
		this.setLayout(new FlowLayout());
		initComponenets(listener,cont);
	}

	private void initComponenets(ActionListener listener,JPanel cont) {
		this.setBackground(Color.white);
		createPerson = new RoundedJButton(15, 15, ConstantsUI.BUTTON_MENU_SHOW_PANEL_CREATE_PERSON, ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.C_MENU_SHOW_CREATE_PERSON_PANEL.toString(), listener );
		jButtonOption1 = new RoundedJButton(15, 15, ConstantsUI.BUTTON_OPTION_1, ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.PANEL_TWO.toString(), listener );
		this.add(createPerson);
		this.add(jButtonOption1);

	}

	public JButton getPerson() {
		return jButtoOption3;
	}

	public RoundedJButton getOption1() {
		return jButtonOption1;
	}

	public void setOption1(RoundedJButton option1) {
		this.jButtonOption1 = option1;
	}

	public RoundedJButton getOption2() {
		return jButtonOption2;
	}

	public void setOption2(RoundedJButton option2) {
		this.jButtonOption2 = option2;
	}

	public RoundedJButton getOption3() {
		return jButtoOption3;
	}

	public void setOption3(RoundedJButton option3) {
		this.jButtoOption3 = option3;
	}

	public JPCreatePerson getJpcreatePerson() {
		return jpcreatePerson;
	}

	public void setJpcreatePerson(JPCreatePerson jpcreatePerson) {
		this.jpcreatePerson = jpcreatePerson;
	}
}