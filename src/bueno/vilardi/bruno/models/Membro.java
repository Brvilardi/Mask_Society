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
     * @param username nome do usuario (String)
     * @param senha senha do usuario (String)
     * @param email email do usuario (String)
     * @param role funcao do usuario (Enum Funcao)
     */
    public Membro(String username, String senha, String email, Funcao role) {
        this.id = Membro.contadorId;
        this.senha = senha;
        this.username = username;
        this.email = email;
        this.role = role;

        Membro.contadorId ++;
    }

    /**
     * Método que retorna os principais dados do usuário em forma de csv
     * @return
     */
    public String toCSV(){
        return Integer.toString(id) + ';' + username + ';' + senha + ';' + role + ';' + email;
    }

    /**
     * Método estático que verifica se um membro possui uma certa senha e nome de usuário
     * @param membro objeto do tipo Membro (ou filho)
     * @param possivelNome nome de usuário (String)
     * @param possivelSenha senha de usuário (String)
     * @return
     */
    public static boolean autenticar(Membro membro, String possivelNome, String possivelSenha){
        if (membro.username.equals(possivelNome) && membro.senha.equals(possivelSenha)){
            return true;
        }
        return false;
    }


    // Métodos das interfaces

    /**
     * Método abstrato que retorna a assinatura do membro (varia de acordo com o membro e horário atual do sistema)
     * @param horario horário atual do sistema
     * @return assinatura do membro (String)
     */
    public abstract String assinar(Horario horario);

    /**
     * Método de apresentação de um membro
     * @param horario horário atuao do sistema
     * @return apresentação do membro (String)
     */
    public String apresentar(Horario horario){
        return "Olá! Meu nome é " + getNome() + ", sou um " + role + "!\n" + assinar(horario) + "\n";
    }

    /**
     * Método que cria a solitação de postagem de uma mensagem (a postagem em si ocorre dentro do sistema)
     * @param mensagem corpo da mensagem que será postada (String)
     * @param sistema sistema que está sendo utilizado
     */
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

    /**
     * Método que verifica se um membro é um Big Brother
     * @return true (se o membro for Big Brother) ou false (se o membro não for Big Brother)
     */
    public boolean ehBigBrother(){
        return role.equals(Funcao.BIG_BROTHER);
    }

    /**
     * Método que retorna o nome do membro
     * @return nome do membro (String)
     */
    public String getNome() {
        return this.username;
    }

    /**
     * Método de toString do Membro
     * @return Alguns atributos imporantes do membro (String)
     */
    @Override
    public String toString() {
        return "role{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email +
                '}';
    }

}
