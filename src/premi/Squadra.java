package premi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import premi.Stadio;

public class Squadra implements Comparable<Squadra>
{
	private String nome;
	private float crediti;
	private Map<String, Float> premi;
	private Map<String, Stadio> stadi;
	
	public Squadra(String nome)
	{
		this.nome = nome;
		this.crediti = 0;
		this.premi = new HashMap<>(); 
		Stadio s = new Stadio(Stadio.PRIMASQUADRA);
		Stadio ss = new Stadio(Stadio.RISERVE);
		this.stadi = new HashMap<>(); 
		this.stadi.put(Stadio.PRIMASQUADRA, s);
		this.stadi.put(Stadio.RISERVE, ss);
	}
	
	public String toString()
	{
		String s = " (";
		for(Entry<String, Float> e: premi.entrySet())
		{
			s += (e.getKey() + " +" +e.getValue() + ", ");
		}
		int len;
		char[] c;
		if(premi.size()!=0)
		{
			len = s.length();
			c = s.toCharArray();
			c[len-2] = ')';
			c[len-1] = '.';
		}
		else
		{
			String t = " (12o posto coppa di Lega).";
			c = t.toCharArray();
			len = t.length();
		}
		
		return this.nome + " +" + this.crediti + String.copyValueOf(c, 0, len);
	}
	
	public void aggiungiCrediti(float crediti, String descrizione)
	{
		this.premi.put(descrizione, crediti); 
		this.crediti+=crediti;
	}

	@Override
	public int compareTo(Squadra o) 
	{
		float diff = this.crediti - o.crediti;
		if(diff>=0)
			return -1;
		else
			return 1;
	}

	public boolean haStadio(String tipo) 
	{
		Stadio s = this.stadi.get(tipo);
		return s.isPresente();
	}

	public float getPremioStadio(String tipo) 
	{
		Stadio s = this.stadi.get(tipo);
		return s.getPremio();
	}
	
	public void setStadio(String tipo, float premio)
	{
		Stadio s = this.stadi.get(tipo);
		s.setPresenza(true);
		s.setPremio(premio);
	}
}
