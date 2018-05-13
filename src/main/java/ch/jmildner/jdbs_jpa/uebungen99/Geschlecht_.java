package ch.jmildner.jdbs_jpa.uebungen99;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-24T09:56:47.606+0100")
@StaticMetamodel(Geschlecht.class)
public class Geschlecht_ {
	public static volatile SingularAttribute<Geschlecht, Long> id;
	public static volatile SingularAttribute<Geschlecht, String> geschlecht;
	public static volatile ListAttribute<Geschlecht, PersonJPA99> personen;
}
