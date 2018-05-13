package ch.jmildner.jdbs_jpa.uebungen99;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.jmildner.tools.MyTools;

public class TestPersonJPA99
{
	private static EntityManagerFactory emf;

	private static Geschlecht gm, gf;


	public static void main(String[] args) throws Exception
	{
		xxx();
		MyTools.pause();
		gf.show();
		gm.show();
	}


	private static void xxx() throws Exception
	{
		MyTools.uebOut("start TestPersonJPA99", 2);

		emf = Persistence.createEntityManagerFactory("H2");

		personenErstellen();

		zeigePersonen();
		zeigeGeschlechter();

		/**
		 * den Cache entleeren
		 */
		emf.getCache().evictAll();

		getGeschlecht();
		getGeschlecht();

		emf.getCache().evictAll();

		emf.close();

		MyTools.untOut("stopp programm", 2);
	}


	private static void getGeschlecht()
	{
		MyTools.uebOut("start getGeschlecht", 2);
		/**
		 * den Cache entleeren
		 */
		emf.getCache().evictAll();

		EntityManager em = emf.createEntityManager();

		gm = em.find(Geschlecht.class, 1L);
		gf = em.find(Geschlecht.class, 2L);


		MyTools.untOut("stopp getGeschlecht", 2);
	}


	private static void personenErstellen() throws Exception
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



	private static void zeigeGeschlechter()
	{
		MyTools.uebOut("start zeigeGeschlechter", 2);
		/**
		 * den Cache entleeren
		 */
		emf.getCache().evictAll();

		EntityManager em = emf.createEntityManager();

		Query q = em.createQuery("select g from Geschlecht g");

		List<?> list = q.getResultList();

		for (Object o : list)
		{
			Geschlecht e = (Geschlecht) o;
			e.show();
		}

		MyTools.untOut("stopp zeigeGeschlechter", 2);
	}


	private static void zeigePersonen()
	{
		MyTools.uebOut("start zeigePersonen", 2);

		/**
		 * den Cache entleeren
		 */
		emf.getCache().evictAll();

		EntityManager em = emf.createEntityManager();

		Query q = em.createQuery("select p from PersonJPA99 p");

		List<?> list = q.getResultList();

		for (Object o : list)
		{
			PersonJPA99 e = (PersonJPA99) o;
			e.show();
		}

		MyTools.untOut("stopp zeigePersonen", 2);
	}

}


