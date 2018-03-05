package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author lyndemberg
 */
@Entity
public class Conversa implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Usuario user1;
    @OneToOne
    private Usuario user2;
    @OneToMany
    @Basic(fetch = FetchType.LAZY)
    private List<Mensagem> mensagens;

    public Conversa() {
        mensagens = new ArrayList<>();
    }

    public Conversa(Usuario user1, Usuario user2) {
        this.user1 = user1;
        this.user2 = user2;
        mensagens = new ArrayList<>();
    }

    public void addMensagem(Mensagem nova){
        mensagens.add(nova);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUser1() {
        return user1;
    }

    public void setUser1(Usuario user1) {
        this.user1 = user1;
    }

    public Usuario getUser2() {
        return user2;
    }

    public void setUser2(Usuario user2) {
        this.user2 = user2;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    
    
    
}
