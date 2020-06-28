package premi.competizioni.coppa;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import premi.Squadra;
import premi.Stadio;
import premi.competizioni.*;

public class CoppaRipescaggioUnoDue extends Competizione
{
	public CoppaRipescaggioUnoDue()
	{
		this.nomeFile = "CoppaRipescaggioUnoDue";
	}; 
	
	@Override
	public void carica(Map<String, Squadra> squadre, List<String> l)
	{
		List<Squadra> pres = new LinkedList<>(); 
		for(String s: l)
		{
			if(((s.split(REGEX))[0]).equals("RIP"))
			{
				pres.add(squadre.get((s.split(REGEX))[1]));
			}
			else
			{
				if(Integer.parseInt((((s.split(REGEX))[4].split("-"))[0]))>Integer.parseInt((((s.split(REGEX))[4].split("-"))[1])))
					squadre.get((s.split(REGEX))[0]).aggiungiCrediti(9f, "vittoria Coppa");
				if(Integer.parseInt((((s.split(REGEX))[4].split("-"))[0]))<Integer.parseInt((((s.split(REGEX))[4].split("-"))[1])))
					squadre.get((s.split(REGEX))[3]).aggiungiCrediti(9f, "vittoria Coppa");
				if(Integer.parseInt((((s.split(REGEX))[4].split("-"))[0]))==Integer.parseInt((((s.split(REGEX))[4].split("-"))[1])))
				{
					squadre.get((s.split(REGEX))[0]).aggiungiCrediti(4f, "pareggio Coppa");
					squadre.get((s.split(REGEX))[3]).aggiungiCrediti(4f, "pareggio Coppa");
				}
				if(squadre.get((s.split(REGEX))[0]).haStadio(Stadio.PRIMASQUADRA))
					squadre.get((s.split(REGEX))[0]).aggiungiCrediti(squadre.get((s.split(REGEX))[0]).getPremioStadio(Stadio.PRIMASQUADRA), "vendita biglietti stadio Coppa");
				pres.add(squadre.get((s.split(REGEX))[0])); pres.add(squadre.get((s.split(REGEX))[3]));
			}		
		}
		for(Squadra sq: squadre.values())
		{
			if(!pres.contains(sq))
				sq.aggiungiCrediti(18f, "ripescaggio Coppa non giocato");
		}
	}
}
