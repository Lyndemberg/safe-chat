
package com.ifpb.seguranca.safechat.model;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author lyndemberg
 */
@Entity
public class Usuario implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String email;
    private String senha;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto;

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
    
    
    
    
}
