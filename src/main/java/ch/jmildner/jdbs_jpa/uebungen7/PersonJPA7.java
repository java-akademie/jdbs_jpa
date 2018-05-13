
package ch.jmildner.jdbs_jpa.uebungen7;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PersonJPA7 implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private Integer kz;
	
	@OneToOne(fetch = FetchType.LAZY)
	private AdresseJPA7 addr;


	public PersonJPA7()
	{
	}


	public PersonJPA7(String name)
	{
		this.name = name;
	}


	public AdresseJPA7 getAddr()
	{
		return addr;
	}


	public Long getId()
	{
		return id;
	}


	public int getKz()
	{
		return kz;
	}


	public String getName()
	{
		return name;
	}


	public void setAddr(AdresseJPA7 addr)
	{
		this.addr = addr;
	}


	public void setKz(int kz)
	{
		this.kz = kz;
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
		return "PersonJPA7 [id=" + id + ", name=" + name + ", kz=" + kz + ", addr=" + addr + "]";
	}
}
