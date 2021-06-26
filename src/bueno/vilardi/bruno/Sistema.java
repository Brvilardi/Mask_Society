package bueno.vilardi.bruno;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.models.*;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Sistema {

    public Map<Integer, Membro> membros;
    private Integer contadorId = 1;
    public Integer sistemaId = 1; //Podem existir diversos sistemas, caso a organização necessite separar fisicamente sistemas
    private Scanner scanner = new Scanner(System.in);

    /**
     * Construtor da classe Sistema, inicializa os parametros mínimos para funcionamento
     */
    public Sistema() {
        // Inicializa o mapa de membros em memória
        this.membros = new HashMap<Integer, Membro>();

        //Abre o arquivo CSV com os dados
        File tempFile = new File("arquivo_super_Secreto_nao_abrir.csv");
        boolean exists = tempFile.exists();
        System.out.println(exists + "");


        criarMembro();

    }

    public Sistema(boolean op){
        this.membros = new HashMap<Integer, Membro>();
        criarMembro("bruno", "1234", Funcao.HEAVY_LIFTER);
        Membro usuario2 = new HeavyLifter("martin", "1234", Funcao.HEAVY_LIFTER);
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
        try {
            // Inicializa o writer
            FileWriter writer = new FileWriter((nomeArquivo+".csv"));
            // Cria uma String com todos os dados
            StringBuilder builder = new StringBuilder();
            for (Integer key : myMap.keySet()) {
                builder.append(myMap.get(key).toCSV());
                builder.append("\r\n");
            }
            String content = builder.toString().trim();
            // Escreve a string no arquivo
            writer.write(content);
            writer.close();


            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String CSV2Map(String nomeArquivo, Map<Integer, Membro> myMap){
        File file = new File("asd.csv");

        //Realiza a leitura
        try {
            // Cria elemento para iterar pelo arquivo
            Scanner scanner = new Scanner(file);

            // Itera pelos elementos do scanner
            while(scanner.hasNext()){
                // CSV → id;nome;senha;role
                String linha = scanner.nextLine();
                String[] dados = linha.split(";");
                criarMembro(dados[1], dados[2], Funcao.valueOf(dados[3]));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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

    private List<String> pedirMembro(){
        // Criaçao da variável output
        List<String> output = new ArrayList<>();

        // Exibicao inicial
        System.out.println("Criação de membro...");
        // Nome do usuario
        System.out.println("Digite o nome de usuário: ");
        String nome = scanner.next();
        output.add(nome);
        // Senha do usuario
        System.out.println("Digite a senha: ");
        String senha = scanner.next();
        output.add(senha);
        // Tipo de usuario
        System.out.println("Digite o tipo de usuário: (1 - Mobile Members), (2 - Heavy Lifters), (3 - Script Guys), (4 - Big Brothers)");
        String tipo = scanner.next();
        output.add(tipo);

        return output;

    }


    private void criarMembro(){
        List<String> dados = pedirMembro();
        criarMembro(dados.get(0), dados.get(1), decodificarTipoMembro(dados.get(2)));
    }

    private void criarMembro(String nome, String senha, Funcao funcao) {

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
        this.membros.put(membro.id, membro);



    }
}
