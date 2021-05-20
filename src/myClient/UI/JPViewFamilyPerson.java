package myClient.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Person;
import myClient.Commands;
/**
 * panel que me permite visualizar las relaciones familiares de una persona dada
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */
public class JPViewFamilyPerson extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField parentesco;

	private JComboBox<String> comboBox;
	private JButton before,after;
	private MyPanel myPanel;
	
	
	/**
	 * inicializo mi panel
	 * @param listener manejador de eventos de mi panel
	 */
	public JPViewFamilyPerson(ActionListener listener) {
		myPanel = new MyPanel();
		initComponet(listener);
	}

	
	/**
	 * inicializamos los componenetes de mi respectivo panel
	 * @param listener manejador de eventos de mi panel
	 */
	private void initComponet(ActionListener listener) {
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(listener);
		this.setLayout(new BorderLayout());
		this.add(comboBox,BorderLayout.NORTH);
		this.add(myPanel,BorderLayout.CENTER);
		parentesco = new JTextField(20);
		parentesco.setText("Parentesco");
		parentesco.setEditable(false);
		parentesco.setPreferredSize(new Dimension(100, 100));
		parentesco.setFont(new Font("SansSerif", Font.BOLD, 20));
		parentesco.setHorizontalAlignment(JTextField.CENTER);
		this.add(parentesco, BorderLayout.SOUTH);
		before = new JButton("<");
		before.setActionCommand(Commands.BEFORE.toString());
		before.addActionListener(listener);
		this.add(before,BorderLayout.WEST);
		after = new JButton(">");
		after.setActionCommand(Commands.AFTER.toString());
		after.addActionListener(listener);
		this.add(after,BorderLayout.EAST);
	}


	
	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<String> comboBox) {
		this.comboBox = comboBox;
	}

	public JButton getAfter() {
		return after;
	}

	public void setAfter(JButton after) {
		this.after = after;
	}

	public JButton getBefore() {
		return before;
	}
	
	public JTextField getParentesco() {
		return parentesco;
	}

	public void setParentesco(JTextField parentesco) {
		this.parentesco = parentesco;
	}


	public void setBefore(JButton before) {
		this.before = before;
	}

	public void updateInfoPerson(Person person) {
		myPanel.updateInfoPerson(person);
	}
	
}
