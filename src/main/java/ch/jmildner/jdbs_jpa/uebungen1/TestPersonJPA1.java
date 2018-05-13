package ch.jmildner.jdbs_jpa.uebungen1;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.jmildner.tools.DateTimeTools;
import ch.jmildner.tools.MyTools;

public class TestPersonJPA1
{

    private static final String DB = "H2";

    private static EntityManagerFactory EMF = null;

    public static void main(String[] args) throws Exception
    {
        System.out.println("Start TestPersonJPA1");
        EMF = Persistence.createEntityManagerFactory(DB);
        test1();
        test2();
        EMF.close();
        System.out.println("\n\nStopp TestPersonJPA1");
    }

    private static void test2() throws Exception
    {
        MyTools.uebOut("start test2", 2);

        EntityManager em = EMF.createEntityManager();

        PersonJPA1 p1 = em.find(PersonJPA1.class, 1L);
        PersonJPA1 p2 = em.find(PersonJPA1.class, 2L);
        PersonJPA1 p3 = em.find(PersonJPA1.class, 3L);

        {
            em.getTransaction().begin();

            p1.setDatum(DateTimeTools.getCurrentDate());
            p1.setZeit(DateTimeTools.getCurrentTime());
            p1.setZeitstempel(DateTimeTools.getCurrentTimestamp());

            analyse(p1);

            p2.setDatum(DateTimeTools.makeDate(1988, 12, 24));
            p2.setZeit(DateTimeTools.makeTime(7, 50, 30));
            p2.setZeitstempel(DateTimeTools.makeTimestamp(1988, 12, 24,
                    6, 30, 15, (int) (0.857985 * 1000000))); // 15.85..sec

            analyse(p2);

            p3.setShortZahl((short) 55);
            p3.setIntZahl(1000000000);
            p3.setLongZahl(MyTools.getRandom());

            em.getTransaction().commit();
        }

        showPersonen();

        {
            em.getTransaction().begin();

            em.remove(p2);

            em.getTransaction().commit();
        }

        showPersonen();

        MyTools.untOut("stopp test2", 2);
    }

    private static void analyse(PersonJPA1 p)
    {
        Date d = p.getDatum();
        Time t = p.getZeit();
        Timestamp ts = p.getZeitstempel();

        System.out.println(ts);
        analyse(d);
        analyse(t);
    }

    private static void analyse(Time t)
    {
        Timestamp ts = new Timestamp(t.getTime());
        System.out.println("Time: " + t + " Timestamp: " + ts);

    }

    private static void analyse(Date d)
    {
        Timestamp ts = new Timestamp(d.getTime());
        System.out.println("Date: " + d + " Timestamp: " + ts);

    }

    private static void test1()
    {
        MyTools.uebOut("start test1", 2);
        EntityManager em = EMF.createEntityManager();

        PersonJPA1 p1 = new PersonJPA1("urs");
        PersonJPA1 p2 = new PersonJPA1("max");
        PersonJPA1 p3 = new PersonJPA1("erich");

        em.getTransaction().begin();

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);

        em.getTransaction().commit();

        showPersonen();

        // em.clear();
        System.out.println(p2 + " IST" + (em.contains(p2) ? "" : " NICHT") + " GEMANAGED\n");

        p2.setName("karl");

        em.getTransaction().begin();
        em.getTransaction().commit();

        showPersonen();

        MyTools.untOut("stopp test1", 2);
    }

    private static void showPersonen()
    {
        MyTools.uebOut("Personen", 2);

        EntityManager em = EMF.createEntityManager();

        Query q = em.createQuery("select p from PersonJPA1 p order by p.id");

        List<?> personen = q.getResultList();

        PersonJPA1.showHeader();

        personen.forEach((o) ->
        {
            ((PersonJPA1) o).show();
        });
    }
}
