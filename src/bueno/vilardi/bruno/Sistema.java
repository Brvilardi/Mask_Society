package bueno.vilardi.bruno;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.enums.Horario;
import bueno.vilardi.bruno.models.*;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class Sistema {

    public Map<Integer, Membro> membros;
    private Membro usuarioLogado;
    private Horario horarioSistema;
    private Integer contadorId = 1;
    public Integer sistemaId = 1; //Podem existir diversos sistemas, caso a organização necessite separar fisicamente sistemas
    private Scanner scanner;
    private FileWriter fw;
    private String NOME_ARQUIVO_CSV = "arquivo_super_Secreto_nao_abrir.csv";
    private String NOME_ARQUIVO_MENSAGENS = "mensagens.txt";


    /**
     * Construtor da classe Sistema, inicializa os parametros mínimos para funcionamento
     */
    public Sistema() {
        // Inicializa o mapa de membros em memória
        this.membros = new HashMap<Integer, Membro>();

        // Inicializa o scanner (delimitado por return)
        scanner = new Scanner(System.in).useDelimiter("\n");

        // Inicializa o horário
        horarioSistema = Horario.REGULAR;

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
            System.out.println("\n\n\nNovo sistema, obrigado por escolher Bruno Vilardi como seu programador!");
            System.out.println("Como esse é um novo sistema, será necessário a criação do primeiro usuário, por favor crie um Big Brother para que você possa criar novos usuários depois\n");
            criarMembro();
        } else {
            CSV2Map(NOME_ARQUIVO_CSV, membros);
        }



    }

    /**
     * Método de início do sistema, faz com que o sistema seja executado
     */
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

    /**
     * Método que exibe o menu inicial do sistema (sem usuário logado)
     * @return true (sistema deve continuar funcionando) ou false (sistema deve para a execução)
     */
    private boolean exibirMenu() {
        System.out.println("\nMenu Inicial:");
        System.out.println("Opções: \n1) Logar usuário \n2) Desligar Sitema");
        System.out.println("Sua opção: ");
        Integer op = scanner.nextInt();
        switch (op){
            case 1:
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

    /**
     * Método que exibe o menu específico para ações de usuário (Mandar mensagem, Ver as mensagens enviadas, Perguntar o horário de trabalho (Regular ou Extra), Deslogar e Desligar o sistema
     * Exibirá opções adicionais para Big Brothers (Criar novo membro, excluir um membro existente, trocar o horário de trabalho (de regular para extra ou de extra para regular) e gerar relatório
     * @return true (deslogar usuário e voltar para o menu inicial) ou false (deslogar usuário e desligar o sistema)
     */
    private boolean exibirMenuUsuario() {
        while (true) {
            // Exibição padrao de membro
            System.out.println("\nMenu de usuário. Olá, " + usuarioLogado.getNome());
            System.out.println("Opções: \n1) Mandar uma mensagem \n2) Ver as mensagens postadas \n3) Qual é o tipo de horário de trabalho? \n4) Deslogar usuario \n5) Desligar sistema");
            // Exibicao de Big Brother
            if (usuarioLogado.ehBigBrother()){
                System.out.println("\nExclusivos de Big Brother: \n6) Criar membro \n7) Excluir membro \n8) Trocar o horário \n9) Gerar relatório");
            }
            System.out.println("Sua opção: ");

            Integer op = scanner.nextInt();

            switch (op) { //return → sai do menu usuario (com true ele volta para o menu inicial e com false desliga sistema) e break continua no menu usario
                case 1:
                    System.out.println("Digite sua mensagem: ");
                    usuarioLogado.postarMensagem(scanner.next(), this);
                    break;
                case 2:
                    exibirMensagens();
                    break;

                case 3:
                    System.out.println("O horário atual é: " + this.horarioSistema);
                    break;
                case 4:
                    usuarioLogado = null;
                    return true;
                case 5:
                    usuarioLogado = null;
                    return false;
                case 6:
                    if (usuarioLogado.ehBigBrother()){
                        criarMembro();
                        break;
                    } else{
                        System.out.println("Opção inválida!");
                        break;
                    }
                case 7:
                    if (usuarioLogado.ehBigBrother()){
                        excluirMembro();
                        break;
                    } else{
                        System.out.println("Opção inválida!");
                        break;
                    }
                case 8:
                    if (usuarioLogado.ehBigBrother()){
                        System.out.print("Tem certeza que deseja trocar o horário do sistema de ");
                        switch (horarioSistema){
                            case EXTRA:
                                System.out.print("EXTRA para REGULAR?");
                                break;
                            case REGULAR:
                                System.out.println("REGULAR para EXTRA?");
                                break;
                        }
                        System.out.println("  (y/n)");
                        if (scanner.next().equals("y")){
                            switch (horarioSistema){
                                case EXTRA:
                                    horarioSistema = Horario.REGULAR;
                                    break;
                                case REGULAR:
                                    horarioSistema = Horario.EXTRA;
                                    break;
                            }

                        }
                        break;
                    } else{
                        System.out.println("Opção inválida!");
                        break;
                    }
                case 9:
                    if (usuarioLogado.ehBigBrother()){
                        exibirRelatorio();
                        break;
                    } else{
                        System.out.println("Opção inválida!");
                        break;
                    }


                default:
                    System.out.println("Opção inválida!");
                    break;

            }
        }

    }

    /**
     * Método que exibe um relatório contendo as informações de todos os membros cadastrados no sistema
     * Após a exibição, o método possibilita pedir para que algum membro se apresente
     * EXCLUSIVO PARA BIG BROTHER
     */
    private void exibirRelatorio() {
        System.out.println("=".repeat(50));
        System.out.println("Relatório de membros: \n");
        for (Integer key : membros.keySet()) {
            System.out.println(membros.get(key));
        }
        System.out.println("=".repeat(50) + "\n");
        System.out.println("Se deseja que alguém se apresente, digite o número do id desse membro. Caso contrário digite 0");

        Integer membroId = -1;
        while(true) {
            System.out.println("Id desejado (ou 0 para sair): ");
            membroId = scanner.nextInt();
            if (membroId == 0) {
                break;
            }
            if (membros.containsKey(membroId)) {
                System.out.println(membros.get(membroId).apresentar(horarioSistema));
            } else {
                System.out.println("Esse membro não existe!");
            }
        }
    }

    /**
     * Método que solicita um nome de membro para o usuário e exclui esse membro
     * EXCLUSIVO PARA BIG BROTHER
     */
    private void excluirMembro() {
        System.out.println("Digite o nome do membro que deseja excluir:");
        String nome = scanner.next();
        Membro membro = getMembro(nome);
        if ( membro != null){
            System.out.println("Tem certeza que deseja excluir o " + membro.role + ", com nome " + membro.getNome() + "(id= " + membro.id + ")? \n(y/n)");
            if (scanner.next().equals("y")){
                excluirMembro(membro.id);
            }
        } else {
            System.out.println("membro " + nome + " não encontrado!");
        }
    }

    /**
     * Método que exclui o membro cujo Id é especificado
     * @param id Número do Id do membro a ser excluido (valor inteiro)
     */
    private void excluirMembro(Integer id) {
        System.out.println("Id a ser removido: " + id);
        System.out.println("membro q vai vazar: " + membros.get(id));
        membros.remove(id);
        Map2CSV(NOME_ARQUIVO_CSV, membros);
    }

    /**
     * Método que busca um membro salvo em memória do sistema
     * @param nome nome do membro a ser buscado
     * @return Objeto membro cujo nome é igual ao especificado
     */
    private Membro getMembro(String nome) {
        for (Integer key : membros.keySet()) {
            if (membros.get(key).getNome().equals(nome)){
                return membros.get(key);
            }
        }
        return null;
    }

    /**
     * Método que exibe todas as mensagens enviadas e armazenadas no sitema
     * O arquivo em que as mensagens são armazenadas/lidas é a variável de sistema NOME_ARQUIVO_MENSAGENS
     */
    private void exibirMensagens() {
        try {
            fw.flush();
            System.out.println("=".repeat(40));
            System.out.println("Mensagens:");
            File fr = new File(NOME_ARQUIVO_MENSAGENS);
            Scanner myReader = new Scanner(fr);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
            System.out.println("=".repeat(40));
        } catch (FileNotFoundException e) {
            System.out.println("\nAlgo de errado aconteceu!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que solicita um nome de usuário e senha e verifica se essas credenciais estão cadastradas no sistema
     * @return true (usuário e senha batem) ou false (usuario ou senha não batem)
     */
    private boolean logarUsuario() {
        // Pede pelo nome de usuario
        System.out.println("Digite o nome de usuário:");
        String nome = scanner.next();
        // Pede a senha
        System.out.println("Digite a senha:");
        String senha = scanner.next();
        return logarUsuario(nome, senha);
    }

    /**
     * Método que recebe um nome de usuário e uma senha e verifica se essas credenciais estão cadastradas no sistema
     * @param nome nome de usuário (String)
     * @param senha senha do usuário (String)
     * @return true (usuário e senha batem) ou false (usuario ou senha não batem)
     */
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
     * Método que converte um Map em memória em CSV e salva em um arquivo csv especificado
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

    /**
     * Método Método que converte um arquivo csv em HashMap de membros em memória
     * @param nomeArquivo nome do arquivo csv
     * @param myMap endereço do mapa a ser salvo
     */
    public void CSV2Map(String nomeArquivo, Map<Integer, Membro> myMap){
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
                criarMembro(dados[1], dados[2], Funcao.valueOf(dados[3]), dados[4]);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Método que recebe um valor numérico e converte em Funcao, seguindo a regra:
     *  1 = Mobile_Member
     *  2 = HEAVY_LIFTER
     *  3 = SCRIPT_GUY
     *  4 = BIG_BROTHER
     * @param tipo tipo de membro (valor inteiro)
     * @return o tipo de membro com o Enum Funcao
     */
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

    /**
     * Método que envia uma String como mensagem (envia via usuário logado)
     * A mensagem é salva no arquivo específicado no atributo fw (FileWritter)
     * @param string
     */
    public void enviarMensagem(String string) {
        try {
            this.fw.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que faz a solicitação de informações necessárias para criação de um membro
     * @return array de Strings com as informações: nomeDeUsuário, senha, tipo e email (respectivamente)
     */
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
        // Email do usuario
        System.out.println("Digite o email de usuário: ");
        String email = scanner.next();
        output.add(email);

        return output;

    }

    /**
     * Método que solicita ao usuário as informações necessárias e cria o membro
     */
    private void criarMembro(){
        List<String> dados = pedirMembro();
        criarMembro(dados.get(0), dados.get(1), decodificarTipoMembro(dados.get(2)), dados.get(3));
    }

    /**
     * Método que cria um membro a partir dos dados informados e salva em memória e no arquivo CSV
     * É utilizado o construtor específico de cada tipo de Membro (MobileMember, HeavyLifter, ScriptGuy e Big Brother)
     * @param nome nome de usuário (String)
     * @param senha senha do usuário (String)
     * @param funcao tipo de membro (Enum Funcao)
     * @param email email do usuário (String)
     */
    private void criarMembro(String nome, String senha, Funcao funcao, String email) {
        // Criar o membro
        Membro membro;
        switch(funcao) {
            case MOBILE_MEMBER:
                membro = new MobileMember(nome, senha, email, funcao);
                break;
            case SCRIPT_GUY:
                membro = new ScriptGuy(nome, senha, email, funcao);
                break;
            case HEAVY_LIFTER:
                membro = new HeavyLifter(nome, senha, email, funcao);
                break;
            case BIG_BROTHER:
                membro = new BigBrother(nome, senha, email, funcao);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + funcao);
        }
        // Adiciona o novo membro em memória e salva no arquivo CSV
        this.membros.put(membro.id, membro);
        Map2CSV(NOME_ARQUIVO_CSV, membros);
    }

    /**
     * Método estático que cria uma marca do tempo atual
     * @return horário atual no formato de "(dia/mês/ano) - hora:minuto:segundo" (String)
     */
    public static String getTimeStamp(){
        String timeStamp = new SimpleDateFormat("(dd/MM/yyyy) - HH:mm:ss").format(new Date());
        return timeStamp;
    }

    /**
     *  Método que retorna o horário atual do sistema
     * @return
     */
    public Horario getHorario() {
        return horarioSistema;
    }
}
