package model;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-05T02:49:23")
@StaticMetamodel(Conversa.class)
public class Conversa_ { 

    public static volatile SingularAttribute<Conversa, Usuario> remetente;
    public static volatile SingularAttribute<Conversa, Integer> id;
    public static volatile SingularAttribute<Conversa, Usuario> destino;
    public static volatile SingularAttribute<Conversa, List> mensagens;

}