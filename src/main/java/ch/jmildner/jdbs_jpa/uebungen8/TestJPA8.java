package ch.jmildner.jdbs_jpa.uebungen8;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ch.jmildner.tools.DateTimeTools;
import ch.jmildner.tools.MyTools;

public class TestJPA8
{
	private static EntityManagerFactory emf;


	public static void main(String[] args) throws Exception
	{
		MyTools.uebOut("start TestJPA8", 2);

		emf = Persistence.createEntityManagerFactory("H2");

		datenErstellen();

		showCustomer();
		showItem();

		innerJoins1();
		innerJoins2();

		outerJoins1();

		emf.close();

		MyTools.untOut("stopp programm", 2);
	}


	private static void outerJoins1() throws Exception
	{
		MyTools.uebOut("start outerJoins1", 2);

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		{
			String sql = "select c,i Customer from Customer c "
					+ "left outer join c.offers i";

			System.out.println("\n----- " + sql);

			Query q = em.createQuery(sql);

			List<?> result = q.getResultList();

			for (Object o : result)
			{
				Object[] oa=(Object[]) o;
				
				Customer c = (Customer) oa[0];
				System.out.println(c);
				
				Item i = (Item) oa[1];
				System.out.println(i);
			}
		}

		{
			String sql = "select c.name,i.title Customer from Customer c "
					+ "left outer join c.purchases i";

			System.out.println("\n----- " + sql);

			Query q = em.createQuery(sql);

			List<?> result = q.getResultList();

			for (Object o : result)
			{
				Object[] oa=(Object[]) o;
				System.out.println(oa[0]+"   "+oa[1]);
			}
		}


		em.getTransaction().commit();

		em.close();

		MyTools.untOut("stopp outerJoins1", 2);
	}

	private static void innerJoins2() throws Exception
	{
		MyTools.uebOut("start innerJoins2", 2);

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		{
			String sql = "select c,i Customer from Customer c "
					+ "inner join c.offers i";

			System.out.println("\n----- " + sql);

			Query q = em.createQuery(sql);

			List<?> result = q.getResultList();

			for (Object o : result)
			{
				Object[] oa=(Object[]) o;
				
				Customer c = (Customer) oa[0];
				System.out.println(c);
				
				Item i = (Item) oa[1];
				System.out.println(i);
			}
		}

		{
			String sql = "select c.name,i.title Customer from Customer c "
					+ "inner join c.offers i";

			System.out.println("\n----- " + sql);

			Query q = em.createQuery(sql);

			List<?> result = q.getResultList();

			for (Object o : result)
			{
				Object[] oa=(Object[]) o;
				System.out.println(oa[0]+"   "+oa[1]);
			}
		}


		em.getTransaction().commit();

		em.close();

		MyTools.untOut("stopp innerJoins2", 2);
	}


	private static void innerJoins1() throws Exception
	{
		MyTools.uebOut("start innerJoins1", 2);

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		{
			String sql = "select c Customer from Customer c "
					+ "inner join c.offers i";

			System.out.println("\n----- " + sql);

			Query q = em.createQuery(sql);

			List<?> result = q.getResultList();

			for (Object o : result)
			{
				Customer c = (Customer) o;

				System.out.println(c);
			}
		}


		{
			String sql = "select c Customer from Customer c "
					+ "inner join c.purchases i";

			System.out.println("\n----- " + sql);

			Query q = em.createQuery(sql);

			List<?> result = q.getResultList();

			for (Object o : result)
			{
				Customer c = (Customer) o;

				System.out.println(c);
			}
		}

		em.getTransaction().commit();

		em.close();

		MyTools.untOut("stopp innerJoins1", 2);
	}


	private static void datenErstellen() throws Exception
	{
		MyTools.uebOut("start customerErstellen", 2);

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();


		Customer c1 = makeCustomer(em, "fritz", "berlin");
		Customer c2 = makeCustomer(em, "sepp", "straubing");
		Customer c3 = makeCustomer(em, "urs", "basel");

		Item i1 = makeItem(em, "stehlampe", 122);
		Item i2 = makeItem(em, "stuhl", 43);
		Item i3 = makeItem(em, "tisch", 53);
		Item i4 = makeItem(em, "fueller", 112);
		Item i5 = makeItem(em, "luftbefeuchter", 93);
		Item i6 = makeItem(em, "fahrrad", 720);
		Item i7 = makeItem(em, "briefoeffner", 17);

		i1.setSeller(c1);
		i2.setSeller(c2);
		i3.setSeller(c1);
		i4.setSeller(c2);
		i5.setSeller(c2);
		i6.setSeller(c1);
		i7.setSeller(c1);

		i7.setVerkauft(DateTimeTools.getCurrentTimestamp());
		i7.setBuyer(c3);


		em.getTransaction().commit();

		em.close();

		MyTools.untOut("stopp customerErstellen", 2);
	}


	private static Item makeItem(EntityManager em, String titel, int preis)
	{
		Item item = new Item();
		item.setTitle(titel);
		item.setPreis(preis);
		em.persist(item);
		return item;
	}


	private static Customer makeCustomer(EntityManager em, String name, String ort)
	{
		Customer customer = new Customer();
		customer.setName(name);
		customer.setOrt(ort);
		em.persist(customer);
		return customer;
	}



	private static void showItem(List<Item> resultList)
	{
		for (Item item : resultList)
		{
			System.out.println(item);
		}
	}


	public static void showItem()
	{
		MyTools.uebOut("start showItem", 2);

		EntityManager em = emf.createEntityManager();

		TypedQuery<Item> typedQuery = em.createQuery("from Item c", Item.class);

		showItem(typedQuery.getResultList());

		MyTools.untOut("stopp showItem", 2);
	}


	private static void showCustomer(List<Customer> resultList)
	{
		for (Customer customer : resultList)
		{
			customer.show();
		}
	}


	public static void showCustomer()
	{
		MyTools.uebOut("start showCustomer", 2);

		EntityManager em = emf.createEntityManager();

		TypedQuery<Customer> typedQuery = em.createQuery("from Customer c", Customer.class);

		showCustomer(typedQuery.getResultList());

		MyTools.untOut("stopp showCustomer", 2);
	}

}


