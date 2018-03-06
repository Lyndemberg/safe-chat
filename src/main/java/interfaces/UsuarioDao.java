/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.Usuario;

/**
 *
 * @author lyndemberg
 */
public interface UsuarioDao {

    void atualizar(Usuario atualizado);

    Usuario autentica(String email, byte[] senha);

    Usuario buscarPorUsername(String username);

    Usuario lerPorEmail(String email);

    Usuario lerPorId(int id);

    void salvar(Usuario novo);
    
}
