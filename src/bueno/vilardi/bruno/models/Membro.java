package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;
import bueno.vilardi.bruno.interfaces.Apresentacao;
import bueno.vilardi.bruno.interfaces.PostarMensagem;
import com.sun.jdi.IntegerType;
import com.sun.source.tree.CompilationUnitTree;

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

    // Atributos da classe Membro
    private static Integer contadorId = 1;

    /**
     * Contrutor da classe Membro
     * @param username nome do usuario
     * @param senha senha do usuario
     * @param role funcao do usuario
     */
    public Membro(String username, String senha, Funcao role) {
        this.id = Membro.contadorId;
        this.senha = senha;
        this.username = username;
        this.role = role;

        Membro.contadorId ++;
    }


    /**
     * Método para saber qual é o horário atual (REGULAR ou EXTRA)
      * @return Horário atual do sistema
     */
    public Horario getHorario(){
        return null; //TO_DO pegar horário do sistema
    }

    public String toCSV(){
        return Integer.toString(id) + ';' + senha + ';' + username + ';' + role;
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
