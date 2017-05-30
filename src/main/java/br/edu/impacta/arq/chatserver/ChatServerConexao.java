package br.edu.impacta.arq.chatserver;

import java.io.*;
import java.net.*;

public class ChatServerConexao implements Runnable {
    private BufferedReader entrada;
    private PrintWriter saida;
    private ListaNickname nicknames;
    private ListaConexoes conexoes;
    public ChatServerConexao(Socket socket, ListaNickname nicknames, ListaConexoes conexoes) 
                                    throws IOException {
        this.nicknames = nicknames;
        this.conexoes = conexoes;
        InputStream is = socket.getInputStream();
        entrada = new BufferedReader(new InputStreamReader(is));
        OutputStream os = socket.getOutputStream();
        saida = new PrintWriter(os);
    }
    public boolean nickDisponivel(String nickname) {
        if (nicknames.contem(nickname)) {
            saida.println("NOK");
            return false;
        }
        else {
            saida.println("OK");
            return true;
        }
    }
    public void publicar(String texto) {
        for (ChatServerConexao c: conexoes.getConexoes()) {
            c.saida.println(texto);
            c.saida.flush();
        }
    }
    public void run() {
        
    }
}

