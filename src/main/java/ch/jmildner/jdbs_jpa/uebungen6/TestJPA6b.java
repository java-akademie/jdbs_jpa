package ch.jmildner.jdbs_jpa.uebungen6;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ch.jmildner.tools.DateTimeTools;
import ch.jmildner.tools.MyTools;
import ch.jmildner.tools.TestDatenTools;

public class TestJPA6b
{

    private static EntityManagerFactory emf;

    private static final int MAX = 100;

    public static void main(String[] args) throws Exception
    {
        MyTools.uebOut("start programm", 2);

        emf = Persistence.createEntityManagerFactory("H2");

        personenMasseninsert(MAX);

        criteriaTest1();

        showPersonenOhneOrderBy();
        showPersonenMitOrderBy();

        emf.close();

        MyTools.untOut("stopp programm", 2);
    }

    private static void criteriaTest1()
    {
        MyTools.uebOut("start criteriaTest1", 2);

        EntityManager em = emf.createEntityManager();

        {
            System.out.println("vorName=johann");

            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<PersonJPA6> cq = cb.createQuery(PersonJPA6.class);

            Root<PersonJPA6> root = cq.from(PersonJPA6.class);

            Path<String> vorName = root.get(PersonJPA6_.vorName);

            Predicate p = cb.equal(vorName, "johann");

            cq.select(root).where(p);

            TypedQuery<PersonJPA6> q = em.createQuery(cq);

            show(q.getResultList());
        }

        {
            System.out.println("vorName=johann oder nachName=vogel");

            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<PersonJPA6> cq = cb.createQuery(PersonJPA6.class);

            Root<PersonJPA6> root = cq.from(PersonJPA6.class);

            Path<String> vorName = root.get(PersonJPA6_.vorName);
            Path<String> nachName = root.get(PersonJPA6_.nachName);

            Predicate p1 = cb.equal(vorName, "johann");
            Predicate p2 = cb.equal(nachName, "vogel");
            Predicate p3 = cb.or(p1, p2);
            cq.select(root).where(p3);

            TypedQuery<PersonJPA6> q = em.createQuery(cq);

            show(q.getResultList());
        }

        {
            System.out.println("vorName=johann oder nachName=vogel gewicht > 55 longZahl > 50");

            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<PersonJPA6> cq = cb.createQuery(PersonJPA6.class);

            Root<PersonJPA6> root = cq.from(PersonJPA6.class);

            Predicate pName = cb.or(cb.equal(root.get(PersonJPA6_.vorName), "johann"),
                    cb.equal(root.get(PersonJPA6_.nachName), "vogel"));
            Predicate pGewicht = cb.gt(root.get(PersonJPA6_.gewicht), 55);
            Predicate pZahl = cb.gt(root.get(PersonJPA6_.longZahl), 50);
            Predicate p = cb.and(pName, pGewicht, pZahl);
            cq.select(root).where(p);

            TypedQuery<PersonJPA6> q = em.createQuery(cq);

            show(q.getResultList());
        }

        em.close();

        MyTools.untOut("stopp criteriaTest1", 2);
    }

    private static void show(List<PersonJPA6> resultList)
    {
        resultList.forEach((person) ->
        {
            person.show();
        });
    }

    private static void personenMasseninsert(final int MAX) throws Exception
    {
        MyTools.uebOut("start personenErstellen", 2);

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        for (int i = 1; i <= MAX; i++)
        {
            PersonJPA6 p = new PersonJPA6(TestDatenTools.getVorname(), TestDatenTools.getNachname());
            p.setIntZahl(MyTools.getRandom(1, 10));
            p.setLongZahl(MyTools.getRandom(1, 100));
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

    private static void showPersonenOhneOrderBy()
    {
        MyTools.uebOut("start showPersonenOhneOrderBy", 2);

        EntityManager em = emf.createEntityManager();

        {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<PersonJPA6> cq = cb.createQuery(PersonJPA6.class);

            Root<PersonJPA6> root = cq.from(PersonJPA6.class);

            cq.select(root);

            TypedQuery<PersonJPA6> q = em.createQuery(cq);

            show(q.getResultList());
        }

        em.close();

        MyTools.untOut("stopp showPersonenOhneOrderBy", 2);
    }

    private static void showPersonenMitOrderBy()
    {
        MyTools.uebOut("start showPersonenMitOrderBy", 2);

        EntityManager em = emf.createEntityManager();

        {
            CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<PersonJPA6> cq = cb.createQuery(PersonJPA6.class);

            Root<PersonJPA6> root = cq.from(PersonJPA6.class);

            cq.select(root);
            cq.orderBy(cb.asc(root.get(PersonJPA6_.nachName)), cb.asc(root.get(PersonJPA6_.vorName)),
                    cb.desc(root.get(PersonJPA6_.id)));

            TypedQuery<PersonJPA6> tq = em.createQuery(cq);

            show(tq.getResultList());
        }

        em.close();

        MyTools.untOut("stopp showPersonenMitOrderBy", 2);
    }
}
