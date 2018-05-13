package ch.jmildner.jdbs_jpa.uebungen7;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-22T21:24:29.202+0100")
@StaticMetamodel(PersonJPA7.class)
public class PersonJPA7_ {
	public static volatile SingularAttribute<PersonJPA7, Long> id;
	public static volatile SingularAttribute<PersonJPA7, String> name;
	public static volatile SingularAttribute<PersonJPA7, Integer> kz;
	public static volatile SingularAttribute<PersonJPA7, AdresseJPA7> addr;
}
