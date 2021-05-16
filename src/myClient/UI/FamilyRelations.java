package myClient.UI;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.RelationType;
import myClient.Commands;

public class FamilyRelations extends JFrame {

	private JComboBox<RelationType> comboBoxOne;
	private JComboBox<String> comboBoxTwo;
	private JLabel jLabel;
	private JLabel jLabelTwo;
	private JPanel panel;
	private JButton addRelationFamily;
	
	public FamilyRelations(Map<Long, String> map,ActionListener listener) {
		panel = new JPanel(); 
		comboBoxOne = new JComboBox<>(RelationType.values());
		addRelationFamily = new JButton("Agregar Relacion Familiar");
		comboBoxTwo = new JComboBox<>();
		jLabel = new JLabel("Esta persona va a ser: ");
		for (Entry<Long, String> entry : map.entrySet()) {
		    comboBoxTwo.addItem(entry.getValue() + "-> CON ID: -> " + entry.getKey());;
		}
		jLabelTwo = new JLabel("de: ");
		addRelationFamily.addActionListener(listener);
		addRelationFamily.setActionCommand(Commands.ADD_RELATION_FAMILY.name());
		panel.add(jLabel);
		panel.add(comboBoxOne);
		panel.add(jLabelTwo);
		panel.add(comboBoxTwo);
		panel.add(addRelationFamily);

		this.add(panel);
		this.setVisible(true);
		this.setSize(700,100);
		this.setLocationRelativeTo(null);
	}

	public JComboBox<RelationType> getComboBoxOne() {
		return comboBoxOne;
	}

	public JComboBox<String> getComboBoxTwo() {
		return comboBoxTwo;
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

	public JButton getAddRelationFamily() {
		return addRelationFamily;
	}
	
	
	
//	
//	public static void main(String[] args) {
//		Map<Long, String> map = new HashMap<Long, String>();
//		map.put((long) 1111, "Carlos");
//		map.put((long) 11121, "Pedro");
//		new FamilyRelations(map);
//	}
}
