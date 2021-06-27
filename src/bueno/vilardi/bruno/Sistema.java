package bueno.vilardi.bruno;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.models.*;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Sistema {

    public Map<Integer, Membro> membros;
    private Integer contadorId = 1;
    public Integer sistemaId = 1; //Podem existir diversos sistemas, caso a organização necessite separar fisicamente sistemas
    private Scanner scanner;
    private FileWriter fw;
    private String NOME_ARQUIVO_CSV = "arquivo_super_Secreto_nao_abrir.csv";
    private Membro usuarioLogado;



    /**
     * Construtor da classe Sistema, inicializa os parametros mínimos para funcionamento
     */
    public Sistema() {
        // Inicializa o mapa de membros em memória
        this.membros = new HashMap<Integer, Membro>();

        // Inicializa o scanner (delimitado por return)
        scanner = new Scanner(System.in).useDelimiter("\n");

        // Inicializa o FileWriter
        File tempFile = new File("mensagens.txt");
        try {
            // Caso o arquivo não exista, cria um novo
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            // Salva o FileWriter como atributo do sistema
            fw = new FileWriter(tempFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }



        // Abre o arquivo CSV com os dados
        File tempFile2 = new File(NOME_ARQUIVO_CSV);
        boolean exists = tempFile2.exists();

        // Caso nao exista o arquivo, inicializar o Sistema com a criacao de um Big Brother
        if (!exists){
            System.out.println("Novo sistema, obrigado por escolher Bruno Vilardi como seu programador!");
            System.out.println("Como esse é um novo sistema, será necessário a criação do primeiro usuário, por favor crie um Big Brother para que você possa criar novos usuários depois\n");
            criarMembro();
        } else {
            CSV2Map(NOME_ARQUIVO_CSV, membros);
        }



    }

    /**
     * Contrutor da classe Sistema com alguns testes - TODO apagar isso
     * @param op
     */
    public Sistema(boolean op){
        this.membros = new HashMap<Integer, Membro>();
        criarMembro("bruno", "1234", Funcao.HEAVY_LIFTER);
        Membro usuario2 = new HeavyLifter("martin", "1234", Funcao.HEAVY_LIFTER);
        membros.put(usuario2.id, usuario2);

    }

    public void run(){
        boolean ligado = true;
        while(ligado){
            ligado = exibirMenu();
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean exibirMenu() {
        System.out.println("Menu Inicial:");
        System.out.println("Opções: \n1) Logar usuário \n2) Desligar Sitema");
        Integer op = scanner.nextInt();
        switch (op){
            case 1:
                System.out.println(membros);
                if(logarUsuario()){
                    return exibirMenuUsuario();
                } else{
                    System.out.println("Usuário ou senha incorretos");
                }
                break;
            case 2:
                return false;
            default:
                System.out.println("Opcão inválida!");
                
        }
        return true;
    }

    private boolean exibirMenuUsuario() {
        while (true) {
            System.out.println("Menu de usuário. Olá, " + usuarioLogado.getNome());
            System.out.println("Opções: \n1) Mandar uma mensagem \n2) Ver as mensagens postadas \n3) Deslogar usuario \n4) Desligar sistema");
            Integer op = scanner.nextInt();

            switch (op) {
                case 1:
                    System.out.println("Digite sua mensagem: ");
                    usuarioLogado.postarMensagemPadrao(scanner.next(), fw);
                    break;
                case 2:
                    exibirMensagens();
                    break;
                case 3:
                    usuarioLogado = null;
                    return true;
                case 4:
                    usuarioLogado = null;
                    return false;
                default:
                    System.out.println("Opção inválida!");

            }
        }

    }

    private void exibirMensagens() {
        try {
            fw.flush();
            System.out.println("Mensagens:");
            File fr = new File("mensagens.txt");
            Scanner myReader = new Scanner(fr);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
            System.out.println("\n");
        } catch (FileNotFoundException e) {
            System.out.println("\nAlgo de errado aconteceu!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean logarUsuario() {
        // Pede pelo nome de usuario
        System.out.println("Digite o nome de usuário:");
        String nome = scanner.next();
        // Pede a senha
        System.out.println("Digite a senha:");
        String senha = scanner.next();
        return logarUsuario(nome, senha);
    }
    
    private boolean logarUsuario(String nome, String senha){
        // Procura pelo usuario:
        for (Map.Entry<Integer, Membro> kvp : membros.entrySet()) {
            if (Membro.autenticar(kvp.getValue(),nome,senha)){
                usuarioLogado = kvp.getValue();
                return true;
            }
        }
        return false;
    }
    

    /**
     * Método que converte um Map em memória para um arquivo csv
     * @param nomeArquivo nome do arquivo csv destino
     * @param myMap mapa Map<Integer, Membro> origem
     */
    public String Map2CSV(String nomeArquivo, Map<Integer, Membro> myMap){
        try {
            // Inicializa o writer
            FileWriter writer = new FileWriter(nomeArquivo);
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
        File file = new File(nomeArquivo);

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
        // Adiciona o novo membro em memória e salva no arquivo CSV
        this.membros.put(membro.id, membro);
        Map2CSV(NOME_ARQUIVO_CSV, membros);



    }

    public static String getTimeStamp(){
        String timeStamp = new SimpleDateFormat("(dd/MM/yyyy) - HH:mm:ss").format(new Date());
        return timeStamp;
    }
}
