
package controllers;

import dao.UsuarioDao;
import model.Mensagem;
import model.Usuario;
import util.CryptoSafeChat;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * @author lyndemberg
 */
@Named
@SessionScoped
public class ControladorMensagem implements Serializable{
    @EJB
    private UsuarioDao usuarioDao;
    private Usuario destino = new Usuario();
    private boolean verResult = false;
    private String mensagem;
    
    
    public String buscar(){
        Usuario retorno = usuarioDao.buscarPorUsername(destino.getUsername());
        if(retorno != null){
            destino = retorno;
            verResult = true;
        }else{
            verResult = false;
        }
        return null;
    }
    
    public String enviarMensagem() throws Exception{
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuario remetente = (Usuario) sessao.getAttribute("usuario");
        byte[] msgCriptografada = CryptoSafeChat.criptografar(mensagem.getBytes(), destino.getChavePublica());
        Mensagem m = new Mensagem(remetente, destino, LocalDateTime.now(), msgCriptografada);
        destino.enviarMsg(m);
        usuarioDao.atualizar(destino);
        mensagem = null;
        return null;
    }
    
    public Usuario getDestino() {
        return destino;
    }

    public void setDestino(Usuario destino) {
        this.destino = destino;
    }

    public boolean isVerResult() {
        return verResult;
    }

    public void setVerResult(boolean verResult) {
        this.verResult = verResult;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
 
}
