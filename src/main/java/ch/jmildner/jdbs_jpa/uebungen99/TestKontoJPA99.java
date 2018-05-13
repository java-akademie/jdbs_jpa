package ch.jmildner.jdbs_jpa.uebungen99;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.jmildner.tools.BigDecimalTools;
import ch.jmildner.tools.MyTools;

public class TestKontoJPA99
{
	private static EntityManagerFactory emf;


	public static void main(String[] args) throws Exception
	{

		MyTools.uebOut("start TestKontoJPA99", 2);

		emf = Persistence.createEntityManagerFactory("H2");

		kontenErstellen();

		zeigeKonten();


		emf.close();

		MyTools.untOut("stopp programm", 2);
	}



	private static void kontenErstellen() throws Exception
	{
		MyTools.uebOut("start kontenErstellen", 2);

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		/**
		 * erstelle Personen
		 */
		for (int i = 1; i <= 100; i++)
		{
			KontoJPA99 k = new KontoJPA99();
			k.setBezeichnung("bez-" + i);
			k.setSaldo(BigDecimalTools
					.make(MyTools.getRandom(i * 50, i * 1000) + ".00"));
			k.setWert(BigDecimalTools.makeRandom(9, 2));
			em.persist(k);
		}

		em.getTransaction().commit();

		em.close();

		MyTools.untOut("stopp kontenErstellen", 2);
	}



	private static void zeigeKonten()
	{
		MyTools.uebOut("start zeigeKonten", 2);

		/**
		 * den Cache entleeren
		 */
		emf.getCache().evictAll();

		EntityManager em = emf.createEntityManager();

		Query q = em.createQuery(
				"select k from KontoJPA99 k order by k.saldo");

		List<?> list = q.getResultList();

		for (Object o : list)
		{
			KontoJPA99 k = (KontoJPA99) o;
			k.show();
		}

		MyTools.untOut("stopp zeigeKonten", 2);
	}

}


