package Util;

public class CPFValidator {

    public static boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos (pontos e traços)
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF tem 11 dígitos
        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais (CPFs inválidos comuns)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcular o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : 11 - resto;

        // Calcular o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : 11 - resto;

        // Verificar se os dígitos calculados coincidem com os dígitos do CPF
        return (Character.getNumericValue(cpf.charAt(9)) == digito1) &&
               (Character.getNumericValue(cpf.charAt(10)) == digito2);
    }
}