package ch.jmildner.jdbs_jpa.first;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestPerson2
{

    private static EntityManagerFactory EMF = null;

    public static void main(String[] args)
    {
        System.out.println("Start Person1Test");
        EMF = Persistence.createEntityManagerFactory("H2");
        test1();
        test2();
        EMF.close();
        System.out.println("Stopp TestPersonJPA1");
    }

    private static void test2()
    {
        EntityManager em = EMF.createEntityManager();
        em.getTransaction().begin();
        Person1 p1 = em.find(Person1.class, 1L);
        p1.setName("fritz");
        em.getTransaction().commit();
        showPersonen();
    }

    private static void test1()
    {
        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();

        Person1 p1 = new Person1("urs");
        Person1 p2 = new Person1("max");
        Person1 p3 = new Person1("erwin");

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);

        em.getTransaction().commit();

        showPersonen();

        // em.clear();
        p2.setName("karl");

        System.out.println(p2 + " IST" + (em.contains(p2) ? "" : " NICHT") + " GEMANAGED\n");

        em.getTransaction().begin();
        em.getTransaction().commit();

        showPersonen();
    }

    private static void showPersonen()
    {
        System.out.println("--------");
        System.out.println("personen");
        System.out.println("--------");

        EntityManager em = EMF.createEntityManager();

        Query q = em.createQuery("select p from Person1 p order by p.name");

        List<?> personen = q.getResultList();

        personen.forEach((o) ->
        {
            System.out.println(o);
        });

        System.out.println("--------\n");
    }
}
