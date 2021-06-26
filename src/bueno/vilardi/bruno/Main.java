package bueno.vilardi.bruno;

import bueno.vilardi.bruno.enums.Funcao;
import bueno.vilardi.bruno.models.Membro;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println(Membro.apresentacoesRegulares.get(Funcao.HEAVY_LIFTER));
        Sistema sistema = new Sistema(true);
        //System.out.println(sistema.membros);
        System.out.println(sistema.Map2CSV("asd", sistema.membros));
        System.out.println(sistema.CSV2Map("asd", sistema.membros));
    }
}
