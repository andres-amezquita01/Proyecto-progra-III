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

public class JPFamilyRelations extends JFrame {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	private JComboBox<RelationType> comboBoxOne;
	//	private JComboBox<String> comboBoxTwo;
	private MyComboBox myComboBoxOne, myComboBox2;
	private JLabel jLabel;
	private JLabel jLabelTwo;
	private JPanel panel;
//	private JButton addRelationFamily;
	private RoundedJButton buttonAddRelationFamily;
	public JPFamilyRelations(Map<Long, String> map,ActionListener listener) {
		panel = new JPanel(); 
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.white);
//		comboBoxOne = new JComboBox<>(RelationType.values());
//		addRelationFamily = new JButton("Agregar Relacion Familiar");
//		comboBoxTwo = new JComboBox<>();
		//-----------
		myComboBoxOne = new MyComboBox("");
		fillMycomboboxOne();
		myComboBox2 = new MyComboBox("");
		fillMycomboBoxTwo( map);
		
		jLabel = new JLabel("Esta persona va a ser: ");
//		for (Entry<Long, String> entry : map.entrySet()) {
//		    comboBoxTwo.addItem(entry.getValue() + "-> CON ID: -> " + entry.getKey());;
//		}
		jLabelTwo = new JLabel("de: ");
//		addRelationFamily.addActionListener(listener);
//		addRelationFamily.setActionCommand(Commands.ADD_RELATION_FAMILY.name());
		
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
//		panel.add(comboBoxOne);
		panel.add(myComboBoxOne);
		panel.add(jLabelTwo);
		panel.add(myComboBox2);
//		panel.add(comboBoxTwo);
//		panel.add(addRelationFamily);
		panel.add(buttonAddRelationFamily);
		this.setBackground(Color.white);
		this.add(panel);
		this.setSize(800,200);
		this.setVisible(true);
//		this.setSize(700,100);
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
	
	public void fillMycomboboxOne() {
		myComboBoxOne.addItem("Primo");
		myComboBoxOne.addItem("Tio");
		myComboBoxOne.addItem("Padre");
		myComboBoxOne.addItem("Abuelo");
		myComboBoxOne.addItem("Hermano");
		myComboBoxOne.addItem("Esposo");
		myComboBoxOne.addItem("Nieto");
		myComboBoxOne.addItem("Hijo");

		//PRIMO,TIO,PADRE,ABUELO,HERMANO,ESPOSO,NIETO,HIJO
	}
	public void fillMycomboBoxTwo(Map<Long, String> map) {
		for (Entry<Long, String> entry : map.entrySet()) {
		    myComboBox2.addItem(entry.getValue() + "-> CON ID: -> " + entry.getKey());;
		}
	}
	
//	
//	public static void main(String[] args) {
//		Map<Long, String> map = new HashMap<Long, String>();
//		map.put((long) 1111, "Carlos");
//		map.put((long) 11121, "Pedro");
//		new FamilyRelations(map);
//	}
}
