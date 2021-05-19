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

import exceptions.OnlyNumbersException;
import model.Password;
import model.Person;

public class JFMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPMenu menu;
	private JPCreatePerson jPcreateStudent;
	private JPLoggin jpLoggin;
	private JPanel container;
	private JScrollPane jScroll;
	private CardLayout cardLayout;
//	private JPOption1 jPanel1;
	private JPanel containerApp;
	
	private JPViewFamilyPerson jPanelViewFamilyPerson;

	
	private static final String PANEL_CREATE_PERSON = "panel persona";
//	private static final String PANEL_LOGGIN = "panel loggin";
	private static final String PANEL_SEARCH_FAMILY_RELATION = "panel buscar relaciones familiares";


	ActionListener actionListener;

	public JFMainWindow(ActionListener controllerApp) {
		this.actionListener = controllerApp;
		this.setTitle(ConstantsUI.T_MAIN_WINDOW);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setMinimumSize(new Dimension(650, 400));
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		getRootPane().setBorder(BorderFactory.createMatteBorder(0, 30, 4, 30, Color.white));
		initRegistry(actionListener);
//		initComponents(controllerApp);

	}
	private void initRegistry(ActionListener actionListener) {
		this.setLayout(new GridLayout(1, 1));
		this.setSize(new Dimension(600,600));
		jpLoggin = new JPLoggin(actionListener);
		jPanelViewFamilyPerson = new JPViewFamilyPerson(actionListener);//---
		this.add(jpLoggin);
		
	}
	private void initComponents(ActionListener actionListener) {
		this.setExtendedState(MAXIMIZED_BOTH);
		containerApp = new JPanel();
		containerApp.setLayout(new BorderLayout());
//		containerFinal.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 30, 4, 30, Color.white));
		this.setExtendedState(MAXIMIZED_BOTH);
		
		container = new JPanel();
//		jPanel1 = new JPOption1(actionListener);
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
//		container.add(jpLoggin, PANEL_LOGGIN);
		cardLayout.show(container, PANEL_CREATE_PERSON);
		containerApp.add(container);

	}
	public void setPane() {
		this.setVisible(false);
		initComponents(actionListener);
		this.setContentPane(containerApp);
		this.setVisible(true);
	}
	public Person getPersonCreated() throws OnlyNumbersException {
		return jPcreateStudent.createPerson();
	}
	public Password getUserCreated() {
		return jpLoggin.createPassword();
	}
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
	public void showExceptionOnlyNumbers() {
		JOptionPane.showMessageDialog(null, ConstantsUI.EXCEPTION_ONLY_NUMBERS);
	}
	public JPViewFamilyPerson getjPanel1() {
		return jPanelViewFamilyPerson;
	}
}




