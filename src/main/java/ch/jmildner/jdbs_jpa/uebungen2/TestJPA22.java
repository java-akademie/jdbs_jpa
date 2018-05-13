package ch.jmildner.jdbs_jpa.uebungen2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.jmildner.tools.MyTools;

public class TestJPA22
{

    private static EntityManagerFactory emf;

    public static void main(String[] args)
    {
        try
        {
            emf = Persistence.createEntityManagerFactory("H2");
            test1();
            emf.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void test1() throws Exception
    {
        MyTools.uebOut("test1", 2);

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        {
            try
            {
                AdresseJPA22 a = new AdresseJPA22("wien");
                PersonJPA22 p = new PersonJPA22("hugo");
                p.setAdresse(a);
                a.setPerson(p);

                em.persist(a);
                em.persist(p);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        em.getTransaction().commit();

        em.close();

        zeigeAdressenJPA22();
        zeigePersonenJPA22();
    }

    private static void zeigePersonenJPA22()
    {
        MyTools.uebOut("PersonenJPA22", 2);

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select p from PersonJPA22 p order by p.id");

        List<?> personen = q.getResultList();

        for (Object o : personen)
        {
            ((PersonJPA22) o).show();
        }

    }

    private static void zeigeAdressenJPA22()
    {
        MyTools.uebOut("AdressenJPA22", 2);

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select p from AdresseJPA22 p order by p.id");

        List<?> personen = q.getResultList();

        for (Object o : personen)
        {
            AdresseJPA22 p = (AdresseJPA22) o;
            p.show();
        }

    }
}
