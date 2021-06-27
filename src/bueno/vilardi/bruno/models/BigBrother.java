package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;

public class BigBrother extends Membro{

    private String assinaturaRegular = "Sempre ajudando as pessoas membros ou não S2!";
    private String assinaturaExtra = "...";

    /**
     * Contrutor da classe Big Brother
     * @param username nome do usuario (String)
     * @param senha senha do usuario (String)
     * @param email email do usuario (String)
     * @param role funcao do usuario (Enum Funcao)
     */
    public BigBrother(String username, String senha, String email, Funcao role) {
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
