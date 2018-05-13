package ch.jmildner.jdbs_jpa.uebungen4;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-03-20T11:46:29.918+0100")
@StaticMetamodel(ProjektJPA4.class)
public class ProjektJPA4_ {
	public static volatile SingularAttribute<ProjektJPA4, Long> id;
	public static volatile SingularAttribute<ProjektJPA4, String> bezeichnung;
	public static volatile CollectionAttribute<ProjektJPA4, MitarbeiterJPA4> mitarbeiterListe;
}
