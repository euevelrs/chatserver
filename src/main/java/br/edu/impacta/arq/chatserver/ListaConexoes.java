package br.edu.impacta.arq.chatserver;
import java.util.concurrent.*;

public class ListaConexoes {
    private CopyOnWriteArrayList<ChatServerConexao> conexoes;
    public ListaConexoes() {
        conexoes = new CopyOnWriteArrayList<>();
    }
    public CopyOnWriteArrayList<ChatServerConexao> getConexoes() {
        return conexoes;
    }
    public void adicionar(ChatServerConexao conexao) {
        conexoes.add(conexao);
    }
    public void remover(ChatServerConexao conexao) {
        conexoes.remove(conexao);
    }
}

