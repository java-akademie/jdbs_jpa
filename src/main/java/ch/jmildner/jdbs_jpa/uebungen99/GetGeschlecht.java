package ch.jmildner.jdbs_jpa.uebungen99;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ch.jmildner.tools.MyTools;

public class GetGeschlecht
{
	private static EntityManagerFactory emf;

	private static Geschlecht gm, gf;


	public static void main(String[] args) throws Exception
	{
		new GetGeschlecht();
		MyTools.pause();
		gm.show();
		gf.show();

	}


	public GetGeschlecht() throws Exception
	{
		MyTools.uebOut("start programm", 2);

		emf = Persistence.createEntityManagerFactory("POSTGRES");

		personenErstellen();

		EntityManager em = emf.createEntityManager();

		gm = em.find(Geschlecht.class, 1L);
		gf = em.find(Geschlecht.class, 2L);

		em.close();

		emf.getCache().evictAll();

		emf.close();

		MyTools.untOut("stopp programm", 2);
	}



	private void personenErstellen() throws Exception
	{
		MyTools.uebOut("start personenErstellen", 2);

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		/**
		 * erstelle Geschlecht
		 */
		Geschlecht m = new Geschlecht("maskulin");
		Geschlecht f = new Geschlecht("feminin");

		em.persist(m);
		em.persist(f);

		/**
		 * erstelle Personen
		 */
		for (int i = 1; i <= 10000; i++)
		{
			PersonJPA99 p = new PersonJPA99();
			p.setName("name-" + i);
			p.setGeschlecht(i % 2 == 0 ? m : f);
			em.persist(p);
		}

		em.getTransaction().commit();

		em.close();

		MyTools.untOut("stopp personenErstellen", 2);
	}

}


