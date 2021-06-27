package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;

public class BigBrother extends Membro{

    private String assinaturaRegular = "Sempre ajudando as pessoas membros ou não S2!";
    private String assinaturaExtra = "...";

    public BigBrother(String username, String senha, Funcao role) {
        super(username, senha, role);
    }


    @Override
    public String apresentar(Horario horario) {
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
