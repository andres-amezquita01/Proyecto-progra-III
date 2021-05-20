package myClient.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import myClient.Commands;
import myClient.items.RoundedJButton;

public class JPSetPersons extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel dtmElements; 
	private JTable jtElements;
	private JScrollPane jsTable;
	private JPanel jPanelDatasSet;
	private JTextField jtPersonId;
	private JTextField jtFirstNewName;
	private RoundedJButton jBsetDatas;
	public JPSetPersons(ActionListener actionListener) {
		initPanelDatas(actionListener);
		initComponents();
	}
	private void initComponents() {
		this.setLayout(new GridLayout(1,2));
		this.setBackground(Color.decode("#30373D"));
		dtmElements = new DefaultTableModel();
		jtElements = new JTable();
		dtmElements.setColumnIdentifiers(ConstantsUI.TITLE_TABLE_DATAS);
		jtElements.setModel(dtmElements);
		jtElements.getTableHeader().setReorderingAllowed(false);
		jtElements.getTableHeader().setBackground( ConstantsUI.COLOR_LIGTH_BLUE);
		jtElements.getTableHeader().setPreferredSize( new Dimension(0, 60));
		jtElements.getTableHeader().setForeground(Color.white);
		jtElements.getTableHeader().setFont( ConstantsUI.FONT_MAIN_WINDOW_LABELS );
		jtElements.getTableHeader().setResizingAllowed(false);
		jtElements.setBackground(Color.white);
		jtElements.setFont( ConstantsUI.FONT_MAIN_WINDOW_LABELS );
		jtElements.setFillsViewportHeight(true);
		jtElements.setRowHeight( 25 );
		jtElements.setBorder(null);
		jtElements.setGridColor(Color.black);
		jsTable = new JScrollPane(jtElements);
		jsTable.setForeground(Color.white);
		jsTable.setBorder(null);
		jsTable.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(jPanelDatasSet);
		this.add(jsTable);
		this.setBorder(null);

	}
	public void initPanelDatas(ActionListener actionListener) {
		jtPersonId = new JTextField();
		jtPersonId.setBorder(BorderFactory.createTitledBorder(ConstantsUI.PERSON_ID_TO_SET_DATAS));
		
		jtFirstNewName = new JTextField();
		jtFirstNewName.setBorder(BorderFactory.createTitledBorder(ConstantsUI.PERSON_NEW_NAME));
		jBsetDatas = new RoundedJButton(15, 15, ConstantsUI.SET_DATAS, ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
				ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.C_BUTTON_SET_DATAS.toString(), actionListener ){
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
		jPanelDatasSet = new JPanel();
		jPanelDatasSet.setLayout(new GridLayout(2, 1));
		jPanelDatasSet.add(jtPersonId);
		jPanelDatasSet.add(jtFirstNewName);
	}
	public void addElementToTable(Object[] vector){
		dtmElements.addRow(vector);
	}
	public void addElementToTable(ArrayList<Object[]> vector){
		cleanRowsTable();
		for (Object[] objects : vector) {
			dtmElements.addRow(objects);
		}
	}
	public void cleanRowsTable() {
		dtmElements.setNumRows(0);
	}
	private void centerTextInCell() {
		DefaultTableCellRenderer centerModel = new DefaultTableCellRenderer(); 
		centerModel.setHorizontalAlignment(SwingConstants.CENTER); 
		for (int i = 0; i < dtmElements.getColumnCount(); i++) {
			jtElements.getColumnModel().getColumn(i).setCellRenderer(centerModel);  
		}
	}
}
