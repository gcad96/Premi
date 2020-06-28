package premi;

import java.util.Map;

public class Manuale 
{
	private static final String REGEX = ", ";

	public static void aggiungiPremi(Map<String, Squadra> m, String squadre, String descrizione, float premio)
	{
		for(String s: squadre.split(REGEX))
		{
			m.get(s).aggiungiCrediti(premio, descrizione);
		}
	}
	
	public static void aggiungiPremi(Map<String, Squadra> m, String descrizione, float premio)
	{
		for(Squadra s: m.values())
		{
			s.aggiungiCrediti(premio, descrizione);
		}
	}
	

}
