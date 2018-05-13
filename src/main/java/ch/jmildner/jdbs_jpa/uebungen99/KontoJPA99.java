package ch.jmildner.jdbs_jpa.uebungen99;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ch.jmildner.tools.BigDecimalTools;


@Entity
public class KontoJPA99
{
	@Id
	@GeneratedValue
	private Long id;

	private String bezeichnung;

	@Column(nullable = true, precision = 9, scale = 2)
	private BigDecimal saldo;

	@Column(nullable = true, precision = 9, scale = 2)
	private BigDecimal wert;



	public BigDecimal getWert()
	{
		return wert;
	}



	public void setWert(BigDecimal wert)
	{
		this.wert = wert;
	}



	@Override
	public String toString()
	{
		return "KontoJPA99 [id=" + String.format("%3d", id)
				+ ", bezeichnung=" + String.format("%10s", bezeichnung)
				+ ", saldo=" + BigDecimalTools.format(saldo, 15)
				+ ", wert=" + BigDecimalTools.format(wert, 15) + "]";
	}



	public String getBezeichnung()
	{
		return bezeichnung;
	}



	public void setBezeichnung(String bezeichnung)
	{
		this.bezeichnung = bezeichnung;
	}



	public BigDecimal getSaldo()
	{
		return saldo;
	}



	public void setSaldo(BigDecimal saldo)
	{
		this.saldo = saldo;
	}



	public Long getId()
	{
		return id;
	}



	public void show()
	{
		System.out.println(this);
	}



}


