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
        String nickname = null;
        try {
            boolean nickValido = false;
            while (!nickValido) {
                nickname = entrada.readLine();
                boolean nicknameEmUso = nicknames.contem(nickname);
                if (nicknameEmUso) {
                    saida.println("NOK");
                    saida.flush();
                }
                else {
                    nicknames.adicionar(nickname);
                    saida.println("OK");
                    saida.flush();
                    nickValido = true;
                }
            }
            String linha;
            while (true) {
                linha = entrada.readLine();
                this.publicar(nickname + " diz: " + linha);
            }
        } catch(IOException e) {
            conexoes.remover(this);
            if (nickname != null) {
                nicknames.remover(nickname);
            }
        }

    }
}

