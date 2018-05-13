package ch.jmildner.jdbs_jpa.uebungen7;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AdresseJPA7 implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String ort;


	public AdresseJPA7()
	{
	}


	public AdresseJPA7(String ort)
	{
		this.ort = ort;
	}


	public Long getId()
	{
		return id;
	}


	public String getOrt()
	{
		return ort;
	}


	public void setOrt(String ort)
	{
		this.ort = ort;
	}


	public void show()
	{
		System.out.println(this);
	}


	@Override
	public String toString()
	{
		return "AdresseJPA7 [id=" + id + ", ort=" + ort + "]";
	}
	
}
