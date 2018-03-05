package controllers;

import interfaces.ConversaDao;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Conversa;
import model.Mensagem;
import model.Usuario;
import util.CryptoSafeChat;

@Named
@SessionScoped
public class ControladorConversa implements Serializable{
    private Usuario destino = new Usuario();
    private String mensagem;
    @EJB
    private ConversaDao conversaDao;
    
    public List<Mensagem> msgsDescriptografadas() throws Exception{
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuario logado = (Usuario) sessao.getAttribute("usuario");
        Conversa conversa = conversaDao.getConversa(logado, destino);
        
        if(conversa != null){
            List<Mensagem> msgs = conversa.getMensagens();
            if(conversa.getRemetente().getEmail().equals(logado.getEmail())){
                for(Mensagem m : msgs){
                    String descrip = new String(CryptoSafeChat.descriptografar(m.getCorpoRemetente(), 
                            logado.getChavePrivada()));
                    m.setCorpoPlano(descrip);
                }
            }else{
                for(Mensagem m : msgs){
                    String descrip = new String(CryptoSafeChat.descriptografar(m.getCorpoDestino(), 
                            logado.getChavePrivada()));
                    m.setCorpoPlano(descrip);
                }
            }
            return msgs;
        }
        return null;
    }
    
    
    public String enviarMensagem() throws Exception{
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuario logado = (Usuario) sessao.getAttribute("usuario");        
        Conversa conversa = conversaDao.getConversa(logado, destino);
        if(conversa != null){
            byte[] corpoRemetente;
            byte[] corpoDestino;
            if(conversa.getRemetente().getEmail().equals(logado.getEmail())){
                corpoRemetente = CryptoSafeChat.criptografar(mensagem.getBytes(), logado.getChavePublica());
                corpoDestino = CryptoSafeChat.criptografar(mensagem.getBytes(), conversa.getDestino().getChavePublica());
            }else{
                corpoDestino = CryptoSafeChat.criptografar(mensagem.getBytes(), logado.getChavePublica());
                corpoRemetente = CryptoSafeChat.criptografar(mensagem.getBytes(), conversa.getRemetente().getChavePublica());
            }
            Mensagem nova = new Mensagem(LocalDateTime.now(),corpoRemetente, corpoDestino,logado,destino);
            conversa.addMensagem(nova);
            conversaDao.atualizar(conversa);
        }else{
            Conversa novaConversa = new Conversa(logado, destino);
            byte[] corpoRemetente = CryptoSafeChat.criptografar(mensagem.getBytes(), logado.getChavePublica());
            byte[] corpoDestino = CryptoSafeChat.criptografar(mensagem.getBytes(), destino.getChavePublica());
            Mensagem nova = new Mensagem(LocalDateTime.now(),corpoRemetente, corpoDestino,logado,destino);
            novaConversa.addMensagem(nova);
            conversaDao.salvar(novaConversa);
        }   
        mensagem = null;
        return null;
    }

    public String abrirConversa(Usuario u){
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuario logado = (Usuario) sessao.getAttribute("usuario");        
        destino = u;
        return "conversa.xhtml";
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getDestino() {
        return destino;
    }

    public void setDestino(Usuario destino) {
        this.destino = destino;
    }
    
    
    
    
    
    
    
}
