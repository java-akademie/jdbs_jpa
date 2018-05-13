
package ch.jmildner.jdbs_jpa.uebungen2;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PersonJPA22 implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private AdresseJPA22 adresse;

    private String name;

    public PersonJPA22()
    {
    }

    public PersonJPA22(String name)
    {
        this.name = name;
    }

    public AdresseJPA22 getAdresse()
    {
        return adresse;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setAdresse(AdresseJPA22 adresse)
    {
        this.adresse = adresse;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void show()
    {
        System.out.println(this);
    }

    @Override
    public String toString()
    {
        return "PersonJPA22 [id=" + id + ", name=" + name + ", adresse=" + adresse + "]";
    }
}
