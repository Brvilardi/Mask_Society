package bueno.vilardi.bruno;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.models.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Sistema {

    public Map<Integer, Membro> membros;
    private Integer contadorId = 1;
    public Integer sistemaId = 1; //Podem existir diversos sistemas, caso a organização necessite separar fisicamente sistemas
    private Scanner scanner = new Scanner(System.in);

    /**
     * Construtor da classe Sistema, inicializa os parametros mínimos para funcionamento
     */
    public Sistema() {
        this.membros = new HashMap<Integer, Membro>();
        Membro usuario = criarMembro();
        membros.put(usuario.id, usuario);

        Membro usuario2 = criarMembro();
        membros.put(usuario2.id, usuario2);
        System.out.println("content: ");
        Map2CSV("123", membros);
    }

    public Sistema(boolean op){
        this.membros = new HashMap<Integer, Membro>();
        Membro usuario = new ScriptGuy("bruno", "1234", Funcao.SCRIPT_GUY);
        Membro usuario2 = new HeavyLifter("bruno", "1234", Funcao.HEAVY_LIFTER);
        membros.put(usuario.id, usuario);
        membros.put(usuario2.id, usuario2);

    }

    public void run(){
        while(true){
            exibirMenu();
        }
    }

    private void exibirMenu() {
        System.out.println("Menu Inicial:");
    }

    /**
     * Método que converte um Map em memória para um arquivo csv
     * @param nomeArquivo nome do arquivo csv destino
     * @param myMap mapa Map<Integer, Membro> origem
     */
    public String Map2CSV(String nomeArquivo, Map<Integer, Membro> myMap){
        StringBuilder builder = new StringBuilder();
        for (Integer key : myMap.keySet()) {
            builder.append(myMap.get(key));
            builder.append("\r\n");
        }

        String content = builder.toString().trim();
        return content;

    }

    private Funcao decodificarTipoMembro(String tipo){
        System.out.println("tipo: " + tipo);
        switch (tipo){
            case "1":
                return Funcao.MOBILE_MEMBER;
            case "2":
                return Funcao.HEAVY_LIFTER;
            case "3":
                return Funcao.SCRIPT_GUY;
            case "4":
                return Funcao.BIG_BROTHER;
        }

        return null;
    }


    private Membro criarMembro() {
        // Exibicao inicial
        System.out.println("Criação de membro...");
        // Nome do usuario
        System.out.println("Digite o nome de usuário: ");
        String nome = scanner.next();
        // Senha do usuario
        System.out.println("Digite a senha: ");
        String senha = scanner.next();
        // Tipo de usuario
        System.out.println("Digite o tipo de usuário: (1 - Mobile Members), (2 - Heavy Lifters), (3 - Script Guys), (4 - Big Brothers)");
        String tipo = scanner.next();
        Funcao funcao = decodificarTipoMembro(tipo);
        if(funcao == null){ return null; }
        // Criar o membro
        Membro membro;
        switch(funcao) {
            case MOBILE_MEMBER:
                membro = new MobileMember(nome, senha, funcao);
                break;
            case SCRIPT_GUY:
                membro = new ScriptGuy(nome, senha, funcao);
                break;
            case HEAVY_LIFTER:
                membro = new HeavyLifter(nome, senha, funcao);
                break;
            case BIG_BROTHER:
                membro = new BigBrother(nome, senha, funcao);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + funcao);
        }
        // Retorna o usuario recém criado
        return membro;



    }
}
