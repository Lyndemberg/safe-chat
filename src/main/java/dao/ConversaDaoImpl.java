/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.ConversaDao;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Conversa;
import model.Usuario;

/**
 *
 * @author lyndemberg
 */
@Stateless
@Local(ConversaDao.class)
public class ConversaDaoImpl implements ConversaDao{
    @PersistenceContext(unitName = "pu-safechat")
    private EntityManager em;
    
    @Override
    public List<Conversa> getConversasUsuario(Usuario user) {
        TypedQuery<Conversa> query = em.createQuery("SELECT c FROM Conversa c WHERE c.remetente=:user OR c.destino=:user", Conversa.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public Conversa getConversa(Usuario user1, Usuario user2) {
        String psql = "SELECT c FROM Conversa c WHERE (c.remetente=:user1 AND c.destino=:user2) OR "
                + "(c.remetente=:user2 AND c.destino=:user1)";
        TypedQuery<Conversa> query = em.createQuery(psql, Conversa.class);
        query.setParameter("user1", user1);
        query.setParameter("user2", user2);
        Optional<Conversa> conversa = query.getResultList().stream().findFirst();
        if(conversa.isPresent()){
            return conversa.get();
        }else{
            return null;
        }
    }

    @Override
    public void salvar(Conversa nova) {
        em.persist(nova);
    }

    @Override
    public void atualizar(Conversa atualizada) {
        em.merge(atualizada);
    }
    
}
