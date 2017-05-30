package br.edu.impacta.arq.chatserver;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListaNickname {
    private CopyOnWriteArrayList<String> nicknames;
    
    public void adicionar(String nickname) {
        nicknames.add(nickname);
    }
    
    public void remover(String nickname) {
        nicknames.remove(nickname);
    }
    
    public boolean contem(String nickname) {
        return nicknames.contains(nickname);
    }
    
    public ListaNickname() {
        nicknames = new CopyOnWriteArrayList<>();
    }
}
