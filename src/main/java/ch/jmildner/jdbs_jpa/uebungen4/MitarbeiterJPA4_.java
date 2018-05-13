package ch.jmildner.jdbs_jpa.uebungen4;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-20T11:46:29.916+0100")
@StaticMetamodel(MitarbeiterJPA4.class)
public class MitarbeiterJPA4_ {
	public static volatile SingularAttribute<MitarbeiterJPA4, Long> id;
	public static volatile SingularAttribute<MitarbeiterJPA4, String> name;
	public static volatile ListAttribute<MitarbeiterJPA4, ProjektJPA4> projektListe;
}
