package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;

public class HeavyLifter extends Membro {

    private String assinaturaRegular = "Podem contar conosco";
    private String assinaturaExtra = "N00b_qu3_n_Se_r3pita.bat";

    /**
     * Construtor da classe HeavyLifter
     * @param username nome do usuario (String)
     * @param senha senha do usuario (String)
     * @param email email do usuario (String)
     * @param role funcao do usuario (Enum Funcao)
     */
    public HeavyLifter(String username, String senha, String email, Funcao role) {
        super(username, senha, email, role);
    }

    /**
     * Implementação do método abstrato assinar
     * @param horario horário atual do sistema
     * @return assinatura padráo do membro
     */
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
