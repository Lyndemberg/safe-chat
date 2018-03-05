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
    private byte[] corpoUser1;
    @Lob
    private byte[] corpoUser2;
    @Transient
    private String corpoPlano;
    @OneToOne
    private Usuario remetente;
    @OneToOne
    private Usuario destino;
    public Mensagem() {
        
    }

    public Mensagem(LocalDateTime hora, byte[] corpoUser1, byte[] corpoUser2, Usuario remetente, Usuario destino) {
        this.hora = hora;
        this.corpoUser1 = corpoUser1;
        this.corpoUser2 = corpoUser2;
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

    public byte[] getCorpoUser1() {
        return corpoUser1;
    }

    public void setCorpoUser1(byte[] corpoUser1) {
        this.corpoUser1 = corpoUser1;
    }

    public byte[] getCorpoUser2() {
        return corpoUser2;
    }

    public void setCorpoUser2(byte[] corpoUser2) {
        this.corpoUser2 = corpoUser2;
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
