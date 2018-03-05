
package controllers;

import interfaces.ConversaDao;
import interfaces.UsuarioDao;
import model.Mensagem;
import model.Usuario;
import util.CryptoSafeChat;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Conversa;

/**
 * @author lyndemberg
 */
@Named
@SessionScoped
public class ControladorBusca implements Serializable{
    @EJB
    private UsuarioDao usuarioDao;
    private Usuario destino = new Usuario();
    private boolean verResult = false;
    @EJB
    private ConversaDao conversaDao;
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
//    public String enviarMensagem() throws Exception{
//        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//        Usuario logado = (Usuario) sessao.getAttribute("usuario");        
//        Conversa conversa = conversaDao.getConversa(logado, destino);
//        if(conversa != null){
//            byte[] corpoRemetente;
//            byte[] corpoDestino;
//            if(conversa.getRemetente().getEmail().equals(logado.getEmail())){
//                corpoRemetente = CryptoSafeChat.criptografar(mensagem.getBytes(), logado.getChavePublica());
//                corpoDestino = CryptoSafeChat.criptografar(mensagem.getBytes(), conversa.getDestino().getChavePublica());
//            }else{
//                corpoDestino = CryptoSafeChat.criptografar(mensagem.getBytes(), logado.getChavePublica());
//                corpoRemetente = CryptoSafeChat.criptografar(mensagem.getBytes(), conversa.getRemetente().getChavePublica());
//            }
//            Mensagem nova = new Mensagem(LocalDateTime.now(),corpoRemetente, corpoDestino);
//            conversa.addMensagem(nova);
//            conversaDao.atualizar(conversa);
//        }else{
//            Conversa novaConversa = new Conversa(logado, destino);
//            byte[] corpoRemetente = CryptoSafeChat.criptografar(mensagem.getBytes(), logado.getChavePublica());
//            byte[] corpoDestino = CryptoSafeChat.criptografar(mensagem.getBytes(), destino.getChavePublica());
//            Mensagem novaMsg = new Mensagem(LocalDateTime.now(),corpoRemetente, corpoDestino);
//            novaConversa.addMensagem(novaMsg);
//            conversaDao.salvar(novaConversa);
//        }   
//        mensagem = null;
//        return null;
//    }
  
    
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
