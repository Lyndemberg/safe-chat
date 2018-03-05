

package controllers;

import interfaces.ConversaDao;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import model.Conversa;
import model.Usuario;

/**
 * @author lyndemberg
 */
@Named
@RequestScoped
public class ControladorConversas {
    @EJB
    private ConversaDao conversaDao;
    
    public List<Conversa> getConversas(){
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Usuario logado = (Usuario) sessao.getAttribute("usuario");    
        return conversaDao.getConversasUsuario(logado);
    }
    
}
