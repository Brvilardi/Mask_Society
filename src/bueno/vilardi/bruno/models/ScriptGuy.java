package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;

public class ScriptGuy extends Membro{

    private String assinaturaRegular = "Prazer em ajudar novos amigos de código";
    private String assinaturaExtra = "QU3Ro_S3us_r3curs0s.py";

    public ScriptGuy(String username, String senha, String email, Funcao role) {
        super(username, senha, email, role);
    }

    @Override
    public String assinar(Horario horario) {
        switch (horario){
            case REGULAR:
                return assinaturaRegular;
            case EXTRA:
                return assinaturaExtra;
            default:
                return null;
        }
    }
}
