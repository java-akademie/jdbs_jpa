package ch.jmildner.jdbs_jpa.uebungen8;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-23T10:26:22.363+0100")
@StaticMetamodel(Customer.class)
public class Customer_ {
	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, String> ort;
	public static volatile ListAttribute<Customer, Item> offers;
	public static volatile ListAttribute<Customer, Item> purchases;
}
