
package dao;

import java.security.PrivateKey;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Mensagem;
import model.Usuario;
import util.CryptoSafeChat;

/**
 * @author lyndemberg
 */
@Stateless
public class MensagemDao {
    @PersistenceContext(unitName = "pu-safechat")
    private EntityManager em;
    
    public List<Mensagem> msgsDescript(Usuario u) throws Exception{
        TypedQuery<Mensagem> query = 
                em.createQuery("SELECT m FROM Mensagem m WHERE m.destino=:usuario", Mensagem.class);
        query.setParameter("usuario", u);
        List<Mensagem> msgs = query.getResultList();
        for(Mensagem m : msgs){
            String plana = new String(CryptoSafeChat.descriptografar(m.getCorpo(), u.getChavePrivada()));
            m.setCorpoPlano(plana);
        }
        return msgs;
    }
}
