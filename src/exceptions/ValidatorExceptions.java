package exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorExceptions {
	public static boolean validateNumberCode(String code) throws OnlyNumbersException{
		boolean validate = false;
		Pattern pat = Pattern.compile("\\d*");
	    Matcher mat = pat.matcher(code);                                                                           
	     if (mat.matches() == true && code != null) {
	    	 System.out.println("validacion es  " + validate + code);
	         validate = true;
	         return validate;
	     } else {
	    	 System.out.println("validacion es  " + validate + code);

	        throw new OnlyNumbersException();                                                                             
	     }
	}
	
}
