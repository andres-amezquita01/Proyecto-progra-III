package myClient.UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Person;

public class JFMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPMenu menu;
	private JPCreatePerson jPcreateStudent;
	private JPanel cont;
	private JScrollPane jScroll;
	private CardLayout cl;
	private JPOption1 j1;
	private static final String CREATE_STUDENT = "Student";
	ActionListener actionListener;

	public JFMainWindow(ActionListener controllerApp) {
		this.actionListener = controllerApp;
		this.setTitle(ConstantsUI.T_MAIN_WINDOW);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(650, 400));
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		getRootPane().setBorder(BorderFactory.createMatteBorder(0, 30, 4, 30, Color.white));
		this.setExtendedState(MAXIMIZED_BOTH);
		initComponents(controllerApp);

	}

	private void initComponents(ActionListener actionListener) {
		cont = new JPanel();
		j1 = new JPOption1();
		cl = new CardLayout();
		cont.setBackground(Color.WHITE);

		jPcreateStudent = new JPCreatePerson(actionListener);
		cont.setLayout(cl);

		cont.add(jPcreateStudent, CREATE_STUDENT);

		menu = new JPMenu(actionListener, cont);
		jScroll = new JScrollPane(cont);
		jScroll.setForeground(Color.white);
		jScroll.setBorder(null);
		jScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

		this.add(menu, BorderLayout.NORTH);
		this.add(jScroll, BorderLayout.CENTER);

		cont.add(jPcreateStudent, "1");
		cont.add(j1, "2");

		cl.show(cont, "1");

		this.add(cont);

	}

	public Person getPersonCreated() {
		return jPcreateStudent.createPerson();
	}

	public void showPanels(String string) {
		cl.show(cont, string);

	}

}