
package com.ifpb.seguranca.safechat.controllers;

import com.ifpb.seguranca.safechat.dao.UsuarioDao;
import com.ifpb.seguranca.safechat.model.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author lyndemberg
 */
@Named
@RequestScoped
public class ControladorUsuario implements Serializable{
    private Part foto;
    private Usuario usuario = new Usuario();
    @EJB
    private UsuarioDao usuarioDao;
    
    public String cadastrar(){
        if(usuarioDao.lerPorEmail(usuario.getEmail()) != null){
            mostrarMsg("Cadastro", "Já existe um usuário com esse email");
        }else{
            byte[] fotoArray = new byte[ (int) foto.getSize()];
            try {
                foto.getInputStream().read(fotoArray);
            } catch (IOException ex) {
                Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setFoto(fotoArray);
            usuarioDao.salvar(usuario);
            return "index.xhtml";
        }
        return null;
    }
    public String logar(){
        Usuario u = usuarioDao.lerPorEmail(usuario.getEmail());
        if(u == null){
            mostrarMsg("Login","Usuário não cadastrado");
            return null;
        }else{
            Usuario autentica = usuarioDao.autentica(usuario.getEmail(), usuario.getSenha());
            if(autentica == null){
                mostrarMsg("Login","Dados incorretos");
                return null;   
            }else{
                HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                sessao.setAttribute("usuario", u);
                return "home.xhtml?faces-redirect=true";
            }
        }
    }
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
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
    
    public void mostrarMsg(String titulo, String corpo){
        FacesMessage mensagem = new FacesMessage(corpo);
        mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(titulo, mensagem);
    }

    
}
