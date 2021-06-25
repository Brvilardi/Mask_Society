package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;

public abstract class Membro {
    protected String senha;
    protected String username;
    protected Funcao role;
    protected String assinaturaRegular;
    protected String assinaturaExtra;

    public Horario getHorario(){
        return null; //TODO pegar horário do sistema
    }


}
