package premi.competizioni;

import premi.*;
import java.util.List;
import java.util.Map;

public class ScontriDiretti extends Competizione
{
	protected static final String SEPARATORE_PUNTEGGIO = "-";
	protected float creditiVittoria;
	protected float creditiPareggio;
	
	public ScontriDiretti()
	{
	}; 
	
	@Override
	public void carica(Map<String, Squadra> squadre, List<String> l)
	{
		for(String s: l)
		{
			if(Integer.parseInt((((s.split(REGEX))[4].split(SEPARATORE_PUNTEGGIO))[0]))>Integer.parseInt((((s.split(REGEX))[4].split(SEPARATORE_PUNTEGGIO))[1])))
				squadre.get((s.split(REGEX))[0]).aggiungiCrediti(this.creditiVittoria, "vittoria " + this.nome);
			if(Integer.parseInt((((s.split(REGEX))[4].split(SEPARATORE_PUNTEGGIO))[0]))<Integer.parseInt((((s.split(REGEX))[4].split(SEPARATORE_PUNTEGGIO))[1])))
				squadre.get((s.split(REGEX))[3]).aggiungiCrediti(this.creditiVittoria, "vittoria " + this.nome);
			if(Integer.parseInt((((s.split(REGEX))[4].split(SEPARATORE_PUNTEGGIO))[0]))==Integer.parseInt((((s.split(REGEX))[4].split(SEPARATORE_PUNTEGGIO))[1])))
			{
				squadre.get((s.split(REGEX))[0]).aggiungiCrediti(this.creditiPareggio, "pareggio " + this.nome);
				squadre.get((s.split(REGEX))[3]).aggiungiCrediti(this.creditiPareggio, "pareggio " + this.nome);
			}
			if(squadre.get((s.split(REGEX))[0]).haStadio(Stadio.PRIMASQUADRA))
				squadre.get((s.split(REGEX))[0]).aggiungiCrediti(squadre.get((s.split(REGEX))[0]).getPremioStadio(Stadio.PRIMASQUADRA), "vendita biglietti stadio " + this.nome);
		}
	}
}
