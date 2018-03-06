
package controllers;

import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;
import model.Usuario;
import util.CryptoSafeChat;
import interfaces.PrivateKeyDao;
import interfaces.UsuarioDao;
import java.security.MessageDigest;
import util.Md5;

/**
 *
 * @author lyndemberg
 */
@Named
@RequestScoped
public class ControladorCadastro {
    private Part foto;
    private Usuario usuario = new Usuario();
    @EJB
    private UsuarioDao usuarioDao;
    @EJB 
    private PrivateKeyDao privateKeyDao;
    private String senha;
    public String cadastrar(){
        if(usuarioDao.buscarPorUsername(usuario.getUsername()) != null){
            mostrarMsg("Cadastro", "Já existe um usuário com esse username");
        }else if(usuarioDao.lerPorEmail(usuario.getEmail()) != null){
            mostrarMsg("Cadastro", "Já existe um usuário com esse email");
        }else{
            byte[] fotoArray = new byte[ (int) foto.getSize()];
            try {
                foto.getInputStream().read(fotoArray);
            } catch (IOException ex) {
                Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setFoto(fotoArray);
            try {
                KeyPair par = CryptoSafeChat.getParDeChaves();
                PublicKey publica = par.getPublic();
                PrivateKey privada = par.getPrivate();
                usuario.setChavePublica(publica);
                
                //salvando private key no mongo
                privateKeyDao.save(usuario.getEmail(), privada);
                
                usuario.setSenha(Md5.toMd5(senha));;
                
                usuarioDao.salvar(usuario);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return "index.xhtml?faces-redirect=true";
        }
        return null;
    }

    public Part getFoto() {
        return foto;
    }

    public void setFoto(Part foto) {
        this.foto = foto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    private void mostrarMsg(String titulo, String corpo){
        FacesMessage mensagem = new FacesMessage(corpo);
        mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(titulo, mensagem);
    }
}
