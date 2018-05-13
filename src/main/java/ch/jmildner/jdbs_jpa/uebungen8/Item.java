package ch.jmildner.jdbs_jpa.uebungen8;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "ITEM")
@NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i")
public class Item implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String title;
	private int preis;

	@Temporal(TemporalType.TIMESTAMP)
	private Date verkauft;

	/**
	 * bi-directional many-to-one association to Customer
	 */
	@ManyToOne
	private Customer seller;

	/**
	 * bi-directional many-to-one association to Customer
	 */
	@ManyToOne
	private Customer buyer;



	public Customer getBuyer()
	{
		return buyer;
	}



	public Long getId()
	{
		return id;
	}



	public int getPreis()
	{
		return preis;
	}



	public Customer getSeller()
	{
		return seller;
	}



	public String getTitle()
	{
		return title;
	}



	public Date getVerkauft()
	{
		return verkauft;
	}



	public void setBuyer(Customer buyer)
	{
		this.buyer = buyer;
		this.buyer.getPurchases().add(this);
	}



	public void setPreis(int preis)
	{
		this.preis = preis;
	}



	public void setSeller(Customer seller)
	{
		this.seller = seller;
		this.seller.getOffers().add(this);
	}



	public void setTitle(String title)
	{
		this.title = title;
	}



	public void setVerkauft(Date verkauft)
	{
		this.verkauft = verkauft;
	}



	@Override
	public String toString()
	{
		return String.format("Item [id=%2d title=%-20s preis=%3d  " + "verkauft=%s  seller=%s  buyer=%s]", id, title,
				preis, verkauft, (seller == null ? null : seller.getId()), (buyer == null ? null : buyer.getId()));
	}



}
