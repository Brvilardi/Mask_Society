package bueno.vilardi.bruno.models;

import bueno.vilardi.bruno.Sistema;
import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;
import bueno.vilardi.bruno.interfaces.Apresentacao;
import bueno.vilardi.bruno.interfaces.Assinatura;
import bueno.vilardi.bruno.interfaces.PostarMensagem;

import java.io.FileWriter;
import java.io.IOException;


/**
 * Classe Membro, que representa o básico de qualquer membro
 */
public abstract class Membro implements Apresentacao, PostarMensagem, Assinatura {
    // Atributos gerais de qualquer Membro
    public Integer id;
    protected String senha;
    protected String username;
    protected String email;
    public Funcao role;
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
    public Membro(String username, String senha, String email, Funcao role) {
        this.id = Membro.contadorId;
        this.senha = senha;
        this.username = username;
        this.email = email;
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


    // Métodos das interfaces
    public abstract String assinar(Horario horario);

    public String apresentar(Horario horario){
        return "Olá! Meu nome é " + getNome() + ", sou um " + role + "!\n" + assinar(horario) + "\n";
    }

    public void postarMensagem(String mensagem, Sistema sistema){
        // Pega o horário atual
        String horario = Sistema.getTimeStamp();

        // Cria a mensagem
        String output = "";
        output = "\n@" + getNome() + ":\n" + mensagem + "\n" + apresentar(sistema.getHorario()) + "\n" + horario;
        output += "\n" + "-".repeat(40);

        // Escreve a mensagem no arquivo
        sistema.enviarMensagem(output);
    }

    public boolean ehBigBrother(){
        return role.equals(Funcao.BIG_BROTHER);
    }


    public String getNome() {
        return this.username;
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
