package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;
import bueno.vilardi.bruno.interfaces.Apresentacao;
import bueno.vilardi.bruno.interfaces.PostarMensagem;

/*
Classe Membro, que representa o básico de qualquer membro
 */
public abstract class Membro implements Apresentacao, PostarMensagem {
    protected String senha;
    protected String username;
    protected Funcao role;
    protected String assinaturaRegular;
    protected String assinaturaExtra;

    /*
    Método para saber qual é o horário atual (REGULAR ou EXTRA)
    retorna o horário atual do sistema
     */
    public Horario getHorario(){
        return null; //TODO pegar horário do sistema
    }


}
