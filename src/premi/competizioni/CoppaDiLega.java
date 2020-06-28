package premi.competizioni;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import premi.Squadra;

public class CoppaDiLega extends Competizione
{
	private Map<Integer, Float> premi;
	public CoppaDiLega()
	{
		this.nomeFile = "CoppaDiLega";
		this.premi = new HashMap<>(); 
		this.premi.put(1, 25f);
		this.premi.put(2, 20f);
		this.premi.put(3, 15f);
		this.premi.put(4, 10f);
		this.premi.put(5, 8f);
		this.premi.put(6, 6f);
		this.premi.put(7, 5f);
		this.premi.put(8, 4f);
		this.premi.put(9, 3f);
		this.premi.put(10, 2f);
		this.premi.put(11, 1f);
		this.premi.put(12, 0f);
	}; 
	
	@Override
	public void carica(Map<String, Squadra> squadre, List<String> l)
	{
		float prec = -1;
		int pos = 1;
		int posPrec = pos;
		for(String s: l)
		{
			if(pos<=squadre.size())
			{
				if(Float.parseFloat(((s.split(REGEX))[3]))!=prec)
				{
					squadre.get((s.split(REGEX))[2]).aggiungiCrediti(this.premi.get(pos), pos + "o posto coppa di Lega");
					posPrec = pos;
				}
				else
					squadre.get((s.split(REGEX))[2]).aggiungiCrediti(this.premi.get(posPrec), posPrec + "o posto coppa di Lega");
					
				pos++;
				prec = Float.parseFloat(((s.split(REGEX))[3]));
			}
			else
			{
				pos = 1;
			}
		}
	}
}

