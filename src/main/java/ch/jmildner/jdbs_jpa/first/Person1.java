package ch.jmildner.jdbs_jpa.first;

import java.io.Serializable;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person1 implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;
        


	public Person1()
	{
	}


	public Person1(String name)
	{
		this.name = name;
	}


	public Long getId()
	{
		return id;
	}


	public String getName()
	{
		return name;
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
		return "Person1 [id=" + id + ", name=" + name + "]";
	}
}
