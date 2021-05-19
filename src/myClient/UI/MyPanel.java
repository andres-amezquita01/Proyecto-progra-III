package myClient.UI;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Person;
import utilities.ComplementDatas;

public class MyPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblId, lblName, lblDateBirth, lblGender;
	private JTextArea txtId, txtName, txtDateBirth, txtGender;
	private ComplementDatas complementDatas;
	public MyPanel() {
		complementDatas = new ComplementDatas();
		setLayout(new GridLayout(4, 2));
		initComponents();
	}

	private void initComponents() {
		this.setBackground(Color.white);
		lblId = new JLabel("Id");
		
		txtId = new JTextArea();
		txtId.setBorder(BorderFactory.createTitledBorder(ConstantsUI.ITEM_PERSON_ID));
		txtId.setEditable(false);
		
		lblName = new JLabel("Nombre");
		
		txtName = new JTextArea();
		txtName.setEditable(false);
		txtName.setBorder(BorderFactory.createTitledBorder(ConstantsUI.ITEM_PERSON_NAME));

		lblDateBirth = new JLabel(ConstantsUI.ITEM_BIRTH_DAY);
		
		txtDateBirth = new JTextArea();
		txtDateBirth.setEditable(false);
		txtDateBirth.setBorder(BorderFactory.createTitledBorder(ConstantsUI.ITEM_BIRTH_DAY));

		lblGender= new JLabel("Genero");
		
		txtGender = new JTextArea();
		txtGender.setEditable(false);
		txtGender.setBorder(BorderFactory.createTitledBorder(ConstantsUI.T_BOX_GENDER));

		add(lblId);
		add(txtId);
		add(lblName);
		add(txtName);
		add(lblDateBirth);
		add(txtDateBirth);
		add(lblGender);
		add(txtGender);
		
		
	}
	

	public void updateInfoPerson(Person person) {
		txtId.setText(person.getId()+"");
		txtName.setText(person.getFirstName().replace("_", ""));
		txtDateBirth.setText(person.getBirthDay().toString());
		txtGender.setText(person.getGender().name());
	}

}