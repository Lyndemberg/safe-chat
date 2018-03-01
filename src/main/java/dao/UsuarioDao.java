package dao;

import model.Usuario;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lyndemberg
 */

@Stateless
public class UsuarioDao {
    @PersistenceContext(unitName = "pu-safechat")
    private EntityManager em;
    
    public Usuario buscarPorUsername(String username){
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.username=:username",Usuario.class);
        query.setParameter("username", username);
        Optional<Usuario> user = query.getResultList().stream().findFirst();
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }
    
    public void salvar(Usuario novo){
        em.persist(novo);
    }
    public void atualizar(Usuario atualizado){
        em.merge(atualizado);
    }
    public Usuario lerPorId(int id){
        return em.find(Usuario.class, id);
    }
    public Usuario lerPorEmail(String email){
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email=:email",Usuario.class);
        query.setParameter("email", email);
        Optional<Usuario> user = query.getResultList().stream().findFirst();
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }
    public Usuario autentica(String email, String senha){
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.email=:email AND u.senha=:senha", Usuario.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        Optional<Usuario> user = query.getResultList().stream().findFirst();
        if(user.isPresent()){
            return user.get();
        }else{
            return null;
        }
    }
    
    
}