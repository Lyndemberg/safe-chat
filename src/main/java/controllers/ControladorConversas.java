/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.MensagemDao;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Mensagem;
import model.Usuario;

/**
 * @author lyndemberg
 */
@Named
@RequestScoped
public class ControladorConversas {
    @EJB
    private MensagemDao msgDao;
    
    public List<Mensagem> getMensagensDescrip(){
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuario logado = (Usuario) sessao.getAttribute("usuario");    
        System.out.println(logado.getEmail());
        try {
            List<Mensagem> msgsRetorno = msgDao.msgsDescript(logado);
            System.out.println(msgsRetorno);
            return msgsRetorno;
        } catch (Exception ex) {
            Logger.getLogger(ControladorConversas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
