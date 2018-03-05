package model;

import conversors.LocalDateTimeToStamp;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Basic;
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
    @Convert(converter = LocalDateTimeToStamp.class)
    private LocalDateTime hora;
    @Lob
    private byte[] corpoRemetente;
    @Lob
    private byte[] corpoDestino;
    @Transient
    private String corpoPlano;
    @OneToOne
    private Usuario remetente;
    @OneToOne
    private Usuario destino;
    public Mensagem() {
        
    }

    public Mensagem(LocalDateTime hora, byte[] corpoRemetente, byte[] corpoDestino, Usuario remetente, Usuario destino) {
        this.hora = hora;
        this.corpoRemetente = corpoRemetente;
        this.corpoDestino = corpoDestino;
        this.remetente = remetente;
        this.destino = destino;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public byte[] getCorpoRemetente() {
        return corpoRemetente;
    }

    public void setCorpoRemetente(byte[] corpoRemetente) {
        this.corpoRemetente = corpoRemetente;
    }

    public byte[] getCorpoDestino() {
        return corpoDestino;
    }

    public void setCorpoDestino(byte[] corpoDestino) {
        this.corpoDestino = corpoDestino;
    }

    public String getCorpoPlano() {
        return corpoPlano;
    }

    public void setCorpoPlano(String corpoPlano) {
        this.corpoPlano = corpoPlano;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestino() {
        return destino;
    }

    public void setDestino(Usuario destino) {
        this.destino = destino;
    }

    public String horaFormatada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return hora.format(formatter);
    }
    
}
