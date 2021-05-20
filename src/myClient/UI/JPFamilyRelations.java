package myClient.UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import myClient.Commands;
import myClient.items.MyComboBox;
import myClient.items.RoundedJButton;
/**
 * 
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *clase que me crea el frame de mis relacione familiares
 */
public class JPFamilyRelations extends JFrame {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyComboBox myComboBoxOne, myComboBox2;
	private JLabel jLabel;
	private JLabel jLabelTwo;
	private JPanel panel;
	private RoundedJButton buttonAddRelationFamily;
	
	
	/**
	 * contructor de mi clase
	 * @param map recibo esta estructura para añadir sus valores al combo Box
	 * @param listener
	 */
	public JPFamilyRelations(Map<Long, String> map,ActionListener listener) {
		panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.white);
		myComboBoxOne = new MyComboBox("");
		fillMycomboboxOne();
		myComboBox2 = new MyComboBox("");
		fillMycomboBoxTwo( map);
		
		jLabel = new JLabel("Esta persona va a ser: ");
		jLabelTwo = new JLabel("de: ");
		buttonAddRelationFamily = new RoundedJButton(15, 15, ConstantsUI.BUTTON_ADD_RELATION_FAMILY, ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.ADD_RELATION_FAMILY.toString(), listener ){
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
		panel.add(jLabel);
		panel.add(myComboBoxOne);
		panel.add(jLabelTwo);
		panel.add(myComboBox2);
		panel.add(buttonAddRelationFamily);
		this.setBackground(Color.white);
		this.add(panel);
		this.setSize(800,200);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public MyComboBox getComboBoxOne() {
		return myComboBoxOne;
	}

	public MyComboBox getComboBoxTwo() {
		return myComboBox2;
	}

	public JLabel getjLabel() {
		return jLabel;
	}

	public JLabel getjLabelTwo() {
		return jLabelTwo;
	}

	public JPanel getPanel() {
		return panel;
	}

	public RoundedJButton getAddRelationFamily() {
		return buttonAddRelationFamily;
	}
	
	
	/**
	 * agrego los items a mi combo box;
	 */
	public void fillMycomboboxOne() {
		myComboBoxOne.addItem("Primo");
		myComboBoxOne.addItem("Tio");
		myComboBoxOne.addItem("Padre");
		myComboBoxOne.addItem("Abuelo");
		myComboBoxOne.addItem("Hermano");
		myComboBoxOne.addItem("Esposo");
		myComboBoxOne.addItem("Nieto");
		myComboBoxOne.addItem("Hijo");

	}
	public void fillMycomboBoxTwo(Map<Long, String> map) {
		for (Entry<Long, String> entry : map.entrySet()) {
		    myComboBox2.addItem(entry.getValue() + "-> CON ID: -> " + entry.getKey());;
		}
	}
	

}
