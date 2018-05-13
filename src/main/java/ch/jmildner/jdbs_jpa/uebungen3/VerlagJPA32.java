package ch.jmildner.jdbs_jpa.uebungen3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class VerlagJPA32
{
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	// INVERSE Seite
	@OneToMany(mappedBy = "verlag")
	@OrderBy("titel")
	List<BuchJPA32> buecher = new ArrayList<BuchJPA32>();


	public VerlagJPA32()
	{
	}


	public VerlagJPA32(String name)
	{
		this.name = name;
	}


	public Collection<BuchJPA32> getBuecher()
	{
		return buecher;
	}


	public Long getId()
	{
		return id;
	}


	public String getName()
	{
		return name;
	}


	public void setBuecher(List<BuchJPA32> buecher)
	{
		this.buecher = buecher;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public void show()
	{
		System.out.println(this);
		
		for (Object o : this.getBuecher())
		{
			System.out.println("    " + o);
		}
	}


	@Override
	public String toString()
	{
		return String.format("VerlagJPA32 [%3d    %-30s anzahlBuecher=%d]", id,name,buecher.size());
	}

}


