package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;

public class ScriptGuy extends Membro{

    private String assinaturaRegular = "Prazer em ajudar novos amigos de código";
    private String assinaturaExtra = "QU3Ro_S3us_r3curs0s.py";

    public ScriptGuy(String username, String senha, Funcao role) {
        super(username, senha, role);
    }

    @Override
    public void apresentar() {
        System.out.println(getApresentacao());
    }

    @Override
    public void postarMensagem() {

    }
}
