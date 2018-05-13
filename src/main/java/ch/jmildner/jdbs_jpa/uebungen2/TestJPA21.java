package ch.jmildner.jdbs_jpa.uebungen2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.jmildner.tools.MyTools;

public class TestJPA21
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
                AdresseJPA21 a = new AdresseJPA21("wien");
                PersonJPA21 p = new PersonJPA21("hugo");
                p.setAdresse(a);

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

        zeigeAdressenJPA21();
        zeigePersonenJPA21();
    }

    private static void zeigePersonenJPA21()
    {
        MyTools.uebOut("Personen", 2);

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select p from PersonJPA21 p order by p.id");

        List<?> personen = q.getResultList();

        personen.forEach((o) ->
        {
            ((PersonJPA21) o).show();
        });
    }

    private static void zeigeAdressenJPA21()
    {
        MyTools.uebOut("AdressenJPA21", 2);

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select p from AdresseJPA21 p order by p.id");

        List<?> adressen = q.getResultList();

        adressen.forEach((o) ->
        {
            ((AdresseJPA21) o).show();
        });
    }
}
