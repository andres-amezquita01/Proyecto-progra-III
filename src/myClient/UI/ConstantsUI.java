
package myClient.UI;

import java.awt.Color;
import java.awt.Font;
/**
 * constantes que manejo como buenas practicas, para los eventos y demas lugares donde necesite estos textos
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */
public class ConstantsUI {
	
	public  static  final String T_MAIN_WINDOW = "Person";
	//------------COLORS---------
	public static final Color COLOR_LIGTH_BLUE = new Color(101, 153, 255);
	public static final Color COLOR_DARCK_BLUE = new Color(60, 128, 215);
	public static Color[] colors = {COLOR_LIGTH_BLUE,Color.BLUE, Color.RED, Color.green, Color.black, Color.gray,Color.black,Color.BLUE, Color.orange
			,COLOR_LIGTH_BLUE,Color.BLUE, Color.RED, Color.orange, Color.gray, Color.gray,Color.black,Color.BLUE, Color.green};
	public static final Font FONT_MAIN_WINDOW_LABELS = new Font("Gadugi", Font.PLAIN, 18);
	public static final Font FONT_MAIN_TITLE_LABELS = new Font("PT Sans", Font.BOLD , 35);
	public static final Color COLOR_LIGTH_RED = Color.decode("#ec333b");


	//-----------------------------------------FONTS
	public static final Font RUBIK_BOLD_16 =  new Font("Rubik", Font.BOLD, 16);
	public static final Font RUBIK_PLAIN_12 =  new Font("Rubik", Font.PLAIN, 12);
	public static final Font RUBIK_PLAIN_16 =  new Font("Rubik", Font.PLAIN, 16);
	public static final Font FONT_MENU = new Font("Franklin Gothic Demi Cond", Font.BOLD, 60);

	//-----------------------------------------COLORS
	public static final Color DARK_PURPLE = new Color(31, 17, 66);
	public static final Color DARK_GOLD = new Color(66, 50, 17);
	public static final Color DARK_RED = new Color(66, 18, 17);
	public static final Color DARK_CYAN= new Color(17, 65, 66);
	public static final Color DARK_GREEN= new Color(36, 66, 17);
	//_________menu_______________
	public  static  final String MENU_REPORT = "Report";
	public  static  final String MENU_ITEM_REPORT_ONE = "btn_1";
	public  static  final String MENU_ITEM_REPORT_TWO = "btn_2";
	public  static  final String MENU_ITEM_REPORT_THREE = "btn_3";
	public  static  final String MENU_ITEM_REPORT_FOUR = "btn_4";
	public  static  final String MENU_ITEM_REPORT_FIVE = "btn_5";
	public  static  final String MENU_ITEM_REPORT_SIX = "btn_6";
	public  static  final String MENU_ITEM_REPORT_SEVEN = "btn_7";
	public  static  final String T_BOX_GENDER = "Gender";
	//create person
	public  static  final String BUTTON_CREATE = "Create";
	public  static  final String BUTTON_MENU_SHOW_PANEL_CREATE_PERSON= "Crear persona";
	public  static  final String BUTTON_OPTION_1 = "Opcion 1";
	public  static  final String BUTTON_OPTION_2 = "Opcion 2";
	public  static  final String BUTTON_CANCEL_CREATE = "Cancel created";
	public  static  final String ITEM_PERSON_ID = "Numero de identificacion";
	public  static  final String ITEM_PERSON_NAME = "Name";
	public  static  final String ITEM_PERSON_LAST_NAME = "Last name";
	public  static  final String ITEM_PERSON_VALUE = "Value";
	public  static  final String ITEM_PERSON_PROFILE = "Profile";
	public  static  final String ITEM_PERSON_PASSPORT = "Passport";
	public  static  final String PLACE_HOLDER_NUMBER_ID= 	"N° identificacion, solo numeros por favor.";

	//-----------LOGGIN----------
	public static final String LOG_IN = "	   INICIAR SESION ";
	public static final Color COLOR_WHITE = Color.decode("#FFFFFF");
	public static final String PASSWORD = "Contraseña";
	public static final String START = "Iniciar sesion";
	public static final String REGISTRY = "Registrarse";
	public static final String RECOVER_PASSWORD = "Recuperar contraseña";
	public static final String REGISTRY_USER = "Usuario";
	public static final String REGISTRY_PASSWORD = "Contraseña";


	//-------------EXCEPTION----------------------
	public static final String EXCEPTION_USER_NOT_REGISTRY= "El usuario es incorrecto.";
	public static final String EXCEPTION_USER_DUPLICATE= "El usuario ya existe.";
	public static final String BUTTON_ADD_RELATION_FAMILY = "añadir relacion";
	public  static  final String ITEM_BIRTH_DAY = "Fecha de nacimiento";
	
	//------------------SEARCH-----------------
	public  static  final String BUTTON_BEFORE = 	"<";
	public  static  final String BUTTON_AFTER = 	">";
	public  static  final String RELATION_FAMILY = 	"Relacion familiar";
	public  static  final String PARENTESCO = 	"Parentesco";
}


























