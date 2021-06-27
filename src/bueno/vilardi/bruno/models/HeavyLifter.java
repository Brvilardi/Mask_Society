package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;

public class HeavyLifter extends Membro {

    private String assinaturaRegular = "Podem contar conosco";
    private String assinaturaExtra = "N00b_qu3_n_Se_r3pita.bat";

    public HeavyLifter(String username, String senha, Funcao role) {
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
