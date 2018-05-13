package ch.jmildner.jdbs_jpa.uebungen5;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.jmildner.tools.MyTools;
import ch.jmildner.tools.TestDatenTools;

public class TestJPA5b
{

    private static EntityManagerFactory emf;

    private static final int MAX = 100;

    public static void main(String[] args) throws Exception
    {
        MyTools.uebOut("start programm", 2);

        emf = Persistence.createEntityManagerFactory("H2");

        personenMasseninsert(MAX);

        // spielen Sie herum !!!
        showPersonen();
        showEinePerson(MAX / 2);

        emf.close();

        MyTools.untOut("stopp programm", 2);
    }

    private static void showEinePerson(int id)
    {
        MyTools.uebOut("start showEinePerson", 2);

        EntityManager em = emf.createEntityManager();

        Query q = em.createNativeQuery("select id, name, addr "
                + "from PersonJPA5nm where id=" + id);

        Object[] result = (Object[]) q.getSingleResult();

        for (Object a : result)
        {
            System.out.print(a + " ");
        }
        System.out.println();

        MyTools.untOut("stopp showEinePerson", 2);
    }

    private static void personenMasseninsert(final int MAX)
            throws Exception
    {
        MyTools.uebOut("start personenErstellen", 2);

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        {
            try
            {
                String sql = "drop table PersonJPA5nm";
                Query q = em.createNativeQuery(sql);
                q.executeUpdate();
                System.out.println("drop OK");
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                em.getTransaction().rollback();
                em.getTransaction().begin();
            }
        }

        {
            String sql = "create table PersonJPA5nm "
                    + "(id int primary key, "
                    + "name varchar(100), addr varchar(100))";
            Query q = em.createNativeQuery(sql);
            q.executeUpdate();
            System.out.println("create OK");
        }

        {
            String sql = "insert into PersonJPA5nm values(?,?,?)";

            Query q = em.createNativeQuery(sql);

            for (int i = 1; i <= MAX; i++)
            {
                q.setParameter(1, i);
                q.setParameter(2, TestDatenTools.getName());
                q.setParameter(3, TestDatenTools.getAdresse());

                q.executeUpdate();
            }
            System.out.println(MAX + " inserts OK");
        }

        em.getTransaction().commit();

        em.close();

        MyTools.untOut("stopp personenErstellen", 2);
    }

    public static void showPersonen()
    {
        MyTools.uebOut("start showPersonen", 2);

        EntityManager em = emf.createEntityManager();

        Query q = em.createNativeQuery(
                "select id, name, addr from PersonJPA5nm");

        List<?> result = q.getResultList();

        for (Object o : result)
        {
            for (Object a : (Object[]) o)
            {
                System.out.print(a + " ");
            }
            System.out.println();
        }

        System.out.println("\n\n\n");

        for (Object o : result)
        {
            Object[] arr = (Object[]) o;
            int id = (int) arr[0];
            String name = (String) arr[1];
            System.out.println(id + " " + name);

        }

        MyTools.untOut("stopp showPersonen", 2);
    }

}
