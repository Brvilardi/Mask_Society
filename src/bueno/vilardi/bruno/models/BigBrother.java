package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;

public class BigBrother extends Membro{

    private String assinaturaRegular = "Sempre ajudando as pessoas membros ou não S2!";
    private String assinaturaExtra = "...";

    public BigBrother(String username, String senha, Funcao role) {
        super(username, senha, role);
    }

    @Override
    public void apresentar() {
        System.out.println(getApresentacaoPadrao());
    }

    @Override
    public void postarMensagem() {

    }
}
