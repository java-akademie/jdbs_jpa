package ch.jmildner.jdbs_jpa.uebungen8;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-04-02T08:36:27.915+0200")
@StaticMetamodel(Item.class)
public class Item_ {
	public static volatile SingularAttribute<Item, Long> id;
	public static volatile SingularAttribute<Item, String> title;
	public static volatile SingularAttribute<Item, Integer> preis;
	public static volatile SingularAttribute<Item, Date> verkauft;
	public static volatile SingularAttribute<Item, Customer> seller;
	public static volatile SingularAttribute<Item, Customer> buyer;
}
