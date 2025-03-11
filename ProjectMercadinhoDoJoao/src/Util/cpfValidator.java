package Util;


public class cpfValidator {
	public static boolean validarCPF(String cpf) {
		
		if (cpf == null || !cpf.matches("\\d{11}")) {
		return false;
		}

		
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
		cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
		cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
		cpf.equals("99999999999")) {
		return false;
		}

	
		int soma = 0;
		for (int i = 0; i < 9; i++) {
		soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}
		int digito1 = 11 - (soma % 11);
		if (digito1 == 10 || digito1 == 11) digito1 = 0;

	
		soma = 0;
		for (int i = 0; i < 10; i++) {
		soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}
		int digito2 = 11 - (soma % 11);
		if (digito2 == 10 || digito2 == 11) digito2 = 0;

		return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
		digito2 == Character.getNumericValue(cpf.charAt(10));
		}
}
