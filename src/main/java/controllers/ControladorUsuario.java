
package controllers;

import model.Usuario;
import java.io.Serializable;
import java.security.PrivateKey;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import interfaces.PrivateKeyDao;
import interfaces.UsuarioDao;

/**
 *
 * @author lyndemberg
 */
@Named
@RequestScoped
public class ControladorUsuario implements Serializable{
    private Usuario usuario = new Usuario();
    @EJB
    private UsuarioDao usuarioDao;
    @EJB 
    private PrivateKeyDao privateKeyDao;
    
    public String logar() throws Exception{
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
                //RECUPERANDO A CHAVE PRIVADA DO MONGO, SETANDO NO USUÁRIO DURANTE A SESSÃO
                PrivateKey privada = privateKeyDao.getPrivateKey(u.getEmail());
                u.setChavePrivada(privada);
                sessao.setAttribute("usuario", u);
                return "conversas.xhtml?faces-redirect=true";
            }
        }
    }
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    private void mostrarMsg(String titulo, String corpo){
        FacesMessage mensagem = new FacesMessage(corpo);
        mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(titulo, mensagem);
    }

    
}
