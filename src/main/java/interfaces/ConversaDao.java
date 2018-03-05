
package interfaces;

import java.util.List;
import model.Conversa;
import model.Usuario;

/**
 *
 * @author lyndemberg
 */
public interface ConversaDao {
    void salvar(Conversa nova);
    void atualizar(Conversa atualizada);
    List<Conversa> getConversasUsuario(Usuario user);
    Conversa getConversa(Usuario user1, Usuario user2);
}
