package myClient.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolTip;

import model.Person;
import myClient.Commands;
import myClient.items.MyComboBox;
import myClient.items.RoundedJButton;
/**
 * panel que me permite visualizar las relaciones familiares de una persona dada
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */
public class JPViewFamilyPerson extends JPanel {
	private static final long serialVersionUID = 1L;

	private JTextArea jTextFielParentesco;
	private MyPanel myPanel;
	private MyComboBox myComboBox;
	private RoundedJButton jButtonBefore, jButtonAfter;

	private JButton viewGraph;
	/**
	 * inicializa el panel
	 * @param listener manejador de eventos de mi panel
	 */
	public JPViewFamilyPerson(ActionListener listener) {
		myPanel = new MyPanel();
		initComponet(listener);
	}
	/**
	 * inicializamos los componenetes del respectivo panel
	 * @param listener manejador de eventos del panel
	 */
	private void initComponet(ActionListener listener) {
		JPanel aux = new JPanel();
		aux.setLayout(new BorderLayout());
		aux.setBackground(Color.white);
		myComboBox = new MyComboBox("");
		myComboBox.setBorder(BorderFactory.createTitledBorder(ConstantsUI.RELATION_FAMILY));
		myComboBox.setActionCommand(Commands.AUX.toString());
		myComboBox.addActionListener(listener);
		this.setLayout(new BorderLayout());
		this.add(myComboBox,BorderLayout.NORTH);
		this.add(myPanel,BorderLayout.CENTER);
		
		jTextFielParentesco = new JTextArea();
		jTextFielParentesco.setBackground(Color.white);
		jTextFielParentesco.setText(ConstantsUI.PARENTESCO);
		jTextFielParentesco.setEditable(false);
		jTextFielParentesco.setPreferredSize(new Dimension(100, 100));
		jTextFielParentesco.setFont(new Font("SansSerif", Font.BOLD, 20));
//		jTextFielParentesco.all;

		aux.add(jTextFielParentesco, BorderLayout.SOUTH);
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
        
       viewGraph = new JButton("Ver Grafo Relaciones Familiares");
       viewGraph.addActionListener(listener);
       viewGraph.setActionCommand(Commands.GRAPH_fAMILY.name());
//       aux.add(viewGraph,BorderLayout.EAST);
      
        
		this.add(jButtonAfter,BorderLayout.EAST);
		this.add(aux,BorderLayout.SOUTH);
	}
	public MyComboBox getComboBox() {
		return myComboBox;
	}
	//----------------------------------------------
	public JTextArea getParentesco() {
		return jTextFielParentesco;
	}
	public void updateInfoPerson(Person person) {
		myPanel.updateInfoPerson(person);
	}
	
}
