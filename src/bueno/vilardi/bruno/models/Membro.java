package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.Sistema;
import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;
import bueno.vilardi.bruno.interfaces.Apresentacao;
import bueno.vilardi.bruno.interfaces.PostarMensagem;

import java.io.FileWriter;
import java.io.IOException;


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


    public String toCSV(){
        return Integer.toString(id) + ';' + username + ';' + senha + ';' + role;
    }

    public static boolean autenticar(Membro membro, String possivelNome, String possivelSenha){
        if (membro.username.equals(possivelNome) && membro.senha.equals(possivelSenha)){
            return true;
        }
        return false;
    }

    public void postarMensagemPadrao(String mensagem, FileWriter fw){ //TODO colocar no sistema
        // Pega o horário atual
        String horario = Sistema.getTimeStamp();

        // Cria a mensagem
        String output = "";
        output = "\n@" + getNome() + ":\n" + mensagem + "\n" + getAssinatura() + "\n" + horario;
        output += "\n" + "-".repeat(40);

        // Escreve a mensagem no arquivo
        try {
            fw.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getAssinatura(){
        switch (getHorario()){
            case REGULAR:
                return assinaturaRegular;
            case EXTRA:
                return assinaturaExtra;
        }
        return assinaturaRegular; //TODO consertar o switch
    }

    public String getNome() {
        return this.username;
    }

    /**
     * Método para saber qual é o horário atual (REGULAR ou EXTRA)
     * @return Horário atual do sistema
     */
    public Horario getHorario(){
        return Horario.REGULAR; //TO_DO pegar horário do sistema
    }

    public String getApresentacaoPadrao(){
        return "Olá! Meu nome é " + username + " e eu sou um " + role.toString();
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
