package premi.competizioni;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import premi.Squadra;
import premi.Stadio;

public class CampionatoRiserve extends Competizione
{
	private Map<Integer, Float> premi;
	
	public CampionatoRiserve()
	{
		this.nomeFile = "CampionatoRiserve";
		this.nome = "Campionato Riserve"; 
		this.premi = new HashMap<>(); 
		this.premi.put(1, 10f);
		this.premi.put(2, 5f);
		this.premi.put(3, 2f);
	}; 
	
	@Override
	public void carica(Map<String, Squadra> squadre, List<String> l)
	{
		int line = 1;
		float prec = -1;
		int pos = 1;
		int posPrec = pos;
		for(String s: l)
		{
			if(line<=4)
			{
				if(Integer.parseInt((((s.split(REGEX))[4].split("-"))[0]))>Integer.parseInt((((s.split(REGEX))[4].split("-"))[1])))
					squadre.get((s.split(REGEX))[0]).aggiungiCrediti(3f, "vittoria campionato Riserve");
				if(Integer.parseInt((((s.split(REGEX))[4].split("-"))[0]))<Integer.parseInt((((s.split(REGEX))[4].split("-"))[1])))
					squadre.get((s.split(REGEX))[3]).aggiungiCrediti(3f, "vittoria campionato Riserve");
				if(Integer.parseInt((((s.split(REGEX))[4].split("-"))[0]))==Integer.parseInt((((s.split(REGEX))[4].split("-"))[1])))
				{
					squadre.get((s.split(REGEX))[0]).aggiungiCrediti(1f, "pareggio campionato Riserve");
					squadre.get((s.split(REGEX))[3]).aggiungiCrediti(1f, "pareggio campionato Riserve");
				}
				line++;
				if(squadre.get((s.split(REGEX))[0]).haStadio(Stadio.RISERVE))
					squadre.get((s.split(REGEX))[0]).aggiungiCrediti(squadre.get((s.split(REGEX))[0]).getPremioStadio(Stadio.RISERVE), "vendita biglietti stadio riserve " + this.nome);
			}
			else
			{
				if(pos<=3)
				{
					if(Float.parseFloat(((s.split(REGEX))[3]))!=prec)
					{
						squadre.get((s.split(REGEX))[2]).aggiungiCrediti(this.premi.get(pos), pos + "o posto classifica giornata Riserve");
						posPrec = pos;
					}
					else
						squadre.get((s.split(REGEX))[2]).aggiungiCrediti(this.premi.get(posPrec), posPrec + "o posto classifica giornata Riserve");
						
					pos++;
					prec = Float.parseFloat(((s.split(REGEX))[3]));
				}
			}
		}
	}
}
