package myClient.UI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolTip;

import com.placeholder.PlaceHolder;
import com.toedter.calendar.JDateChooser;
import model.Gender;
import model.Person;
import myClient.Commands;
import myClient.items.MyComboBox;
import myClient.items.RoundedJButton;
import utilities.ComplementDatas;


/**
 * panel donde pedimos la informacion de una persona al usuario para que esta la pueda crear
 * al crearla se registrara en todos los archivos tanto de indices como en el archivo maestro
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */
public class JPCreatePerson extends JPanel{
	private static final long serialVersionUID = 1L;
	private JPanel panelButton;
	private JTextField jtPersonId;
	private JTextField jtFirstName;
	private JTextField jtLastName;
	private MyComboBox mcbSelectGender;
	private JDateChooser jChooserBirthDay;
	private JSpinner jSpinnerValue;
	private JTextField jtProfile;
	private JTextField jtPassPort;
	private RoundedJButton jBCreate, jBCancelCreate,exitApp;
	
	
	/**
	 * contructor de mi clase donde la inicializo
	 * @param actionListener manejador de eventos de mi clase
	 */
	public JPCreatePerson(ActionListener actionListener) {
		setBackground(Color.white);
		initComponents(actionListener);
	}
	
	/**
	 *inicializamos  todos  mis componentes que van a ir en el panel de crear personna 
	 * @param actionListener
	 */
	private void initComponents(  ActionListener actionListener ) {
		GridLayout gridLayout = new GridLayout(9, 1);
		gridLayout.setVgap( 15 );
		setLayout(new GridLayout(9,1));
		setBorder(BorderFactory.createMatteBorder(20, 200, 200, 200, Color.white));

		jtPersonId = new JTextField();
		jtPersonId.setBorder(BorderFactory.createTitledBorder(ConstantsUI.ITEM_PERSON_ID));
		@SuppressWarnings("unused")
		PlaceHolder holder = new PlaceHolder(jtPersonId,ConstantsUI.PLACE_HOLDER_NUMBER_ID);
		mcbSelectGender = new MyComboBox("");
		mcbSelectGender.setBorder(BorderFactory.createTitledBorder(ConstantsUI.T_BOX_GENDER));
		addItemGender();
		
		
		
		jtFirstName = new JTextField();
		jtFirstName.setBorder(BorderFactory.createTitledBorder(ConstantsUI.ITEM_PERSON_NAME));
		
		jtLastName= new JTextField();
		jtLastName.setBorder(BorderFactory.createTitledBorder(ConstantsUI.ITEM_PERSON_LAST_NAME));

		jChooserBirthDay = new JDateChooser();
		
		jSpinnerValue = new JSpinner();
		jSpinnerValue.setBorder(BorderFactory.createTitledBorder(ConstantsUI.ITEM_PERSON_VALUE));

		
		jtProfile = new JTextField();
		jtProfile.setBorder(BorderFactory.createTitledBorder(ConstantsUI.ITEM_PERSON_PROFILE));

		jtPassPort = new JTextField();
		jtPassPort.setBorder(BorderFactory.createTitledBorder(ConstantsUI.ITEM_PERSON_PASSPORT));

		jBCreate = new RoundedJButton(15, 15, ConstantsUI.BUTTON_CREATE, ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.C_CREATE_PERSON.toString(), actionListener ){
            private static final long serialVersionUID = 1L;
            @Override
            public JToolTip createToolTip() {
                JToolTip toolTip = super.createToolTip();
                toolTip.setBackground(ConstantsUI.COLOR_DARCK_BLUE);
                toolTip.setForeground(Color.WHITE);
                toolTip.setFont(ConstantsUI.RUBIK_BOLD_16);
                return toolTip;
            }
        };;
        
    	jBCancelCreate = new RoundedJButton(15, 15, ConstantsUI.BUTTON_CANCEL_CREATE, ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.C_CANCEL_CREATE_PERSON.toString(), actionListener ){
            private static final long serialVersionUID = 1L;
            @Override
            public JToolTip createToolTip() {
                JToolTip toolTip = super.createToolTip();
                toolTip.setBackground(ConstantsUI.COLOR_DARCK_BLUE);
                toolTip.setForeground(Color.WHITE);
                toolTip.setFont(ConstantsUI.RUBIK_BOLD_16);
                return toolTip;
            }
        };;
        
        
    	exitApp = new RoundedJButton(15, 15, ConstantsUI.BUTTON_EXIT_APP, ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.EXIT.toString(), actionListener ){
            private static final long serialVersionUID = 1L;
            @Override
            public JToolTip createToolTip() {
                JToolTip toolTip = super.createToolTip();
                toolTip.setBackground(ConstantsUI.COLOR_DARCK_BLUE);
                toolTip.setForeground(Color.WHITE);
                toolTip.setFont(ConstantsUI.RUBIK_BOLD_16);
                return toolTip;
            }
        };;
        
        
        
        jBCancelCreate.setBackground( Color.decode("#922B3E"));
        exitApp.setBackground( Color.decode("#922B3E"));
        GridLayout gridLayoutButton = new GridLayout(1, 2);
        gridLayoutButton.setVgap( 15 );
        gridLayoutButton.setHgap(15);
        panelButton = new JPanel();
        panelButton.setBackground(Color.white);
        panelButton.setLayout(gridLayoutButton);
        panelButton.add(jBCancelCreate);
        panelButton.add(jBCreate);
        panelButton.add(exitApp);
        
		add(jtPersonId);
		add(jtFirstName);
		add(jtLastName);
		add(mcbSelectGender);
		add(jChooserBirthDay);
		add(jSpinnerValue);
		add(jtProfile);
		add(jtPassPort);
		add(panelButton);


	}
	
	
	/**
	 * añadimos el genero a nuestro panel
	 */
	public void addItemGender() {
		mcbSelectGender.addItem("Femenino");
		mcbSelectGender.addItem("Masculino");
	}
	
	public void createButtons() {
		
	}
	
	/**
	 * creamos la personas la cual tendra sus atributos 
	 * @return devolvemos la persona
	 */
	public Person createPerson()  {
			return new Person(Integer.parseInt(jtPersonId.getText()),
					jtFirstName.getText(), 
					jtLastName.getText(), 
					Gender.values()[mcbSelectGender.getSelectedIndex()],
					new ComplementDatas().parseStringToLocalDate(jChooserBirthDay.getCalendar()),
					(int)(jSpinnerValue.getValue()),
					jtProfile.getText(), null, jtPassPort.getText());
	}
	/**
	 * Limpia las areas de texto
	 */
	public void clear() {
		jtPersonId.setText("");
		jtFirstName.setText("");
		jtLastName.setText("");
		jtProfile.setText("");
		jtPassPort.setText("");
	}
	
	
}
