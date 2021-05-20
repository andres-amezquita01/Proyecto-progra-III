package myClient.UI;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Person;
import utilities.ComplementDatas;

/**
 * panel donde se encuentra los elementos de mi panel para mostrar los datos de una  persona 
 * en las relaciones familiares
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */
public class MyPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblId, lblName, lblDateBirth, lblGender;
	private JTextField txtId, txtName, txtDateBirth, txtGender;
	private ComplementDatas complementDatas;
	
	/**
	 * contructor de mi panel donde inicializo mis componenetes y le creo un layout
	 */
	public MyPanel() {
		complementDatas = new ComplementDatas();
		setLayout(new GridLayout(4, 2));
		initComponents();
	}
	
	/**
	 * incializo los componetes que tendra mi panel
	 */

	private void initComponents() {
		lblId = new JLabel("Id");
		txtId = new JTextField(20);
		txtId.setEditable(false);
		lblName = new JLabel("Nombre");
		txtName = new JTextField(20);
		txtName.setEditable(false);
		lblDateBirth = new JLabel("Fecha de nacimiento");
		txtDateBirth = new JTextField(20);
		txtDateBirth.setEditable(false);
		lblGender= new JLabel("Genero");
		txtGender = new JTextField(20);
		txtGender.setEditable(false);
		
		add(lblId);
		add(txtId);
		add(lblName);
		add(txtName);
		add(lblDateBirth);
		add(txtDateBirth);
		add(lblGender);
		add(txtGender);
		
		
	}
	
	public JLabel getLblId() {
		return lblId;
	}

	public void setLblId(JLabel lblId) {
		this.lblId = lblId;
	}

	public JLabel getLblGender() {
		return lblGender;
	}

	public void setLblGender(JLabel lblGender) {
		this.lblGender = lblGender;
	}

	public JLabel getLblDateBirth() {
		return lblDateBirth;
	}

	public void setLblDateBirth(JLabel lblDateBirth) {
		this.lblDateBirth = lblDateBirth;
	}

	public JLabel getLblName() {
		return lblName;
	}

	public void setLblName(JLabel lblName) {
		this.lblName = lblName;
	}

	public JTextField getTxtId() {
		return txtId;
	}

	public void setTxtId(JTextField txtId) {
		this.txtId = txtId;
	}

	public JTextField getTxtDateBirth() {
		return txtDateBirth;
	}

	public void setTxtDateBirth(JTextField txtDateBirth) {
		this.txtDateBirth = txtDateBirth;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	
	/**
	 * edito los elemetos de la relacion de la persona actual 
	 * @param person persona que quiero mostrar
	 */
	public void updateInfoPerson(Person person) {
		txtId.setText(person.getId()+"");
		txtName.setText(person.getFirstName().replace("_", ""));
		txtDateBirth.setText(person.getBirthDay().toString());
		txtGender.setText(person.getGender().name());
	}

}