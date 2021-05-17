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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
	private JPOption1 jPanel1;
	private JPanel containerApp;
	private static final String PANEL_CREATE_PERSON = "panel persona";
	private static final String PANEL_LOGGIN = "panel loggin";
	private static final String PANEL_1 = "1";
	private static final String PANEL_2 = "2";


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
		this.add(jpLoggin);
	}
	private void initComponents(ActionListener actionListener) {
		this.setExtendedState(MAXIMIZED_BOTH);
		containerApp = new JPanel();
		containerApp.setLayout(new BorderLayout());
//		containerFinal.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 30, 4, 30, Color.white));
		this.setExtendedState(MAXIMIZED_BOTH);
		container = new JPanel();
		jPanel1 = new JPOption1(actionListener);
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
		container.add(jPanel1, PANEL_2);
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
	public Person getPersonCreated() {
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
	public void showPanel1() {
		cardLayout.show(container, PANEL_1);
	}
	public void showPanel2() {
		cardLayout.show(container, PANEL_2);
	}
}