package ch.jmildner.jdbs_jpa.uebungen8;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "CUSTOMER")
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String ort;

	/**
	 * bi-directional many-to-one association to Item
	 */
	@OneToMany(mappedBy = "seller")
	private List<Item> offers = new ArrayList<Item>();

	/**
	 * bi-directional many-to-one association to Item
	 */
	@OneToMany(mappedBy = "buyer")
	private List<Item> purchases = new ArrayList<Item>();


	public Customer()
	{
	}


	public Long getId()
	{
		return id;
	}


	public String getName()
	{
		return name;
	}



	public List<Item> getOffers()
	{
		return this.offers;
	}



	public String getOrt()
	{
		return ort;
	}


	public List<Item> getPurchases()
	{
		return this.purchases;
	}


	public Item removeOffer(Item offer)
	{
		getOffers().remove(offer);
		offer.setSeller(null);
		return offer;
	}


	public Item removePurchase(Item purchase)
	{
		getPurchases().remove(purchase);
		purchase.setBuyer(null);
		return purchase;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public void setOffer(Item offer)
	{
		offers.add(offer);
	}


	public void setOffers(List<Item> offers)
	{
		this.offers = offers;
	}



	public void setOrt(String ort)
	{
		this.ort = ort;
	}


	public void setPurchase(Item purchase)
	{
		purchases.add(purchase);
	}



	public void setPurchases(List<Item> purchases)
	{
		this.purchases = purchases;
	}



	@Override
	public String toString()
	{
		return String.format("Customer [id=%d   name=%-10s "
				+ "ort=%-15s    offers=%d purchases=%d]", id, name, ort,
				offers.size(), purchases.size());
	}


	public void show()
	{
		System.out.println(this);

		System.out.println("     OFFERS");
		if (this.getOffers().size() == 0)
		{
			System.out.println("     none");
		}
		else
		{
			for (Object o : this.getOffers())
			{
				System.out.println("     " + o);
			}
		}

		System.out.println("     PURCHASES");
		if (this.getPurchases().size() == 0)
		{
			System.out.println("     none");
		}
		else
		{
			for (Object o : this.getPurchases())
			{
				System.out.println("     " + o);
			}
		}
	}
}
