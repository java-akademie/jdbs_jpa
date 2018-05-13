package ch.jmildner.jdbs_jpa.uebungen3;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.jmildner.tools.MyTools;

public class TestJPA32
{

    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("H2");

    ;



	public static void main(String[] args) throws Exception
    {
        MyTools.uebOut("start programm", 2);

        verlageUndBuecherErstellen();

        zeigeVerlage();
        zeigeBuecher();

        MyTools.untOut("stopp programm", 2);
    }

    private static void verlageUndBuecherErstellen() throws Exception
    {
        MyTools.uebOut("start verlageUndBuecherErstellen", 2);

        EntityManager em = EMF.createEntityManager();

        em.getTransaction().begin();

        /**
         * erstelle Verlage
         */
        VerlagJPA32 v1 = new VerlagJPA32("hanser verlag");
        VerlagJPA32 v2 = new VerlagJPA32("rororo");
        VerlagJPA32 v3 = new VerlagJPA32("das jugendbuch");

        em.persist(v1);
        em.persist(v2);
        em.persist(v3);

        /**
         * erstelleBuecher
         */
        buchErstellen(em, v1, "html und css");
        buchErstellen(em, v1, "servlets und jsp");
        buchErstellen(em, v1, "java server faces");
        buchErstellen(em, v1, "php und mysql");
        buchErstellen(em, v1, "informatik");
        buchErstellen(em, v1, "lerne java in 7 tagen");
        buchErstellen(em, v1, "java enterprise edition");
        buchErstellen(em, v1, "c sharp");
        buchErstellen(em, v1, "internteprogrammierung");

        buchErstellen(em, v2, "und jimmy ging zum regenbogen");
        buchErstellen(em, v2, "der besuch der alten dame");
        buchErstellen(em, v2, "die buddenbrooks");
        buchErstellen(em, v2, "der medicus");

        buchErstellen(em, v3, "winnetou");
        buchErstellen(em, v3, "abenteuer am amazonas");
        buchErstellen(em, v3, "pippi langstrumpf");

        em.getTransaction().commit();

        em.close();

        MyTools.untOut("stopp verlageUndBuecherErstellen", 2);
    }

    private static void buchErstellen(EntityManager em, VerlagJPA32 verlag, String titel)
    {
        BuchJPA32 buch = new BuchJPA32(titel);
        em.persist(buch);
        buch.setVerlag(verlag);
    }

    private static void zeigeVerlage()
    {
        MyTools.uebOut("start zeigeVerlage", 2);
        /**
         * den Cache entleeren
         */
        EMF.getCache().evictAll();

        EntityManager em = EMF.createEntityManager();

        Query q = em.createQuery("select v from VerlagJPA32 v order by v.id");

        List<?> verlage = q.getResultList();

        verlage.forEach((o) ->
        {
            ((VerlagJPA32) o).show();
        });

        MyTools.untOut("stopp zeigeVerlage", 2);
    }

    private static void zeigeBuecher()
    {
        MyTools.uebOut("start zeigeBuecher", 2);

        /**
         * den Cache entleeren
         */
        EMF.getCache().evictAll();

        EntityManager em = EMF.createEntityManager();

        Query q = em.createQuery("select b from BuchJPA32 b order by b.titel");

        List<?> buecher = q.getResultList();

        buecher.forEach((o) ->
        {
            ((BuchJPA32) o).show();
        });

        MyTools.untOut("stopp zeigeBuecher", 2);
    }

}
