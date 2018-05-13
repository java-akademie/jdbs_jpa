package ch.jmildner.jdbs_jpa.uebungen1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class PersonJPA1 implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private String name;

    private short shortZahl;

    private int pintZahl;
    private long plongZahl;

    private Integer wintZahl;
    private Long wlongZahl;

    private Date datum;
    private Time zeit;
    private Timestamp zeitstempel;

    private BigInteger wert;
    private BigDecimal saldo;

    public PersonJPA1()
    {
    }

    public PersonJPA1(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public PersonJPA1(String name)
    {
        this.name = name;
    }

    public Date getDatum()
    {
        return datum;
    }

    public Long getId()
    {
        return id;
    }

    public int getIntZahl()
    {
        return pintZahl;
    }

    public long getLongZahl()
    {
        return plongZahl;
    }

    public String getName()
    {
        return name;
    }

    public BigDecimal getSaldo()
    {
        return saldo;
    }

    public short getShortZahl()
    {
        return shortZahl;
    }

    public BigInteger getWert()
    {
        return wert;
    }

    public Time getZeit()
    {
        return zeit;
    }

    public Timestamp getZeitstempel()
    {
        return zeitstempel;
    }

    public void setDatum(Date datum)
    {
        this.datum = datum;
    }

    public void setIntZahl(int intZahl)
    {
        this.pintZahl = intZahl;
    }

    public void setLongZahl(long longZahl)
    {
        this.plongZahl = longZahl;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSaldo(BigDecimal saldo)
    {
        this.saldo = saldo;
    }

    public void setShortZahl(short shortZahl)
    {
        this.shortZahl = shortZahl;
    }

    public void setWert(BigInteger wert)
    {
        this.wert = wert;
    }

    public void setZeit(Time zeit)
    {
        this.zeit = zeit;
    }

    public void setZeitstempel(Timestamp zeitstempel)
    {
        this.zeitstempel = zeitstempel;
    }

    public void show()
    {
        System.out.println(this);
    }

    public static void showHeader()
    {
        System.out.printf(
                "%5s %4s  %5s %13s %13s %13s %13s    %-11s %-9s %-24s %-20s %n",
                "ID", "VERS", "SHORT", "P_INT", "P_LONG", "W_INT",
                "W_LONG", "DATUM", "ZEIT", "ZEITSTEMPEL", "NAME");
    }

    @Override
    public String toString()
    {
        return String.format(
                "%5d %4d  %5d %13d %13d %13d %13d    %-11s %-9s %-24s %-20s",
                id, version, shortZahl, pintZahl, plongZahl, wintZahl,
                wlongZahl, datum, zeit, zeitstempel, name);
    }
}
