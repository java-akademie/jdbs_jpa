package ch.jmildner.jdbs_jpa.uebungen1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "Dali", date = "2017-04-02T08:36:27.882+0200")
@StaticMetamodel(PersonJPA1.class)
public class PersonJPA1_
{

    public static volatile SingularAttribute<PersonJPA1, Long> id;
    public static volatile SingularAttribute<PersonJPA1, Integer> version;
    public static volatile SingularAttribute<PersonJPA1, String> name;
    public static volatile SingularAttribute<PersonJPA1, Short> shortZahl;
    public static volatile SingularAttribute<PersonJPA1, Integer> intZahl;
    public static volatile SingularAttribute<PersonJPA1, Long> longZahl;
    public static volatile SingularAttribute<PersonJPA1, Date> datum;
    public static volatile SingularAttribute<PersonJPA1, Time> zeit;
    public static volatile SingularAttribute<PersonJPA1, Timestamp> zeitstempel;
    public static volatile SingularAttribute<PersonJPA1, BigInteger> wert;
    public static volatile SingularAttribute<PersonJPA1, BigDecimal> saldo;
}
