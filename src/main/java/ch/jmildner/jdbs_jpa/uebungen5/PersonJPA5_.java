package ch.jmildner.jdbs_jpa.uebungen5;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-02T08:36:27.899+0200")
@StaticMetamodel(PersonJPA5.class)
public class PersonJPA5_ {
	public static volatile SingularAttribute<PersonJPA5, Long> id;
	public static volatile SingularAttribute<PersonJPA5, Integer> version;
	public static volatile SingularAttribute<PersonJPA5, String> vorName;
	public static volatile SingularAttribute<PersonJPA5, String> nachName;
	public static volatile SingularAttribute<PersonJPA5, Short> gewicht;
	public static volatile SingularAttribute<PersonJPA5, Integer> intZahl;
	public static volatile SingularAttribute<PersonJPA5, Long> longZahl;
	public static volatile SingularAttribute<PersonJPA5, Date> datum;
	public static volatile SingularAttribute<PersonJPA5, Time> zeit;
	public static volatile SingularAttribute<PersonJPA5, Timestamp> zeitstempel;
}
