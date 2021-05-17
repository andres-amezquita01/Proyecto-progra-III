package myClient.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;

import myClient.Commands;
import myClient.items.RoundedJButton;

public class JPOption1 extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JLabel message;
	private JComboBox<String> combox;
	private RoundedJButton search;

	public JPOption1(ActionListener actionListener) {
//		setMessage(new JLabel("panel Opcion 1"));
		combox = new  JComboBox<String>();
		combox.addItem("ddddfew");
		combox.setBorder(BorderFactory.createTitledBorder("Persona"));
		combox.setPreferredSize(new Dimension(900, 65));
		combox.setBackground(Color.WHITE);
		
		search = new RoundedJButton(15, 15, "SEARCH", ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.SEARCH_RELATIONS_FAMILIES.name(), actionListener ){
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
        
		this.setBackground(Color.WHITE);
		this.add(combox);
		this.add(search);
//		this.add(message);
	}

//	public JLabel getMessage() {
//		return message;
//	}

//	public void setMessage(JLabel message) {
//		this.message = message;
//	}

}