package ch.jmildner.jdbs_jpa.uebungen99;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PersonJPA99
{
	@Id
	@GeneratedValue
	private Long id;

	private String name;


	// OWNER Seite
	@ManyToOne
	private Geschlecht geschlecht;


	public Geschlecht getGeschlecht()
	{
		return geschlecht;
	}


	public Long getId()
	{
		return id;
	}


	public String getName()
	{
		return name;
	}


	public void setGeschlecht(Geschlecht geschlecht)
	{
		this.geschlecht = geschlecht;
		this.geschlecht.getPersonen().add(this);
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
		return "PersonJPA99 [id=" + id + ", name=" + name + ", geschlecht=" + geschlecht + "]";
	}

}


