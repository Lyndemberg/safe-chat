package model;

import conversors.LocalDateTimeToStamp;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * @author lyndemberg
 */
@Entity
public class Mensagem implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Usuario remetente;
    @ManyToOne
    @JoinColumn(name="destino_id")
    private Usuario destino;
    @Convert(converter = LocalDateTimeToStamp.class)
    private LocalDateTime hora;
    @Lob
    private byte[] corpo;
    @Transient
    private String corpoPlano;
    
    public Mensagem() {
        
    }

    public Mensagem(Usuario remetente, Usuario destino, LocalDateTime hora, byte[] corpo) {
        this.remetente = remetente;
        this.destino = destino;
        this.hora = hora;
        this.corpo = corpo;
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

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public byte[] getCorpo() {
        return corpo;
    }

    public void setCorpo(byte[] corpo) {
        this.corpo = corpo;
    }

    public Usuario getDestino() {
        return destino;
    }

    public void setDestino(Usuario destino) {
        this.destino = destino;
    }

    public String getCorpoPlano() {
        return corpoPlano;
    }

    public void setCorpoPlano(String corpoPlano) {
        this.corpoPlano = corpoPlano;
    }
    
    
    
    
    
    
}
