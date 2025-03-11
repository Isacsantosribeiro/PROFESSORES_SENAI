package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class emailValidator {
	public static boolean validarEmail(String email) {
		   String validador = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		   Pattern pattern = Pattern.compile(validador);
		   Matcher matcher = pattern.matcher(email);
		   
		       return matcher.matches();
		}
}
