package ch.jmildner.jdbs_jpa.uebungen7;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import ch.jmildner.tools.MyTools;
import ch.jmildner.tools.TestDatenTools;

public class TestJPA7
{

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception
    {
        MyTools.uebOut("start programm", 2);

        emf = Persistence.createEntityManagerFactory("H2");

        personenMasseninsert(100);

        test1();
        test2();
        test3();
        test4();

        showPersonen();

        emf.close();

        MyTools.untOut("stopp programm", 2);
    }

    private static void test4()
    {
        MyTools.uebOut("start test4", 2);

        EntityManager em = emf.createEntityManager();

        {
            String sql = "select 'counts'" + ",concat('personen: ',count(p))" + ",concat('name: ',count(p.name))"
                    + ",concat('distinct name: ',count(distinct p.name))" + ",concat('kz: ',count(p.kz))"
                    + ",concat('distinct kz: ',count(distinct p.kz))" + " from PersonJPA7 p";

            System.out.println("\n----- " + sql);

            Query q = em.createQuery(sql);

            List<?> rl = q.getResultList();

            for (Object arrayTupel : rl)
            {
                Object[] arrayAttribute = (Object[]) arrayTupel;

                for (Object attribut : arrayAttribute)
                {
                    System.out.print(attribut + "   ");
                }
                System.out.println();
            }
        }

        em.close();

        MyTools.untOut("stopp test4", 2);
    }

    private static void test3()
    {
        MyTools.uebOut("start test3", 2);

        EntityManager em = emf.createEntityManager();

        {
            String sql = "select  p.kz, p.name  from PersonJPA7 p";

            System.out.println("\n----- " + sql);

            Query q = em.createQuery(sql);

            List<?> rl = q.getResultList();

            for (Object arrayTupel : rl)
            {
                Object[] arrayAttribute = (Object[]) arrayTupel;

                for (Object attribut : arrayAttribute)
                {
                    System.out.print(attribut + "/");
                }
                System.out.println();
            }
        }

        {
            String sql = "select distinct p.name from PersonJPA7 p";

            System.out.println("\n----- " + sql);

            Query q = em.createQuery(sql);

            List<?> rl = q.getResultList();

            rl.forEach((attribut) ->
            {
                System.out.println(attribut);
            });
        }

        em.close();

        MyTools.untOut("stopp test3", 2);
    }

    private static void test2()
    {
        MyTools.uebOut("start test2", 2);

        EntityManager em = emf.createEntityManager();

        {
            String sql = "select p.id, p.name from PersonJPA7 p "
                    + "where p.name='uniqueName'";

            System.out.println("\n----- " + sql);

            Query q = em.createQuery(sql);

            Object[] arrayAttribute = (Object[]) q.getSingleResult();

            for (Object attribut : arrayAttribute)
            {
                System.out.print(attribut + "/");
            }

            System.out.println();
        }

        {
            String sql = "select p.id, p.name from PersonJPA7 p "
                    + "where p.name='gruber'";

            System.out.println("\n----- " + sql);

            Query q = em.createQuery(sql);

            List<?> rl = q.getResultList();

            for (Object arrayTupel : rl)
            {
                Object[] arrayAttribute = (Object[]) arrayTupel;

                for (Object attribut : arrayAttribute)
                {
                    System.out.print(attribut + "/");
                }
                System.out.println();
            }
        }

        em.close();

        MyTools.untOut("stopp test2", 2);
    }

    private static void test1()
    {
        MyTools.uebOut("start test1", 2);

        EntityManager em = emf.createEntityManager();

        {
            String sql = "select p from PersonJPA7 p "
                    + "where p.name='uniqueName'";

            System.out.println("\n----- " + sql);

            Query q = em.createQuery(sql);

            PersonJPA7 p = (PersonJPA7) q.getSingleResult();

            p.show();
        }

        {
            String sql = "select p from PersonJPA7 p "
                    + "where p.name='gruber'";

            System.out.println("\n----- " + sql);

            Query q = em.createQuery(sql);

            List<?> resultList = q.getResultList();

            for (Object o : resultList)
            {
                PersonJPA7 p = (PersonJPA7) o;
                p.show();
            }
        }

        em.close();

        MyTools.untOut("stopp test1", 2);
    }

    private static void personenMasseninsert(final int MAX) throws Exception
    {
        MyTools.uebOut("start personenErstellen", 2);

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        int zid = MAX / 2;

        for (int i = 1; i <= MAX; i++)
        {
            PersonJPA7 p = new PersonJPA7(i == zid ? "uniqueName" : TestDatenTools.getNachname());

            int zkz = i % 4;
            if (zkz != 0)
            {
                p.setKz(i % 4);
            }

            em.persist(p);

            AdresseJPA7 a = new AdresseJPA7(TestDatenTools.getOrt());

            em.persist(a);

            p.setAddr(a);

        }

        em.getTransaction().commit();

        em.close();

        System.out.println(MAX + " Personen erstellt");

        MyTools.untOut("stopp personenErstellen", 2);
    }

    private static void show(List<PersonJPA7> resultList)
    {
        for (PersonJPA7 person : resultList)
        {
            person.show();
            person.setKz(987);
        }
    }

    static void showPersonen()
    {
        MyTools.uebOut("start showPersonen", 2);

        EntityManager em = emf.createEntityManager();

        // em.getTransaction().begin();
        TypedQuery<PersonJPA7> typedQuery = em.createQuery("select p from PersonJPA7 p", PersonJPA7.class);

        show(typedQuery.getResultList());

        // em.getTransaction().commit();
        MyTools.untOut("stopp showPersonen", 2);
    }
}
