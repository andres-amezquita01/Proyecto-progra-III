package myClient.items;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * clase que me permite manipular las propiedades de un Button mas especificamente 
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */
public class RoundedJButton extends JButton{
	/**
	 * 
	 */
	private int arcW;
	private int arcH;
	private static final long serialVersionUID = 1L;
	
	/**
	 * contructor donde recibo los parametros que quiero manipular de mi boton
	 * @param arcW
	 * @param arcH
	 * @param text texto de mi boton
	 * @param codeColorBackground color de mi boton
	 * @param codeColorForeground fuente de mi botton
	 */
	public RoundedJButton( int arcW, int arcH, String text, String codeColorBackground, String codeColorForeground ) {
		super( text );
		this.arcW = arcW;
		this.arcH = arcH;
		this.setBackground( Color.decode( codeColorBackground ) );
		//			super(size);
		//			setOpaque(false); 
	}

	
	/**
	 * metodo que me permite agregarle una imagen que va a estar asociada a mi Botton
	 * @param arcW
	 * @param arcH
	 * @param text
	 * @param codeColorBackground
	 * @param codeColorForeground
	 * @param pathImageIcon
	 */
	public RoundedJButton( int arcW, int arcH, String text, String codeColorBackground, String codeColorForeground, String pathImageIcon) {
		super( text );
		this.arcW = arcW;
		this.arcH = arcH;
		this.setBackground( Color.decode( codeColorBackground ) );
		setContentAreaFilled( false );
		setForeground( Color.decode( codeColorForeground));
		this.setIcon( new ImageIcon(getClass().getResource( pathImageIcon )) );
	}
	
	
	/**
	 * metodo que me permite agregarle un comando a mi boton el cual tendra asosiado un determinado evento
	 * @param arcW
	 * @param arcH
	 * @param text
	 * @param codeColorBackground
	 * @param codeColorForeground
	 * @param pathImageIcon
	 */
	
	public RoundedJButton( int arcW, int arcH, String text, Color codeColorBackground, Color codeColorForeground, String command, ActionListener listener) {
		super( text );
		this.arcW = arcW;
		this.arcH = arcH;
		this.setBackground( codeColorBackground );
		this.setContentAreaFilled( false );
		this.setForeground(  codeColorForeground );
		this.setActionCommand( command );
		this.addActionListener( listener );
	}
	
	/**
	 * metodo que me permite agregarle un evento y una fuente a mi boton una fuente es para los estilos
	 * @param arcW
	 * @param arcH
	 * @param text
	 * @param codeColorBackground
	 * @param codeColorForeground
	 * @param pathImageIcon
	 */
	
	public RoundedJButton( int arcW, int arcH, String text, Color codeColorBackground, Color codeColorForeground, Font font, String command, ActionListener listener) {
		super( text );
		this.arcW = arcW;
		this.arcH = arcH;
		this.setBackground( codeColorBackground );
		this.setContentAreaFilled( false );
		this.setForeground(  codeColorForeground );
		this.setFont( font );
		this.setActionCommand( command );
		this.addActionListener( listener );
	}



	/**
	 * metodo que modifica el metodo paintCompoment y qye me permite editar valores de mi boton como 
	 * por ejemplo su dimension
	 */
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; 
		g2.setColor(getBackground());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);           
		g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, arcW, arcH);
		super.paintComponent(g);
	}

	
	/**
	 * metodo que sobreescribe el paintBorder y el cual me permite sobreescribir el metodo de pintar un borde
	 */
	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcW, arcH);
	}

}
