package Util;

public class phoneValidator {
	public static boolean validarTelefone(String telefone) {
		// Verifica se o telefone tem exatamente 11 dígitos e se é composto apenas por números
		if (telefone == null || !telefone.matches("\\d{11}")) {
		return false;
		}

		// Verifica se o DDD é válido (entre 11 e 99)
		String ddd = telefone.substring(0, 2);
		if (Integer.parseInt(ddd) < 11 || Integer.parseInt(ddd) > 99) {
		return false;
		}

		// Verifica se o número começa com 9 (para celular)
		char primeiroDigito = telefone.charAt(2);
		if (primeiroDigito != '9') {
		return false;
		}

		// Se passou em todas as verificações, o telefone é válido
		return true;
		}
}
