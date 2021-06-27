package bueno.vilardi.bruno.interfaces;

import java.io.FileWriter;

public interface PostarMensagem {
    void postarMensagem(String mensagem, FileWriter fw);
}
