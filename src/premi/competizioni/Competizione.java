package premi.competizioni;

import java.util.List;
import java.util.Map;
import premi.Squadra;

public class Competizione 
{
	protected static final String REGEX = " *\t *";
	protected String nomeFile;
	protected String nome;
	
	public Competizione() 
	{
		;
	}

	public String getNome() 
	{
		return nomeFile;
	}
	
	public void carica(Map<String, Squadra> squadre, List<String> l)
	{
		;
	}
}
