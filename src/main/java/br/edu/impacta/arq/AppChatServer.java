package br.edu.impacta.arq;

import java.net.*;
import java.io.*;
import br.edu.impacta.arq.chatserver.*;

public class AppChatServer {
    public static void main( String[] args ) throws IOException {
        ServerSocket servidor = new ServerSocket(9000);
        ListaNickname nicknames = new ListaNickname();
        ListaConexoes conexoes = new ListaConexoes();
        while (true) {
            Socket socket = servidor.accept();
            ChatServerConexao conexao = new ChatServerConexao(socket,nicknames,conexoes);
            conexoes.adicionar(conexao);
            Thread thread = new Thread(conexao);
            thread.start();
        }
    }
}
