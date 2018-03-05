package controllers;

import interfaces.ConversaDao;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private Usuario logado;
    @PostConstruct
    public void init(){
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        logado = (Usuario) sessao.getAttribute("usuario");
    }
    
    public List<Mensagem> msgsDescriptografadas() throws Exception{
        Conversa conversa = conversaDao.getConversa(logado, destino);
        if(conversa != null){
            List<Mensagem> msgs = conversa.getMensagens();
            if(conversa.getUser1().getEmail().equals(logado.getEmail())){
                for(Mensagem m : msgs){
                    String descrip = new String(CryptoSafeChat.descriptografar(m.getCorpoUser1(), 
                            logado.getChavePrivada()));
                    m.setCorpoPlano(descrip);
                }
            }else{
                for(Mensagem m : msgs){
                    String descrip = new String(CryptoSafeChat.descriptografar(m.getCorpoUser2(), 
                            logado.getChavePrivada()));
                    m.setCorpoPlano(descrip);
                }
            }
            return msgs;
        }
        return null;
    }
    
    
    public String enviarMensagem() throws Exception{
        Conversa conversa = conversaDao.getConversa(logado, destino);
        Mensagem nova;
        byte[] corpoUser1;
        byte[] corpoUser2;
        if(conversa != null){    
            if(conversa.getUser1().getEmail().equals(logado.getEmail())){
                corpoUser1 = CryptoSafeChat.criptografar(mensagem.getBytes(), logado.getChavePublica());
                corpoUser2 = CryptoSafeChat.criptografar(mensagem.getBytes(), conversa.getUser2().getChavePublica());
            }else{
                corpoUser2 = CryptoSafeChat.criptografar(mensagem.getBytes(), logado.getChavePublica());
                corpoUser1 = CryptoSafeChat.criptografar(mensagem.getBytes(), conversa.getUser1().getChavePublica());
            }
            nova = new Mensagem(LocalDateTime.now(),corpoUser1, corpoUser2,logado,destino);
            conversa.addMensagem(nova);
            conversaDao.atualizar(conversa);
        }else{
            Conversa novaConversa = new Conversa(logado, destino);
            corpoUser1 = CryptoSafeChat.criptografar(mensagem.getBytes(), logado.getChavePublica());
            corpoUser2 = CryptoSafeChat.criptografar(mensagem.getBytes(), destino.getChavePublica());
            nova = new Mensagem(LocalDateTime.now(),corpoUser1, corpoUser2,logado,destino);
            novaConversa.addMensagem(nova);
            conversaDao.salvar(novaConversa);
        }   
        mensagem = null;
        return null;
    }

    public String abrirConversa(Usuario u){
        destino = u;
        return "conversa.xhtml?faces-redirect=true";
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
