package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;

public class MobileMember extends Membro{

    private String assinaturaRegular = "Happy Coding!";
    private String assinaturaExtra = "Happy_C0d1ng. Maskers";

    public MobileMember(String username, String senha, Funcao role) {
        super(username, senha, role);
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
