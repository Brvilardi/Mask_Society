package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;
import bueno.vilardi.bruno.interfaces.Apresentacao;
import bueno.vilardi.bruno.interfaces.PostarMensagem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
Classe Membro, que representa o básico de qualquer membro
 */
public abstract class Membro implements Apresentacao, PostarMensagem {
    // Atributos gerais de qualquer Membro
    protected String senha;
    protected String username;
    protected Funcao role;
    protected String assinaturaRegular;
    protected String assinaturaExtra;

    public Membro(String username, String senha, Funcao role) {
        this.senha = senha;
        this.username = username;
        this.role = role;
    }

    /*
            Método para saber qual é o horário atual (REGULAR ou EXTRA)
            retorna o horário atual do sistema
    */
    public Horario getHorario(){
        return null; //TODO pegar horário do sistema
    }


}
