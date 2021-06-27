package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;

public class MobileMember extends Membro{

    private String assinaturaRegular = "Happy Coding!";
    private String assinaturaExtra = "Happy_C0d1ng. Maskers";

    public MobileMember(String username, String senha, Funcao role) {
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
