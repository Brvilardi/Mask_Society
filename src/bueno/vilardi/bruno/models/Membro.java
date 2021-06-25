package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;
import bueno.vilardi.bruno.interfaces.Apresentacao;
import bueno.vilardi.bruno.interfaces.PostarMensagem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Classe Membro, que representa o básico de qualquer membro
 */
public abstract class Membro implements Apresentacao, PostarMensagem {
    // Atributos gerais de qualquer Membro
    public Integer id;
    protected String senha;
    protected String username;
    protected Funcao role;
    protected String assinaturaRegular;
    protected String assinaturaExtra;

    /**
     * Contrutor da classe Membro
     * @param username nome do usuario
     * @param senha senha do usuario
     * @param role funcao do usuario
     */
    public Membro(String username, String senha, Funcao role) {
        this.senha = senha;
        this.username = username;
        this.role = role;
    }


    /**
     * Método para saber qual é o horário atual (REGULAR ou EXTRA)
      * @return Horário atual do sistema
     */
    public Horario getHorario(){
        return null; //TO_DO pegar horário do sistema
    }

    @Override
    public String toString() {
        return "Membro{" +
                "id=" + id +
                ", senha='" + senha + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
