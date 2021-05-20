package myClient.items;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;
import myClient.UI.ConstantsUI;

/**
 * 
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *manejo mi combo box donde manipulo mi Jcombo box de tal manera que 
 *pueda personalizarse de una manera muy especifica
 */
public class MyComboBox extends JComboBox<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int arcW;
	private int arcH;
	
	/**
	 * contructor donde hago los cambios correspondientes a mi JComboBoxs
	 */
	public MyComboBox() {
		super();
		setBackground(Color.WHITE);
		setFont(ConstantsUI.RUBIK_PLAIN_16);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setForeground(Color.BLACK);
		
	}

	
	/**
	 * Contructir donde le puedo agregar un evento a mi combo box
	 * @param actionCommand nombre del evento que quiero asiganarle a mi JComboBox
	 * @param actionListener listener que se empleara para los eventos de mi combo box
	 */
	public MyComboBox(String actionCommand, ActionListener actionListener) {
		super();
		this.arcW = arcW;
		this.arcH = arcH;
		setBackground(Color.WHITE);
		setFont(ConstantsUI.RUBIK_BOLD_16);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setForeground(ConstantsUI.COLOR_DARCK_BLUE);
		setActionCommand( actionCommand );
		addActionListener( actionListener );
		setBorder(BorderFactory.createTitledBorder(ConstantsUI.T_BOX_GENDER));
		
		
		setUI(new BasicComboBoxUI() {
			      
			      @Override
			      public void paintCurrentValueBackground(Graphics g,
			                                 Rectangle bounds,
			                                 boolean hasFocus)
			      {
			          g.setColor(Color.WHITE);            
			          g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
			      }
			        
			      //Pinta los items
			      @Override
			      protected ListCellRenderer createRenderer()
			      {
			          return new DefaultListCellRenderer() {      
			              
			          @Override
			          public Component getListCellRendererComponent(JList list,Object value,int index,
			            boolean isSelected,boolean cellHasFocus) {
			        
			          super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
			          list.setSelectionBackground(ConstantsUI.COLOR_LIGTH_BLUE);
			          if (isSelected)
			          {
			              setBackground(ConstantsUI.COLOR_LIGTH_BLUE);
			              setForeground(Color.WHITE);
			          }
			          else
			          {
			              setBackground( Color.WHITE );            
			              setForeground( new Color(70,70,70));
			          }
			          return this;
			        }
			      };
			      }
		});
		
	}
	
	
	/**
	 * contructor donde puedo agregarle a mi combo box unos estilos personalizados
	 * @param n
	 */
	public MyComboBox(String n) {
		super();
		this.arcW = arcW;
		this.arcH = arcH;
		setBackground(Color.WHITE);
		setFont(ConstantsUI.RUBIK_BOLD_16);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setForeground(ConstantsUI.COLOR_DARCK_BLUE);
		setActionCommand( actionCommand );
		setBorder(BorderFactory.createTitledBorder(ConstantsUI.T_BOX_GENDER));
		
		
		setUI(new BasicComboBoxUI() {
			      
			      @Override
			      public void paintCurrentValueBackground(Graphics g,
			                                 Rectangle bounds,
			                                 boolean hasFocus)
			      {
			          g.setColor(Color.WHITE);            
			          g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
			      }
			        
			      //Pinta los items
			      @Override
			      protected ListCellRenderer createRenderer()
			      {
			          return new DefaultListCellRenderer() {      
			              
			          @Override
			          public Component getListCellRendererComponent(JList list,Object value,int index,
			            boolean isSelected,boolean cellHasFocus) {
			        
			          super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
			          list.setSelectionBackground(ConstantsUI.COLOR_LIGTH_BLUE);
			          if (isSelected)
			          {
			              setBackground(ConstantsUI.COLOR_LIGTH_BLUE);
			              setForeground(Color.WHITE);
			          }
			          else
			          {
			              setBackground( Color.WHITE );            
			              setForeground( new Color(70,70,70));
			          }
			          return this;
			        }
			      };
			      }
		});
		
	}
	
}
