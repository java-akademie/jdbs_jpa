package ch.jmildner.jdbs_jpa.uebungen3;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-20T11:46:29.915+0100")
@StaticMetamodel(VerlagJPA32.class)
public class VerlagJPA32_ {
	public static volatile SingularAttribute<VerlagJPA32, Long> id;
	public static volatile SingularAttribute<VerlagJPA32, String> name;
	public static volatile ListAttribute<VerlagJPA32, BuchJPA32> buecher;
}
