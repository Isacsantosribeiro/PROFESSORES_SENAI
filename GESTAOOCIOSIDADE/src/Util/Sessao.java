package Util;

import Model.Agente;

public class Sessao {
    private static Agente agenteLogado;

    public static void setAgenteLogado(Agente agente) {
        agenteLogado = agente;
    }

    public static Agente getAgenteLogado() {
        return agenteLogado;
    }

    public static void limpar() {
        agenteLogado = null;
    }
}
///
//