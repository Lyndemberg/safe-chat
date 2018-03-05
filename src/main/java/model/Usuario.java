
package model;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * @author lyndemberg
 */
@Entity
public class Usuario implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    
    @Column(unique = true)
    private String username;
    
    @Column(unique = true)
    private String email;
    private String senha;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private PublicKey chavePublica;
    
    @Transient
    private PrivateKey chavePrivada;
    
    public Usuario() {
    }

    
    public Usuario(String username, String email, String senha, byte[] foto) {
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Usuario{" + "username=" + username + ", email=" + email + ", senha=" + senha + ", foto=" + foto + '}';
    }
    
    public String fotoBase64(){
        return Base64.encode(foto);
    }

    public PublicKey getChavePublica() {
        return chavePublica;
    }

    public void setChavePublica(PublicKey chavePublica) {
        this.chavePublica = chavePublica;
    }

    public PrivateKey getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(PrivateKey chavePrivada) {
        this.chavePrivada = chavePrivada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
