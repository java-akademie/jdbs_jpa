
package ch.jmildner.jdbs_jpa.uebungen5;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ch.jmildner.tools.DateTimeTools;
import ch.jmildner.tools.MyTools;
import ch.jmildner.tools.TestDatenTools;

public class TestJPA5a
{
	private static EntityManagerFactory emf;
	private static final int MAX = 100;


	public static void main(String[] args) throws Exception
	{
		MyTools.uebOut("start programm", 2);

		emf = Persistence.createEntityManagerFactory("H2");

		personenMasseninsert(MAX);

		dynamischeQueries();
		parametrisierteQueries();
		benannteQueries();
		nativeQueries();
		massenUpdatesUndDeletes();
		aggregatFunktionen();
		groupByHaving();

		showPersonen();

		emf.close();

		MyTools.untOut("stopp programm", 2);
	}


	private static void groupByHaving()
	{
		MyTools.uebOut("start groupByHaving", 2);

		EntityManager em = emf.createEntityManager();

		{
			System.out.println("GROUP BY");

			String sql = "select count(p) , avg(p.gewicht), p.vorName " + "from PersonJPA5 p "
					+ "group by p.vorName order by p.vorName";

			Query q = em.createQuery(sql);

			List<?> erg = q.getResultList();

			for (Object o : erg)
			{
				for (Object a : (Object[]) o)
				{
					System.out.print(String.format("%-5s  ", a));
				}
				System.out.println();
			}
		}
		{
			System.out.println("\n\nGROUP BY HAVING");


			String sql = "select count(p) , avg(p.gewicht), p.vorName " + "from PersonJPA5 p "
					+ "group by p.vorName having count(p) > 4 " + "order by p.vorName";

			Query q = em.createQuery(sql);

			List<?> erg = q.getResultList();

			for (Object o : erg)
			{
				for (Object a : (Object[]) o)
				{
					System.out.print(String.format("%-5s  ", a));
				}
				System.out.println();
			}
		}

		em.close();

		MyTools.untOut("stopp groupByHaving", 2);
	}



	private static void aggregatFunktionen()
	{
		MyTools.uebOut("start aggregatFunktionen", 2);

		EntityManager em = emf.createEntityManager();

		{
			System.out.println("SINGLE RESULT: SUM");

			String sql = "select sum(p.gewicht) from PersonJPA5 p";


			Query q = em.createQuery(sql);

			Long summe = (Long) q.getSingleResult();

			System.out.println(summe);
		}


		{
			System.out.println("RESULT LIST: COUNT, SUM, AVG, MAX, MIN");
			String sql = "select count(p), sum(p.gewicht) , avg(p.gewicht) , "
					+ "max(p.gewicht) , min(p.gewicht) from PersonJPA5 p";

			Query q = em.createQuery(sql);

			Object[] o = (Object[]) q.getSingleResult();

			System.out.printf("count=%s, sum=%s, avg=%s, max=%s, min=%s %n", o[0], o[1], o[2], o[3], o[4]);
		}
		em.close();
		MyTools.untOut("stopp aggregatFunktionen", 2);
	}


	private static void massenUpdatesUndDeletes()
	{
		MyTools.uebOut("start massenUpdatesUndDeletes", 2);

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		{
			String sql = "update PersonJPA5 p set p.gewicht=88 " + "where p.vorName IN ('peter','bruno','gerhard')";

			Query q = em.createQuery(sql);
			int erg = q.executeUpdate();
			System.out.println("Anzahl auf 88 kg upgedated: " + erg);
		}

		{
			String sql = "update PersonJPA5 p set p.gewicht=66 " + "where p.vorName in ('heidi','karin','gerlinde')";

			Query q = em.createQuery(sql);
			int erg = q.executeUpdate();
			System.out.println("Anzahl auf 66 kg upgedated: " + erg);
		}

		{
			String sql = "delete from PersonJPA5 p where " + "(p.vorName like '%a') " + "or (p.vorName like '%b') "
					+ "or (p.vorName like '%c') " + "or (p.vorName like '%d') ";
			Query q = em.createQuery(sql);
			int erg = q.executeUpdate();
			System.out.println("Anzahl geloescht: " + erg);
		}

		em.getTransaction().commit();

		em.close();

		MyTools.untOut("stopp massenUpdatesUndDeletes", 2);
	}



	private static void nativeQueries()
	{
		MyTools.uebOut("start nativeQueries", 2);

		EntityManager em = emf.createEntityManager();

		{
			String sql = "select p.id, p.vorName, p.nachName " + "from PersonJPA5 p where p.vorName='bruno'";

			Query q = em.createNativeQuery(sql);

			List<?> erg = q.getResultList();

			for (Object o : erg)
			{
				for (Object a : (Object[]) o)
				{
					System.out.print(a + " ");
				}
				System.out.println();
			}
		}

		em.close();

		MyTools.untOut("stopp nativeQueries", 2);
	}


	private static void benannteQueries()
	{
		MyTools.uebOut("start benannteQueries", 2);

		EntityManager em = emf.createEntityManager();

		{
			Query q = em.createNamedQuery("getCount");
			Long count = (Long) q.getSingleResult();
			System.out.println("Count: " + count);
		}

		{
			TypedQuery<PersonJPA5> q = em.createNamedQuery("findByNachName", PersonJPA5.class);
			q.setParameter("persNachName", "huber");
			List<PersonJPA5> list = q.getResultList();
			for (PersonJPA5 p : list)
			{
				p.show();
			}
		}

		em.close();

		MyTools.untOut("stopp benannteQueries", 2);
	}


	private static void parametrisierteQueries()
	{
		MyTools.uebOut("start parametrisierteQueries", 2);

		EntityManager em = emf.createEntityManager();

		{
			System.out.println("SINGLE RESULT");
			String sql = "select p from PersonJPA5 p " + "where p.id=:persId";
			Query q = em.createQuery(sql);
			q.setParameter("persId", 16);
			PersonJPA5 p = (PersonJPA5) q.getSingleResult();
			p.show();
		}

		{
			System.out.println("RESULT LIST");
			String sql = "select p from PersonJPA5 p " + "where p.vorName=:persVorName";
			Query q = em.createQuery(sql);
			q.setParameter("persVorName", "peter");
			List<?> list = q.getResultList();
			for (Object o : list)
			{
				System.out.println(o);
			}
		}
		em.close();
		MyTools.untOut("stopp parametrisierteQueries", 2);
	}


	private static void dynamischeQueries()
	{
		MyTools.uebOut("start dynamischeQueries", 2);

		EntityManager em = emf.createEntityManager();

		{
			System.out.println("SINGLE RESULT");
			String sql = "select p from PersonJPA5 p " + "where p.id=15";
			Query q = em.createQuery(sql);
			PersonJPA5 p = (PersonJPA5) q.getSingleResult();
			p.show();
		}


		{
			System.out.println("SINGLE RESULT (TYPED QUERY)");
			String sql = "select p from PersonJPA5 p " + "where p.id=15";
			TypedQuery<PersonJPA5> q = em.createQuery(sql, PersonJPA5.class);
			PersonJPA5 p = q.getSingleResult();
			p.show();
		}

		{
			System.out.println("RESULT LIST");
			String sql = "select p from PersonJPA5 p " + "where p.vorName='johann'";
			Query q = em.createQuery(sql);
			List<?> list = q.getResultList();
			for (Object o : list)
			{
				PersonJPA5 p = (PersonJPA5) o;
				p.show();
			}
		}

		{
			System.out.println("RESULT LIST (TYPED QUERY)");
			String sql = "select p from PersonJPA5 p " + "where p.vorName='johann'";
			TypedQuery<PersonJPA5> q = em.createQuery(sql, PersonJPA5.class);
			List<PersonJPA5> list = q.getResultList();
			for (PersonJPA5 p : list)
			{
				p.show();
			}
		}

		em.close();

		MyTools.untOut("stopp dynamischeQueries", 2);
	}


	private static void personenMasseninsert(final int MAX) throws Exception
	{
		MyTools.uebOut("start personenErstellen", 2);

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		for (int i = 1; i <= MAX; i++)
		{
			PersonJPA5 p = new PersonJPA5(TestDatenTools.getVorname(), TestDatenTools.getNachname());
			p.setIntZahl(MyTools.getRandom());
			p.setLongZahl(MyTools.getRandom());
			p.setDatum(DateTimeTools.makeRandomDate(1960, 2017));
			p.setZeit(DateTimeTools.makeRandomTime());
			p.setZeitstempel(DateTimeTools.makeRandomTimestamp(2017));
			p.setGewicht((short) MyTools.getRandom(50, 90));
			em.persist(p);
		}

		em.getTransaction().commit();

		em.close();

		MyTools.untOut("stopp personenErstellen", 2);
	}


	public static void showPersonen()
	{
		MyTools.uebOut("start showPersonen", 2);

		EntityManager em = emf.createEntityManager();

		Query q = em.createQuery("select p from PersonJPA5 p order by p.vorName");

		List<?> result = q.getResultList();

		for (Object o : result)
		{
			System.out.println(o);
		}

		MyTools.untOut("stopp showPersonen", 2);
	}
}

