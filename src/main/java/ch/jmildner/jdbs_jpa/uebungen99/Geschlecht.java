package ch.jmildner.jdbs_jpa.uebungen99;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Geschlecht
{
	@Id
	@GeneratedValue
	private Long id;

	private String geschlecht;

	// INVERSE Seite
	@OneToMany(mappedBy = "geschlecht", fetch = FetchType.LAZY)
	@OrderBy("name")
	List<PersonJPA99> personen = new ArrayList<PersonJPA99>();


	public Geschlecht()
	{
	}


	public Geschlecht(String geschlecht)
	{
		this.geschlecht = geschlecht;
	}


	public Long getId()
	{
		return id;
	}



	public String getGeschlecht()
	{
		return geschlecht;
	}



	public List<PersonJPA99> getPersonen()
	{
		return personen;
	}



	public void setGeschlecht(String geschlecht)
	{
		this.geschlecht = geschlecht;
	}



	public void show()
	{
		System.out.println(this);

		// for (Object o : this.personen)
		// {
		// System.out.println(" " + o);
		// }
	}



	@Override
	public String toString()
	{
		return "Geschlecht [id=" + id + ", geschlecht=" + geschlecht + ", personen=" + personen.size() + "]";
	}


}


