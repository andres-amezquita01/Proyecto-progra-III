package myClient.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolTip;

import model.Person;
import myClient.Commands;
import myClient.items.MyComboBox;
import myClient.items.RoundedJButton;

public class JPViewFamilyPerson extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextArea jTextFielParentesco;

//	private JComboBox<String> comboBox;
//	private JButton before,after;
	private MyPanel myPanel;
	private MyComboBox myComboBox;
	private RoundedJButton jButtonBefore, jButtonAfter;
	public JPViewFamilyPerson(ActionListener listener) {
		myPanel = new MyPanel();
		initComponet(listener);
	}

	private void initComponet(ActionListener listener) {
		
		myComboBox = new MyComboBox("");
		myComboBox.setBorder(BorderFactory.createTitledBorder(ConstantsUI.RELATION_FAMILY));

		this.setLayout(new BorderLayout());
		this.add(myComboBox,BorderLayout.NORTH);
		this.add(myPanel,BorderLayout.CENTER);
		
		jTextFielParentesco = new JTextArea();
		jTextFielParentesco.setBackground(Color.white);
		jTextFielParentesco.setText("Parentesco");
		jTextFielParentesco.setEditable(false);
		jTextFielParentesco.setPreferredSize(new Dimension(100, 100));
		jTextFielParentesco.setFont(new Font("SansSerif", Font.BOLD, 20));
//		jTextFielParentesco.setHorizontalAlignment(JTextArea.CENTER_ALIGNMENT);
		this.add(jTextFielParentesco, BorderLayout.SOUTH);
		jButtonBefore = new RoundedJButton(15, 15, ConstantsUI.BUTTON_BEFORE, ConstantsUI.COLOR_LIGTH_RED, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.BEFORE.toString(), listener ){
            private static final long serialVersionUID = 1L;
            @Override
            public JToolTip createToolTip() {
                JToolTip toolTip = super.createToolTip();
                toolTip.setBackground(ConstantsUI.COLOR_DARCK_BLUE);
                toolTip.setForeground(Color.WHITE);
                toolTip.setFont(ConstantsUI.RUBIK_BOLD_16);
                return toolTip;
            }
        };
		this.add(jButtonBefore,BorderLayout.WEST);
		jButtonAfter = new RoundedJButton(15, 15, ConstantsUI.BUTTON_AFTER, ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.AFTER.toString(), listener ){
            private static final long serialVersionUID = 1L;
            @Override
            public JToolTip createToolTip() {
                JToolTip toolTip = super.createToolTip();
                toolTip.setBackground(ConstantsUI.COLOR_DARCK_BLUE);
                toolTip.setForeground(Color.WHITE);
                toolTip.setFont(ConstantsUI.RUBIK_BOLD_16);
                return toolTip;
            }
        };
		this.add(jButtonAfter,BorderLayout.EAST);
	}
//	private void initComponet(ActionListener listener) {
//		
//		comboBox = new JComboBox<String>();
//		comboBox.addActionListener(listener);
//		this.setLayout(new BorderLayout());
//		this.add(comboBox,BorderLayout.NORTH);
//		this.add(myPanel,BorderLayout.CENTER);
//		parentesco = new JTextField(20);
//		parentesco.setText("Parentesco");
//		parentesco.setEditable(false);
//		parentesco.setPreferredSize(new Dimension(100, 100));
//		parentesco.setFont(new Font("SansSerif", Font.BOLD, 20));
//		parentesco.setHorizontalAlignment(JTextField.CENTER);
//		this.add(parentesco, BorderLayout.SOUTH);
//		before = new JButton("<");
//		before.setActionCommand(Commands.BEFORE.toString());
//		before.addActionListener(listener);
//		this.add(before,BorderLayout.WEST);
//		after = new JButton(">");
//		after.setActionCommand(Commands.AFTER.toString());
//		after.addActionListener(listener);
//		this.add(after,BorderLayout.EAST);
//	}


	
	public MyComboBox getComboBox() {
		return myComboBox;
	}

	public JTextArea getParentesco() {
		return jTextFielParentesco;
	}

	public void setParentesco(JTextArea parentesco) {
		this.jTextFielParentesco = parentesco;
	}


	public void updateInfoPerson(Person person) {
		myPanel.updateInfoPerson(person);
	}
	
}
