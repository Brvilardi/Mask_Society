package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;

public class HeavyLifter extends Membro {

    private String assinaturaRegular = "Podem contar conosco";
    private String assinaturaExtra = "N00b_qu3_n_Se_r3pita.bat";

    public HeavyLifter(String username, String senha, String email, Funcao role) {
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
