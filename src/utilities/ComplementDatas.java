package utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComboBox;

/**
 * Clase con metodos auxiliares.
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */
public class ComplementDatas {
	public ComplementDatas() {
		
	}
	
	public void fillComboBox(Map<Long, String> map,JComboBox<String> comboBox) {
		for (Entry<Long, String> entry : map.entrySet()) {
			comboBox.addItem(entry.getValue() + "-> CON ID: -> " + entry.getKey());;
		}
	}
	
	/**
	 * Valida que un string contenga la longitud deseada, si es mayor
	 * se recorta hasta la longitud, si es menor se rellena con el caracter "_".
	 * @param word String a validar que cumpla con la longitud.
	 * @param length longitud que debe tener el string.
	 */
	public String stringSize(String word, int length) {
		if (word.length() < length) {
			String complement ="";
			for (int i = word.length(); i < length; i++) {
				complement += "_";
			}
			word = word + complement;
		}else if(word.length() > length) {
			word = word.substring(0, length);
		}
		return word;
	}
	
	public  LocalDate parseStringToLocalDate(Calendar calendar) { 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String date = simpleDateFormat.format(calendar.getTime());
		LocalDate localDate1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return localDate1;
	}
	private  String toStringCalendar(Calendar calendar){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		return simpleDateFormat.format(calendar.getTime());
		
	}
}	















