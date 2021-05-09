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


public class JFMainWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel containerBody;
	private JPCreatePerson jPcreateStudent;
	private JScrollPane jScroll;

	private static final String LOAD_ANIMATION = "load Animation";
	private static final String TABLE_TADAS = "Table Datas";
	private static final String CREATE_STUDENT = "Student";
	ActionListener actionListener;

	public JFMainWindow(ActionListener controllerApp) {
		this.actionListener =  controllerApp;
		this.setTitle(ConstantsUI.T_MAIN_WINDOW);
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
		this.setMinimumSize(new Dimension(650, 400));
		this.setLocationRelativeTo( null );
		this.setLayout( new BorderLayout() );
//		this.setIconImage(new ImageIcon(getClass().getResource(ConstantsUI.PATH_ICON_APP)).getImage());
		getRootPane().setBorder(BorderFactory.createMatteBorder(0, 30, 4, 30, Color.white));
		
		this.setExtendedState( MAXIMIZED_BOTH );
		initComponents(controllerApp);

	}

	public void setDimentionReport() {
//		jPReportBarGraphic.setHeigth((int)(containerBody.getSize().height*0.90));
//		jPReportBarGraphic.setWidth(containerBody.getSize().width);
//		jPReportBarGraphic.setSize(jScroll.getSize());
	}
	private void initComponents( ActionListener actionListener ) {
		containerBody = new JPanel();
		containerBody.setBackground(Color.WHITE);
		jPcreateStudent = new JPCreatePerson(actionListener);
		containerBody.setLayout(new CardLayout(0, 0));
		
		containerBody.add(jPcreateStudent, CREATE_STUDENT);
		
		jScroll = new JScrollPane(containerBody);
		jScroll.setForeground(Color.white);
		jScroll.setBorder(null);
		jScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

//		this.add(jPcontainerMenu, BorderLayout.NORTH);
		this.add(jScroll, BorderLayout.CENTER);
//		this.add(jpFooter,BorderLayout.SOUTH);

	}
	public void showPanelTable() {
		CardLayout cl = (CardLayout)(containerBody.getLayout());
	    cl.show(containerBody, TABLE_TADAS);
	}
	
	public void showPanelCreateStudent() {
		CardLayout cl = (CardLayout)(containerBody.getLayout());
	    cl.show(containerBody, CREATE_STUDENT);
	}

	public void showLoadScreen() {
		CardLayout cl = (CardLayout)(containerBody.getLayout());
	    cl.show(containerBody, LOAD_ANIMATION);
	}
	
	
	
	public void makeVisiblePanelReportEigth() {
		
	}
	
	
	public Person getPersonCreated()  {
		return jPcreateStudent.createPerson();
	}
	public void changeLanguage(){
//		this.setTitle( HandlerLanguage.languageProperties.getProperty(ConstantsUI.T_MAIN_WINDOW ));
//		jpTableElements.changeLanguage();
//		jPcontainerMenu.changeLanguage();
//		jPReportBarGraphic.changeLanguage();
//		jpReportLinesGraphic.changeLanguage();
		jPcreateStudent.changeLanguage();
	}
}

