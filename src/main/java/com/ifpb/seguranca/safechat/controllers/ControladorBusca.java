
package com.ifpb.seguranca.safechat.controllers;

import com.ifpb.seguranca.safechat.dao.UsuarioDao;
import com.ifpb.seguranca.safechat.model.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author lyndemberg
 */
@Named
@RequestScoped
public class ControladorBusca implements Serializable{
    @EJB
    private UsuarioDao usuarioDao;
    private Usuario usuario = new Usuario();
    private boolean verResult = false;

    public String buscar(){
        Usuario retorno = usuarioDao.buscarPorUsername(usuario.getUsername());
        if(retorno != null){
            usuario = retorno;
            verResult = true;
        }else{
            verResult = false;
        }
        return null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isVerResult() {
        return verResult;
    }

    public void setVerResult(boolean verResult) {
        this.verResult = verResult;
    }

    
    
    
    
    
}
