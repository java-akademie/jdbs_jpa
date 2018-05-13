package ch.jmildner.jdbs_jpa.uebungen6;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-02T08:36:27.906+0200")
@StaticMetamodel(PersonJPA6.class)
public class PersonJPA6_ {
	public static volatile SingularAttribute<PersonJPA6, Long> id;
	public static volatile SingularAttribute<PersonJPA6, Integer> version;
	public static volatile SingularAttribute<PersonJPA6, String> vorName;
	public static volatile SingularAttribute<PersonJPA6, String> nachName;
	public static volatile SingularAttribute<PersonJPA6, Short> gewicht;
	public static volatile SingularAttribute<PersonJPA6, Integer> intZahl;
	public static volatile SingularAttribute<PersonJPA6, Long> longZahl;
	public static volatile SingularAttribute<PersonJPA6, Date> datum;
	public static volatile SingularAttribute<PersonJPA6, Time> zeit;
	public static volatile SingularAttribute<PersonJPA6, Timestamp> zeitstempel;
}
