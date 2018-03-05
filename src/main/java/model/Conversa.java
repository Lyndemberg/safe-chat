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
    private Usuario remetente;
    @OneToOne
    private Usuario destino;
    @OneToMany
    @Basic(fetch = FetchType.LAZY)
    private List<Mensagem> mensagens;

    public Conversa() {
        mensagens = new ArrayList<>();
    }

    public Conversa(Usuario remetente, Usuario destino) {
        this.remetente = remetente;
        this.destino = destino;
        mensagens = new ArrayList<>();
    }

    public void addMensagem(Mensagem nova){
        mensagens.add(nova);
    }

    public Usuario getDestino() {
        return destino;
    }

    public void setDestino(Usuario destino) {
        this.destino = destino;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }
    
    
    
}
