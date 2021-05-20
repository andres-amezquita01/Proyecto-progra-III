package myClient.UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import myClient.Commands;
import myClient.items.RoundedJButton;
/**
 * clase que nos permite crear un menu para la eleccion de las acciones que va a realiar el usuario
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */
public class JPMenu extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoundedJButton createPerson,option1,option2,option3;
	private JPCreatePerson jpcreatePerson;

	
	/**
	 * contructor de mi clase donde inializo los componentes de mi menu
	 * @param listener manejador de eventos de mi clase
	 * @param cont panel que va a contener los elementos de mi menu
	 */
	public JPMenu(ActionListener listener,JPanel cont) {
		jpcreatePerson = new JPCreatePerson(listener);
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout());
		initComponenets(listener,cont);
	}
	
	/**
	 * componentes que van a ir en nuestra aplicacion
	 * @param listener manejador de eventos que llevara mi menu
	 * @param cont panel que contendra mi menu
	 */

	private void initComponenets(ActionListener listener,JPanel cont) {
		createPerson = new RoundedJButton(15, 15, "Crear Persona", ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.C_MENU_SHOW_CREATE_PERSON_PANEL.name(), listener );
		option1 = new RoundedJButton(15, 15, "Buscar Relaciones Familiares", ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.C_MENU_SHOW_SEARCH_RELATION_FAMILY_PANEL.name(), listener );
		option3 = new RoundedJButton(15, 15, "Agregar Una Relacion Familiar", ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.C_MENU_CREATE_RELATION_FAMILY.name(), listener );



		this.add(createPerson);
		this.add(option1);
		this.add(option3);

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