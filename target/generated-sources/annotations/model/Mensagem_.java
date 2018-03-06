package model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-06T10:30:06")
@StaticMetamodel(Mensagem.class)
public class Mensagem_ { 

    public static volatile SingularAttribute<Mensagem, Usuario> remetente;
    public static volatile SingularAttribute<Mensagem, byte[]> corpoUser1;
    public static volatile SingularAttribute<Mensagem, byte[]> corpoUser2;
    public static volatile SingularAttribute<Mensagem, LocalDateTime> hora;
    public static volatile SingularAttribute<Mensagem, Integer> id;
    public static volatile SingularAttribute<Mensagem, Usuario> destino;

}