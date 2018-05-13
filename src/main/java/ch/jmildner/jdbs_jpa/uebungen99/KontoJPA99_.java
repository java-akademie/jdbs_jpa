package ch.jmildner.jdbs_jpa.uebungen99;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-02T08:36:27.921+0200")
@StaticMetamodel(KontoJPA99.class)
public class KontoJPA99_ {
	public static volatile SingularAttribute<KontoJPA99, Long> id;
	public static volatile SingularAttribute<KontoJPA99, String> bezeichnung;
	public static volatile SingularAttribute<KontoJPA99, BigDecimal> saldo;
	public static volatile SingularAttribute<KontoJPA99, BigDecimal> wert;
}
