package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;

public class ScriptGuy extends Membro{

    private String assinaturaRegular = "Prazer em ajudar novos amigos de código";
    private String assinaturaExtra = "QU3Ro_S3us_r3curs0s.py";

    /**
     * Construtor da classe ScriptGuy
     * @param username nome do usuario (String)
     * @param senha senha do usuario (String)
     * @param email email do usuario (String)
     * @param role funcao do usuario (Enum Funcao)
     */
    public ScriptGuy(String username, String senha, String email, Funcao role) {
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
