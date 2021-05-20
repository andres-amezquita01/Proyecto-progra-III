package myClient.UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Password;
import model.Person;

/**
 * clase donde manejamos el frame principal de la aplicacion el cual me permite la interaccion con el usuario
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */
public class JFMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPMenu menu;
	private JPCreatePerson jPcreateStudent;
	private JPLoggin jpLoggin;
	private JPanel container;
	private JScrollPane jScroll;
	private CardLayout cardLayout;
	private JPanel containerApp;
	
	private JPViewFamilyPerson jPanelViewFamilyPerson;

	
	private static final String PANEL_CREATE_PERSON = "panel persona";
	private static final String PANEL_SEARCH_FAMILY_RELATION = "panel buscar relaciones familiares";


	ActionListener actionListener;

	
	
	/**
	 * contructor de mi clase donde inializo y ubico los paneles correspondientes a mi apliacion
	 * @param controllerApp manejador de eventos que va a tenr mi frame principal
	 */
	public JFMainWindow(ActionListener controllerApp) {
		this.actionListener = controllerApp;
		this.setTitle(ConstantsUI.T_MAIN_WINDOW);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		getRootPane().setBorder(BorderFactory.createMatteBorder(0, 30, 4, 30, Color.white));
		initRegistry(actionListener);

	}
	
	
	/**
	 * metodo que me permite modificar el layout de mi frame principa, ademas donde instancio mi respectivo login y demas
	 * @param actionListener Eventos que tendra mi frame algunos de sus paneles tambien tendran asociado este mismo listenersSS
	 */
	private void initRegistry(ActionListener actionListener) {
		this.setLayout(new GridLayout(1, 1));
		this.setSize(new Dimension(600,600));
		jpLoggin = new JPLoggin(actionListener);
		jPanelViewFamilyPerson = new JPViewFamilyPerson(actionListener);//---
		this.add(jpLoggin);
		
	}
	
	
	/**
	 * metodo que me inicializa el frame principal despues de que mi usuario haga el respectivo login 
	 * @param actionListener manejador de eventos para mi frame
	 */
	private void initComponents(ActionListener actionListener) {
		this.setExtendedState(MAXIMIZED_BOTH);
		containerApp = new JPanel();
		containerApp.setLayout(new BorderLayout());
		this.setExtendedState(MAXIMIZED_BOTH);
		
		container = new JPanel();
		jPanelViewFamilyPerson = new JPViewFamilyPerson(actionListener);//---
		cardLayout = new CardLayout();
		
		
		container.setBackground(Color.WHITE);

		jPcreateStudent = new JPCreatePerson(actionListener);
		container.setLayout(cardLayout);
		container.add(jPcreateStudent, PANEL_CREATE_PERSON);

		menu = new JPMenu(actionListener, container);
		jScroll = new JScrollPane(container);
		jScroll.setForeground(Color.white);
		jScroll.setBorder(null);
		jScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

		containerApp.add(menu, BorderLayout.NORTH);
		containerApp.add(jScroll, BorderLayout.CENTER);

		container.add(jPcreateStudent, PANEL_CREATE_PERSON);
		container.add(jPanelViewFamilyPerson, PANEL_SEARCH_FAMILY_RELATION);//---
		cardLayout.show(container, PANEL_CREATE_PERSON);
		containerApp.add(container);

	}
	
	/**
	 * cuando se inicia sesion le permito al usuario poder acceder al frame principal mediante este metodo
	 */
	public void setPane() {
		this.setVisible(false);
		initComponents(actionListener);
		this.setContentPane(containerApp);
		this.setVisible(true);
	}
	
	
	public Person getPersonCreated() {
		return jPcreateStudent.createPerson();
	}
	public Password getUserCreated() {
		return jpLoggin.createPassword();
	}
	
	/**
	 * me muestra un cardLayout en mi frame principal
	 * @param string
	 */
	public void showPanels(String string) {
		cardLayout.show(container, string);
	}
	public void showPanelPerson() {
		cardLayout.show(container, PANEL_CREATE_PERSON);
	}
	public void showPanelSearchFamilyRelation() {
		cardLayout.show(container, PANEL_SEARCH_FAMILY_RELATION);
	}
	public void showExceptionUserNotRegistry() {
		JOptionPane.showMessageDialog(null, ConstantsUI.EXCEPTION_USER_NOT_REGISTRY);
	}
	public void showExceptionUserDuplicate() {
		JOptionPane.showMessageDialog(null, ConstantsUI.EXCEPTION_USER_NOT_REGISTRY);
	}
	public void showPasswordRecovered(String passwordRecovered) {
		JOptionPane.showMessageDialog(null, "contraseņa recuperada\n " + passwordRecovered);
		
	}	
	
	public JPViewFamilyPerson getjPanel1() {
		return jPanelViewFamilyPerson;
	}
}




