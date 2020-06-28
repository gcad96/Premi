package premi.competizioni;

import premi.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ScontriDirettiDiRipescaggio extends Competizione
{
	protected static final String SEPARATORE_PUNTEGGIO = "-";
	protected static final String DELIMITATORE = ": ";
	protected static final String SEPARATORE_SQUADRE = " ,";
	protected float creditiVittoria;
	protected float creditiPareggio;
	protected float creditiRipescaggioNonGiocato;
	
	public ScontriDirettiDiRipescaggio()
	{
	}; 
	
	@Override
	public void carica(Map<String, Squadra> squadre, List<String> l)
	{
		List<Squadra> elim = new LinkedList<>();
		for(String s: (l.remove(0).split(DELIMITATORE))[1].split(SEPARATORE_SQUADRE))
		{
			
			Squadra sq = squadre.get(s);
			if(sq!=null)
				elim.add(sq);
		}
		
		List<Squadra> pres = new LinkedList<>(); 
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
			pres.add(squadre.get((s.split(REGEX))[0])); pres.add(squadre.get((s.split(REGEX))[3]));		
		}
		for(Squadra sq: squadre.values())
		{
			if(!pres.contains(sq) && !elim.contains(sq))
				sq.aggiungiCrediti(this.creditiRipescaggioNonGiocato, "ripescaggio " + this.nome + " non giocato");
		}
	}
}