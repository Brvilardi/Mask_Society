package bueno.vilardi.bruno;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.models.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Sistema {

    private Map<Integer, Membro> membros;
    public Integer sistemaId = 1; //Podem existir diversos sistemas, caso a organização necessite separar fisicamente sistemas
    private Scanner scanner = new Scanner(System.in);

    public Sistema() {
        this.membros = new HashMap<Integer, Membro>();
        Membro usuario = criarMembro();
        System.out.println(usuario);
        System.out.println(usuario.getClass());
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
