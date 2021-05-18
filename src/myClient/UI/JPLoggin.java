package myClient.UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;

import com.placeholder.PlaceHolder;

import model.Password;
import myClient.Commands;
import myClient.items.RoundedJButton;

public class JPLoggin extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel labelLogin;
	private JTextField textUser;
	private JTextField textPass;
	private RoundedJButton buttonLongin;
	private RoundedJButton buttonCheckIn;
	private RoundedJButton buttonRecoveredPassWord;
	
	public JPLoggin(ActionListener controller) {
		initComponents(controller);
	}
	private void initComponents(ActionListener controller) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(ConstantsUI.COLOR_LIGTH_RED);
			
			Dimension minSize = new Dimension(0, 0);
			Dimension prefSize = new Dimension(0, 0);
			Dimension maxSize = new Dimension(150, 0);
			labelLogin = new JLabel(ConstantsUI.LOG_IN, SwingConstants.CENTER);
			labelLogin.setForeground(ConstantsUI.COLOR_WHITE);
			labelLogin.setFont(ConstantsUI.FONT_MENU);
			textUser = new JTextField(10);
//			textUser.setText(ConstantsUI.ITEM_PERSON_NAME);
			textPass = new JTextField(10);
//			textPass.setText(ConstantsUI.PASSWORD);
			PlaceHolder holder = new PlaceHolder(textUser, ConstantsUI.REGISTRY_USER);
			PlaceHolder holder_2 = new PlaceHolder(textPass, ConstantsUI.PASSWORD);

			buttonLongin = new RoundedJButton(15, 15, ConstantsUI.START,Color.black, Color.WHITE, 
					ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.C_LOGIN_BUTTON_ENTRY.toString(), controller ){
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
			buttonLongin.setBorderPainted(false);
			buttonCheckIn =  new RoundedJButton(15, 15, ConstantsUI.REGISTRY, ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
					ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.C_LOGIN_BUTTON_REGISTRY.toString(), controller ){
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
			buttonCheckIn.setBackground(ConstantsUI.COLOR_LIGTH_RED);
			buttonCheckIn.setForeground(ConstantsUI.COLOR_WHITE);
			buttonCheckIn.setContentAreaFilled(false);
			buttonCheckIn.setBorderPainted(false);

			
			buttonRecoveredPassWord = new RoundedJButton(15, 15, ConstantsUI.RECOVER_PASSWORD, ConstantsUI.COLOR_DARCK_BLUE, Color.WHITE, 
					ConstantsUI.FONT_MAIN_WINDOW_LABELS, Commands.C_LOGIN_BUTTON_RECOVER_PASSWORD.toString(), controller ){
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
			buttonRecoveredPassWord.setBackground(ConstantsUI.COLOR_LIGTH_RED);
			buttonRecoveredPassWord.setForeground(ConstantsUI.COLOR_WHITE);
			buttonRecoveredPassWord.setBorderPainted(false);
			//metodo para hacer trasnparentes los botones
			buttonRecoveredPassWord.setContentAreaFilled(false);
			Component component = Box.createRigidArea (new Dimension (150,150));
			component.setBackground(ConstantsUI.COLOR_LIGTH_RED);
			add (component);
			Box boxLabelLogin = Box.createHorizontalBox();
			boxLabelLogin.add(labelLogin);
			boxLabelLogin.add(new Box.Filler(minSize, prefSize, maxSize));
			add(boxLabelLogin);
			add (Box.createRigidArea (new Dimension (50,50)));
			add(textUser);
			add(Box.createVerticalGlue());
			add (Box.createRigidArea (new Dimension (20,20)));
			add(textPass);
			add (Box.createRigidArea (new Dimension (10,10)));
			Box boxLogin = Box.createHorizontalBox();
			Dimension minSize2 = new Dimension(0, 0);
			Dimension prefSize2 = new Dimension(0, 0);
			Dimension maxSize2 = new Dimension(0, 0);
			boxLogin.add(new Box.Filler(minSize2, prefSize2, maxSize2));
			boxLogin.add(buttonLongin);
			add(boxLogin);
			Box box =  Box.createHorizontalBox();
			box.add(buttonCheckIn);
			Dimension minSize1 = new Dimension(5, 100);
			Dimension prefSize1 = new Dimension(5, 100);
			Dimension maxSize1 = new Dimension(Short.MAX_VALUE, 100);
			box.add(new Box.Filler(minSize1, prefSize1, maxSize1));
			box.add(buttonRecoveredPassWord);
			add(box);

		}
	public Password createPassword() {
		return new Password(textUser.getText(), textPass.getText());
	}
}
